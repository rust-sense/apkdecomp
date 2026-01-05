package com.facebook.hermes.intl;

import android.icu.lang.UCharacter;
import android.os.Build;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class Intl {
    private static List<String> canonicalizeLocaleList(List<String> list) throws JSRangeErrorException {
        if (list.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str == null) {
                throw new JSRangeErrorException("Incorrect locale information provided");
            }
            if (str.isEmpty()) {
                throw new JSRangeErrorException("Incorrect locale information provided");
            }
            String canonicalizeLocaleId = LocaleIdentifier.canonicalizeLocaleId(str);
            if (!canonicalizeLocaleId.isEmpty() && !arrayList.contains(canonicalizeLocaleId)) {
                arrayList.add(canonicalizeLocaleId);
            }
        }
        return arrayList;
    }

    public static List<String> getCanonicalLocales(List<String> list) throws JSRangeErrorException {
        return canonicalizeLocaleList(list);
    }

    public static String toLocaleLowerCase(List<String> list, String str) throws JSRangeErrorException {
        String lowerCase;
        String[] strArr = new String[list.size()];
        if (Build.VERSION.SDK_INT >= 24) {
            lowerCase = UCharacter.toLowerCase(Intl$$ExternalSyntheticApiModelOutline0.m212m(LocaleMatcher.bestFitMatch((String[]) list.toArray(strArr)).matchedLocale.getLocale()), str);
            return lowerCase;
        }
        return str.toLowerCase((Locale) LocaleMatcher.lookupMatch((String[]) list.toArray(strArr)).matchedLocale.getLocale());
    }

    public static String toLocaleUpperCase(List<String> list, String str) throws JSRangeErrorException {
        String upperCase;
        String[] strArr = new String[list.size()];
        if (Build.VERSION.SDK_INT >= 24) {
            upperCase = UCharacter.toUpperCase(Intl$$ExternalSyntheticApiModelOutline0.m212m(LocaleMatcher.bestFitMatch((String[]) list.toArray(strArr)).matchedLocale.getLocale()), str);
            return upperCase;
        }
        return str.toUpperCase((Locale) LocaleMatcher.lookupMatch((String[]) list.toArray(strArr)).matchedLocale.getLocale());
    }
}
