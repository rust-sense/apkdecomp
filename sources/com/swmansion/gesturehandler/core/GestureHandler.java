package com.swmansion.gesturehandler.core;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.swmansion.gesturehandler.core.GestureHandler;
import io.sentry.protocol.SentryThread;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: GestureHandler.kt */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b7\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 Ì\u0001*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002:\u0006Ë\u0001Ì\u0001Í\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010e\u001a\u00020fJ\u0010\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020\u001dH\u0016J\u0010\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020iH\u0002J\u0010\u0010k\u001a\u00020f2\u0006\u0010l\u001a\u00020WH\u0002J\u0010\u0010m\u001a\u00020f2\u0006\u0010l\u001a\u00020WH\u0002J*\u0010n\u001a\u00028\u00002\u0017\u0010o\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020f0p¢\u0006\u0002\bqH\u0084\bø\u0001\u0000¢\u0006\u0002\u0010rJ\u0006\u0010s\u001a\u00020fJ\u0006\u0010t\u001a\u00020fJ\b\u0010u\u001a\u00020fH\u0002J\b\u0010v\u001a\u0004\u0018\u00010\u000eJ\b\u0010w\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010x\u001a\n z*\u0004\u0018\u00010y0y2\u0006\u0010l\u001a\u00020WH\u0002J\u0010\u0010{\u001a\u00020f2\u0006\u0010j\u001a\u00020iH\u0016J\u0018\u0010|\u001a\u00020f2\u0006\u0010}\u001a\u00020\u00052\u0006\u0010~\u001a\u00020\u0005H\u0016J\u0010\u0010\u007f\u001a\u00020f2\u0006\u0010j\u001a\u00020iH\u0002J\t\u0010\u0080\u0001\u001a\u00020fH\u0016J\u0011\u0010\u0081\u0001\u001a\u00020f2\u0006\u0010j\u001a\u00020iH\u0002J\u0011\u0010\u0082\u0001\u001a\u00020f2\u0006\u0010j\u001a\u00020iH\u0002J\u0007\u0010\u0083\u0001\u001a\u00020fJ\t\u0010\u0084\u0001\u001a\u00020fH\u0002J\u0007\u0010\u0085\u0001\u001a\u00020fJ\t\u0010\u0086\u0001\u001a\u00020\u0005H\u0002J\u0018\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u00012\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u0001H\u0002J\u0019\u0010\u008b\u0001\u001a\u00020f2\u0007\u0010\u008c\u0001\u001a\u00020i2\u0007\u0010\u008d\u0001\u001a\u00020iJ\u0014\u0010\u008e\u0001\u001a\u00020\u001d2\u000b\u0010\u008f\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000J\u0012\u0010\u0090\u0001\u001a\u00020\u001d2\u0007\u0010\u0091\u0001\u001a\u00020\u0005H\u0002J\"\u0010$\u001a\u00020\u001d2\b\u0010]\u001a\u0004\u0018\u00010\\2\u0007\u0010\u0092\u0001\u001a\u00020%2\u0007\u0010\u0093\u0001\u001a\u00020%J\u0011\u0010\u0094\u0001\u001a\u00020f2\u0006\u0010}\u001a\u00020\u0005H\u0002J\u0011\u0010\u0095\u0001\u001a\u00020\u001d2\u0006\u0010j\u001a\u00020iH\u0002J\t\u0010\u0096\u0001\u001a\u00020fH\u0014J\u001a\u0010\u0097\u0001\u001a\u00020f2\u0006\u0010j\u001a\u00020i2\u0007\u0010\u008d\u0001\u001a\u00020iH\u0014J\u001a\u0010\u0098\u0001\u001a\u00020f2\u0006\u0010j\u001a\u00020i2\u0007\u0010\u008d\u0001\u001a\u00020iH\u0014J\t\u0010\u0099\u0001\u001a\u00020fH\u0014J\t\u0010\u009a\u0001\u001a\u00020fH\u0014J\u001a\u0010\u009b\u0001\u001a\u00020f2\u0006\u0010}\u001a\u00020\u00052\u0007\u0010\u009c\u0001\u001a\u00020\u0005H\u0014J\u001b\u0010\u009d\u0001\u001a\u00020f2\b\u0010]\u001a\u0004\u0018\u00010\\2\b\u0010@\u001a\u0004\u0018\u00010AJ\u0007\u0010\u009e\u0001\u001a\u00020fJ\t\u0010\u009f\u0001\u001a\u00020fH\u0016J\t\u0010 \u0001\u001a\u00020fH\u0016J\u000f\u0010¡\u0001\u001a\u00028\u0000H\u0004¢\u0006\u0003\u0010¢\u0001J\u0016\u0010£\u0001\u001a\u00028\u00002\u0007\u0010¤\u0001\u001a\u00020\u001d¢\u0006\u0003\u0010¥\u0001J\u0016\u0010¦\u0001\u001a\u00028\u00002\u0007\u0010§\u0001\u001a\u00020%¢\u0006\u0003\u0010¨\u0001JC\u0010¦\u0001\u001a\u00028\u00002\u0007\u0010©\u0001\u001a\u00020%2\u0007\u0010ª\u0001\u001a\u00020%2\u0007\u0010«\u0001\u001a\u00020%2\u0007\u0010¬\u0001\u001a\u00020%2\u0007\u0010\u00ad\u0001\u001a\u00020%2\u0007\u0010®\u0001\u001a\u00020%¢\u0006\u0003\u0010¯\u0001J\u0018\u0010°\u0001\u001a\u00028\u00002\t\u0010±\u0001\u001a\u0004\u0018\u00010\u001b¢\u0006\u0003\u0010²\u0001J\u0015\u0010³\u0001\u001a\u00028\u00002\u0006\u00105\u001a\u00020\u001d¢\u0006\u0003\u0010¥\u0001J\u0014\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00106\u001a\u00020\u0005J\u0016\u0010´\u0001\u001a\u0006\u0012\u0002\b\u00030\u00002\t\u0010µ\u0001\u001a\u0004\u0018\u00010?J\u0011\u0010¶\u0001\u001a\u00020f2\u0006\u0010j\u001a\u00020iH\u0002J\u0015\u0010·\u0001\u001a\u00028\u00002\u0006\u0010H\u001a\u00020\u001d¢\u0006\u0003\u0010¥\u0001J\u0012\u0010¸\u0001\u001a\u00020\u001d2\u0007\u0010\u008d\u0001\u001a\u00020iH\u0004J\u0016\u0010¹\u0001\u001a\u00020\u001d2\u000b\u0010º\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016J\u0016\u0010»\u0001\u001a\u00020\u001d2\u000b\u0010º\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016J\u0016\u0010¼\u0001\u001a\u00020\u001d2\u000b\u0010º\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016J\u0014\u0010½\u0001\u001a\u00020\u001d2\u000b\u0010º\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000J\u0010\u0010¾\u0001\u001a\u00020f2\u0007\u0010¿\u0001\u001a\u00020\u0005J\u0010\u0010À\u0001\u001a\u00020f2\u0007\u0010¿\u0001\u001a\u00020\u0005J\n\u0010Á\u0001\u001a\u00030Â\u0001H\u0016J\u0014\u0010Ã\u0001\u001a\u00030Ä\u00012\b\u0010Å\u0001\u001a\u00030Ä\u0001H\u0004J\u000f\u0010Æ\u0001\u001a\u00020f2\u0006\u0010j\u001a\u00020iJ\u0007\u0010Ç\u0001\u001a\u00020\u001dJ\u0017\u0010È\u0001\u001a\u00020f2\u000e\u0010É\u0001\u001a\t\u0012\u0004\u0012\u00020f0Ê\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R\u001e\u0010#\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u001e\u0010$\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u001e\u0010&\u001a\u00020%2\u0006\u0010\r\u001a\u00020%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001e\u0010)\u001a\u00020%2\u0006\u0010\r\u001a\u00020%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u000e\u0010+\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010-\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b.\u0010(R\u0011\u0010/\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b0\u0010(R\u0011\u00101\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b2\u0010(R\u0011\u00103\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b4\u0010(R\u000e\u00105\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00106\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0007\"\u0004\b8\u0010\tR\u001a\u00109\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001e\"\u0004\b;\u0010 R\u001e\u0010<\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001e\u0010F\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0007R\u000e\u0010H\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010I\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u001e\"\u0004\bK\u0010 R\u001e\u0010L\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\u0007R\u001a\u0010N\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0007\"\u0004\bP\u0010\tR\u001e\u0010Q\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0007R\u000e\u0010S\u001a\u00020TX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010U\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010W0VX\u0082\u0004¢\u0006\u0004\n\u0002\u0010XR\u001e\u0010Y\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0007R\u000e\u0010[\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010]\u001a\u0004\u0018\u00010\\2\b\u0010\r\u001a\u0004\u0018\u00010\\@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b^\u0010_R\u000e\u0010`\u001a\u00020TX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010a\u001a\u00020%2\u0006\u0010\r\u001a\u00020%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bb\u0010(R\u001e\u0010c\u001a\u00020%2\u0006\u0010\r\u001a\u00020%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bd\u0010(\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006Î\u0001"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler;", "ConcreteGestureHandlerT", "", "()V", "actionType", "", "getActionType", "()I", "setActionType", "(I)V", "activationIndex", "getActivationIndex", "setActivationIndex", "<set-?>", "Lcom/facebook/react/bridge/WritableArray;", "allTouchesPayload", "getAllTouchesPayload", "()Lcom/facebook/react/bridge/WritableArray;", "changedTouchesPayload", "getChangedTouchesPayload", "", "eventCoalescingKey", "getEventCoalescingKey", "()S", "hitSlop", "", "interactionController", "Lcom/swmansion/gesturehandler/core/GestureHandlerInteractionController;", "isActive", "", "()Z", "setActive", "(Z)V", "isAwaiting", "setAwaiting", "isEnabled", "isWithinBounds", "", "lastAbsolutePositionX", "getLastAbsolutePositionX", "()F", "lastAbsolutePositionY", "getLastAbsolutePositionY", "lastEventOffsetX", "lastEventOffsetY", "lastPositionInWindowX", "getLastPositionInWindowX", "lastPositionInWindowY", "getLastPositionInWindowY", "lastRelativePositionX", "getLastRelativePositionX", "lastRelativePositionY", "getLastRelativePositionY", "manualActivation", "mouseButton", "getMouseButton", "setMouseButton", "needsPointerData", "getNeedsPointerData", "setNeedsPointerData", "numberOfPointers", "getNumberOfPointers", "onTouchEventListener", "Lcom/swmansion/gesturehandler/core/OnTouchEventListener;", "orchestrator", "Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "getOrchestrator", "()Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "setOrchestrator", "(Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;)V", "pointerType", "getPointerType", "shouldCancelWhenOutside", "shouldResetProgress", "getShouldResetProgress", "setShouldResetProgress", SentryThread.JsonKeys.STATE, "getState", ViewHierarchyNode.JsonKeys.TAG, "getTag", "setTag", "touchEventType", "getTouchEventType", "trackedPointerIDs", "", "trackedPointers", "", "Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "[Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "trackedPointersCount", "getTrackedPointersCount", "trackedPointersIDsCount", "Landroid/view/View;", "view", "getView", "()Landroid/view/View;", "windowOffset", ViewHierarchyNode.JsonKeys.X, "getX", ViewHierarchyNode.JsonKeys.Y, "getY", "activate", "", "force", "adaptEvent", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "addChangedPointer", "pointerData", "addPointerToAll", "applySelf", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Lcom/swmansion/gesturehandler/core/GestureHandler;", "begin", "cancel", "cancelPointers", "consumeAllTouchesPayload", "consumeChangedTouchesPayload", "createPointerData", "Lcom/facebook/react/bridge/WritableMap;", "kotlin.jvm.PlatformType", "dispatchHandlerUpdate", "dispatchStateChange", "newState", "prevState", "dispatchTouchDownEvent", "dispatchTouchEvent", "dispatchTouchMoveEvent", "dispatchTouchUpEvent", ViewProps.END, "extractAllPointersData", "fail", "findNextLocalPointerId", "getActivity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "handle", "transformedEvent", "sourceEvent", "hasCommonPointers", "other", "isButtonInConfig", "clickedButton", "posX", "posY", "moveToState", "needAdapt", "onCancel", "onHandle", "onHandleHover", "onPrepare", "onReset", "onStateChange", "previousState", "prepare", "reset", "resetConfig", "resetProgress", "self", "()Lcom/swmansion/gesturehandler/core/GestureHandler;", "setEnabled", "enabled", "(Z)Lcom/swmansion/gesturehandler/core/GestureHandler;", "setHitSlop", ViewProps.PADDING, "(F)Lcom/swmansion/gesturehandler/core/GestureHandler;", "leftPad", "topPad", "rightPad", "bottomPad", "width", "height", "(FFFFFF)Lcom/swmansion/gesturehandler/core/GestureHandler;", "setInteractionController", "controller", "(Lcom/swmansion/gesturehandler/core/GestureHandlerInteractionController;)Lcom/swmansion/gesturehandler/core/GestureHandler;", "setManualActivation", "setOnTouchEventListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setPointerType", "setShouldCancelWhenOutside", "shouldActivateWithMouse", "shouldBeCancelledBy", "handler", "shouldRecognizeSimultaneously", "shouldRequireToWaitForFailure", "shouldWaitForHandlerFailure", "startTrackingPointer", "pointerId", "stopTrackingPointer", "toString", "", "transformPoint", "Landroid/graphics/PointF;", "point", "updatePointerData", "wantEvents", "withMarkedAsInBounds", "closure", "Lkotlin/Function0;", "AdaptEventException", "Companion", "PointerData", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class GestureHandler<ConcreteGestureHandlerT extends GestureHandler<ConcreteGestureHandlerT>> {
    public static final int ACTION_TYPE_JS_FUNCTION_NEW_API = 4;
    public static final int ACTION_TYPE_JS_FUNCTION_OLD_API = 3;
    public static final int ACTION_TYPE_NATIVE_ANIMATED_EVENT = 2;
    public static final int ACTION_TYPE_REANIMATED_WORKLET = 1;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DIRECTION_DOWN = 8;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 4;
    private static final int HIT_SLOP_BOTTOM_IDX = 3;
    private static final int HIT_SLOP_HEIGHT_IDX = 5;
    private static final int HIT_SLOP_LEFT_IDX = 0;
    public static final float HIT_SLOP_NONE = Float.NaN;
    private static final int HIT_SLOP_RIGHT_IDX = 2;
    private static final int HIT_SLOP_TOP_IDX = 1;
    private static final int HIT_SLOP_WIDTH_IDX = 4;
    private static final int MAX_POINTERS_COUNT = 12;
    public static final int POINTER_TYPE_MOUSE = 2;
    public static final int POINTER_TYPE_OTHER = 3;
    public static final int POINTER_TYPE_STYLUS = 1;
    public static final int POINTER_TYPE_TOUCH = 0;
    public static final int STATE_ACTIVE = 4;
    public static final int STATE_BEGAN = 2;
    public static final int STATE_CANCELLED = 3;
    public static final int STATE_END = 5;
    public static final int STATE_FAILED = 1;
    public static final int STATE_UNDETERMINED = 0;
    private static short nextEventCoalescingKey;
    private static MotionEvent.PointerCoords[] pointerCoords;
    private static MotionEvent.PointerProperties[] pointerProps;
    private int actionType;
    private int activationIndex;
    private WritableArray allTouchesPayload;
    private WritableArray changedTouchesPayload;
    private short eventCoalescingKey;
    private float[] hitSlop;
    private GestureHandlerInteractionController interactionController;
    private boolean isActive;
    private boolean isAwaiting;
    private boolean isEnabled;
    private boolean isWithinBounds;
    private float lastAbsolutePositionX;
    private float lastAbsolutePositionY;
    private float lastEventOffsetX;
    private float lastEventOffsetY;
    private boolean manualActivation;
    private int mouseButton;
    private boolean needsPointerData;
    private int numberOfPointers;
    private OnTouchEventListener onTouchEventListener;
    private GestureHandlerOrchestrator orchestrator;
    private int pointerType;
    private boolean shouldCancelWhenOutside;
    private boolean shouldResetProgress;
    private int state;
    private int tag;
    private int touchEventType;
    private final int[] trackedPointerIDs = new int[12];
    private final PointerData[] trackedPointers;
    private int trackedPointersCount;
    private int trackedPointersIDsCount;
    private View view;
    private final int[] windowOffset;
    private float x;
    private float y;

    private final boolean isButtonInConfig(int clickedButton) {
        int i = this.mouseButton;
        return i == 0 ? clickedButton == 1 : (clickedButton & i) != 0;
    }

    public final WritableArray consumeAllTouchesPayload() {
        WritableArray writableArray = this.allTouchesPayload;
        this.allTouchesPayload = null;
        return writableArray;
    }

    public final WritableArray consumeChangedTouchesPayload() {
        WritableArray writableArray = this.changedTouchesPayload;
        this.changedTouchesPayload = null;
        return writableArray;
    }

    public final int getActionType() {
        return this.actionType;
    }

    public final int getActivationIndex() {
        return this.activationIndex;
    }

    public final WritableArray getAllTouchesPayload() {
        return this.allTouchesPayload;
    }

    public final WritableArray getChangedTouchesPayload() {
        return this.changedTouchesPayload;
    }

    public final short getEventCoalescingKey() {
        return this.eventCoalescingKey;
    }

    public final float getLastAbsolutePositionX() {
        return this.lastAbsolutePositionX;
    }

    public final float getLastAbsolutePositionY() {
        return this.lastAbsolutePositionY;
    }

    public final float getLastRelativePositionX() {
        return this.lastAbsolutePositionX;
    }

    public final float getLastRelativePositionY() {
        return this.lastAbsolutePositionY;
    }

    protected final int getMouseButton() {
        return this.mouseButton;
    }

    public final boolean getNeedsPointerData() {
        return this.needsPointerData;
    }

    public final int getNumberOfPointers() {
        return this.numberOfPointers;
    }

    protected final GestureHandlerOrchestrator getOrchestrator() {
        return this.orchestrator;
    }

    public final int getPointerType() {
        return this.pointerType;
    }

    public final boolean getShouldResetProgress() {
        return this.shouldResetProgress;
    }

    public final int getState() {
        return this.state;
    }

    public final int getTag() {
        return this.tag;
    }

    public final int getTouchEventType() {
        return this.touchEventType;
    }

    public final int getTrackedPointersCount() {
        return this.trackedPointersCount;
    }

    public final View getView() {
        return this.view;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    /* renamed from: isActive, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    /* renamed from: isAwaiting, reason: from getter */
    public final boolean getIsAwaiting() {
        return this.isAwaiting;
    }

    /* renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    /* renamed from: isWithinBounds, reason: from getter */
    public final boolean getIsWithinBounds() {
        return this.isWithinBounds;
    }

    protected void onCancel() {
    }

    protected void onHandleHover(MotionEvent event, MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
    }

    protected void onPrepare() {
    }

    protected void onReset() {
    }

    protected void onStateChange(int newState, int previousState) {
    }

    public void resetConfig() {
        this.needsPointerData = false;
        this.manualActivation = false;
        this.shouldCancelWhenOutside = false;
        this.isEnabled = true;
        this.hitSlop = null;
    }

    public void resetProgress() {
    }

    public final void setActionType(int i) {
        this.actionType = i;
    }

    public final void setActivationIndex(int i) {
        this.activationIndex = i;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
    }

    public final void setAwaiting(boolean z) {
        this.isAwaiting = z;
    }

    /* renamed from: setMouseButton, reason: collision with other method in class */
    protected final void m600setMouseButton(int i) {
        this.mouseButton = i;
    }

    public final void setNeedsPointerData(boolean z) {
        this.needsPointerData = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final GestureHandler<?> setOnTouchEventListener(OnTouchEventListener listener) {
        this.onTouchEventListener = listener;
        return this;
    }

    protected final void setOrchestrator(GestureHandlerOrchestrator gestureHandlerOrchestrator) {
        this.orchestrator = gestureHandlerOrchestrator;
    }

    public final void setShouldResetProgress(boolean z) {
        this.shouldResetProgress = z;
    }

    public final void setTag(int i) {
        this.tag = i;
    }

    public final boolean wantEvents() {
        int i;
        return (!this.isEnabled || (i = this.state) == 1 || i == 3 || i == 5 || this.trackedPointersIDsCount <= 0) ? false : true;
    }

    public GestureHandler() {
        int[] iArr = new int[2];
        for (int i = 0; i < 2; i++) {
            iArr[i] = 0;
        }
        this.windowOffset = iArr;
        this.isEnabled = true;
        PointerData[] pointerDataArr = new PointerData[12];
        for (int i2 = 0; i2 < 12; i2++) {
            pointerDataArr[i2] = null;
        }
        this.trackedPointers = pointerDataArr;
        this.pointerType = 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ConcreteGestureHandlerT self() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type ConcreteGestureHandlerT of com.swmansion.gesturehandler.core.GestureHandler");
        return this;
    }

    protected final ConcreteGestureHandlerT applySelf(Function1<? super ConcreteGestureHandlerT, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        GestureHandler self = self();
        block.invoke(self);
        return self;
    }

    public void dispatchStateChange(int newState, int prevState) {
        OnTouchEventListener onTouchEventListener = this.onTouchEventListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onStateChange(self(), newState, prevState);
        }
    }

    public void dispatchHandlerUpdate(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        OnTouchEventListener onTouchEventListener = this.onTouchEventListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onHandlerUpdate(self(), event);
        }
    }

    public void dispatchTouchEvent() {
        OnTouchEventListener onTouchEventListener;
        if (this.changedTouchesPayload == null || (onTouchEventListener = this.onTouchEventListener) == null) {
            return;
        }
        onTouchEventListener.onTouchEvent(self());
    }

    public final boolean hasCommonPointers(GestureHandler<?> other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int length = this.trackedPointerIDs.length;
        for (int i = 0; i < length; i++) {
            if (this.trackedPointerIDs[i] != -1 && other.trackedPointerIDs[i] != -1) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setEnabled$lambda$3$lambda$2(GestureHandler this_applySelf) {
        Intrinsics.checkNotNullParameter(this_applySelf, "$this_applySelf");
        this_applySelf.cancel();
    }

    public final ConcreteGestureHandlerT setHitSlop(float padding) {
        return setHitSlop(padding, padding, padding, padding, Float.NaN, Float.NaN);
    }

    public final GestureHandler<ConcreteGestureHandlerT> setMouseButton(int mouseButton) {
        this.mouseButton = mouseButton;
        return this;
    }

    public final void prepare(View view, GestureHandlerOrchestrator orchestrator) {
        if (this.view != null || this.orchestrator != null) {
            throw new IllegalStateException("Already prepared or hasn't been reset".toString());
        }
        Arrays.fill(this.trackedPointerIDs, -1);
        this.trackedPointersIDsCount = 0;
        this.state = 0;
        this.view = view;
        this.orchestrator = orchestrator;
        Activity activity = getActivity(view != null ? view.getContext() : null);
        View findViewById = activity != null ? activity.findViewById(R.id.content) : null;
        if (findViewById != null) {
            findViewById.getLocationOnScreen(this.windowOffset);
        } else {
            int[] iArr = this.windowOffset;
            iArr[0] = 0;
            iArr[1] = 0;
        }
        onPrepare();
    }

    private final Activity getActivity(Context context) {
        if (context instanceof ReactContext) {
            return ((ReactContext) context).getCurrentActivity();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private final int findNextLocalPointerId() {
        int[] iArr;
        int i = 0;
        while (i < this.trackedPointersIDsCount) {
            int i2 = 0;
            while (true) {
                iArr = this.trackedPointerIDs;
                if (i2 >= iArr.length || iArr[i2] == i) {
                    break;
                }
                i2++;
            }
            if (i2 == iArr.length) {
                return i;
            }
            i++;
        }
        return i;
    }

    public final void startTrackingPointer(int pointerId) {
        int[] iArr = this.trackedPointerIDs;
        if (iArr[pointerId] == -1) {
            iArr[pointerId] = findNextLocalPointerId();
            this.trackedPointersIDsCount++;
        }
    }

    public final void stopTrackingPointer(int pointerId) {
        int[] iArr = this.trackedPointerIDs;
        if (iArr[pointerId] != -1) {
            iArr[pointerId] = -1;
            this.trackedPointersIDsCount--;
        }
    }

    private final boolean needAdapt(MotionEvent event) {
        if (event.getPointerCount() != this.trackedPointersIDsCount) {
            return true;
        }
        int length = this.trackedPointerIDs.length;
        for (int i = 0; i < length; i++) {
            int i2 = this.trackedPointerIDs[i];
            if (i2 != -1 && i2 != i) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b6 A[EDGE_INSN: B:34:0x00b6->B:35:0x00b6 BREAK  A[LOOP:0: B:13:0x006d->B:31:0x00b3], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e0  */
    /* JADX WARN: Type inference failed for: r3v6, types: [android.view.MotionEvent$PointerProperties[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final android.view.MotionEvent adaptEvent(android.view.MotionEvent r26) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandler.adaptEvent(android.view.MotionEvent):android.view.MotionEvent");
    }

    /* compiled from: GestureHandler.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B%\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$AdaptEventException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handler", "Lcom/swmansion/gesturehandler/core/GestureHandler;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "e", "Ljava/lang/IllegalArgumentException;", "Lkotlin/IllegalArgumentException;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Landroid/view/MotionEvent;Ljava/lang/IllegalArgumentException;)V", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class AdaptEventException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AdaptEventException(GestureHandler<?> handler, MotionEvent event, IllegalArgumentException e) {
            super(StringsKt.trimIndent("\n    handler: " + Reflection.getOrCreateKotlinClass(handler.getClass()).getSimpleName() + "\n    state: " + handler.getState() + "\n    view: " + handler.getView() + "\n    orchestrator: " + handler.getOrchestrator() + "\n    isEnabled: " + handler.getIsEnabled() + "\n    isActive: " + handler.getIsActive() + "\n    isAwaiting: " + handler.getIsAwaiting() + "\n    trackedPointersCount: " + ((GestureHandler) handler).trackedPointersIDsCount + "\n    trackedPointers: " + ArraysKt.joinToString$default(((GestureHandler) handler).trackedPointerIDs, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + "\n    while handling event: " + event + "\n    "), e);
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(e, "e");
        }
    }

    public final void handle(MotionEvent transformedEvent, MotionEvent sourceEvent) {
        int i;
        Intrinsics.checkNotNullParameter(transformedEvent, "transformedEvent");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        if (!this.isEnabled || (i = this.state) == 3 || i == 1 || i == 5 || this.trackedPointersIDsCount < 1) {
            return;
        }
        try {
            MotionEvent adaptEvent = adaptEvent(sourceEvent);
            MotionEvent motionEvent = new MotionEvent[]{adaptEvent(transformedEvent), adaptEvent}[0];
            this.x = motionEvent.getX();
            this.y = motionEvent.getY();
            this.numberOfPointers = motionEvent.getPointerCount();
            boolean isWithinBounds = isWithinBounds(this.view, this.x, this.y);
            this.isWithinBounds = isWithinBounds;
            if (this.shouldCancelWhenOutside && !isWithinBounds) {
                int i2 = this.state;
                if (i2 == 4) {
                    cancel();
                    return;
                } else {
                    if (i2 == 2) {
                        fail();
                        return;
                    }
                    return;
                }
            }
            this.lastAbsolutePositionX = GestureUtils.INSTANCE.getLastPointerX(motionEvent, true);
            this.lastAbsolutePositionY = GestureUtils.INSTANCE.getLastPointerY(motionEvent, true);
            this.lastEventOffsetX = motionEvent.getRawX() - motionEvent.getX();
            this.lastEventOffsetY = motionEvent.getRawY() - motionEvent.getY();
            if (sourceEvent.getAction() == 0 || sourceEvent.getAction() == 9 || sourceEvent.getAction() == 7) {
                setPointerType(sourceEvent);
            }
            if (sourceEvent.getAction() == 9 || sourceEvent.getAction() == 7 || sourceEvent.getAction() == 10) {
                onHandleHover(motionEvent, adaptEvent);
            } else {
                onHandle(motionEvent, adaptEvent);
            }
            if (!Intrinsics.areEqual(motionEvent, transformedEvent)) {
                motionEvent.recycle();
            }
            if (Intrinsics.areEqual(adaptEvent, sourceEvent)) {
                return;
            }
            adaptEvent.recycle();
        } catch (AdaptEventException unused) {
            fail();
        }
    }

    private final void dispatchTouchDownEvent(MotionEvent event) {
        this.changedTouchesPayload = null;
        this.touchEventType = 1;
        int pointerId = event.getPointerId(event.getActionIndex());
        this.trackedPointers[pointerId] = new PointerData(pointerId, event.getX(event.getActionIndex()), event.getY(event.getActionIndex()), (event.getX(event.getActionIndex()) + (event.getRawX() - event.getX())) - this.windowOffset[0], (event.getY(event.getActionIndex()) + (event.getRawY() - event.getY())) - this.windowOffset[1]);
        this.trackedPointersCount++;
        PointerData pointerData = this.trackedPointers[pointerId];
        Intrinsics.checkNotNull(pointerData);
        addChangedPointer(pointerData);
        extractAllPointersData();
        dispatchTouchEvent();
    }

    private final void dispatchTouchUpEvent(MotionEvent event) {
        extractAllPointersData();
        this.changedTouchesPayload = null;
        this.touchEventType = 3;
        int pointerId = event.getPointerId(event.getActionIndex());
        this.trackedPointers[pointerId] = new PointerData(pointerId, event.getX(event.getActionIndex()), event.getY(event.getActionIndex()), (event.getX(event.getActionIndex()) + (event.getRawX() - event.getX())) - this.windowOffset[0], (event.getY(event.getActionIndex()) + (event.getRawY() - event.getY())) - this.windowOffset[1]);
        PointerData pointerData = this.trackedPointers[pointerId];
        Intrinsics.checkNotNull(pointerData);
        addChangedPointer(pointerData);
        this.trackedPointers[pointerId] = null;
        this.trackedPointersCount--;
        dispatchTouchEvent();
    }

    private final void dispatchTouchMoveEvent(MotionEvent event) {
        this.changedTouchesPayload = null;
        this.touchEventType = 2;
        float rawX = event.getRawX() - event.getX();
        float rawY = event.getRawY() - event.getY();
        int pointerCount = event.getPointerCount();
        int i = 0;
        for (int i2 = 0; i2 < pointerCount; i2++) {
            PointerData pointerData = this.trackedPointers[event.getPointerId(i2)];
            if (pointerData != null && (pointerData.getX() != event.getX(i2) || pointerData.getY() != event.getY(i2))) {
                pointerData.setX(event.getX(i2));
                pointerData.setY(event.getY(i2));
                pointerData.setAbsoluteX((event.getX(i2) + rawX) - this.windowOffset[0]);
                pointerData.setAbsoluteY((event.getY(i2) + rawY) - this.windowOffset[1]);
                addChangedPointer(pointerData);
                i++;
            }
        }
        if (i > 0) {
            extractAllPointersData();
            dispatchTouchEvent();
        }
    }

    public final void updatePointerData(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getActionMasked() == 0 || event.getActionMasked() == 5) {
            dispatchTouchDownEvent(event);
            dispatchTouchMoveEvent(event);
        } else if (event.getActionMasked() == 1 || event.getActionMasked() == 6) {
            dispatchTouchMoveEvent(event);
            dispatchTouchUpEvent(event);
        } else if (event.getActionMasked() == 2) {
            dispatchTouchMoveEvent(event);
        }
    }

    private final void extractAllPointersData() {
        this.allTouchesPayload = null;
        for (PointerData pointerData : this.trackedPointers) {
            if (pointerData != null) {
                addPointerToAll(pointerData);
            }
        }
    }

    private final void cancelPointers() {
        this.touchEventType = 4;
        this.changedTouchesPayload = null;
        extractAllPointersData();
        for (PointerData pointerData : this.trackedPointers) {
            if (pointerData != null) {
                addChangedPointer(pointerData);
            }
        }
        this.trackedPointersCount = 0;
        ArraysKt.fill$default(this.trackedPointers, (Object) null, 0, 0, 6, (Object) null);
        dispatchTouchEvent();
    }

    private final void addChangedPointer(PointerData pointerData) {
        if (this.changedTouchesPayload == null) {
            this.changedTouchesPayload = Arguments.createArray();
        }
        WritableArray writableArray = this.changedTouchesPayload;
        Intrinsics.checkNotNull(writableArray);
        writableArray.pushMap(createPointerData(pointerData));
    }

    private final void addPointerToAll(PointerData pointerData) {
        if (this.allTouchesPayload == null) {
            this.allTouchesPayload = Arguments.createArray();
        }
        WritableArray writableArray = this.allTouchesPayload;
        Intrinsics.checkNotNull(writableArray);
        writableArray.pushMap(createPointerData(pointerData));
    }

    private final WritableMap createPointerData(PointerData pointerData) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", pointerData.getPointerId());
        createMap.putDouble(ViewHierarchyNode.JsonKeys.X, PixelUtil.toDIPFromPixel(pointerData.getX()));
        createMap.putDouble(ViewHierarchyNode.JsonKeys.Y, PixelUtil.toDIPFromPixel(pointerData.getY()));
        createMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(pointerData.getAbsoluteX()));
        createMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(pointerData.getAbsoluteY()));
        return createMap;
    }

    private final void moveToState(int newState) {
        UiThreadUtil.assertOnUiThread();
        if (this.state == newState) {
            return;
        }
        if (this.trackedPointersCount > 0 && (newState == 5 || newState == 3 || newState == 1)) {
            cancelPointers();
        }
        int i = this.state;
        this.state = newState;
        if (newState == 4) {
            short s = nextEventCoalescingKey;
            nextEventCoalescingKey = (short) (s + 1);
            this.eventCoalescingKey = s;
        }
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        Intrinsics.checkNotNull(gestureHandlerOrchestrator);
        gestureHandlerOrchestrator.onHandlerStateChange(this, newState, i);
        onStateChange(newState, i);
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler<?> handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldRequireHandlerToWaitForFailure(this, handler);
    }

    public final boolean shouldWaitForHandlerFailure(GestureHandler<?> handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldWaitForHandlerFailure(this, handler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler<?> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this) {
            return true;
        }
        GestureHandlerInteractionController gestureHandlerInteractionController = this.interactionController;
        if (gestureHandlerInteractionController != null) {
            return gestureHandlerInteractionController.shouldRecognizeSimultaneously(this, handler);
        }
        return false;
    }

    public boolean shouldBeCancelledBy(GestureHandler<?> handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldHandlerBeCancelledBy(this, handler);
    }

    public final boolean isWithinBounds(View view, float posX, float posY) {
        float f;
        Intrinsics.checkNotNull(view);
        float width = view.getWidth();
        float height = view.getHeight();
        float[] fArr = this.hitSlop;
        if (fArr != null) {
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[2];
            float f5 = fArr[3];
            Companion companion = INSTANCE;
            float f6 = companion.hitSlopSet(f2) ? 0.0f - f2 : 0.0f;
            r6 = companion.hitSlopSet(f3) ? 0.0f - f3 : 0.0f;
            if (companion.hitSlopSet(f4)) {
                width += f4;
            }
            if (companion.hitSlopSet(f5)) {
                height += f5;
            }
            float f7 = fArr[4];
            float f8 = fArr[5];
            if (companion.hitSlopSet(f7)) {
                if (!companion.hitSlopSet(f2)) {
                    f6 = width - f7;
                } else if (!companion.hitSlopSet(f4)) {
                    width = f7 + f6;
                }
            }
            if (companion.hitSlopSet(f8)) {
                if (!companion.hitSlopSet(f3)) {
                    r6 = height - f8;
                } else if (!companion.hitSlopSet(f5)) {
                    height = f8 + r6;
                }
            }
            f = r6;
            r6 = f6;
        } else {
            f = 0.0f;
        }
        return r6 <= posX && posX <= width && f <= posY && posY <= height;
    }

    public final void cancel() {
        int i = this.state;
        if (i == 4 || i == 0 || i == 2 || this.isAwaiting) {
            onCancel();
            moveToState(3);
        }
    }

    public final void fail() {
        int i = this.state;
        if (i == 4 || i == 0 || i == 2) {
            moveToState(1);
        }
    }

    public final void activate() {
        activate(false);
    }

    public void activate(boolean force) {
        if (!this.manualActivation || force) {
            int i = this.state;
            if (i == 0 || i == 2) {
                moveToState(4);
            }
        }
    }

    public final void begin() {
        if (this.state == 0) {
            moveToState(2);
        }
    }

    public final void end() {
        int i = this.state;
        if (i == 2 || i == 4) {
            moveToState(5);
        }
    }

    protected void onHandle(MotionEvent event, MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        moveToState(1);
    }

    protected final boolean shouldActivateWithMouse(MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        if (sourceEvent.getToolType(0) == 3) {
            if (sourceEvent.getAction() == 0 || sourceEvent.getAction() == 1 || sourceEvent.getAction() == 6 || sourceEvent.getAction() == 5 || !(sourceEvent.getAction() == 2 || isButtonInConfig(sourceEvent.getActionButton()))) {
                return false;
            }
            if (sourceEvent.getAction() == 2 && !isButtonInConfig(sourceEvent.getButtonState())) {
                return false;
            }
        }
        return true;
    }

    protected final PointF transformPoint(PointF point) {
        PointF transformPointToViewCoords;
        Intrinsics.checkNotNullParameter(point, "point");
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        if (gestureHandlerOrchestrator != null && (transformPointToViewCoords = gestureHandlerOrchestrator.transformPointToViewCoords(this.view, point)) != null) {
            return transformPointToViewCoords;
        }
        point.x = Float.NaN;
        point.y = Float.NaN;
        return point;
    }

    public final void reset() {
        this.view = null;
        this.orchestrator = null;
        Arrays.fill(this.trackedPointerIDs, -1);
        this.trackedPointersIDsCount = 0;
        this.trackedPointersCount = 0;
        ArraysKt.fill$default(this.trackedPointers, (Object) null, 0, 0, 6, (Object) null);
        this.touchEventType = 0;
        onReset();
    }

    public final void withMarkedAsInBounds(Function0<Unit> closure) {
        Intrinsics.checkNotNullParameter(closure, "closure");
        this.isWithinBounds = true;
        closure.invoke();
        this.isWithinBounds = false;
    }

    private final void setPointerType(MotionEvent event) {
        int toolType = event.getToolType(event.getActionIndex());
        int i = 1;
        if (toolType == 1) {
            i = 0;
        } else if (toolType != 2) {
            i = 3;
            if (toolType == 3) {
                i = 2;
            }
        }
        this.pointerType = i;
    }

    public String toString() {
        String simpleName;
        View view = this.view;
        if (view == null) {
            simpleName = null;
        } else {
            Intrinsics.checkNotNull(view);
            simpleName = view.getClass().getSimpleName();
        }
        return getClass().getSimpleName() + "@[" + this.tag + "]:" + simpleName;
    }

    public final float getLastPositionInWindowX() {
        return (this.lastAbsolutePositionX + this.lastEventOffsetX) - this.windowOffset[0];
    }

    public final float getLastPositionInWindowY() {
        return (this.lastAbsolutePositionY + this.lastEventOffsetY) - this.windowOffset[1];
    }

    /* compiled from: GestureHandler.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0010H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0004H\u0002J\u0010\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\"X\u0082.¢\u0006\u0004\n\u0002\u0010$R\u0018\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\"X\u0082.¢\u0006\u0004\n\u0002\u0010'¨\u00061"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$Companion;", "", "()V", "ACTION_TYPE_JS_FUNCTION_NEW_API", "", "ACTION_TYPE_JS_FUNCTION_OLD_API", "ACTION_TYPE_NATIVE_ANIMATED_EVENT", "ACTION_TYPE_REANIMATED_WORKLET", "DIRECTION_DOWN", "DIRECTION_LEFT", "DIRECTION_RIGHT", "DIRECTION_UP", "HIT_SLOP_BOTTOM_IDX", "HIT_SLOP_HEIGHT_IDX", "HIT_SLOP_LEFT_IDX", "HIT_SLOP_NONE", "", "HIT_SLOP_RIGHT_IDX", "HIT_SLOP_TOP_IDX", "HIT_SLOP_WIDTH_IDX", "MAX_POINTERS_COUNT", "POINTER_TYPE_MOUSE", "POINTER_TYPE_OTHER", "POINTER_TYPE_STYLUS", "POINTER_TYPE_TOUCH", "STATE_ACTIVE", "STATE_BEGAN", "STATE_CANCELLED", "STATE_END", "STATE_FAILED", "STATE_UNDETERMINED", "nextEventCoalescingKey", "", "pointerCoords", "", "Landroid/view/MotionEvent$PointerCoords;", "[Landroid/view/MotionEvent$PointerCoords;", "pointerProps", "Landroid/view/MotionEvent$PointerProperties;", "[Landroid/view/MotionEvent$PointerProperties;", "hitSlopSet", "", "value", "initPointerProps", "", "size", "stateToString", "", SentryThread.JsonKeys.STATE, "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String stateToString(int state) {
            if (state == 0) {
                return "UNDETERMINED";
            }
            if (state == 1) {
                return "FAILED";
            }
            if (state == 2) {
                return "BEGIN";
            }
            if (state == 3) {
                return "CANCELLED";
            }
            if (state == 4) {
                return "ACTIVE";
            }
            if (state != 5) {
                return null;
            }
            return "END";
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void initPointerProps(int size) {
            if (GestureHandler.pointerProps == null) {
                GestureHandler.pointerProps = new MotionEvent.PointerProperties[12];
                GestureHandler.pointerCoords = new MotionEvent.PointerCoords[12];
            }
            while (size > 0) {
                MotionEvent.PointerProperties[] pointerPropertiesArr = GestureHandler.pointerProps;
                MotionEvent.PointerCoords[] pointerCoordsArr = null;
                if (pointerPropertiesArr == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerProps");
                    pointerPropertiesArr = null;
                }
                int i = size - 1;
                if (pointerPropertiesArr[i] != null) {
                    return;
                }
                MotionEvent.PointerProperties[] pointerPropertiesArr2 = GestureHandler.pointerProps;
                if (pointerPropertiesArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerProps");
                    pointerPropertiesArr2 = null;
                }
                pointerPropertiesArr2[i] = new MotionEvent.PointerProperties();
                MotionEvent.PointerCoords[] pointerCoordsArr2 = GestureHandler.pointerCoords;
                if (pointerCoordsArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerCoords");
                } else {
                    pointerCoordsArr = pointerCoordsArr2;
                }
                pointerCoordsArr[i] = new MotionEvent.PointerCoords();
                size--;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hitSlopSet(float value) {
            return !Float.isNaN(value);
        }
    }

    /* compiled from: GestureHandler.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006\""}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "", "pointerId", "", ViewHierarchyNode.JsonKeys.X, "", ViewHierarchyNode.JsonKeys.Y, "absoluteX", "absoluteY", "(IFFFF)V", "getAbsoluteX", "()F", "setAbsoluteX", "(F)V", "getAbsoluteY", "setAbsoluteY", "getPointerId", "()I", "getX", "setX", "getY", "setY", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class PointerData {
        private float absoluteX;
        private float absoluteY;
        private final int pointerId;
        private float x;
        private float y;

        public static /* synthetic */ PointerData copy$default(PointerData pointerData, int i, float f, float f2, float f3, float f4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = pointerData.pointerId;
            }
            if ((i2 & 2) != 0) {
                f = pointerData.x;
            }
            float f5 = f;
            if ((i2 & 4) != 0) {
                f2 = pointerData.y;
            }
            float f6 = f2;
            if ((i2 & 8) != 0) {
                f3 = pointerData.absoluteX;
            }
            float f7 = f3;
            if ((i2 & 16) != 0) {
                f4 = pointerData.absoluteY;
            }
            return pointerData.copy(i, f5, f6, f7, f4);
        }

        /* renamed from: component1, reason: from getter */
        public final int getPointerId() {
            return this.pointerId;
        }

        /* renamed from: component2, reason: from getter */
        public final float getX() {
            return this.x;
        }

        /* renamed from: component3, reason: from getter */
        public final float getY() {
            return this.y;
        }

        /* renamed from: component4, reason: from getter */
        public final float getAbsoluteX() {
            return this.absoluteX;
        }

        /* renamed from: component5, reason: from getter */
        public final float getAbsoluteY() {
            return this.absoluteY;
        }

        public final PointerData copy(int pointerId, float x, float y, float absoluteX, float absoluteY) {
            return new PointerData(pointerId, x, y, absoluteX, absoluteY);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PointerData)) {
                return false;
            }
            PointerData pointerData = (PointerData) other;
            return this.pointerId == pointerData.pointerId && Float.compare(this.x, pointerData.x) == 0 && Float.compare(this.y, pointerData.y) == 0 && Float.compare(this.absoluteX, pointerData.absoluteX) == 0 && Float.compare(this.absoluteY, pointerData.absoluteY) == 0;
        }

        public final float getAbsoluteX() {
            return this.absoluteX;
        }

        public final float getAbsoluteY() {
            return this.absoluteY;
        }

        public final int getPointerId() {
            return this.pointerId;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public int hashCode() {
            return (((((((this.pointerId * 31) + Float.floatToIntBits(this.x)) * 31) + Float.floatToIntBits(this.y)) * 31) + Float.floatToIntBits(this.absoluteX)) * 31) + Float.floatToIntBits(this.absoluteY);
        }

        public final void setAbsoluteX(float f) {
            this.absoluteX = f;
        }

        public final void setAbsoluteY(float f) {
            this.absoluteY = f;
        }

        public final void setX(float f) {
            this.x = f;
        }

        public final void setY(float f) {
            this.y = f;
        }

        public String toString() {
            return "PointerData(pointerId=" + this.pointerId + ", x=" + this.x + ", y=" + this.y + ", absoluteX=" + this.absoluteX + ", absoluteY=" + this.absoluteY + ")";
        }

        public PointerData(int i, float f, float f2, float f3, float f4) {
            this.pointerId = i;
            this.x = f;
            this.y = f2;
            this.absoluteX = f3;
            this.absoluteY = f4;
        }
    }

    public final ConcreteGestureHandlerT setShouldCancelWhenOutside(boolean shouldCancelWhenOutside) {
        ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        concretegesturehandlert.shouldCancelWhenOutside = shouldCancelWhenOutside;
        return concretegesturehandlert;
    }

    public final ConcreteGestureHandlerT setEnabled(boolean enabled) {
        final ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        if (concretegesturehandlert.view != null && concretegesturehandlert.isEnabled != enabled) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.core.GestureHandler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    GestureHandler.setEnabled$lambda$3$lambda$2(GestureHandler.this);
                }
            });
        }
        concretegesturehandlert.isEnabled = enabled;
        return concretegesturehandlert;
    }

    public final ConcreteGestureHandlerT setManualActivation(boolean manualActivation) {
        ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        concretegesturehandlert.manualActivation = manualActivation;
        return concretegesturehandlert;
    }

    public final ConcreteGestureHandlerT setHitSlop(float leftPad, float topPad, float rightPad, float bottomPad, float width, float height) {
        ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        if (concretegesturehandlert.hitSlop == null) {
            concretegesturehandlert.hitSlop = new float[6];
        }
        float[] fArr = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr);
        fArr[0] = leftPad;
        float[] fArr2 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr2);
        fArr2[1] = topPad;
        float[] fArr3 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr3);
        fArr3[2] = rightPad;
        float[] fArr4 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr4);
        fArr4[3] = bottomPad;
        float[] fArr5 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr5);
        fArr5[4] = width;
        float[] fArr6 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr6);
        fArr6[5] = height;
        Companion companion = INSTANCE;
        if (companion.hitSlopSet(width) && companion.hitSlopSet(leftPad) && companion.hitSlopSet(rightPad)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined".toString());
        }
        if (companion.hitSlopSet(width) && !companion.hitSlopSet(leftPad) && !companion.hitSlopSet(rightPad)) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined".toString());
        }
        if (companion.hitSlopSet(height) && companion.hitSlopSet(bottomPad) && companion.hitSlopSet(topPad)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined".toString());
        }
        if (!companion.hitSlopSet(height) || companion.hitSlopSet(bottomPad) || companion.hitSlopSet(topPad)) {
            return concretegesturehandlert;
        }
        throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined".toString());
    }

    public final ConcreteGestureHandlerT setInteractionController(GestureHandlerInteractionController controller) {
        ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        concretegesturehandlert.interactionController = controller;
        return concretegesturehandlert;
    }
}
