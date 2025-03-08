package io.sentry;

import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class SentryAppStartProfilingOptions implements JsonUnknown, JsonSerializable {
    boolean isProfilingEnabled;
    Double profileSampleRate;
    boolean profileSampled;
    String profilingTracesDirPath;
    int profilingTracesHz;
    Double traceSampleRate;
    boolean traceSampled;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String IS_PROFILING_ENABLED = "is_profiling_enabled";
        public static final String PROFILE_SAMPLED = "profile_sampled";
        public static final String PROFILE_SAMPLE_RATE = "profile_sample_rate";
        public static final String PROFILING_TRACES_DIR_PATH = "profiling_traces_dir_path";
        public static final String PROFILING_TRACES_HZ = "profiling_traces_hz";
        public static final String TRACE_SAMPLED = "trace_sampled";
        public static final String TRACE_SAMPLE_RATE = "trace_sample_rate";
    }

    public Double getProfileSampleRate() {
        return this.profileSampleRate;
    }

    public String getProfilingTracesDirPath() {
        return this.profilingTracesDirPath;
    }

    public int getProfilingTracesHz() {
        return this.profilingTracesHz;
    }

    public Double getTraceSampleRate() {
        return this.traceSampleRate;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public boolean isProfileSampled() {
        return this.profileSampled;
    }

    public boolean isProfilingEnabled() {
        return this.isProfilingEnabled;
    }

    public boolean isTraceSampled() {
        return this.traceSampled;
    }

    public void setProfileSampleRate(Double d) {
        this.profileSampleRate = d;
    }

    public void setProfileSampled(boolean z) {
        this.profileSampled = z;
    }

    public void setProfilingEnabled(boolean z) {
        this.isProfilingEnabled = z;
    }

    public void setProfilingTracesDirPath(String str) {
        this.profilingTracesDirPath = str;
    }

    public void setProfilingTracesHz(int i) {
        this.profilingTracesHz = i;
    }

    public void setTraceSampleRate(Double d) {
        this.traceSampleRate = d;
    }

    public void setTraceSampled(boolean z) {
        this.traceSampled = z;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public SentryAppStartProfilingOptions() {
        this.traceSampled = false;
        this.traceSampleRate = null;
        this.profileSampled = false;
        this.profileSampleRate = null;
        this.profilingTracesDirPath = null;
        this.isProfilingEnabled = false;
        this.profilingTracesHz = 0;
    }

    SentryAppStartProfilingOptions(SentryOptions sentryOptions, TracesSamplingDecision tracesSamplingDecision) {
        this.traceSampled = tracesSamplingDecision.getSampled().booleanValue();
        this.traceSampleRate = tracesSamplingDecision.getSampleRate();
        this.profileSampled = tracesSamplingDecision.getProfileSampled().booleanValue();
        this.profileSampleRate = tracesSamplingDecision.getProfileSampleRate();
        this.profilingTracesDirPath = sentryOptions.getProfilingTracesDirPath();
        this.isProfilingEnabled = sentryOptions.isProfilingEnabled();
        this.profilingTracesHz = sentryOptions.getProfilingTracesHz();
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name(JsonKeys.PROFILE_SAMPLED).value(iLogger, Boolean.valueOf(this.profileSampled));
        objectWriter.name(JsonKeys.PROFILE_SAMPLE_RATE).value(iLogger, this.profileSampleRate);
        objectWriter.name(JsonKeys.TRACE_SAMPLED).value(iLogger, Boolean.valueOf(this.traceSampled));
        objectWriter.name(JsonKeys.TRACE_SAMPLE_RATE).value(iLogger, this.traceSampleRate);
        objectWriter.name(JsonKeys.PROFILING_TRACES_DIR_PATH).value(iLogger, this.profilingTracesDirPath);
        objectWriter.name(JsonKeys.IS_PROFILING_ENABLED).value(iLogger, Boolean.valueOf(this.isProfilingEnabled));
        objectWriter.name(JsonKeys.PROFILING_TRACES_HZ).value(iLogger, Integer.valueOf(this.profilingTracesHz));
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

    public static final class Deserializer implements JsonDeserializer<SentryAppStartProfilingOptions> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryAppStartProfilingOptions deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            String nextName;
            jsonObjectReader.beginObject();
            SentryAppStartProfilingOptions sentryAppStartProfilingOptions = new SentryAppStartProfilingOptions();
            ConcurrentHashMap concurrentHashMap = null;
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "trace_sampled":
                        Boolean nextBooleanOrNull = jsonObjectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.traceSampled = nextBooleanOrNull.booleanValue();
                            break;
                        }
                    case "profiling_traces_dir_path":
                        String nextStringOrNull = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.profilingTracesDirPath = nextStringOrNull;
                            break;
                        }
                    case "is_profiling_enabled":
                        Boolean nextBooleanOrNull2 = jsonObjectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull2 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.isProfilingEnabled = nextBooleanOrNull2.booleanValue();
                            break;
                        }
                    case "profile_sampled":
                        Boolean nextBooleanOrNull3 = jsonObjectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull3 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.profileSampled = nextBooleanOrNull3.booleanValue();
                            break;
                        }
                    case "profiling_traces_hz":
                        Integer nextIntegerOrNull = jsonObjectReader.nextIntegerOrNull();
                        if (nextIntegerOrNull == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.profilingTracesHz = nextIntegerOrNull.intValue();
                            break;
                        }
                    case "trace_sample_rate":
                        Double nextDoubleOrNull = jsonObjectReader.nextDoubleOrNull();
                        if (nextDoubleOrNull == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.traceSampleRate = nextDoubleOrNull;
                            break;
                        }
                    case "profile_sample_rate":
                        Double nextDoubleOrNull2 = jsonObjectReader.nextDoubleOrNull();
                        if (nextDoubleOrNull2 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.profileSampleRate = nextDoubleOrNull2;
                            break;
                        }
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        jsonObjectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            sentryAppStartProfilingOptions.setUnknown(concurrentHashMap);
            jsonObjectReader.endObject();
            return sentryAppStartProfilingOptions;
        }
    }
}
