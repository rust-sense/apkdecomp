package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonObjectReader;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectWriter;
import io.sentry.SentryLockReason;
import io.sentry.protocol.SentryStackTrace;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class SentryThread implements JsonUnknown, JsonSerializable {
    private Boolean crashed;
    private Boolean current;
    private Boolean daemon;
    private Map<String, SentryLockReason> heldLocks;
    private Long id;
    private Boolean main;
    private String name;
    private Integer priority;
    private SentryStackTrace stacktrace;
    private String state;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String CRASHED = "crashed";
        public static final String CURRENT = "current";
        public static final String DAEMON = "daemon";
        public static final String HELD_LOCKS = "held_locks";
        public static final String ID = "id";
        public static final String MAIN = "main";
        public static final String NAME = "name";
        public static final String PRIORITY = "priority";
        public static final String STACKTRACE = "stacktrace";
        public static final String STATE = "state";
    }

    public Map<String, SentryLockReason> getHeldLocks() {
        return this.heldLocks;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public SentryStackTrace getStacktrace() {
        return this.stacktrace;
    }

    public String getState() {
        return this.state;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public Boolean isCrashed() {
        return this.crashed;
    }

    public Boolean isCurrent() {
        return this.current;
    }

    public Boolean isDaemon() {
        return this.daemon;
    }

    public Boolean isMain() {
        return this.main;
    }

    public void setCrashed(Boolean bool) {
        this.crashed = bool;
    }

    public void setCurrent(Boolean bool) {
        this.current = bool;
    }

    public void setDaemon(Boolean bool) {
        this.daemon = bool;
    }

    public void setHeldLocks(Map<String, SentryLockReason> map) {
        this.heldLocks = map;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setMain(Boolean bool) {
        this.main = bool;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPriority(Integer num) {
        this.priority = num;
    }

    public void setStacktrace(SentryStackTrace sentryStackTrace) {
        this.stacktrace = sentryStackTrace;
    }

    public void setState(String str) {
        this.state = str;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.id != null) {
            objectWriter.name("id").value(this.id);
        }
        if (this.priority != null) {
            objectWriter.name(JsonKeys.PRIORITY).value(this.priority);
        }
        if (this.name != null) {
            objectWriter.name("name").value(this.name);
        }
        if (this.state != null) {
            objectWriter.name(JsonKeys.STATE).value(this.state);
        }
        if (this.crashed != null) {
            objectWriter.name(JsonKeys.CRASHED).value(this.crashed);
        }
        if (this.current != null) {
            objectWriter.name(JsonKeys.CURRENT).value(this.current);
        }
        if (this.daemon != null) {
            objectWriter.name(JsonKeys.DAEMON).value(this.daemon);
        }
        if (this.main != null) {
            objectWriter.name(JsonKeys.MAIN).value(this.main);
        }
        if (this.stacktrace != null) {
            objectWriter.name("stacktrace").value(iLogger, this.stacktrace);
        }
        if (this.heldLocks != null) {
            objectWriter.name(JsonKeys.HELD_LOCKS).value(iLogger, this.heldLocks);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<SentryThread> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryThread deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            String nextName;
            SentryThread sentryThread = new SentryThread();
            jsonObjectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "daemon":
                        sentryThread.daemon = jsonObjectReader.nextBooleanOrNull();
                        break;
                    case "priority":
                        sentryThread.priority = jsonObjectReader.nextIntegerOrNull();
                        break;
                    case "held_locks":
                        Map nextMapOrNull = jsonObjectReader.nextMapOrNull(iLogger, new SentryLockReason.Deserializer());
                        if (nextMapOrNull == null) {
                            break;
                        } else {
                            sentryThread.heldLocks = new HashMap(nextMapOrNull);
                            break;
                        }
                    case "id":
                        sentryThread.id = jsonObjectReader.nextLongOrNull();
                        break;
                    case "main":
                        sentryThread.main = jsonObjectReader.nextBooleanOrNull();
                        break;
                    case "name":
                        sentryThread.name = jsonObjectReader.nextStringOrNull();
                        break;
                    case "state":
                        sentryThread.state = jsonObjectReader.nextStringOrNull();
                        break;
                    case "crashed":
                        sentryThread.crashed = jsonObjectReader.nextBooleanOrNull();
                        break;
                    case "current":
                        sentryThread.current = jsonObjectReader.nextBooleanOrNull();
                        break;
                    case "stacktrace":
                        sentryThread.stacktrace = (SentryStackTrace) jsonObjectReader.nextOrNull(iLogger, new SentryStackTrace.Deserializer());
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        jsonObjectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            sentryThread.setUnknown(concurrentHashMap);
            jsonObjectReader.endObject();
            return sentryThread;
        }
    }
}
