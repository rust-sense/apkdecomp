package com.facebook.hermes.intl;

import android.icu.text.CompactDecimalFormat;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.MeasureFormat;
import android.icu.text.NumberFormat;
import android.icu.text.NumberingSystem;
import android.icu.util.Currency;
import android.icu.util.MeasureUnit;
import android.icu.util.ULocale;
import com.facebook.hermes.intl.IPlatformNumberFormatter;
import com.facebook.react.views.text.ReactTextView$$ExternalSyntheticApiModelOutline0;
import java.text.AttributedCharacterIterator;
import java.text.Format;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class PlatformNumberFormatterICU implements IPlatformNumberFormatter {
    private Format mFinalFormat;
    private LocaleObjectICU mLocaleObject;
    private MeasureUnit mMeasureUnit;
    private android.icu.text.NumberFormat mNumberFormat;
    private IPlatformNumberFormatter.Style mStyle;

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public /* bridge */ /* synthetic */ IPlatformNumberFormatter configure(ILocaleObject iLocaleObject, String str, IPlatformNumberFormatter.Style style, IPlatformNumberFormatter.CurrencySign currencySign, IPlatformNumberFormatter.Notation notation, IPlatformNumberFormatter.CompactDisplay compactDisplay) throws JSRangeErrorException {
        return configure((ILocaleObject<?>) iLocaleObject, str, style, currencySign, notation, compactDisplay);
    }

    PlatformNumberFormatterICU() {
    }

    private void initialize(android.icu.text.NumberFormat numberFormat, ILocaleObject<?> iLocaleObject, IPlatformNumberFormatter.Style style) {
        this.mNumberFormat = numberFormat;
        this.mFinalFormat = numberFormat;
        this.mLocaleObject = (LocaleObjectICU) iLocaleObject;
        this.mStyle = style;
        numberFormat.setRoundingMode(4);
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterICU setCurrency(String str, IPlatformNumberFormatter.CurrencyDisplay currencyDisplay) throws JSRangeErrorException {
        Currency currency;
        DecimalFormatSymbols decimalFormatSymbols;
        if (this.mStyle == IPlatformNumberFormatter.Style.CURRENCY) {
            currency = Currency.getInstance(str);
            this.mNumberFormat.setCurrency(currency);
            if (currencyDisplay != IPlatformNumberFormatter.CurrencyDisplay.CODE) {
                str = currency.getName(this.mLocaleObject.getLocale(), currencyDisplay.getNameStyle(), (boolean[]) null);
            }
            if (ReactTextView$$ExternalSyntheticApiModelOutline0.m546m((Object) this.mNumberFormat)) {
                DecimalFormat m = ReactTextView$$ExternalSyntheticApiModelOutline0.m((Object) this.mNumberFormat);
                decimalFormatSymbols = m.getDecimalFormatSymbols();
                decimalFormatSymbols.setCurrencySymbol(str);
                m.setDecimalFormatSymbols(decimalFormatSymbols);
            }
        }
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterICU setGrouping(boolean z) {
        this.mNumberFormat.setGroupingUsed(z);
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterICU setMinIntergerDigits(int i) {
        if (i != -1) {
            this.mNumberFormat.setMinimumIntegerDigits(i);
        }
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterICU setSignificantDigits(IPlatformNumberFormatter.RoundingType roundingType, int i, int i2) throws JSRangeErrorException {
        int minimumSignificantDigits;
        if (ReactTextView$$ExternalSyntheticApiModelOutline0.m546m((Object) this.mNumberFormat) && roundingType == IPlatformNumberFormatter.RoundingType.SIGNIFICANT_DIGITS) {
            DecimalFormat m = ReactTextView$$ExternalSyntheticApiModelOutline0.m((Object) this.mNumberFormat);
            if (i >= 0) {
                m.setMinimumSignificantDigits(i);
            }
            if (i2 >= 0) {
                minimumSignificantDigits = m.getMinimumSignificantDigits();
                if (i2 < minimumSignificantDigits) {
                    throw new JSRangeErrorException("maximumSignificantDigits should be at least equal to minimumSignificantDigits");
                }
                m.setMaximumSignificantDigits(i2);
            }
            m.setSignificantDigitsUsed(true);
        }
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterICU setFractionDigits(IPlatformNumberFormatter.RoundingType roundingType, int i, int i2) {
        if (roundingType == IPlatformNumberFormatter.RoundingType.FRACTION_DIGITS) {
            if (i >= 0) {
                this.mNumberFormat.setMinimumFractionDigits(i);
            }
            if (i2 >= 0) {
                this.mNumberFormat.setMaximumFractionDigits(i2);
            }
            if (ReactTextView$$ExternalSyntheticApiModelOutline0.m546m((Object) this.mNumberFormat)) {
                ReactTextView$$ExternalSyntheticApiModelOutline0.m((Object) this.mNumberFormat).setSignificantDigitsUsed(false);
            }
        }
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterICU setSignDisplay(IPlatformNumberFormatter.SignDisplay signDisplay) {
        DecimalFormatSymbols decimalFormatSymbols;
        String negativePrefix;
        String negativeSuffix;
        char plusSign;
        char plusSign2;
        if (ReactTextView$$ExternalSyntheticApiModelOutline0.m546m((Object) this.mNumberFormat)) {
            DecimalFormat m = ReactTextView$$ExternalSyntheticApiModelOutline0.m((Object) this.mNumberFormat);
            decimalFormatSymbols = m.getDecimalFormatSymbols();
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay[signDisplay.ordinal()];
            if (i == 1) {
                m.setPositivePrefix("");
                m.setPositiveSuffix("");
                m.setNegativePrefix("");
                m.setNegativeSuffix("");
            } else if (i == 2 || i == 3) {
                negativePrefix = m.getNegativePrefix();
                if (!negativePrefix.isEmpty()) {
                    plusSign2 = decimalFormatSymbols.getPlusSign();
                    m.setPositivePrefix(new String(new char[]{plusSign2}));
                }
                negativeSuffix = m.getNegativeSuffix();
                if (!negativeSuffix.isEmpty()) {
                    plusSign = decimalFormatSymbols.getPlusSign();
                    m.setPositiveSuffix(new String(new char[]{plusSign}));
                }
            }
        }
        return this;
    }

    /* renamed from: com.facebook.hermes.intl.PlatformNumberFormatterICU$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay;

        static {
            int[] iArr = new int[IPlatformNumberFormatter.SignDisplay.values().length];
            $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay = iArr;
            try {
                iArr[IPlatformNumberFormatter.SignDisplay.NEVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay[IPlatformNumberFormatter.SignDisplay.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay[IPlatformNumberFormatter.SignDisplay.EXCEPTZERO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static MeasureUnit parseUnit(String str) throws JSRangeErrorException {
        Set available;
        String subtype;
        String subtype2;
        String type;
        available = MeasureUnit.getAvailable();
        Iterator it = available.iterator();
        while (it.hasNext()) {
            MeasureUnit m537m = ReactTextView$$ExternalSyntheticApiModelOutline0.m537m(it.next());
            subtype = m537m.getSubtype();
            if (!subtype.equals(str)) {
                subtype2 = m537m.getSubtype();
                StringBuilder sb = new StringBuilder();
                type = m537m.getType();
                sb.append(type);
                sb.append("-");
                sb.append(str);
                if (subtype2.equals(sb.toString())) {
                }
            }
            return m537m;
        }
        throw new JSRangeErrorException("Unknown unit: " + str);
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterICU setUnits(String str, IPlatformNumberFormatter.UnitDisplay unitDisplay) throws JSRangeErrorException {
        MeasureFormat measureFormat;
        if (this.mStyle == IPlatformNumberFormatter.Style.UNIT) {
            this.mMeasureUnit = parseUnit(str);
            measureFormat = MeasureFormat.getInstance(this.mLocaleObject.getLocale(), unitDisplay.getFormatWidth(), this.mNumberFormat);
            this.mFinalFormat = measureFormat;
        }
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public String format(double d) {
        ULocale forLanguageTag;
        android.icu.text.NumberFormat numberFormat;
        String format;
        ULocale uLocale;
        android.icu.text.NumberFormat numberFormat2;
        String format2;
        try {
            try {
                if (ReactTextView$$ExternalSyntheticApiModelOutline0.m$1(this.mFinalFormat) && this.mMeasureUnit != null) {
                    Format format3 = this.mFinalFormat;
                    ReactTextView$$ExternalSyntheticApiModelOutline0.m544m();
                    return format3.format(ReactTextView$$ExternalSyntheticApiModelOutline0.m(Double.valueOf(d), this.mMeasureUnit));
                }
                return this.mFinalFormat.format(Double.valueOf(d));
            } catch (RuntimeException unused) {
                forLanguageTag = ULocale.forLanguageTag("en");
                numberFormat = android.icu.text.NumberFormat.getInstance(forLanguageTag);
                format = numberFormat.format(d);
                return format;
            }
        } catch (NumberFormatException unused2) {
            uLocale = ULocale.getDefault();
            numberFormat2 = android.icu.text.NumberFormat.getInstance(uLocale);
            format2 = numberFormat2.format(d);
            return format2;
        }
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public String fieldToString(AttributedCharacterIterator.Attribute attribute, double d) {
        NumberFormat.Field field;
        NumberFormat.Field field2;
        NumberFormat.Field field3;
        NumberFormat.Field field4;
        NumberFormat.Field field5;
        NumberFormat.Field field6;
        NumberFormat.Field field7;
        NumberFormat.Field field8;
        NumberFormat.Field field9;
        NumberFormat.Field field10;
        NumberFormat.Field field11;
        field = NumberFormat.Field.SIGN;
        if (attribute == field) {
            return Double.compare(d, 0.0d) >= 0 ? "plusSign" : "minusSign";
        }
        field2 = NumberFormat.Field.INTEGER;
        if (attribute == field2) {
            return Double.isNaN(d) ? "nan" : Double.isInfinite(d) ? "infinity" : "integer";
        }
        field3 = NumberFormat.Field.FRACTION;
        if (attribute == field3) {
            return "fraction";
        }
        field4 = NumberFormat.Field.EXPONENT;
        if (attribute == field4) {
            return "exponentInteger";
        }
        field5 = NumberFormat.Field.EXPONENT_SIGN;
        if (attribute == field5) {
            return "exponentMinusSign";
        }
        field6 = NumberFormat.Field.EXPONENT_SYMBOL;
        if (attribute == field6) {
            return "exponentSeparator";
        }
        field7 = NumberFormat.Field.DECIMAL_SEPARATOR;
        if (attribute == field7) {
            return "decimal";
        }
        field8 = NumberFormat.Field.GROUPING_SEPARATOR;
        if (attribute == field8) {
            return "group";
        }
        field9 = NumberFormat.Field.PERCENT;
        if (attribute == field9) {
            return "percentSign";
        }
        field10 = NumberFormat.Field.PERMILLE;
        if (attribute == field10) {
            return "permilleSign";
        }
        field11 = NumberFormat.Field.CURRENCY;
        return attribute == field11 ? "currency" : attribute.toString().equals("android.icu.text.NumberFormat$Field(compact)") ? "compact" : "literal";
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public AttributedCharacterIterator formatToParts(double d) {
        ULocale forLanguageTag;
        android.icu.text.NumberFormat numberFormat;
        AttributedCharacterIterator formatToCharacterIterator;
        ULocale forLanguageTag2;
        android.icu.text.NumberFormat numberFormat2;
        AttributedCharacterIterator formatToCharacterIterator2;
        ULocale uLocale;
        android.icu.text.NumberFormat numberFormat3;
        AttributedCharacterIterator formatToCharacterIterator3;
        try {
            try {
                if (ReactTextView$$ExternalSyntheticApiModelOutline0.m$1(this.mFinalFormat) && this.mMeasureUnit != null) {
                    Format format = this.mFinalFormat;
                    ReactTextView$$ExternalSyntheticApiModelOutline0.m544m();
                    return format.formatToCharacterIterator(ReactTextView$$ExternalSyntheticApiModelOutline0.m(Double.valueOf(d), this.mMeasureUnit));
                }
                return this.mFinalFormat.formatToCharacterIterator(Double.valueOf(d));
            } catch (RuntimeException unused) {
                forLanguageTag = ULocale.forLanguageTag("en");
                numberFormat = android.icu.text.NumberFormat.getInstance(forLanguageTag);
                formatToCharacterIterator = numberFormat.formatToCharacterIterator(Double.valueOf(d));
                return formatToCharacterIterator;
            }
        } catch (NumberFormatException unused2) {
            uLocale = ULocale.getDefault();
            numberFormat3 = android.icu.text.NumberFormat.getInstance(uLocale);
            formatToCharacterIterator3 = numberFormat3.formatToCharacterIterator(Double.valueOf(d));
            return formatToCharacterIterator3;
        } catch (Exception unused3) {
            forLanguageTag2 = ULocale.forLanguageTag("en");
            numberFormat2 = android.icu.text.NumberFormat.getInstance(forLanguageTag2);
            formatToCharacterIterator2 = numberFormat2.formatToCharacterIterator(Double.valueOf(d));
            return formatToCharacterIterator2;
        }
    }

    public static int getCurrencyDigits(String str) throws JSRangeErrorException {
        Currency currency;
        int defaultFractionDigits;
        try {
            currency = Currency.getInstance(str);
            defaultFractionDigits = currency.getDefaultFractionDigits();
            return defaultFractionDigits;
        } catch (IllegalArgumentException unused) {
            throw new JSRangeErrorException("Invalid currency code !");
        }
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterICU configure(ILocaleObject<?> iLocaleObject, String str, IPlatformNumberFormatter.Style style, IPlatformNumberFormatter.CurrencySign currencySign, IPlatformNumberFormatter.Notation notation, IPlatformNumberFormatter.CompactDisplay compactDisplay) throws JSRangeErrorException {
        NumberingSystem instanceByName;
        android.icu.text.NumberFormat numberFormat;
        CompactDecimalFormat.CompactStyle compactStyle;
        CompactDecimalFormat compactDecimalFormat;
        if (!str.isEmpty()) {
            try {
                instanceByName = NumberingSystem.getInstanceByName(JSObjects.getJavaString(str));
                if (instanceByName == null) {
                    throw new JSRangeErrorException("Invalid numbering system: " + str);
                }
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(JSObjects.getJavaString(str));
                iLocaleObject.setUnicodeExtensions("nu", arrayList);
            } catch (RuntimeException unused) {
                throw new JSRangeErrorException("Invalid numbering system: " + str);
            }
        }
        if (notation == IPlatformNumberFormatter.Notation.COMPACT && (style == IPlatformNumberFormatter.Style.DECIMAL || style == IPlatformNumberFormatter.Style.UNIT)) {
            if (compactDisplay == IPlatformNumberFormatter.CompactDisplay.SHORT) {
                compactStyle = CompactDecimalFormat.CompactStyle.SHORT;
            } else {
                compactStyle = CompactDecimalFormat.CompactStyle.LONG;
            }
            compactDecimalFormat = CompactDecimalFormat.getInstance(Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()), compactStyle);
            initialize(compactDecimalFormat, iLocaleObject, style);
        } else {
            numberFormat = android.icu.text.NumberFormat.getInstance(Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()), style.getInitialNumberFormatStyle(notation, currencySign));
            if (notation == IPlatformNumberFormatter.Notation.ENGINEERING) {
                numberFormat.setMaximumIntegerDigits(3);
            }
            initialize(numberFormat, iLocaleObject, style);
        }
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
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

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        NumberingSystem numberingSystem;
        String name;
        numberingSystem = NumberingSystem.getInstance(Intl$$ExternalSyntheticApiModelOutline0.m212m(iLocaleObject.getLocale()));
        name = numberingSystem.getName();
        return name;
    }
}
