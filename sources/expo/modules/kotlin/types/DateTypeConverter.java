package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.SingleType;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: DateTypeConverter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016¨\u0006\u0010"}, d2 = {"Lexpo/modules/kotlin/types/DateTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "Ljava/time/LocalDate;", "isOptional", "", "(Z)V", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "convertFromLong", "", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DateTypeConverter extends DynamicAwareTypeConverters<LocalDate> {

    /* compiled from: DateTypeConverter.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReadableType.values().length];
            try {
                iArr[ReadableType.String.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReadableType.Number.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    public DateTypeConverter(boolean z) {
        super(z);
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public LocalDate convertFromDynamic(Dynamic value) {
        DateTimeFormatter dateTimeFormatter;
        LocalDate parse;
        Intrinsics.checkNotNullParameter(value, "value");
        ReadableType type = value.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            String asString = value.asString();
            dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
            parse = LocalDate.parse(asString, dateTimeFormatter);
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            return parse;
        }
        if (i == 2) {
            return convertFromLong((long) value.asDouble());
        }
        throw new UnexpectedException("Unknown argument type: " + value.getType());
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public LocalDate convertFromAny(Object value) {
        DateTimeFormatter dateTimeFormatter;
        LocalDate parse;
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof String) {
            dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
            parse = LocalDate.parse((CharSequence) value, dateTimeFormatter);
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            return parse;
        }
        if (value instanceof Long) {
            return convertFromLong(((Number) value).longValue());
        }
        throw new UnexpectedException("Unknown argument type: " + Reflection.getOrCreateKotlinClass(value.getClass()));
    }

    private final LocalDate convertFromLong(long value) {
        Instant ofEpochMilli;
        ZoneId systemDefault;
        ZonedDateTime atZone;
        LocalDate localDate;
        ofEpochMilli = Instant.ofEpochMilli(value);
        systemDefault = ZoneId.systemDefault();
        atZone = ofEpochMilli.atZone(systemDefault);
        localDate = atZone.toLocalDate();
        Intrinsics.checkNotNullExpressionValue(localDate, "toLocalDate(...)");
        return localDate;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    /* renamed from: getCppRequiredTypes */
    public ExpectedType get$cppRequireType() {
        return new ExpectedType(new SingleType(CppType.INT, null, 2, null), new SingleType(CppType.STRING, null, 2, null));
    }
}
