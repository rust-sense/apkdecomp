package expo.modules.updates.db;

import android.net.Uri;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.dao.JSONDataDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BuildData.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0004J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\rJ\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lexpo/modules/updates/db/BuildData;", "", "()V", "staticBuildDataKey", "", "clearAllUpdatesFromDatabase", "", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "ensureBuildDataIsConsistent", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "getBuildDataFromConfig", "Lorg/json/JSONObject;", "getBuildDataFromDatabase", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "isBuildDataConsistent", "", "databaseBuildData", "setBuildDataInDatabase", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BuildData {
    public static final BuildData INSTANCE = new BuildData();
    private static String staticBuildDataKey = "staticBuildData";

    private BuildData() {
    }

    public final void ensureBuildDataIsConsistent(UpdatesConfiguration updatesConfiguration, UpdatesDatabase database) {
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(database, "database");
        JSONObject buildDataFromDatabase = getBuildDataFromDatabase(database, updatesConfiguration.getScopeKey());
        if (buildDataFromDatabase == null) {
            setBuildDataInDatabase(database, updatesConfiguration);
        } else {
            if (isBuildDataConsistent(updatesConfiguration, buildDataFromDatabase)) {
                return;
            }
            clearAllUpdatesFromDatabase(database);
            setBuildDataInDatabase(database, updatesConfiguration);
        }
    }

    public final void clearAllUpdatesFromDatabase(UpdatesDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        database.updateDao().deleteUpdates(database.updateDao().loadAllUpdates());
    }

    public final boolean isBuildDataConsistent(UpdatesConfiguration updatesConfiguration, JSONObject databaseBuildData) {
        String str;
        JSONObject jSONObject;
        String str2;
        JSONObject jSONObject2;
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(databaseBuildData, "databaseBuildData");
        JSONObject buildDataFromConfig = getBuildDataFromConfig(updatesConfiguration);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Boolean.valueOf(Intrinsics.areEqual(Uri.parse(databaseBuildData.get(UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY).toString()), buildDataFromConfig.get(UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY))));
        Iterator<String> keys = buildDataFromConfig.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY).keys();
        Intrinsics.checkNotNullExpressionValue(keys, "keys(...)");
        while (true) {
            Object obj = null;
            if (keys.hasNext()) {
                String next = keys.next();
                JSONObject jSONObject3 = databaseBuildData.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY);
                Intrinsics.checkNotNullExpressionValue(jSONObject3, "getJSONObject(...)");
                Intrinsics.checkNotNull(next);
                if (jSONObject3.has(next)) {
                    KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                        str2 = jSONObject3.getString(next);
                        if (str2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        str2 = (String) Double.valueOf(jSONObject3.getDouble(next));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        str2 = (String) Integer.valueOf(jSONObject3.getInt(next));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        str2 = (String) Long.valueOf(jSONObject3.getLong(next));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        str2 = (String) Boolean.valueOf(jSONObject3.getBoolean(next));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        Object jSONArray = jSONObject3.getJSONArray(next);
                        if (jSONArray == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str2 = (String) jSONArray;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        Object jSONObject4 = jSONObject3.getJSONObject(next);
                        if (jSONObject4 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str2 = (String) jSONObject4;
                    } else {
                        Object obj2 = jSONObject3.get(next);
                        if (obj2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str2 = (String) obj2;
                    }
                } else {
                    str2 = null;
                }
                JSONObject jSONObject5 = buildDataFromConfig.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY);
                Intrinsics.checkNotNullExpressionValue(jSONObject5, "getJSONObject(...)");
                if (jSONObject5.has(next)) {
                    KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                        String string = jSONObject5.getString(next);
                        if (string == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                        }
                        jSONObject2 = string;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        jSONObject2 = Double.valueOf(jSONObject5.getDouble(next));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        jSONObject2 = Integer.valueOf(jSONObject5.getInt(next));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        jSONObject2 = Long.valueOf(jSONObject5.getLong(next));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        jSONObject2 = Boolean.valueOf(jSONObject5.getBoolean(next));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        JSONArray jSONArray2 = jSONObject5.getJSONArray(next);
                        if (jSONArray2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                        }
                        jSONObject2 = jSONArray2;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        JSONObject jSONObject6 = jSONObject5.getJSONObject(next);
                        if (jSONObject6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                        }
                        jSONObject2 = jSONObject6;
                    } else {
                        jSONObject2 = jSONObject5.get(next);
                        if (jSONObject2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                        }
                    }
                    obj = jSONObject2;
                }
                arrayList.add(Boolean.valueOf(Intrinsics.areEqual(str2, obj)));
            } else {
                Iterator<String> keys2 = databaseBuildData.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY).keys();
                Intrinsics.checkNotNullExpressionValue(keys2, "keys(...)");
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    JSONObject jSONObject7 = databaseBuildData.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY);
                    Intrinsics.checkNotNullExpressionValue(jSONObject7, "getJSONObject(...)");
                    Intrinsics.checkNotNull(next2);
                    if (jSONObject7.has(next2)) {
                        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(String.class);
                        if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(String.class))) {
                            str = jSONObject7.getString(next2);
                            if (str == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                        } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                            str = (String) Double.valueOf(jSONObject7.getDouble(next2));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                            str = (String) Integer.valueOf(jSONObject7.getInt(next2));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                            str = (String) Long.valueOf(jSONObject7.getLong(next2));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                            str = (String) Boolean.valueOf(jSONObject7.getBoolean(next2));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                            Object jSONArray3 = jSONObject7.getJSONArray(next2);
                            if (jSONArray3 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str = (String) jSONArray3;
                        } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                            Object jSONObject8 = jSONObject7.getJSONObject(next2);
                            if (jSONObject8 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str = (String) jSONObject8;
                        } else {
                            Object obj3 = jSONObject7.get(next2);
                            if (obj3 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str = (String) obj3;
                        }
                    } else {
                        str = null;
                    }
                    JSONObject jSONObject9 = buildDataFromConfig.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY);
                    Intrinsics.checkNotNullExpressionValue(jSONObject9, "getJSONObject(...)");
                    if (jSONObject9.has(next2)) {
                        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
                        if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(String.class))) {
                            String string2 = jSONObject9.getString(next2);
                            if (string2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                            }
                            jSONObject = string2;
                        } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                            jSONObject = Double.valueOf(jSONObject9.getDouble(next2));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                            jSONObject = Integer.valueOf(jSONObject9.getInt(next2));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                            jSONObject = Long.valueOf(jSONObject9.getLong(next2));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                            jSONObject = Boolean.valueOf(jSONObject9.getBoolean(next2));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                            JSONArray jSONArray4 = jSONObject9.getJSONArray(next2);
                            if (jSONArray4 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                            }
                            jSONObject = jSONArray4;
                        } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                            JSONObject jSONObject10 = jSONObject9.getJSONObject(next2);
                            if (jSONObject10 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                            }
                            jSONObject = jSONObject10;
                        } else {
                            jSONObject = jSONObject9.get(next2);
                            if (jSONObject == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                            }
                        }
                    } else {
                        jSONObject = null;
                    }
                    arrayList.add(Boolean.valueOf(Intrinsics.areEqual(str, jSONObject)));
                }
                ArrayList arrayList2 = arrayList;
                if ((arrayList2 instanceof Collection) && arrayList2.isEmpty()) {
                    return true;
                }
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    if (!((Boolean) it.next()).booleanValue()) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    public final void setBuildDataInDatabase(UpdatesDatabase database, UpdatesConfiguration updatesConfiguration) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        JSONObject buildDataFromConfig = getBuildDataFromConfig(updatesConfiguration);
        JSONDataDao jsonDataDao = database.jsonDataDao();
        if (jsonDataDao != null) {
            String str = staticBuildDataKey;
            String jSONObject = buildDataFromConfig.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            jsonDataDao.setJSONStringForKey(str, jSONObject, updatesConfiguration.getScopeKey());
        }
    }

    public final JSONObject getBuildDataFromDatabase(UpdatesDatabase database, String scopeKey) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        JSONDataDao jsonDataDao = database.jsonDataDao();
        String loadJSONStringForKey = jsonDataDao != null ? jsonDataDao.loadJSONStringForKey(staticBuildDataKey, scopeKey) : null;
        if (loadJSONStringForKey == null) {
            return null;
        }
        return new JSONObject(loadJSONStringForKey);
    }

    private final JSONObject getBuildDataFromConfig(UpdatesConfiguration updatesConfiguration) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : updatesConfiguration.getRequestHeaders().entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY, updatesConfiguration.getUpdateUrl());
        jSONObject2.put(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY, jSONObject);
        return jSONObject2;
    }
}
