package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonObjectReader;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectWriter;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class App implements JsonUnknown, JsonSerializable {
    public static final String TYPE = "app";
    private String appBuild;
    private String appIdentifier;
    private String appName;
    private Date appStartTime;
    private String appVersion;
    private String buildType;
    private String deviceAppHash;
    private Boolean inForeground;
    private Map<String, String> permissions;
    private Map<String, Object> unknown;
    private List<String> viewNames;

    public static final class JsonKeys {
        public static final String APP_BUILD = "app_build";
        public static final String APP_IDENTIFIER = "app_identifier";
        public static final String APP_NAME = "app_name";
        public static final String APP_PERMISSIONS = "permissions";
        public static final String APP_START_TIME = "app_start_time";
        public static final String APP_VERSION = "app_version";
        public static final String BUILD_TYPE = "build_type";
        public static final String DEVICE_APP_HASH = "device_app_hash";
        public static final String IN_FOREGROUND = "in_foreground";
        public static final String VIEW_NAMES = "view_names";
    }

    public String getAppBuild() {
        return this.appBuild;
    }

    public String getAppIdentifier() {
        return this.appIdentifier;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getBuildType() {
        return this.buildType;
    }

    public String getDeviceAppHash() {
        return this.deviceAppHash;
    }

    public Boolean getInForeground() {
        return this.inForeground;
    }

    public Map<String, String> getPermissions() {
        return this.permissions;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public List<String> getViewNames() {
        return this.viewNames;
    }

    public void setAppBuild(String str) {
        this.appBuild = str;
    }

    public void setAppIdentifier(String str) {
        this.appIdentifier = str;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setAppStartTime(Date date) {
        this.appStartTime = date;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setBuildType(String str) {
        this.buildType = str;
    }

    public void setDeviceAppHash(String str) {
        this.deviceAppHash = str;
    }

    public void setInForeground(Boolean bool) {
        this.inForeground = bool;
    }

    public void setPermissions(Map<String, String> map) {
        this.permissions = map;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public void setViewNames(List<String> list) {
        this.viewNames = list;
    }

    public App() {
    }

    App(App app) {
        this.appBuild = app.appBuild;
        this.appIdentifier = app.appIdentifier;
        this.appName = app.appName;
        this.appStartTime = app.appStartTime;
        this.appVersion = app.appVersion;
        this.buildType = app.buildType;
        this.deviceAppHash = app.deviceAppHash;
        this.permissions = CollectionUtils.newConcurrentHashMap(app.permissions);
        this.inForeground = app.inForeground;
        this.viewNames = CollectionUtils.newArrayList(app.viewNames);
        this.unknown = CollectionUtils.newConcurrentHashMap(app.unknown);
    }

    public Date getAppStartTime() {
        Date date = this.appStartTime;
        if (date != null) {
            return (Date) date.clone();
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        App app = (App) obj;
        return Objects.equals(this.appIdentifier, app.appIdentifier) && Objects.equals(this.appStartTime, app.appStartTime) && Objects.equals(this.deviceAppHash, app.deviceAppHash) && Objects.equals(this.buildType, app.buildType) && Objects.equals(this.appName, app.appName) && Objects.equals(this.appVersion, app.appVersion) && Objects.equals(this.appBuild, app.appBuild) && Objects.equals(this.permissions, app.permissions) && Objects.equals(this.inForeground, app.inForeground) && Objects.equals(this.viewNames, app.viewNames);
    }

    public int hashCode() {
        return Objects.hash(this.appIdentifier, this.appStartTime, this.deviceAppHash, this.buildType, this.appName, this.appVersion, this.appBuild, this.permissions, this.inForeground, this.viewNames);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.appIdentifier != null) {
            objectWriter.name(JsonKeys.APP_IDENTIFIER).value(this.appIdentifier);
        }
        if (this.appStartTime != null) {
            objectWriter.name(JsonKeys.APP_START_TIME).value(iLogger, this.appStartTime);
        }
        if (this.deviceAppHash != null) {
            objectWriter.name(JsonKeys.DEVICE_APP_HASH).value(this.deviceAppHash);
        }
        if (this.buildType != null) {
            objectWriter.name(JsonKeys.BUILD_TYPE).value(this.buildType);
        }
        if (this.appName != null) {
            objectWriter.name(JsonKeys.APP_NAME).value(this.appName);
        }
        if (this.appVersion != null) {
            objectWriter.name(JsonKeys.APP_VERSION).value(this.appVersion);
        }
        if (this.appBuild != null) {
            objectWriter.name(JsonKeys.APP_BUILD).value(this.appBuild);
        }
        Map<String, String> map = this.permissions;
        if (map != null && !map.isEmpty()) {
            objectWriter.name(JsonKeys.APP_PERMISSIONS).value(iLogger, this.permissions);
        }
        if (this.inForeground != null) {
            objectWriter.name(JsonKeys.IN_FOREGROUND).value(this.inForeground);
        }
        if (this.viewNames != null) {
            objectWriter.name(JsonKeys.VIEW_NAMES).value(iLogger, this.viewNames);
        }
        Map<String, Object> map2 = this.unknown;
        if (map2 != null) {
            for (String str : map2.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<App> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public App deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            String nextName;
            jsonObjectReader.beginObject();
            App app = new App();
            ConcurrentHashMap concurrentHashMap = null;
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "device_app_hash":
                        app.deviceAppHash = jsonObjectReader.nextStringOrNull();
                        break;
                    case "view_names":
                        List<String> list = (List) jsonObjectReader.nextObjectOrNull();
                        if (list == null) {
                            break;
                        } else {
                            app.setViewNames(list);
                            break;
                        }
                    case "app_version":
                        app.appVersion = jsonObjectReader.nextStringOrNull();
                        break;
                    case "in_foreground":
                        app.inForeground = jsonObjectReader.nextBooleanOrNull();
                        break;
                    case "build_type":
                        app.buildType = jsonObjectReader.nextStringOrNull();
                        break;
                    case "app_identifier":
                        app.appIdentifier = jsonObjectReader.nextStringOrNull();
                        break;
                    case "app_start_time":
                        app.appStartTime = jsonObjectReader.nextDateOrNull(iLogger);
                        break;
                    case "permissions":
                        app.permissions = CollectionUtils.newConcurrentHashMap((Map) jsonObjectReader.nextObjectOrNull());
                        break;
                    case "app_name":
                        app.appName = jsonObjectReader.nextStringOrNull();
                        break;
                    case "app_build":
                        app.appBuild = jsonObjectReader.nextStringOrNull();
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        jsonObjectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            app.setUnknown(concurrentHashMap);
            jsonObjectReader.endObject();
            return app;
        }
    }
}
