package expo.modules.localization;

import android.text.TextUtils;
import androidx.core.text.util.LocalePreferences;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocalizationUtils.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0010\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f\u001a'\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00020\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\b¢\u0006\u0002\u0010\u0014\u001a\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u000e\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u001a\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0019"}, d2 = {"ISOCurrencyCodes", "", "", "getISOCurrencyCodes", "()[Ljava/lang/String;", "ISOCurrencyCodes$delegate", "Lkotlin/Lazy;", "USES_FAHRENHEIT", "", "getUSES_FAHRENHEIT", "()Ljava/util/List;", "USES_IMPERIAL", "getUSES_IMPERIAL", "getCountryCode", "locale", "Ljava/util/Locale;", "getCurrencyCode", "getLocaleNames", "kotlin.jvm.PlatformType", "locales", "(Ljava/util/List;)[Ljava/lang/String;", "getRegionCode", "getSystemProperty", "key", "getTemperatureUnit", "expo-localization_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LocalizationUtilsKt {
    private static final List<String> USES_IMPERIAL = CollectionsKt.listOf((Object[]) new String[]{"US", "LR", "MM"});
    private static final List<String> USES_FAHRENHEIT = CollectionsKt.listOf((Object[]) new String[]{"AG", "BZ", "VG", "FM", "MH", "MS", "KN", "BS", "CY", "TC", "US", "LR", "PW", "KY"});
    private static final Lazy ISOCurrencyCodes$delegate = LazyKt.lazy(new Function0<String[]>() { // from class: expo.modules.localization.LocalizationUtilsKt$ISOCurrencyCodes$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
            Intrinsics.checkNotNullExpressionValue(availableCurrencies, "getAvailableCurrencies(...)");
            Set<Currency> set = availableCurrencies;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                String currencyCode = ((Currency) it.next()).getCurrencyCode();
                Intrinsics.checkNotNull(currencyCode, "null cannot be cast to non-null type kotlin.String");
                arrayList.add(currencyCode);
            }
            return (String[]) arrayList.toArray(new String[0]);
        }
    });

    public static final List<String> getUSES_FAHRENHEIT() {
        return USES_FAHRENHEIT;
    }

    public static final List<String> getUSES_IMPERIAL() {
        return USES_IMPERIAL;
    }

    public static final String[] getISOCurrencyCodes() {
        return (String[]) ISOCurrencyCodes$delegate.getValue();
    }

    public static final String[] getLocaleNames(List<Locale> locales) {
        Intrinsics.checkNotNullParameter(locales, "locales");
        List<Locale> list = locales;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Locale) it.next()).toLanguageTag());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final String getCountryCode(Locale locale) {
        Object m868constructorimpl;
        Intrinsics.checkNotNullParameter(locale, "locale");
        try {
            Result.Companion companion = Result.INSTANCE;
            String country = locale.getCountry();
            if (TextUtils.isEmpty(country)) {
                country = null;
            }
            m868constructorimpl = Result.m868constructorimpl(country);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m868constructorimpl = Result.m868constructorimpl(ResultKt.createFailure(th));
        }
        return (String) (Result.m874isFailureimpl(m868constructorimpl) ? null : m868constructorimpl);
    }

    public static final String getSystemProperty(String key) {
        Object m868constructorimpl;
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            Result.Companion companion = Result.INSTANCE;
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod("get", String.class).invoke(cls, key);
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.String");
            m868constructorimpl = Result.m868constructorimpl((String) invoke);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m868constructorimpl = Result.m868constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m874isFailureimpl(m868constructorimpl)) {
            m868constructorimpl = null;
        }
        String str = (String) m868constructorimpl;
        return str == null ? "" : str;
    }

    public static final String getCurrencyCode(Locale locale) {
        Object m868constructorimpl;
        Intrinsics.checkNotNullParameter(locale, "locale");
        try {
            Result.Companion companion = Result.INSTANCE;
            Currency currency = Currency.getInstance(locale);
            m868constructorimpl = Result.m868constructorimpl(currency != null ? currency.getCurrencyCode() : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m868constructorimpl = Result.m868constructorimpl(ResultKt.createFailure(th));
        }
        return (String) (Result.m874isFailureimpl(m868constructorimpl) ? null : m868constructorimpl);
    }

    public static final String getRegionCode(Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String systemProperty = getSystemProperty("ro.miui.region");
        if (systemProperty.length() == 0) {
            systemProperty = getCountryCode(locale);
        }
        return systemProperty;
    }

    public static final String getTemperatureUnit(Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String regionCode = getRegionCode(locale);
        if (regionCode == null) {
            return null;
        }
        return USES_FAHRENHEIT.contains(regionCode) ? "fahrenheit" : LocalePreferences.TemperatureUnit.CELSIUS;
    }
}
