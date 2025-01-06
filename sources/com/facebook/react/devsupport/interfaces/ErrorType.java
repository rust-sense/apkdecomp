package com.facebook.react.devsupport.interfaces;

/* loaded from: classes.dex */
public enum ErrorType {
    JS("JS"),
    NATIVE("Native");

    private final String name;

    public String getName() {
        return this.name;
    }

    ErrorType(String str) {
        this.name = str;
    }
}
