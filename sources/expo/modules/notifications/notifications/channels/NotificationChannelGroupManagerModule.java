package expo.modules.notifications.notifications.channels;

import android.app.NotificationChannelGroup;
import android.os.Build;
import android.os.Bundle;
import androidx.tracing.Trace;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.haptics.HapticsModule$$ExternalSyntheticApiModelOutline0;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.notifications.ModuleNotFoundException;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: NotificationChannelGroupManagerModule.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lexpo/modules/notifications/notifications/channels/NotificationChannelGroupManagerModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "groupManager", "Lexpo/modules/notifications/notifications/channels/managers/NotificationsChannelGroupManager;", "groupSerializer", "Lexpo/modules/notifications/notifications/channels/serializers/NotificationsChannelGroupSerializer;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getNameFromOptions", "", "groupOptions", "Lexpo/modules/core/arguments/ReadableArguments;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NotificationChannelGroupManagerModule extends Module {
    private NotificationsChannelGroupManager groupManager;
    private NotificationsChannelGroupSerializer groupSerializer;

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        NotificationChannelGroupManagerModule notificationChannelGroupManagerModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (notificationChannelGroupManagerModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(notificationChannelGroupManagerModule);
            moduleDefinitionBuilder.Name("ExpoNotificationChannelGroupManager");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$OnCreate$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Object obj;
                    try {
                        obj = NotificationChannelGroupManagerModule.this.getAppContext().getLegacyModuleRegistry().getModule(NotificationsChannelsProvider.class);
                    } catch (Exception unused) {
                        obj = null;
                    }
                    NotificationsChannelsProvider notificationsChannelsProvider = (NotificationsChannelsProvider) obj;
                    if (notificationsChannelsProvider == null) {
                        throw new ModuleNotFoundException(Reflection.getOrCreateKotlinClass(NotificationsChannelsProvider.class));
                    }
                    NotificationChannelGroupManagerModule notificationChannelGroupManagerModule2 = NotificationChannelGroupManagerModule.this;
                    NotificationsChannelGroupManager groupManager = notificationsChannelsProvider.getGroupManager();
                    Intrinsics.checkNotNullExpressionValue(groupManager, "getGroupManager(...)");
                    notificationChannelGroupManagerModule2.groupManager = groupManager;
                    NotificationChannelGroupManagerModule notificationChannelGroupManagerModule3 = NotificationChannelGroupManagerModule.this;
                    NotificationsChannelGroupSerializer groupSerializer = notificationsChannelsProvider.getGroupSerializer();
                    Intrinsics.checkNotNullExpressionValue(groupSerializer, "getGroupSerializer(...)");
                    notificationChannelGroupManagerModule3.groupSerializer = groupSerializer;
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionComponent = new AsyncFunctionWithPromiseComponent("getNotificationChannelGroupAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$1
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        NotificationsChannelGroupManager notificationsChannelGroupManager;
                        NotificationsChannelGroupSerializer notificationsChannelGroupSerializer;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                            NotificationsChannelGroupSerializer notificationsChannelGroupSerializer2 = null;
                            if (notificationsChannelGroupManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                                notificationsChannelGroupManager = null;
                            }
                            NotificationChannelGroup notificationChannelGroup = notificationsChannelGroupManager.getNotificationChannelGroup(str);
                            notificationsChannelGroupSerializer = NotificationChannelGroupManagerModule.this.groupSerializer;
                            if (notificationsChannelGroupSerializer == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
                            } else {
                                notificationsChannelGroupSerializer2 = notificationsChannelGroupSerializer;
                            }
                            notificationsChannelGroupSerializer2.toBundle(notificationChannelGroup);
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                asyncFunctionComponent = new AsyncFunctionComponent("getNotificationChannelGroupAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))}, new Function1<Object[], Bundle>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Bundle invoke(Object[] objArr) {
                        NotificationsChannelGroupManager notificationsChannelGroupManager;
                        NotificationsChannelGroupSerializer notificationsChannelGroupSerializer;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        String str = (String) objArr[0];
                        NotificationsChannelGroupSerializer notificationsChannelGroupSerializer2 = null;
                        if (Build.VERSION.SDK_INT < 26) {
                            return null;
                        }
                        notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                        if (notificationsChannelGroupManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                            notificationsChannelGroupManager = null;
                        }
                        NotificationChannelGroup notificationChannelGroup = notificationsChannelGroupManager.getNotificationChannelGroup(str);
                        notificationsChannelGroupSerializer = NotificationChannelGroupManagerModule.this.groupSerializer;
                        if (notificationsChannelGroupSerializer == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
                        } else {
                            notificationsChannelGroupSerializer2 = notificationsChannelGroupSerializer;
                        }
                        return notificationsChannelGroupSerializer2.toBundle(notificationChannelGroup);
                    }
                });
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getNotificationChannelGroupAsync", asyncFunctionComponent);
            moduleDefinitionBuilder.getAsyncFunctions().put("getNotificationChannelGroupsAsync", new AsyncFunctionComponent("getNotificationChannelGroupsAsync", new AnyType[0], new Function1<Object[], List<? extends Bundle>>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final List<? extends Bundle> invoke(Object[] it) {
                    NotificationsChannelGroupManager notificationsChannelGroupManager;
                    NotificationsChannelGroupSerializer notificationsChannelGroupSerializer;
                    Intrinsics.checkNotNullParameter(it, "it");
                    NotificationsChannelGroupSerializer notificationsChannelGroupSerializer2 = null;
                    if (Build.VERSION.SDK_INT < 26) {
                        return null;
                    }
                    notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                    if (notificationsChannelGroupManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                        notificationsChannelGroupManager = null;
                    }
                    List<NotificationChannelGroup> notificationChannelGroups = notificationsChannelGroupManager.getNotificationChannelGroups();
                    Intrinsics.checkNotNullExpressionValue(notificationChannelGroups, "getNotificationChannelGroups(...)");
                    List<NotificationChannelGroup> list = notificationChannelGroups;
                    notificationsChannelGroupSerializer = NotificationChannelGroupManagerModule.this.groupSerializer;
                    if (notificationsChannelGroupSerializer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
                    } else {
                        notificationsChannelGroupSerializer2 = notificationsChannelGroupSerializer;
                    }
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator<T> it2 = list.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(notificationsChannelGroupSerializer2.toBundle(HapticsModule$$ExternalSyntheticApiModelOutline0.m658m(it2.next())));
                    }
                    return arrayList;
                }
            }));
            moduleDefinitionBuilder.getAsyncFunctions().put("setNotificationChannelGroupAsync", new AsyncFunctionComponent("setNotificationChannelGroupAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$5
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$6
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(ReadableArguments.class);
                }
            }))}, new Function1<Object[], Bundle>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$7
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Bundle invoke(Object[] objArr) {
                    NotificationsChannelGroupManager notificationsChannelGroupManager;
                    String nameFromOptions;
                    NotificationsChannelGroupSerializer notificationsChannelGroupSerializer;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Object obj = objArr[0];
                    ReadableArguments readableArguments = (ReadableArguments) objArr[1];
                    String str = (String) obj;
                    NotificationsChannelGroupSerializer notificationsChannelGroupSerializer2 = null;
                    if (Build.VERSION.SDK_INT < 26) {
                        return null;
                    }
                    notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                    if (notificationsChannelGroupManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                        notificationsChannelGroupManager = null;
                    }
                    nameFromOptions = NotificationChannelGroupManagerModule.this.getNameFromOptions(readableArguments);
                    NotificationChannelGroup createNotificationChannelGroup = notificationsChannelGroupManager.createNotificationChannelGroup(str, nameFromOptions, readableArguments);
                    notificationsChannelGroupSerializer = NotificationChannelGroupManagerModule.this.groupSerializer;
                    if (notificationsChannelGroupSerializer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
                    } else {
                        notificationsChannelGroupSerializer2 = notificationsChannelGroupSerializer;
                    }
                    return notificationsChannelGroupSerializer2.toBundle(createNotificationChannelGroup);
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("deleteNotificationChannelGroupAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$8
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        NotificationsChannelGroupManager notificationsChannelGroupManager;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                            if (notificationsChannelGroupManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                                notificationsChannelGroupManager = null;
                            }
                            notificationsChannelGroupManager.deleteNotificationChannelGroup(str);
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$9
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$10
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        NotificationsChannelGroupManager notificationsChannelGroupManager;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        String str = (String) objArr[0];
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationsChannelGroupManager = NotificationChannelGroupManagerModule.this.groupManager;
                            if (notificationsChannelGroupManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("groupManager");
                                notificationsChannelGroupManager = null;
                            }
                            notificationsChannelGroupManager.deleteNotificationChannelGroup(str);
                        }
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent2 = new StringAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr, function1);
                                } else {
                                    asyncFunctionComponent2 = new AsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr, function1);
                                }
                            } else {
                                asyncFunctionComponent2 = new FloatAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent2 = new BoolAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent2 = new IntAsyncFunctionComponent("deleteNotificationChannelGroupAsync", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent2;
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("deleteNotificationChannelGroupAsync", asyncFunctionWithPromiseComponent);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getNameFromOptions(ReadableArguments groupOptions) {
        String string = groupOptions.getString("name");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
