package io.sentry;

import android.app.ApplicationExitInfo;
import android.content.res.loader.ResourcesLoader;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.RenderNode;
import android.graphics.drawable.AdaptiveIconDrawable;
import java.nio.file.FileSystemException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class SentryWrapper$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ ApplicationExitInfo m(Object obj) {
        return (ApplicationExitInfo) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ ResourcesLoader m780m() {
        return new ResourcesLoader();
    }

    public static /* synthetic */ BlendModeColorFilter m(int i, BlendMode blendMode) {
        return new BlendModeColorFilter(i, blendMode);
    }

    public static /* synthetic */ RenderNode m(String str) {
        return new RenderNode(str);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ FileSystemException m787m(Object obj) {
        return (FileSystemException) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ FileSystemException m788m(String str) {
        return new FileSystemException(str);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ FileVisitResult m790m(Object obj) {
        return (FileVisitResult) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ FileVisitor m791m(Object obj) {
        return (FileVisitor) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Path m793m(Object obj) {
        return (Path) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m796m() {
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m799m(Object obj) {
        return obj instanceof AdaptiveIconDrawable;
    }

    public static /* synthetic */ void m$1() {
    }

    public static /* synthetic */ void m$2() {
    }
}
