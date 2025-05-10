package expo.modules.manifests.core;

import expo.modules.updates.UpdatesConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: EmbeddedManifest.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0006\u0010\n\u001a\u00020\u000bJ\n\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0016J\u0006\u0010\u0010\u001a\u00020\u0006J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016¨\u0006\u0014"}, d2 = {"Lexpo/modules/manifests/core/EmbeddedManifest;", "Lexpo/modules/manifests/core/Manifest;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getAppKey", "", "getAssets", "Lorg/json/JSONArray;", "getBundleURL", "getCommitTimeLong", "", "getEASProjectID", "getExpoClientConfigRootObject", "getExpoGoConfigRootObject", "getExpoGoSDKVersion", "getID", "getScopeKey", "getSlug", "getStableLegacyID", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EmbeddedManifest extends Manifest {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmbeddedManifest(JSONObject json) {
        super(json);
        Intrinsics.checkNotNullParameter(json, "json");
    }

    public final String getID() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("id");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("id"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return (String) Integer.valueOf(json.getInt("id"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return (String) Long.valueOf(json.getLong("id"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return (String) Boolean.valueOf(json.getBoolean("id"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            Object jSONArray = json.getJSONArray("id");
            if (jSONArray != null) {
                return (String) jSONArray;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            Object jSONObject = json.getJSONObject("id");
            if (jSONObject != null) {
                return (String) jSONObject;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        Object obj = json.get("id");
        if (obj != null) {
            return (String) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public final long getCommitTimeLong() throws JSONException {
        Long l;
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Long.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            Object string = json.getString("commitTime");
            if (string == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
            l = (Long) string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            l = (Long) Double.valueOf(json.getDouble("commitTime"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            l = (Long) Integer.valueOf(json.getInt("commitTime"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            l = Long.valueOf(json.getLong("commitTime"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            l = (Long) Boolean.valueOf(json.getBoolean("commitTime"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            Object jSONArray = json.getJSONArray("commitTime");
            if (jSONArray == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
            l = (Long) jSONArray;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            Object jSONObject = json.getJSONObject("commitTime");
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
            l = (Long) jSONObject;
        } else {
            Object obj = json.get("commitTime");
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
            l = (Long) obj;
        }
        return l.longValue();
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getStableLegacyID() {
        String str;
        JSONObject json = getJson();
        if (json.has("originalFullName")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = json.getString("originalFullName");
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(json.getDouble("originalFullName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(json.getInt("originalFullName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(json.getLong("originalFullName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(json.getBoolean("originalFullName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                Object jSONArray = json.getJSONArray("originalFullName");
                if (jSONArray == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                Object jSONObject = json.getJSONObject("originalFullName");
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) jSONObject;
            } else {
                Object obj = json.get("originalFullName");
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) obj;
            }
        } else {
            str = null;
        }
        return str == null ? getLegacyID() : str;
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getScopeKey() {
        String str;
        JSONObject json = getJson();
        if (json.has(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = json.getString(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(json.getDouble(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(json.getInt(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(json.getLong(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(json.getBoolean(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                Object jSONArray = json.getJSONArray(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                if (jSONArray == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                Object jSONObject = json.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) jSONObject;
            } else {
                Object obj = json.get(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                str = (String) obj;
            }
        } else {
            str = null;
        }
        return str == null ? getStableLegacyID() : str;
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getEASProjectID() {
        JSONObject json = getJson();
        if (!json.has("projectId")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("projectId");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("projectId"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return (String) Integer.valueOf(json.getInt("projectId"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return (String) Long.valueOf(json.getLong("projectId"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return (String) Boolean.valueOf(json.getBoolean("projectId"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            Object jSONArray = json.getJSONArray("projectId");
            if (jSONArray != null) {
                return (String) jSONArray;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            Object jSONObject = json.getJSONObject("projectId");
            if (jSONObject != null) {
                return (String) jSONObject;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        Object obj = json.get("projectId");
        if (obj != null) {
            return (String) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    @Override // expo.modules.manifests.core.Manifest
    public JSONArray getAssets() {
        JSONObject json = getJson();
        if (!json.has("assets")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONArray.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            Object string = json.getString("assets");
            if (string != null) {
                return (JSONArray) string;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (JSONArray) Double.valueOf(json.getDouble("assets"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return (JSONArray) Integer.valueOf(json.getInt("assets"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return (JSONArray) Long.valueOf(json.getLong("assets"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return (JSONArray) Boolean.valueOf(json.getBoolean("assets"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = json.getJSONArray("assets");
            if (jSONArray != null) {
                return jSONArray;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            Object jSONObject = json.getJSONObject("assets");
            if (jSONObject != null) {
                return (JSONArray) jSONObject;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
        }
        Object obj = json.get("assets");
        if (obj != null) {
            return (JSONArray) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getBundleURL() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("bundleUrl");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("bundleUrl"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return (String) Integer.valueOf(json.getInt("bundleUrl"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return (String) Long.valueOf(json.getLong("bundleUrl"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return (String) Boolean.valueOf(json.getBoolean("bundleUrl"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            Object jSONArray = json.getJSONArray("bundleUrl");
            if (jSONArray != null) {
                return (String) jSONArray;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            Object jSONObject = json.getJSONObject("bundleUrl");
            if (jSONObject != null) {
                return (String) jSONObject;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        Object obj = json.get("bundleUrl");
        if (obj != null) {
            return (String) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getExpoGoSDKVersion() {
        JSONObject json = getJson();
        if (!json.has("sdkVersion")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("sdkVersion");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("sdkVersion"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return (String) Integer.valueOf(json.getInt("sdkVersion"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return (String) Long.valueOf(json.getLong("sdkVersion"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return (String) Boolean.valueOf(json.getBoolean("sdkVersion"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            Object jSONArray = json.getJSONArray("sdkVersion");
            if (jSONArray != null) {
                return (String) jSONArray;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            Object jSONObject = json.getJSONObject("sdkVersion");
            if (jSONObject != null) {
                return (String) jSONObject;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        Object obj = json.get("sdkVersion");
        if (obj != null) {
            return (String) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    @Override // expo.modules.manifests.core.Manifest
    public JSONObject getExpoGoConfigRootObject() {
        return getJson();
    }

    @Override // expo.modules.manifests.core.Manifest
    public JSONObject getExpoClientConfigRootObject() {
        return getJson();
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getSlug() {
        JSONObject json = getJson();
        if (!json.has("slug")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("slug");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("slug"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return (String) Integer.valueOf(json.getInt("slug"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return (String) Long.valueOf(json.getLong("slug"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return (String) Boolean.valueOf(json.getBoolean("slug"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            Object jSONArray = json.getJSONArray("slug");
            if (jSONArray != null) {
                return (String) jSONArray;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            Object jSONObject = json.getJSONObject("slug");
            if (jSONObject != null) {
                return (String) jSONObject;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        Object obj = json.get("slug");
        if (obj != null) {
            return (String) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getAppKey() {
        JSONObject json = getJson();
        if (!json.has("appKey")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("appKey");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("appKey"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return (String) Integer.valueOf(json.getInt("appKey"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return (String) Long.valueOf(json.getLong("appKey"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return (String) Boolean.valueOf(json.getBoolean("appKey"));
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            Object jSONArray = json.getJSONArray("appKey");
            if (jSONArray != null) {
                return (String) jSONArray;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            Object jSONObject = json.getJSONObject("appKey");
            if (jSONObject != null) {
                return (String) jSONObject;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        Object obj = json.get("appKey");
        if (obj != null) {
            return (String) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}
