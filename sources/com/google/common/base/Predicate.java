package com.google.common.base;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public interface Predicate<T> {
    boolean apply(@NullableDecl T t);

    boolean equals(@NullableDecl Object obj);
}
