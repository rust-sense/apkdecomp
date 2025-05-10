package expo.modules.kotlin.viewevent;

import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: ViewEventDelegate.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B>\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012/\u0010\u0005\u001a+\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000b¢\u0006\u0002\u0010\fJ#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u0012\u001a\u00020\u00042\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0086\u0002R7\u0010\u0005\u001a+\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00040\u00040\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lexpo/modules/kotlin/viewevent/ViewEventDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "", "view", "Landroid/view/View;", "coalescingKey", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_EVENT, "", "Lexpo/modules/kotlin/viewevent/CoalescingKey;", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "viewHolder", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getValue", "Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "thisRef", "property", "Lkotlin/reflect/KProperty;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewEventDelegate<T> {
    private final Function1<T, Short> coalescingKey;
    private final WeakReference<View> viewHolder;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewEventDelegate(View view, Function1<? super T, Short> function1) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.coalescingKey = function1;
        this.viewHolder = new WeakReference<>(view);
    }

    public final ViewEventCallback<T> getValue(View thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        View view = this.viewHolder.get();
        if (view == null) {
            throw new IllegalStateException("Can't send the '" + property.getName() + "' event from the view that is deallocated");
        }
        return new ViewEvent(property.getName(), view, this.coalescingKey);
    }
}
