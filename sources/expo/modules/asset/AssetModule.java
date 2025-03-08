package expo.modules.asset;

import android.content.Context;
import androidx.tracing.Trace;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.SuspendFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.text.Charsets;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: AssetModule.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lexpo/modules/asset/AssetModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "downloadAsset", "Landroid/net/Uri;", "appContext", "Lexpo/modules/kotlin/AppContext;", "uri", "Ljava/net/URI;", "localUrl", "Ljava/io/File;", "(Lexpo/modules/kotlin/AppContext;Ljava/net/URI;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMD5HashOfFileContent", "", "file", "getMD5HashOfFilePath", "expo-asset_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AssetModule extends Module {
    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.AppContextLost();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getMD5HashOfFilePath(URI uri) {
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        byte[] bytes = uri2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] digest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(digest, "digest(...)");
        return ArraysKt.joinToString$default(digest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: expo.modules.asset.AssetModule$getMD5HashOfFilePath$1
            public final CharSequence invoke(byte b) {
                String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                return format;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getMD5HashOfFileContent(File file) {
        try {
            DigestInputStream fileInputStream = new FileInputStream(file);
            try {
                fileInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance(MessageDigestAlgorithms.MD5));
                try {
                    byte[] digest = fileInputStream.getMessageDigest().digest();
                    Intrinsics.checkNotNullExpressionValue(digest, "digest(...)");
                    String joinToString$default = ArraysKt.joinToString$default(digest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: expo.modules.asset.AssetModule$getMD5HashOfFileContent$1$1$1
                        public final CharSequence invoke(byte b) {
                            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                            return format;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                            return invoke(b.byteValue());
                        }
                    }, 30, (Object) null);
                    CloseableKt.closeFinally(fileInputStream, null);
                    CloseableKt.closeFinally(fileInputStream, null);
                    return joinToString$default;
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object downloadAsset(expo.modules.kotlin.AppContext r6, java.net.URI r7, java.io.File r8, kotlin.coroutines.Continuation<? super android.net.Uri> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof expo.modules.asset.AssetModule$downloadAsset$1
            if (r0 == 0) goto L14
            r0 = r9
            expo.modules.asset.AssetModule$downloadAsset$1 r0 = (expo.modules.asset.AssetModule$downloadAsset$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            expo.modules.asset.AssetModule$downloadAsset$1 r0 = new expo.modules.asset.AssetModule$downloadAsset$1
            r0.<init>(r5, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r9)
            goto L7a
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r9)
            java.io.File r9 = r8.getParentFile()
            if (r9 == 0) goto L42
            boolean r9 = r9.exists()
            if (r9 != r3) goto L42
            goto L45
        L42:
            r8.mkdirs()
        L45:
            expo.modules.interfaces.filesystem.FilePermissionModuleInterface r9 = r6.getFilePermission()
            if (r9 == 0) goto L80
            android.content.Context r2 = r6.getReactContext()
            java.lang.String r4 = r8.getParent()
            java.util.EnumSet r9 = r9.getPathPermissions(r2, r4)
            if (r9 == 0) goto L80
            expo.modules.interfaces.filesystem.Permission r2 = expo.modules.interfaces.filesystem.Permission.WRITE
            boolean r9 = r9.contains(r2)
            if (r9 != r3) goto L80
            kotlinx.coroutines.CoroutineScope r6 = r6.getBackgroundCoroutineScope()
            kotlin.coroutines.CoroutineContext r6 = r6.getCoroutineContext()
            expo.modules.asset.AssetModule$downloadAsset$2 r9 = new expo.modules.asset.AssetModule$downloadAsset$2
            r2 = 0
            r9.<init>(r7, r5, r8, r2)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r6, r9, r0)
            if (r9 != r1) goto L7a
            return r1
        L7a:
            java.lang.String r6 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)
            return r9
        L80:
            expo.modules.asset.UnableToDownloadAssetException r6 = new expo.modules.asset.UnableToDownloadAssetException
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.asset.AssetModule.downloadAsset(expo.modules.kotlin.AppContext, java.net.URI, java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AssetModule assetModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (assetModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(assetModule);
            moduleDefinitionBuilder.Name("ExpoAsset");
            AsyncFunctionBuilder AsyncFunction = moduleDefinitionBuilder.AsyncFunction("downloadAsync");
            AsyncFunction.setAsyncFunctionComponent(new SuspendFunctionComponent(AsyncFunction.getName(), new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(URI.class), false, new Function0<KType>() { // from class: expo.modules.asset.AssetModule$definition$lambda$3$$inlined$Coroutine$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(URI.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.asset.AssetModule$definition$lambda$3$$inlined$Coroutine$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.asset.AssetModule$definition$lambda$3$$inlined$Coroutine$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))}, new AssetModule$definition$lambda$3$$inlined$Coroutine$4(null, this)));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
