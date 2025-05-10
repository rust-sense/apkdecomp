package expo.modules.gl;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.TextureView;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.viewevent.ViewEventCallback;
import expo.modules.kotlin.viewevent.ViewEventDelegate;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: GLView.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0002J \u0010\"\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000fH\u0016J\u0010\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020!H\u0016J \u0010'\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000fH\u0016J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020!H\u0016J\u0010\u0010)\u001a\u00020\u001e2\b\u0010*\u001a\u0004\u0018\u00010+R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lexpo/modules/gl/GLView;", "Landroid/view/TextureView;", "Landroid/view/TextureView$SurfaceTextureListener;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;)V", "enableExperimentalWorkletSupport", "", "getEnableExperimentalWorkletSupport", "()Z", "setEnableExperimentalWorkletSupport", "(Z)V", "exglContextId", "", "getExglContextId", "()I", "glContext", "Lexpo/modules/gl/GLContext;", "onSurfaceCreate", "Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "Lexpo/modules/gl/OnSurfaceCreateRecord;", "getOnSurfaceCreate", "()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "onSurfaceCreate$delegate", "Lexpo/modules/kotlin/viewevent/ViewEventDelegate;", "onSurfaceCreateWasCalled", "onSurfaceTextureWasCalledWithZeroSize", "flush", "", "initializeSurfaceInGLContext", "surfaceTexture", "Landroid/graphics/SurfaceTexture;", "onSurfaceTextureAvailable", "width", "height", "onSurfaceTextureDestroyed", "surface", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "runOnGLThread", "r", "Ljava/lang/Runnable;", "expo-gl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GLView extends TextureView implements TextureView.SurfaceTextureListener {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(GLView.class, "onSurfaceCreate", "getOnSurfaceCreate()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0))};
    private boolean enableExperimentalWorkletSupport;
    private GLContext glContext;

    /* renamed from: onSurfaceCreate$delegate, reason: from kotlin metadata */
    private final ViewEventDelegate onSurfaceCreate;
    private boolean onSurfaceCreateWasCalled;
    private boolean onSurfaceTextureWasCalledWithZeroSize;

    public final boolean getEnableExperimentalWorkletSupport() {
        return this.enableExperimentalWorkletSupport;
    }

    public final ViewEventCallback<OnSurfaceCreateRecord> getOnSurfaceCreate() {
        return this.onSurfaceCreate.getValue(this, $$delegatedProperties[0]);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    public final void setEnableExperimentalWorkletSupport(boolean z) {
        this.enableExperimentalWorkletSupport = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GLView(Context context, AppContext appContext) {
        super(context);
        Object obj;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Iterator<T> it = appContext.getRegistry().getRegistry().values().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((ModuleHolder) obj).getModule() instanceof GLObjectManagerModule) {
                    break;
                }
            }
        }
        ModuleHolder moduleHolder = (ModuleHolder) obj;
        Module module = moduleHolder != null ? moduleHolder.getModule() : null;
        this.glContext = new GLContext((GLObjectManagerModule) (module instanceof GLObjectManagerModule ? module : null));
        this.onSurfaceCreate = new ViewEventDelegate(this, null);
        setSurfaceTextureListener(this);
        setOpaque(false);
    }

    private final int getExglContextId() {
        return this.glContext.getContextId();
    }

    public final void runOnGLThread(Runnable r) {
        this.glContext.runAsync(r);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public synchronized void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Intrinsics.checkNotNullParameter(surfaceTexture, "surfaceTexture");
        if (!this.onSurfaceCreateWasCalled) {
            if (width == 0 || height == 0) {
                this.onSurfaceTextureWasCalledWithZeroSize = true;
            }
            if (!this.onSurfaceTextureWasCalledWithZeroSize) {
                initializeSurfaceInGLContext(surfaceTexture);
            }
            this.onSurfaceCreateWasCalled = true;
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        this.glContext.destroy();
        this.onSurfaceCreateWasCalled = false;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public synchronized void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        Intrinsics.checkNotNullParameter(surfaceTexture, "surfaceTexture");
        if (this.onSurfaceTextureWasCalledWithZeroSize && (width != 0 || height != 0)) {
            initializeSurfaceInGLContext(surfaceTexture);
            this.onSurfaceTextureWasCalledWithZeroSize = false;
        }
    }

    public final void flush() {
        this.glContext.flush();
    }

    private final void initializeSurfaceInGLContext(SurfaceTexture surfaceTexture) {
        this.glContext.initialize(surfaceTexture, Boolean.valueOf(this.enableExperimentalWorkletSupport), new Runnable() { // from class: expo.modules.gl.GLView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GLView.initializeSurfaceInGLContext$lambda$0(GLView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initializeSurfaceInGLContext$lambda$0(GLView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getOnSurfaceCreate().invoke(new OnSurfaceCreateRecord(this$0.getExglContextId()));
    }
}
