package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
public final class DynamiteModule {
    private static Boolean zzif = null;
    private static zzi zzig = null;
    private static zzk zzih = null;
    private static String zzii = null;
    private static int zzij = -1;
    private final Context zzin;
    private static final ThreadLocal<zza> zzik = new ThreadLocal<>();
    private static final VersionPolicy.zza zzil = new com.google.android.gms.dynamite.zza();
    public static final VersionPolicy PREFER_REMOTE = new com.google.android.gms.dynamite.zzb();
    public static final VersionPolicy PREFER_LOCAL = new zzc();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    private static final VersionPolicy zzim = new zzg();

    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    public interface VersionPolicy {

        public interface zza {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        public static class zzb {
            public int zzir = 0;
            public int zzis = 0;
            public int zzit = 0;
        }

        zzb zza(Context context, String str, zza zzaVar) throws LoadingException;
    }

    private static class zza {
        public Cursor zzio;

        private zza() {
        }

        /* synthetic */ zza(com.google.android.gms.dynamite.zza zzaVar) {
            this();
        }
    }

    private static class zzb implements VersionPolicy.zza {
        private final int zzip;
        private final int zziq = 0;

        public zzb(int i, int i2) {
            this.zzip = i;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza
        public final int getLocalVersion(Context context, String str) {
            return this.zzip;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza
        public final int zza(Context context, String str, boolean z) {
            return 0;
        }
    }

    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        ThreadLocal<zza> threadLocal = zzik;
        zza zzaVar = threadLocal.get();
        com.google.android.gms.dynamite.zza zzaVar2 = null;
        zza zzaVar3 = new zza(zzaVar2);
        threadLocal.set(zzaVar3);
        try {
            VersionPolicy.zzb zza2 = versionPolicy.zza(context, str, zzil);
            int i = zza2.zzir;
            int i2 = zza2.zzis;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            Log.i("DynamiteModule", sb.toString());
            if (zza2.zzit == 0 || ((zza2.zzit == -1 && zza2.zzir == 0) || (zza2.zzit == 1 && zza2.zzis == 0))) {
                int i3 = zza2.zzir;
                int i4 = zza2.zzis;
                StringBuilder sb2 = new StringBuilder(91);
                sb2.append("No acceptable module found. Local version is ");
                sb2.append(i3);
                sb2.append(" and remote version is ");
                sb2.append(i4);
                sb2.append(".");
                throw new LoadingException(sb2.toString(), zzaVar2);
            }
            if (zza2.zzit == -1) {
                DynamiteModule zze = zze(context, str);
                if (zzaVar3.zzio != null) {
                    zzaVar3.zzio.close();
                }
                threadLocal.set(zzaVar);
                return zze;
            }
            if (zza2.zzit != 1) {
                int i5 = zza2.zzit;
                StringBuilder sb3 = new StringBuilder(47);
                sb3.append("VersionPolicy returned invalid code:");
                sb3.append(i5);
                throw new LoadingException(sb3.toString(), zzaVar2);
            }
            try {
                DynamiteModule zza3 = zza(context, str, zza2.zzis);
                if (zzaVar3.zzio != null) {
                    zzaVar3.zzio.close();
                }
                threadLocal.set(zzaVar);
                return zza3;
            } catch (LoadingException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
                if (zza2.zzir == 0 || versionPolicy.zza(context, str, new zzb(zza2.zzir, 0)).zzit != -1) {
                    throw new LoadingException("Remote load failed. No local fallback found.", e, zzaVar2);
                }
                DynamiteModule zze2 = zze(context, str);
                if (zzaVar3.zzio != null) {
                    zzaVar3.zzio.close();
                }
                zzik.set(zzaVar);
                return zze2;
            }
        } catch (Throwable th) {
            if (zzaVar3.zzio != null) {
                zzaVar3.zzio.close();
            }
            zzik.set(zzaVar);
            throw th;
        }
    }

    public final Context getModuleContext() {
        return this.zzin;
    }

    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, com.google.android.gms.dynamite.zza zzaVar) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, com.google.android.gms.dynamite.zza zzaVar) {
            this(str, th);
        }
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (!declaredField.get(null).equals(str)) {
                String valueOf = String.valueOf(declaredField.get(null));
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
                sb2.append("Module descriptor id '");
                sb2.append(valueOf);
                sb2.append("' didn't match expected id '");
                sb2.append(str);
                sb2.append("'");
                Log.e("DynamiteModule", sb2.toString());
                return 0;
            }
            return declaredField2.getInt(null);
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String valueOf2 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zza(Context context, String str, boolean z) {
        Class<?> loadClass;
        Field declaredField;
        Boolean bool;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool2 = zzif;
                if (bool2 == null) {
                    try {
                        loadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                        declaredField = loadClass.getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                        String valueOf = String.valueOf(e);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(valueOf);
                        Log.w("DynamiteModule", sb.toString());
                        bool2 = Boolean.FALSE;
                    }
                    synchronized (loadClass) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader != null) {
                            if (classLoader == ClassLoader.getSystemClassLoader()) {
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    zza(classLoader);
                                } catch (LoadingException unused) {
                                }
                                bool = Boolean.TRUE;
                            }
                        } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        } else {
                            try {
                                int zzc = zzc(context, str, z);
                                String str2 = zzii;
                                if (str2 != null && !str2.isEmpty()) {
                                    zzh zzhVar = new zzh(zzii, ClassLoader.getSystemClassLoader());
                                    zza(zzhVar);
                                    declaredField.set(null, zzhVar);
                                    zzif = Boolean.TRUE;
                                    return zzc;
                                }
                                return zzc;
                            } catch (LoadingException unused2) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            }
                        }
                        bool2 = bool;
                        zzif = bool2;
                    }
                }
                if (bool2.booleanValue()) {
                    try {
                        return zzc(context, str, z);
                    } catch (LoadingException e2) {
                        String valueOf2 = String.valueOf(e2.getMessage());
                        Log.w("DynamiteModule", valueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf2) : new String("Failed to retrieve remote module version: "));
                        return 0;
                    }
                }
                return zzb(context, str, z);
            }
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw th;
        }
    }

    private static int zzb(Context context, String str, boolean z) {
        zzi zzj = zzj(context);
        if (zzj == null) {
            return 0;
        }
        try {
            if (zzj.zzak() >= 2) {
                return zzj.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzj.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b0  */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.dynamite.zza] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int zzc(android.content.Context r8, java.lang.String r9, boolean r10) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r10 == 0) goto La
            java.lang.String r8 = "api_force_staging"
            goto Lc
        La:
            java.lang.String r8 = "api"
        Lc:
            int r10 = r8.length()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            int r10 = r10 + 42
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            int r2 = r2.length()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            int r10 = r10 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r2.<init>(r10)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r10 = "content://com.google.android.gms.chimera/"
            r2.append(r10)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r2.append(r8)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r8 = "/"
            r2.append(r8)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r2.append(r9)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            android.net.Uri r2 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r8 == 0) goto L83
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            if (r9 == 0) goto L83
            r9 = 0
            int r9 = r8.getInt(r9)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            if (r9 <= 0) goto L7c
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r10 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r10)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            r1 = 2
            java.lang.String r1 = r8.getString(r1)     // Catch: java.lang.Throwable -> L79
            com.google.android.gms.dynamite.DynamiteModule.zzii = r1     // Catch: java.lang.Throwable -> L79
            java.lang.String r1 = "loaderVersion"
            int r1 = r8.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L79
            if (r1 < 0) goto L67
            int r1 = r8.getInt(r1)     // Catch: java.lang.Throwable -> L79
            com.google.android.gms.dynamite.DynamiteModule.zzij = r1     // Catch: java.lang.Throwable -> L79
        L67:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L79
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r10 = com.google.android.gms.dynamite.DynamiteModule.zzik     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            java.lang.Object r10 = r10.get()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            com.google.android.gms.dynamite.DynamiteModule$zza r10 = (com.google.android.gms.dynamite.DynamiteModule.zza) r10     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            if (r10 == 0) goto L7c
            android.database.Cursor r1 = r10.zzio     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            if (r1 != 0) goto L7c
            r10.zzio = r8     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            goto L7d
        L79:
            r9 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L79
            throw r9     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
        L7c:
            r0 = r8
        L7d:
            if (r0 == 0) goto L82
            r0.close()
        L82:
            return r9
        L83:
            java.lang.String r9 = "DynamiteModule"
            java.lang.String r10 = "Failed to retrieve remote module version."
            android.util.Log.w(r9, r10)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r9 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            java.lang.String r10 = "Failed to connect to dynamite module ContentResolver."
            r9.<init>(r10, r0)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            throw r9     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
        L92:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto Lae
        L96:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L9f
        L9b:
            r8 = move-exception
            goto Lae
        L9d:
            r8 = move-exception
            r9 = r0
        L9f:
            boolean r10 = r8 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch: java.lang.Throwable -> Lac
            if (r10 == 0) goto La4
            throw r8     // Catch: java.lang.Throwable -> Lac
        La4:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Throwable -> Lac
            java.lang.String r1 = "V2 version check failed"
            r10.<init>(r1, r8, r0)     // Catch: java.lang.Throwable -> Lac
            throw r10     // Catch: java.lang.Throwable -> Lac
        Lac:
            r8 = move-exception
            r0 = r9
        Lae:
            if (r0 == 0) goto Lb3
            r0.close()
        Lb3:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zze(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        Boolean bool;
        IObjectWrapper zza2;
        com.google.android.gms.dynamite.zza zzaVar = null;
        try {
            synchronized (DynamiteModule.class) {
                bool = zzif;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.", zzaVar);
            }
            if (bool.booleanValue()) {
                return zzb(context, str, i);
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
            sb.append("Selected remote version of ");
            sb.append(str);
            sb.append(", version >= ");
            sb.append(i);
            Log.i("DynamiteModule", sb.toString());
            zzi zzj = zzj(context);
            if (zzj == null) {
                throw new LoadingException("Failed to create IDynamiteLoader.", zzaVar);
            }
            if (zzj.zzak() >= 2) {
                zza2 = zzj.zzb(ObjectWrapper.wrap(context), str, i);
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                zza2 = zzj.zza(ObjectWrapper.wrap(context), str, i);
            }
            if (ObjectWrapper.unwrap(zza2) == null) {
                throw new LoadingException("Failed to load remote module.", zzaVar);
            }
            return new DynamiteModule((Context) ObjectWrapper.unwrap(zza2));
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, zzaVar);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw new LoadingException("Failed to load remote module.", th, zzaVar);
        }
    }

    private static zzi zzj(Context context) {
        zzi zzjVar;
        synchronized (DynamiteModule.class) {
            zzi zziVar = zzig;
            if (zziVar != null) {
                return zziVar;
            }
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzjVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    if (queryLocalInterface instanceof zzi) {
                        zzjVar = (zzi) queryLocalInterface;
                    } else {
                        zzjVar = new zzj(iBinder);
                    }
                }
                if (zzjVar != null) {
                    zzig = zzjVar;
                    return zzjVar;
                }
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException, RemoteException {
        zzk zzkVar;
        IObjectWrapper zza2;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            zzkVar = zzih;
        }
        com.google.android.gms.dynamite.zza zzaVar = null;
        if (zzkVar == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", zzaVar);
        }
        zza zzaVar2 = zzik.get();
        if (zzaVar2 == null || zzaVar2.zzio == null) {
            throw new LoadingException("No result cursor", zzaVar);
        }
        Context applicationContext = context.getApplicationContext();
        Cursor cursor = zzaVar2.zzio;
        ObjectWrapper.wrap(null);
        if (zzaj().booleanValue()) {
            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
            zza2 = zzkVar.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        } else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            zza2 = zzkVar.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        }
        Context context2 = (Context) ObjectWrapper.unwrap(zza2);
        if (context2 == null) {
            throw new LoadingException("Failed to get module context", zzaVar);
        }
        return new DynamiteModule(context2);
    }

    private static Boolean zzaj() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zzij >= 2);
        }
        return valueOf;
    }

    private static void zza(ClassLoader classLoader) throws LoadingException {
        zzk zzlVar;
        com.google.android.gms.dynamite.zza zzaVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzlVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzk) {
                    zzlVar = (zzk) queryLocalInterface;
                } else {
                    zzlVar = new zzl(iBinder);
                }
            }
            zzih = zzlVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzaVar);
        }
    }

    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzin.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzin = (Context) Preconditions.checkNotNull(context);
    }
}
