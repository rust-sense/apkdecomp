package com.facebook.hermes.intl;

import android.icu.text.DateFormat;
import android.icu.text.NumberingSystem;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.icu.util.ULocale;
import androidx.core.text.util.LocalePreferences;
import com.facebook.hermes.intl.IPlatformDateTimeFormatter;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes.dex */
public class PlatformDateTimeFormatterICU implements IPlatformDateTimeFormatter {
    private DateFormat mDateFormat = null;

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public String format(double d) {
        String format;
        format = this.mDateFormat.format(new Date((long) d));
        return format;
    }

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public String fieldToString(AttributedCharacterIterator.Attribute attribute, String str) {
        DateFormat.Field field;
        DateFormat.Field field2;
        DateFormat.Field field3;
        DateFormat.Field field4;
        DateFormat.Field field5;
        DateFormat.Field field6;
        DateFormat.Field field7;
        DateFormat.Field field8;
        DateFormat.Field field9;
        DateFormat.Field field10;
        DateFormat.Field field11;
        DateFormat.Field field12;
        DateFormat.Field field13;
        field = DateFormat.Field.DAY_OF_WEEK;
        if (attribute == field) {
            return "weekday";
        }
        field2 = DateFormat.Field.ERA;
        if (attribute == field2) {
            return "era";
        }
        field3 = DateFormat.Field.YEAR;
        if (attribute == field3) {
            try {
                Double.parseDouble(str);
                return "year";
            } catch (NumberFormatException unused) {
                return "yearName";
            }
        }
        field4 = DateFormat.Field.MONTH;
        if (attribute == field4) {
            return "month";
        }
        field5 = DateFormat.Field.DAY_OF_MONTH;
        if (attribute == field5) {
            return "day";
        }
        field6 = DateFormat.Field.HOUR0;
        if (attribute == field6) {
            return "hour";
        }
        field7 = DateFormat.Field.HOUR1;
        if (attribute == field7) {
            return "hour";
        }
        field8 = DateFormat.Field.HOUR_OF_DAY0;
        if (attribute == field8) {
            return "hour";
        }
        field9 = DateFormat.Field.HOUR_OF_DAY1;
        if (attribute == field9) {
            return "hour";
        }
        field10 = DateFormat.Field.MINUTE;
        if (attribute == field10) {
            return "minute";
        }
        field11 = DateFormat.Field.SECOND;
        if (attribute == field11) {
            return "second";
        }
        field12 = DateFormat.Field.TIME_ZONE;
        if (attribute == field12) {
            return "timeZoneName";
        }
        field13 = DateFormat.Field.AM_PM;
        return attribute == field13 ? "dayPeriod" : attribute.toString().equals("android.icu.text.DateFormat$Field(related year)") ? "relatedYear" : "literal";
    }

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public AttributedCharacterIterator formatToParts(double d) {
        AttributedCharacterIterator formatToCharacterIterator;
        formatToCharacterIterator = this.mDateFormat.formatToCharacterIterator(Double.valueOf(d));
        return formatToCharacterIterator;
    }

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public String getDefaultCalendarName(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        DateFormat dateInstance;
        Calendar calendar;
        String type;
        dateInstance = DateFormat.getDateInstance(3, Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
        calendar = dateInstance.getCalendar();
        type = calendar.getType();
        return UnicodeExtensionKeys.resolveCalendarAlias(type);
    }

    private static class PatternUtils {
        private PatternUtils() {
        }

        public static String getPatternWithoutLiterals(String str) {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '\'') {
                    z = !z;
                } else if (!z && ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z'))) {
                    sb.append(str.charAt(i));
                }
            }
            return sb.toString();
        }
    }

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public IPlatformDateTimeFormatter.HourCycle getDefaultHourCycle(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        DateFormat timeInstance;
        String pattern;
        IPlatformDateTimeFormatter.HourCycle hourCycle;
        try {
            timeInstance = DateFormat.getTimeInstance(0, Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
            pattern = Intl$$ExternalSyntheticApiModelOutline0.m207m((Object) timeInstance).toPattern();
            String patternWithoutLiterals = PatternUtils.getPatternWithoutLiterals(pattern);
            if (patternWithoutLiterals.contains(String.valueOf('h'))) {
                hourCycle = IPlatformDateTimeFormatter.HourCycle.H12;
            } else if (patternWithoutLiterals.contains(String.valueOf('K'))) {
                hourCycle = IPlatformDateTimeFormatter.HourCycle.H11;
            } else if (patternWithoutLiterals.contains(String.valueOf('H'))) {
                hourCycle = IPlatformDateTimeFormatter.HourCycle.H23;
            } else {
                hourCycle = IPlatformDateTimeFormatter.HourCycle.H24;
            }
            return hourCycle;
        } catch (ClassCastException unused) {
            return IPlatformDateTimeFormatter.HourCycle.H24;
        }
    }

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public String getDefaultTimeZone(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        Calendar calendar;
        TimeZone timeZone;
        String id;
        calendar = Calendar.getInstance(Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
        timeZone = calendar.getTimeZone();
        id = timeZone.getID();
        return id;
    }

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        NumberingSystem numberingSystem;
        String name;
        numberingSystem = NumberingSystem.getInstance(Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
        name = numberingSystem.getName();
        return name;
    }

    static int toICUDateStyle(IPlatformDateTimeFormatter.DateStyle dateStyle) throws JSRangeErrorException {
        int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle[dateStyle.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        throw new JSRangeErrorException("Invalid DateStyle: " + dateStyle.toString());
    }

    /* renamed from: com.facebook.hermes.intl.PlatformDateTimeFormatterICU$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle;

        static {
            int[] iArr = new int[IPlatformDateTimeFormatter.TimeStyle.values().length];
            $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle = iArr;
            try {
                iArr[IPlatformDateTimeFormatter.TimeStyle.FULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle[IPlatformDateTimeFormatter.TimeStyle.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle[IPlatformDateTimeFormatter.TimeStyle.MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle[IPlatformDateTimeFormatter.TimeStyle.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle[IPlatformDateTimeFormatter.TimeStyle.UNDEFINED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[IPlatformDateTimeFormatter.DateStyle.values().length];
            $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle = iArr2;
            try {
                iArr2[IPlatformDateTimeFormatter.DateStyle.FULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle[IPlatformDateTimeFormatter.DateStyle.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle[IPlatformDateTimeFormatter.DateStyle.MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle[IPlatformDateTimeFormatter.DateStyle.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle[IPlatformDateTimeFormatter.DateStyle.UNDEFINED.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    static int toICUTimeStyle(IPlatformDateTimeFormatter.TimeStyle timeStyle) throws JSRangeErrorException {
        int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle[timeStyle.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        throw new JSRangeErrorException("Invalid DateStyle: " + timeStyle.toString());
    }

    private static void replaceChars(StringBuilder sb, String str, String str2) {
        int indexOf = sb.indexOf(str);
        if (indexOf != -1) {
            sb.replace(indexOf, str.length() + indexOf, str2);
        }
    }

    private static String getPatternForStyle(ILocaleObject<?> iLocaleObject, IPlatformDateTimeFormatter.DateStyle dateStyle, IPlatformDateTimeFormatter.TimeStyle timeStyle) throws JSRangeErrorException {
        DateFormat dateTimeInstance;
        String localizedPattern;
        DateFormat dateInstance;
        String localizedPattern2;
        DateFormat timeInstance;
        String localizedPattern3;
        if (dateStyle == IPlatformDateTimeFormatter.DateStyle.UNDEFINED) {
            timeInstance = DateFormat.getTimeInstance(toICUTimeStyle(timeStyle), Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
            localizedPattern3 = Intl$$ExternalSyntheticApiModelOutline0.m207m((Object) timeInstance).toLocalizedPattern();
            return localizedPattern3;
        }
        if (timeStyle == IPlatformDateTimeFormatter.TimeStyle.UNDEFINED) {
            dateInstance = DateFormat.getDateInstance(toICUDateStyle(dateStyle), Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
            localizedPattern2 = Intl$$ExternalSyntheticApiModelOutline0.m207m((Object) dateInstance).toLocalizedPattern();
            return localizedPattern2;
        }
        dateTimeInstance = DateFormat.getDateTimeInstance(toICUDateStyle(dateStyle), toICUTimeStyle(timeStyle), Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
        localizedPattern = Intl$$ExternalSyntheticApiModelOutline0.m207m((Object) dateTimeInstance).toLocalizedPattern();
        return localizedPattern;
    }

    private static void replacePatternChars(StringBuilder sb, char[] cArr, char c) {
        for (int i = 0; i < sb.length(); i++) {
            int length = cArr.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    if (sb.charAt(i) == cArr[i2]) {
                        sb.setCharAt(i, c);
                        break;
                    }
                    i2++;
                }
            }
        }
    }

    private static String getSkeleton(ILocaleObject<?> iLocaleObject, IPlatformDateTimeFormatter.WeekDay weekDay, IPlatformDateTimeFormatter.Era era, IPlatformDateTimeFormatter.Year year, IPlatformDateTimeFormatter.Month month, IPlatformDateTimeFormatter.Day day, IPlatformDateTimeFormatter.Hour hour, IPlatformDateTimeFormatter.Minute minute, IPlatformDateTimeFormatter.Second second, IPlatformDateTimeFormatter.TimeZoneName timeZoneName, IPlatformDateTimeFormatter.HourCycle hourCycle, IPlatformDateTimeFormatter.DateStyle dateStyle, IPlatformDateTimeFormatter.TimeStyle timeStyle, Object obj) throws JSRangeErrorException {
        StringBuilder sb = new StringBuilder();
        if (dateStyle != IPlatformDateTimeFormatter.DateStyle.UNDEFINED || timeStyle != IPlatformDateTimeFormatter.TimeStyle.UNDEFINED) {
            sb.append(getPatternForStyle(iLocaleObject, dateStyle, timeStyle));
            HashMap<String, String> unicodeExtensions = iLocaleObject.getUnicodeExtensions();
            if (unicodeExtensions.containsKey("hc")) {
                String str = unicodeExtensions.get("hc");
                if (str == LocalePreferences.HourCycle.H11 || str == LocalePreferences.HourCycle.H12) {
                    replacePatternChars(sb, new char[]{'H', 'K', 'k'}, 'h');
                } else if (str == LocalePreferences.HourCycle.H23 || str == LocalePreferences.HourCycle.H24) {
                    replacePatternChars(sb, new char[]{'h', 'H', 'K'}, 'k');
                }
            }
            if (hourCycle == IPlatformDateTimeFormatter.HourCycle.H11 || hourCycle == IPlatformDateTimeFormatter.HourCycle.H12) {
                replacePatternChars(sb, new char[]{'H', 'K', 'k'}, 'h');
            } else if (hourCycle == IPlatformDateTimeFormatter.HourCycle.H23 || hourCycle == IPlatformDateTimeFormatter.HourCycle.H24) {
                replacePatternChars(sb, new char[]{'h', 'H', 'K'}, 'k');
            }
            if (!JSObjects.isUndefined(obj) && !JSObjects.isNull(obj)) {
                if (JSObjects.getJavaBoolean(obj)) {
                    replacePatternChars(sb, new char[]{'H', 'K', 'k'}, 'h');
                } else {
                    replacePatternChars(sb, new char[]{'h', 'H', 'K'}, 'k');
                }
            }
        } else {
            sb.append(weekDay.getSkeleonSymbol());
            sb.append(era.getSkeleonSymbol());
            sb.append(year.getSkeleonSymbol());
            sb.append(month.getSkeleonSymbol());
            sb.append(day.getSkeleonSymbol());
            if (hourCycle == IPlatformDateTimeFormatter.HourCycle.H11 || hourCycle == IPlatformDateTimeFormatter.HourCycle.H12) {
                sb.append(hour.getSkeleonSymbol12());
            } else {
                sb.append(hour.getSkeleonSymbol24());
            }
            sb.append(minute.getSkeleonSymbol());
            sb.append(second.getSkeleonSymbol());
            sb.append(timeZoneName.getSkeleonSymbol());
        }
        return sb.toString();
    }

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public void configure(ILocaleObject<?> iLocaleObject, String str, String str2, IPlatformDateTimeFormatter.FormatMatcher formatMatcher, IPlatformDateTimeFormatter.WeekDay weekDay, IPlatformDateTimeFormatter.Era era, IPlatformDateTimeFormatter.Year year, IPlatformDateTimeFormatter.Month month, IPlatformDateTimeFormatter.Day day, IPlatformDateTimeFormatter.Hour hour, IPlatformDateTimeFormatter.Minute minute, IPlatformDateTimeFormatter.Second second, IPlatformDateTimeFormatter.TimeZoneName timeZoneName, IPlatformDateTimeFormatter.HourCycle hourCycle, Object obj, IPlatformDateTimeFormatter.DateStyle dateStyle, IPlatformDateTimeFormatter.TimeStyle timeStyle, Object obj2) throws JSRangeErrorException {
        Calendar calendar;
        NumberingSystem instanceByName;
        DateFormat patternInstance;
        TimeZone timeZone;
        DateFormat patternInstance2;
        String skeleton = getSkeleton(iLocaleObject, weekDay, era, year, month, day, hour, minute, second, timeZoneName, hourCycle, dateStyle, timeStyle, obj2);
        if (str.isEmpty()) {
            calendar = null;
        } else {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(JSObjects.getJavaString(str));
            ILocaleObject<?> cloneObject = iLocaleObject.cloneObject();
            cloneObject.setUnicodeExtensions("ca", arrayList);
            calendar = Calendar.getInstance(Intl$$ExternalSyntheticApiModelOutline0.m212m(cloneObject.getLocale()));
        }
        if (!str2.isEmpty()) {
            try {
                instanceByName = NumberingSystem.getInstanceByName(JSObjects.getJavaString(str2));
                if (instanceByName == null) {
                    throw new JSRangeErrorException("Invalid numbering system: " + str2);
                }
                ArrayList<String> arrayList2 = new ArrayList<>();
                arrayList2.add(JSObjects.getJavaString(str2));
                iLocaleObject.setUnicodeExtensions("nu", arrayList2);
            } catch (RuntimeException unused) {
                throw new JSRangeErrorException("Invalid numbering system: " + str2);
            }
        }
        if (calendar != null) {
            patternInstance2 = DateFormat.getPatternInstance(calendar, skeleton, Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
            this.mDateFormat = patternInstance2;
        } else {
            patternInstance = DateFormat.getPatternInstance(skeleton, Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
            this.mDateFormat = patternInstance;
        }
        if (JSObjects.isUndefined(obj) || JSObjects.isNull(obj)) {
            return;
        }
        timeZone = TimeZone.getTimeZone(JSObjects.getJavaString(obj));
        this.mDateFormat.setTimeZone(timeZone);
    }

    @Override // com.facebook.hermes.intl.IPlatformDateTimeFormatter
    public String[] getAvailableLocales() {
        ULocale[] availableLocales;
        String languageTag;
        ArrayList arrayList = new ArrayList();
        availableLocales = ULocale.getAvailableLocales();
        for (ULocale uLocale : availableLocales) {
            languageTag = uLocale.toLanguageTag();
            arrayList.add(languageTag);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    PlatformDateTimeFormatterICU() {
    }
}
