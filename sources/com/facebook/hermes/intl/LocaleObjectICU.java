package com.facebook.hermes.intl;

import android.icu.util.ULocale;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class LocaleObjectICU implements ILocaleObject<ULocale> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean mIsDirty;
    private ULocale m_icuLocale;
    private ULocale.Builder m_icuLocaleBuilder;

    private LocaleObjectICU(ULocale uLocale) {
        this.m_icuLocaleBuilder = null;
        this.mIsDirty = false;
        this.m_icuLocale = uLocale;
    }

    private LocaleObjectICU(String str) throws JSRangeErrorException {
        this.m_icuLocale = null;
        this.m_icuLocaleBuilder = null;
        this.mIsDirty = false;
        ULocale.Builder m209m = Intl$$ExternalSyntheticApiModelOutline0.m209m();
        this.m_icuLocaleBuilder = m209m;
        try {
            m209m.setLanguageTag(str);
            this.mIsDirty = true;
        } catch (RuntimeException e) {
            throw new JSRangeErrorException(e.getMessage());
        }
    }

    private void ensureNotDirty() throws JSRangeErrorException {
        ULocale build;
        if (this.mIsDirty) {
            try {
                build = this.m_icuLocaleBuilder.build();
                this.m_icuLocale = build;
                this.mIsDirty = false;
            } catch (RuntimeException e) {
                throw new JSRangeErrorException(e.getMessage());
            }
        }
    }

    @Override // com.facebook.hermes.intl.ILocaleObject
    public ArrayList<String> getUnicodeExtensions(String str) throws JSRangeErrorException {
        String keywordValue;
        ensureNotDirty();
        String CanonicalKeyToICUKey = UnicodeExtensionKeys.CanonicalKeyToICUKey(str);
        ArrayList<String> arrayList = new ArrayList<>();
        keywordValue = this.m_icuLocale.getKeywordValue(CanonicalKeyToICUKey);
        if (keywordValue != null && !keywordValue.isEmpty()) {
            Collections.addAll(arrayList, keywordValue.split("-|_"));
        }
        return arrayList;
    }

    @Override // com.facebook.hermes.intl.ILocaleObject
    public HashMap<String, String> getUnicodeExtensions() throws JSRangeErrorException {
        Iterator keywords;
        String keywordValue;
        ensureNotDirty();
        HashMap<String, String> hashMap = new HashMap<>();
        keywords = this.m_icuLocale.getKeywords();
        if (keywords != null) {
            while (keywords.hasNext()) {
                String str = (String) keywords.next();
                String ICUKeyToCanonicalKey = UnicodeExtensionKeys.ICUKeyToCanonicalKey(str);
                keywordValue = this.m_icuLocale.getKeywordValue(str);
                hashMap.put(ICUKeyToCanonicalKey, keywordValue);
            }
        }
        return hashMap;
    }

    @Override // com.facebook.hermes.intl.ILocaleObject
    public void setUnicodeExtensions(String str, ArrayList<String> arrayList) throws JSRangeErrorException {
        ULocale.Builder locale;
        ensureNotDirty();
        if (this.m_icuLocaleBuilder == null) {
            locale = Intl$$ExternalSyntheticApiModelOutline0.m209m().setLocale(this.m_icuLocale);
            this.m_icuLocaleBuilder = locale;
        }
        try {
            this.m_icuLocaleBuilder.setUnicodeLocaleKeyword(str, TextUtils.join("-", arrayList));
            this.mIsDirty = true;
        } catch (RuntimeException e) {
            throw new JSRangeErrorException(e.getMessage());
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.hermes.intl.ILocaleObject
    public ULocale getLocale() throws JSRangeErrorException {
        ensureNotDirty();
        return this.m_icuLocale;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.hermes.intl.ILocaleObject
    public ULocale getLocaleWithoutExtensions() throws JSRangeErrorException {
        ULocale build;
        ensureNotDirty();
        ULocale.Builder m209m = Intl$$ExternalSyntheticApiModelOutline0.m209m();
        m209m.setLocale(this.m_icuLocale);
        m209m.clearExtensions();
        build = m209m.build();
        return build;
    }

    @Override // com.facebook.hermes.intl.ILocaleObject
    public String toCanonicalTag() throws JSRangeErrorException {
        String languageTag;
        languageTag = getLocale().toLanguageTag();
        return languageTag;
    }

    @Override // com.facebook.hermes.intl.ILocaleObject
    public String toCanonicalTagWithoutExtensions() throws JSRangeErrorException {
        String languageTag;
        languageTag = getLocaleWithoutExtensions().toLanguageTag();
        return languageTag;
    }

    @Override // com.facebook.hermes.intl.ILocaleObject
    public ILocaleObject<ULocale> cloneObject() throws JSRangeErrorException {
        ensureNotDirty();
        return new LocaleObjectICU(this.m_icuLocale);
    }

    public static ILocaleObject<ULocale> createFromLocaleId(String str) throws JSRangeErrorException {
        return new LocaleObjectICU(str);
    }

    public static ILocaleObject<ULocale> createFromULocale(ULocale uLocale) {
        return new LocaleObjectICU(uLocale);
    }

    public static ILocaleObject<ULocale> createDefault() {
        ULocale.Category category;
        ULocale uLocale;
        category = ULocale.Category.FORMAT;
        uLocale = ULocale.getDefault(category);
        return new LocaleObjectICU(uLocale);
    }
}
