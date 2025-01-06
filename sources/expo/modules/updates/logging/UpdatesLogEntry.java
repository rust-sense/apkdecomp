package expo.modules.updates.logging;

import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UpdatesLogEntry.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 ,2\u00020\u0001:\u0001,BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u0006\u0010\u001b\u001a\u00020\u0005J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\fHÆ\u0003Jl\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000f¨\u0006-"}, d2 = {"Lexpo/modules/updates/logging/UpdatesLogEntry;", "", "timestamp", "", "message", "", "code", "level", "duration", "updateId", "assetId", "stacktrace", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getAssetId", "()Ljava/lang/String;", "getCode", "getDuration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLevel", "getMessage", "getStacktrace", "()Ljava/util/List;", "getTimestamp", "()J", "getUpdateId", "asString", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lexpo/modules/updates/logging/UpdatesLogEntry;", "equals", "", "other", "hashCode", "", "toString", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class UpdatesLogEntry {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String assetId;
    private final String code;
    private final Long duration;
    private final String level;
    private final String message;
    private final List<String> stacktrace;
    private final long timestamp;
    private final String updateId;

    /* renamed from: component1, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* renamed from: component4, reason: from getter */
    public final String getLevel() {
        return this.level;
    }

    /* renamed from: component5, reason: from getter */
    public final Long getDuration() {
        return this.duration;
    }

    /* renamed from: component6, reason: from getter */
    public final String getUpdateId() {
        return this.updateId;
    }

    /* renamed from: component7, reason: from getter */
    public final String getAssetId() {
        return this.assetId;
    }

    public final List<String> component8() {
        return this.stacktrace;
    }

    public final UpdatesLogEntry copy(long timestamp, String message, String code, String level, Long duration, String updateId, String assetId, List<String> stacktrace) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(level, "level");
        return new UpdatesLogEntry(timestamp, message, code, level, duration, updateId, assetId, stacktrace);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdatesLogEntry)) {
            return false;
        }
        UpdatesLogEntry updatesLogEntry = (UpdatesLogEntry) other;
        return this.timestamp == updatesLogEntry.timestamp && Intrinsics.areEqual(this.message, updatesLogEntry.message) && Intrinsics.areEqual(this.code, updatesLogEntry.code) && Intrinsics.areEqual(this.level, updatesLogEntry.level) && Intrinsics.areEqual(this.duration, updatesLogEntry.duration) && Intrinsics.areEqual(this.updateId, updatesLogEntry.updateId) && Intrinsics.areEqual(this.assetId, updatesLogEntry.assetId) && Intrinsics.areEqual(this.stacktrace, updatesLogEntry.stacktrace);
    }

    public final String getAssetId() {
        return this.assetId;
    }

    public final String getCode() {
        return this.code;
    }

    public final Long getDuration() {
        return this.duration;
    }

    public final String getLevel() {
        return this.level;
    }

    public final String getMessage() {
        return this.message;
    }

    public final List<String> getStacktrace() {
        return this.stacktrace;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getUpdateId() {
        return this.updateId;
    }

    public int hashCode() {
        int m = ((((((SourceMap$$ExternalSyntheticBackport0.m(this.timestamp) * 31) + this.message.hashCode()) * 31) + this.code.hashCode()) * 31) + this.level.hashCode()) * 31;
        Long l = this.duration;
        int hashCode = (m + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.updateId;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.assetId;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<String> list = this.stacktrace;
        return hashCode3 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "UpdatesLogEntry(timestamp=" + this.timestamp + ", message=" + this.message + ", code=" + this.code + ", level=" + this.level + ", duration=" + this.duration + ", updateId=" + this.updateId + ", assetId=" + this.assetId + ", stacktrace=" + this.stacktrace + ")";
    }

    public UpdatesLogEntry(long j, String message, String code, String level, Long l, String str, String str2, List<String> list) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(level, "level");
        this.timestamp = j;
        this.message = message;
        this.code = code;
        this.level = level;
        this.duration = l;
        this.updateId = str;
        this.assetId = str2;
        this.stacktrace = list;
    }

    public final String asString() {
        JSONObject jSONObject = new JSONObject(MapsKt.mapOf(TuplesKt.to("timestamp", Long.valueOf(this.timestamp)), TuplesKt.to("message", this.message), TuplesKt.to("code", this.code), TuplesKt.to("level", this.level)));
        Long l = this.duration;
        if (l != null) {
            jSONObject.put("duration", l.longValue());
        }
        String str = this.updateId;
        if (str != null) {
            jSONObject.put("updateId", str);
        }
        String str2 = this.assetId;
        if (str2 != null) {
            jSONObject.put("assetId", str2);
        }
        List<String> list = this.stacktrace;
        if (list != null && !list.isEmpty()) {
            jSONObject.put("stacktrace", new JSONArray((Collection) this.stacktrace));
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }

    /* compiled from: UpdatesLogEntry.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/logging/UpdatesLogEntry$Companion;", "", "()V", "create", "Lexpo/modules/updates/logging/UpdatesLogEntry;", "json", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UpdatesLogEntry create(String json) {
            Long l;
            String str;
            String str2;
            String str3;
            Long l2;
            Long l3;
            String str4;
            String str5;
            String str6;
            String str7;
            JSONArray jSONArray;
            ArrayList arrayList;
            Intrinsics.checkNotNullParameter(json, "json");
            try {
                JSONObject jSONObject = new JSONObject(json);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Long.class);
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                    Object string = jSONObject.getString("timestamp");
                    if (string == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    l = (Long) string;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    l = (Long) Double.valueOf(jSONObject.getDouble("timestamp"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    l = (Long) Integer.valueOf(jSONObject.getInt("timestamp"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    l = Long.valueOf(jSONObject.getLong("timestamp"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    l = (Long) Boolean.valueOf(jSONObject.getBoolean("timestamp"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    Object jSONArray2 = jSONObject.getJSONArray("timestamp");
                    if (jSONArray2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    l = (Long) jSONArray2;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    Object jSONObject2 = jSONObject.getJSONObject("timestamp");
                    if (jSONObject2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    l = (Long) jSONObject2;
                } else {
                    Object obj = jSONObject.get("timestamp");
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    l = (Long) obj;
                }
                long longValue = l.longValue();
                KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                    str = jSONObject.getString("message");
                    if (str == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str = (String) Double.valueOf(jSONObject.getDouble("message"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str = (String) Integer.valueOf(jSONObject.getInt("message"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str = (String) Long.valueOf(jSONObject.getLong("message"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str = (String) Boolean.valueOf(jSONObject.getBoolean("message"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    Object jSONArray3 = jSONObject.getJSONArray("message");
                    if (jSONArray3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str = (String) jSONArray3;
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    Object jSONObject3 = jSONObject.getJSONObject("message");
                    if (jSONObject3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str = (String) jSONObject3;
                } else {
                    Object obj2 = jSONObject.get("message");
                    if (obj2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str = (String) obj2;
                }
                String str8 = str;
                KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(String.class))) {
                    str2 = jSONObject.getString("code");
                    if (str2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str2 = (String) Double.valueOf(jSONObject.getDouble("code"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str2 = (String) Integer.valueOf(jSONObject.getInt("code"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str2 = (String) Long.valueOf(jSONObject.getLong("code"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str2 = (String) Boolean.valueOf(jSONObject.getBoolean("code"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    Object jSONArray4 = jSONObject.getJSONArray("code");
                    if (jSONArray4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str2 = (String) jSONArray4;
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    Object jSONObject4 = jSONObject.getJSONObject("code");
                    if (jSONObject4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str2 = (String) jSONObject4;
                } else {
                    Object obj3 = jSONObject.get("code");
                    if (obj3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str2 = (String) obj3;
                }
                String str9 = str2;
                KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(String.class))) {
                    str3 = jSONObject.getString("level");
                    if (str3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str3 = (String) Double.valueOf(jSONObject.getDouble("level"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str3 = (String) Integer.valueOf(jSONObject.getInt("level"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str3 = (String) Long.valueOf(jSONObject.getLong("level"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str3 = (String) Boolean.valueOf(jSONObject.getBoolean("level"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    Object jSONArray5 = jSONObject.getJSONArray("level");
                    if (jSONArray5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str3 = (String) jSONArray5;
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    Object jSONObject5 = jSONObject.getJSONObject("level");
                    if (jSONObject5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str3 = (String) jSONObject5;
                } else {
                    Object obj4 = jSONObject.get("level");
                    if (obj4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str3 = (String) obj4;
                }
                String str10 = str3;
                if (jSONObject.has("duration")) {
                    KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Long.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(String.class))) {
                        Object string2 = jSONObject.getString("duration");
                        if (string2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                        }
                        l2 = (Long) string2;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        l2 = (Long) Double.valueOf(jSONObject.getDouble("duration"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        l2 = (Long) Integer.valueOf(jSONObject.getInt("duration"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        l2 = Long.valueOf(jSONObject.getLong("duration"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        l2 = (Long) Boolean.valueOf(jSONObject.getBoolean("duration"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        Object jSONArray6 = jSONObject.getJSONArray("duration");
                        if (jSONArray6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                        }
                        l2 = (Long) jSONArray6;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        Object jSONObject6 = jSONObject.getJSONObject("duration");
                        if (jSONObject6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                        }
                        l2 = (Long) jSONObject6;
                    } else {
                        Object obj5 = jSONObject.get("duration");
                        if (obj5 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                        }
                        l2 = (Long) obj5;
                    }
                    l3 = l2;
                } else {
                    l3 = null;
                }
                if (jSONObject.has("updateId")) {
                    KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(String.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(String.class))) {
                        str4 = jSONObject.getString("updateId");
                        if (str4 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        str4 = (String) Double.valueOf(jSONObject.getDouble("updateId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        str4 = (String) Integer.valueOf(jSONObject.getInt("updateId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        str4 = (String) Long.valueOf(jSONObject.getLong("updateId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        str4 = (String) Boolean.valueOf(jSONObject.getBoolean("updateId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        Object jSONArray7 = jSONObject.getJSONArray("updateId");
                        if (jSONArray7 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str4 = (String) jSONArray7;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        Object jSONObject7 = jSONObject.getJSONObject("updateId");
                        if (jSONObject7 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str4 = (String) jSONObject7;
                    } else {
                        Object obj6 = jSONObject.get("updateId");
                        if (obj6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str4 = (String) obj6;
                    }
                    str5 = str4;
                } else {
                    str5 = null;
                }
                if (jSONObject.has("assetId")) {
                    KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(String.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(String.class))) {
                        str6 = jSONObject.getString("assetId");
                        if (str6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        str6 = (String) Double.valueOf(jSONObject.getDouble("assetId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        str6 = (String) Integer.valueOf(jSONObject.getInt("assetId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        str6 = (String) Long.valueOf(jSONObject.getLong("assetId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        str6 = (String) Boolean.valueOf(jSONObject.getBoolean("assetId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        Object jSONArray8 = jSONObject.getJSONArray("assetId");
                        if (jSONArray8 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str6 = (String) jSONArray8;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        Object jSONObject8 = jSONObject.getJSONObject("assetId");
                        if (jSONObject8 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str6 = (String) jSONObject8;
                    } else {
                        Object obj7 = jSONObject.get("assetId");
                        if (obj7 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str6 = (String) obj7;
                    }
                    str7 = str6;
                } else {
                    str7 = null;
                }
                if (jSONObject.has("stacktrace")) {
                    KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(JSONArray.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(String.class))) {
                        Object string3 = jSONObject.getString("stacktrace");
                        if (string3 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                        }
                        jSONArray = (JSONArray) string3;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        jSONArray = (JSONArray) Double.valueOf(jSONObject.getDouble("stacktrace"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        jSONArray = (JSONArray) Integer.valueOf(jSONObject.getInt("stacktrace"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        jSONArray = (JSONArray) Long.valueOf(jSONObject.getLong("stacktrace"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        jSONArray = (JSONArray) Boolean.valueOf(jSONObject.getBoolean("stacktrace"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        jSONArray = jSONObject.getJSONArray("stacktrace");
                        if (jSONArray == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        Object jSONObject9 = jSONObject.getJSONObject("stacktrace");
                        if (jSONObject9 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                        }
                        jSONArray = (JSONArray) jSONObject9;
                    } else {
                        Object obj8 = jSONObject.get("stacktrace");
                        if (obj8 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                        }
                        jSONArray = (JSONArray) obj8;
                    }
                } else {
                    jSONArray = null;
                }
                if (jSONArray != null) {
                    int length = jSONArray.length();
                    ArrayList arrayList2 = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        arrayList2.add(jSONArray.getString(i));
                    }
                    arrayList = arrayList2;
                } else {
                    arrayList = null;
                }
                return new UpdatesLogEntry(longValue, str8, str9, str10, l3, str5, str7, arrayList);
            } catch (JSONException unused) {
                return null;
            }
        }
    }
}
