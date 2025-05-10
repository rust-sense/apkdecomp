package io.sentry;

import io.sentry.MonitorSchedule;
import io.sentry.SentryOptions;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class MonitorConfig implements JsonUnknown, JsonSerializable {
    private Long checkinMargin;
    private Long failureIssueThreshold;
    private Long maxRuntime;
    private Long recoveryThreshold;
    private MonitorSchedule schedule;
    private String timezone;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String CHECKIN_MARGIN = "checkin_margin";
        public static final String FAILURE_ISSUE_THRESHOLD = "failure_issue_threshold";
        public static final String MAX_RUNTIME = "max_runtime";
        public static final String RECOVERY_THRESHOLD = "recovery_threshold";
        public static final String SCHEDULE = "schedule";
        public static final String TIMEZONE = "timezone";
    }

    public Long getCheckinMargin() {
        return this.checkinMargin;
    }

    public Long getFailureIssueThreshold() {
        return this.failureIssueThreshold;
    }

    public Long getMaxRuntime() {
        return this.maxRuntime;
    }

    public Long getRecoveryThreshold() {
        return this.recoveryThreshold;
    }

    public MonitorSchedule getSchedule() {
        return this.schedule;
    }

    public String getTimezone() {
        return this.timezone;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public void setCheckinMargin(Long l) {
        this.checkinMargin = l;
    }

    public void setFailureIssueThreshold(Long l) {
        this.failureIssueThreshold = l;
    }

    public void setMaxRuntime(Long l) {
        this.maxRuntime = l;
    }

    public void setRecoveryThreshold(Long l) {
        this.recoveryThreshold = l;
    }

    public void setSchedule(MonitorSchedule monitorSchedule) {
        this.schedule = monitorSchedule;
    }

    public void setTimezone(String str) {
        this.timezone = str;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public MonitorConfig(MonitorSchedule monitorSchedule) {
        this.schedule = monitorSchedule;
        SentryOptions.Cron cron = HubAdapter.getInstance().getOptions().getCron();
        if (cron != null) {
            this.checkinMargin = cron.getDefaultCheckinMargin();
            this.maxRuntime = cron.getDefaultMaxRuntime();
            this.timezone = cron.getDefaultTimezone();
            this.failureIssueThreshold = cron.getDefaultFailureIssueThreshold();
            this.recoveryThreshold = cron.getDefaultRecoveryThreshold();
        }
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name(JsonKeys.SCHEDULE);
        this.schedule.serialize(objectWriter, iLogger);
        if (this.checkinMargin != null) {
            objectWriter.name(JsonKeys.CHECKIN_MARGIN).value(this.checkinMargin);
        }
        if (this.maxRuntime != null) {
            objectWriter.name(JsonKeys.MAX_RUNTIME).value(this.maxRuntime);
        }
        if (this.timezone != null) {
            objectWriter.name("timezone").value(this.timezone);
        }
        if (this.failureIssueThreshold != null) {
            objectWriter.name(JsonKeys.FAILURE_ISSUE_THRESHOLD).value(this.failureIssueThreshold);
        }
        if (this.recoveryThreshold != null) {
            objectWriter.name(JsonKeys.RECOVERY_THRESHOLD).value(this.recoveryThreshold);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<MonitorConfig> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public MonitorConfig deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            String nextName;
            jsonObjectReader.beginObject();
            MonitorSchedule monitorSchedule = null;
            Long l = null;
            Long l2 = null;
            String str = null;
            Long l3 = null;
            Long l4 = null;
            HashMap hashMap = null;
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "timezone":
                        str = jsonObjectReader.nextStringOrNull();
                        break;
                    case "checkin_margin":
                        l = jsonObjectReader.nextLongOrNull();
                        break;
                    case "schedule":
                        monitorSchedule = new MonitorSchedule.Deserializer().deserialize(jsonObjectReader, iLogger);
                        break;
                    case "recovery_threshold":
                        l4 = jsonObjectReader.nextLongOrNull();
                        break;
                    case "max_runtime":
                        l2 = jsonObjectReader.nextLongOrNull();
                        break;
                    case "failure_issue_threshold":
                        l3 = jsonObjectReader.nextLongOrNull();
                        break;
                    default:
                        if (hashMap == null) {
                            hashMap = new HashMap();
                        }
                        jsonObjectReader.nextUnknown(iLogger, hashMap, nextName);
                        break;
                }
            }
            jsonObjectReader.endObject();
            if (monitorSchedule == null) {
                IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"schedule\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"schedule\"", illegalStateException);
                throw illegalStateException;
            }
            MonitorConfig monitorConfig = new MonitorConfig(monitorSchedule);
            monitorConfig.setCheckinMargin(l);
            monitorConfig.setMaxRuntime(l2);
            monitorConfig.setTimezone(str);
            monitorConfig.setFailureIssueThreshold(l3);
            monitorConfig.setRecoveryThreshold(l4);
            monitorConfig.setUnknown(hashMap);
            return monitorConfig;
        }
    }
}
