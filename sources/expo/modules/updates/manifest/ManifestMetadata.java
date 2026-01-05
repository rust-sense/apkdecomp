package expo.modules.updates.manifest;

import android.util.Log;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.dao.JSONDataDao;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ManifestMetadata.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\"\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ(\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n*\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lexpo/modules/updates/manifest/ManifestMetadata;", "", "()V", "EXTRA_PARAMS_KEY", "", "MANIFEST_FILTERS_KEY", "MANIFEST_SERVER_DEFINED_HEADERS_KEY", "TAG", "kotlin.jvm.PlatformType", "getExtraParams", "", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "getJSONObject", "Lorg/json/JSONObject;", "key", "getManifestFilters", "getServerDefinedHeaders", "saveMetadata", "", "responseHeaderData", "Lexpo/modules/updates/manifest/ResponseHeaderData;", "setExtraParam", "value", "asStringStringMap", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ManifestMetadata {
    private static final String EXTRA_PARAMS_KEY = "extraParams";
    private static final String MANIFEST_FILTERS_KEY = "manifestFilters";
    private static final String MANIFEST_SERVER_DEFINED_HEADERS_KEY = "serverDefinedHeaders";
    public static final ManifestMetadata INSTANCE = new ManifestMetadata();
    private static final String TAG = "ManifestMetadata";

    private ManifestMetadata() {
    }

    private final JSONObject getJSONObject(String key, UpdatesDatabase database, UpdatesConfiguration configuration) {
        try {
            JSONDataDao jsonDataDao = database.jsonDataDao();
            Intrinsics.checkNotNull(jsonDataDao);
            String loadJSONStringForKey = jsonDataDao.loadJSONStringForKey(key, configuration.getScopeKey());
            if (loadJSONStringForKey != null) {
                return new JSONObject(loadJSONStringForKey);
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error retrieving " + key + " from database", e);
            return null;
        }
    }

    @JvmStatic
    public static final JSONObject getServerDefinedHeaders(UpdatesDatabase database, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return INSTANCE.getJSONObject(MANIFEST_SERVER_DEFINED_HEADERS_KEY, database, configuration);
    }

    public final JSONObject getManifestFilters(UpdatesDatabase database, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return getJSONObject(MANIFEST_FILTERS_KEY, database, configuration);
    }

    public final Map<String, String> getExtraParams(UpdatesDatabase database, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        JSONObject jSONObject = getJSONObject(EXTRA_PARAMS_KEY, database, configuration);
        if (jSONObject != null) {
            return asStringStringMap(jSONObject);
        }
        return null;
    }

    public final void setExtraParam(UpdatesDatabase database, UpdatesConfiguration configuration, final String key, final String value) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(key, "key");
        JSONDataDao jsonDataDao = database.jsonDataDao();
        Intrinsics.checkNotNull(jsonDataDao);
        jsonDataDao.updateJSONStringForKey(EXTRA_PARAMS_KEY, configuration.getScopeKey(), new Function1<String, String>() { // from class: expo.modules.updates.manifest.ManifestMetadata$setExtraParam$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
            
                r5 = expo.modules.updates.manifest.ManifestMetadata.INSTANCE.asStringStringMap(r0);
             */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.String invoke(java.lang.String r5) {
                /*
                    r4 = this;
                    if (r5 == 0) goto L8
                    org.json.JSONObject r0 = new org.json.JSONObject
                    r0.<init>(r5)
                    goto L9
                L8:
                    r0 = 0
                L9:
                    if (r0 == 0) goto L19
                    expo.modules.updates.manifest.ManifestMetadata r5 = expo.modules.updates.manifest.ManifestMetadata.INSTANCE
                    java.util.Map r5 = expo.modules.updates.manifest.ManifestMetadata.access$asStringStringMap(r5, r0)
                    if (r5 == 0) goto L19
                    java.util.Map r5 = kotlin.collections.MapsKt.toMutableMap(r5)
                    if (r5 != 0) goto L20
                L19:
                    java.util.LinkedHashMap r5 = new java.util.LinkedHashMap
                    r5.<init>()
                    java.util.Map r5 = (java.util.Map) r5
                L20:
                    java.lang.String r0 = r1
                    java.lang.String r1 = r2
                    if (r0 == 0) goto L2a
                    r5.put(r1, r0)
                    goto L2d
                L2a:
                    r5.remove(r1)
                L2d:
                    java.util.Map r5 = kotlin.collections.MapsKt.toMap(r5)
                    java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
                    int r1 = r5.size()
                    int r1 = kotlin.collections.MapsKt.mapCapacity(r1)
                    r0.<init>(r1)
                    java.util.Map r0 = (java.util.Map) r0
                    java.util.Set r1 = r5.entrySet()
                    java.lang.Iterable r1 = (java.lang.Iterable) r1
                    java.util.Iterator r1 = r1.iterator()
                L4a:
                    boolean r2 = r1.hasNext()
                    if (r2 == 0) goto L68
                    java.lang.Object r2 = r1.next()
                    java.util.Map$Entry r2 = (java.util.Map.Entry) r2
                    java.lang.Object r3 = r2.getKey()
                    java.lang.Object r2 = r2.getValue()
                    java.lang.String r2 = (java.lang.String) r2
                    expo.modules.structuredheaders.StringItem r2 = expo.modules.structuredheaders.StringItem.valueOf(r2)
                    r0.put(r3, r2)
                    goto L4a
                L68:
                    expo.modules.structuredheaders.Dictionary r0 = expo.modules.structuredheaders.Dictionary.valueOf(r0)
                    r0.serialize()
                    org.json.JSONObject r0 = new org.json.JSONObject
                    r0.<init>(r5)
                    java.lang.String r5 = r0.toString()
                    java.lang.String r0 = "toString(...)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: expo.modules.updates.manifest.ManifestMetadata$setExtraParam$1.invoke(java.lang.String):java.lang.String");
            }
        });
    }

    public final void saveMetadata(ResponseHeaderData responseHeaderData, UpdatesDatabase database, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(responseHeaderData, "responseHeaderData");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (responseHeaderData.getServerDefinedHeaders() != null) {
            linkedHashMap.put(MANIFEST_SERVER_DEFINED_HEADERS_KEY, String.valueOf(responseHeaderData.getServerDefinedHeaders()));
        }
        if (responseHeaderData.getManifestFilters() != null) {
            linkedHashMap.put(MANIFEST_FILTERS_KEY, String.valueOf(responseHeaderData.getManifestFilters()));
        }
        if (!linkedHashMap.isEmpty()) {
            JSONDataDao jsonDataDao = database.jsonDataDao();
            Intrinsics.checkNotNull(jsonDataDao);
            jsonDataDao.setMultipleFields(linkedHashMap, configuration.getScopeKey());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, String> asStringStringMap(JSONObject jSONObject) {
        String str;
        Map createMapBuilder = MapsKt.createMapBuilder();
        Iterator<String> keys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "keys(...)");
        for (String str2 : SequencesKt.asSequence(keys)) {
            Intrinsics.checkNotNull(str2);
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = jSONObject.getString(str2);
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(jSONObject.getDouble(str2));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(jSONObject.getInt(str2));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(jSONObject.getLong(str2));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(jSONObject.getBoolean(str2));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                Object jSONArray = jSONObject.getJSONArray(str2);
                if (jSONArray == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                Object jSONObject2 = jSONObject.getJSONObject(str2);
                if (jSONObject2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) jSONObject2;
            } else {
                Object obj = jSONObject.get(str2);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) obj;
            }
            createMapBuilder.put(str2, str);
        }
        return MapsKt.build(createMapBuilder);
    }
}
