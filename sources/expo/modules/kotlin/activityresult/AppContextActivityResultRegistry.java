package expo.modules.kotlin.activityresult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import expo.modules.kotlin.activityresult.AppContextActivityResultRegistry;
import expo.modules.kotlin.providers.CurrentActivityProvider;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppContextActivityResultRegistry.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u000289B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0007JH\u0010!\u001a\u00020\"\"\b\b\u0000\u0010#*\u00020\u000e\"\u0004\b\u0001\u0010$2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0014\u0010&\u001a\u0010\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$\u0018\u00010\fH\u0002J\b\u0010'\u001a\u00020\u0012H\u0002JC\u0010(\u001a\u00020\"\"\b\b\u0000\u0010#*\u00020\u000e\"\u0004\b\u0001\u0010$2\u0006\u0010\u001d\u001a\u00020\u00122\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$0*2\b\b\u0001\u0010+\u001a\u0002H#H\u0007¢\u0006\u0002\u0010,J\u000e\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020/J\\\u00100\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$01\"\b\b\u0000\u0010#*\u00020\u000e\"\u0004\b\u0001\u0010$2\u0006\u0010%\u001a\u00020\u000b2\u0006\u00102\u001a\u0002032\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$0*2\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H$05H\u0007J\u000e\u00106\u001a\u00020\"2\u0006\u0010.\u001a\u00020/J\u0010\u00107\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u000bH\u0007R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00120\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0014j\b\u0012\u0004\u0012\u00020\u000b`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lexpo/modules/kotlin/activityresult/AppContextActivityResultRegistry;", "", "currentActivityProvider", "Lexpo/modules/kotlin/providers/CurrentActivityProvider;", "(Lexpo/modules/kotlin/providers/CurrentActivityProvider;)V", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "getActivity", "()Landroidx/appcompat/app/AppCompatActivity;", "keyToCallbacksAndContract", "", "", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultRegistry$CallbacksAndContract;", "keyToInputParam", "Ljava/io/Serializable;", "keyToLifecycleContainers", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultRegistry$LifecycleContainer;", "keyToRequestCode", "", "launchedKeys", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "pendingResults", "Landroid/os/Bundle;", "random", "Ljava/util/Random;", "requestCodeToKey", "dispatchResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "doDispatch", "", "I", "O", "key", "callbacksAndContract", "generateRandomNumber", "onLaunch", "contract", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "input", "(ILexpo/modules/kotlin/activityresult/AppContextActivityResultContract;Ljava/io/Serializable;)V", "persistInstanceState", "context", "Landroid/content/Context;", "register", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultLauncher;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "fallbackCallback", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultFallbackCallback;", "restoreInstanceState", "unregister", "CallbacksAndContract", "LifecycleContainer", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AppContextActivityResultRegistry {
    private final CurrentActivityProvider currentActivityProvider;
    private final Map<String, CallbacksAndContract<?, ?>> keyToCallbacksAndContract;
    private final Map<String, Serializable> keyToInputParam;
    private final Map<String, LifecycleContainer> keyToLifecycleContainers;
    private final Map<String, Integer> keyToRequestCode;
    private ArrayList<String> launchedKeys;
    private final Bundle pendingResults;
    private Random random;
    private final Map<Integer, String> requestCodeToKey;

    /* compiled from: AppContextActivityResultRegistry.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AppContextActivityResultRegistry(CurrentActivityProvider currentActivityProvider) {
        Intrinsics.checkNotNullParameter(currentActivityProvider, "currentActivityProvider");
        this.currentActivityProvider = currentActivityProvider;
        this.random = new Random();
        this.requestCodeToKey = new HashMap();
        this.keyToRequestCode = new HashMap();
        this.keyToLifecycleContainers = new HashMap();
        this.launchedKeys = new ArrayList<>();
        this.keyToCallbacksAndContract = new HashMap();
        this.keyToInputParam = new HashMap();
        this.pendingResults = new Bundle();
    }

    private final AppCompatActivity getActivity() {
        Activity currentActivity = this.currentActivityProvider.getCurrentActivity();
        AppCompatActivity appCompatActivity = currentActivity instanceof AppCompatActivity ? (AppCompatActivity) currentActivity : null;
        if (appCompatActivity != null) {
            return appCompatActivity;
        }
        throw new IllegalArgumentException("Current Activity is not available at the moment".toString());
    }

    public final <I extends Serializable, O> void onLaunch(final int requestCode, AppContextActivityResultContract<I, O> contract, I input) {
        Bundle bundle;
        Parcelable parcelableExtra;
        Object parcelableExtra2;
        Intrinsics.checkNotNullParameter(contract, "contract");
        Intrinsics.checkNotNullParameter(input, "input");
        Intent createIntent = contract.createIntent(getActivity(), input);
        if (createIntent.hasExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) {
            bundle = createIntent.getBundleExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
            createIntent.removeExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
        } else {
            bundle = null;
        }
        Bundle bundle2 = bundle;
        String action = createIntent.getAction();
        if (action != null) {
            int hashCode = action.hashCode();
            if (hashCode != -1837081951) {
                if (hashCode == -591152331 && action.equals(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST)) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        parcelableExtra2 = createIntent.getParcelableExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST, IntentSenderRequest.class);
                        parcelableExtra = (Parcelable) parcelableExtra2;
                    } else {
                        parcelableExtra = createIntent.getParcelableExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST);
                    }
                    Intrinsics.checkNotNull(parcelableExtra);
                    IntentSenderRequest intentSenderRequest = (IntentSenderRequest) parcelableExtra;
                    try {
                        ActivityCompat.startIntentSenderForResult(getActivity(), intentSenderRequest.getIntentSender(), requestCode, intentSenderRequest.getFillInIntent(), intentSenderRequest.getFlagsMask(), intentSenderRequest.getFlagsValues(), 0, bundle2);
                        return;
                    } catch (IntentSender.SendIntentException e) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: expo.modules.kotlin.activityresult.AppContextActivityResultRegistry$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                AppContextActivityResultRegistry.onLaunch$lambda$1(AppContextActivityResultRegistry.this, requestCode, e);
                            }
                        });
                        return;
                    }
                }
            } else if (action.equals(ActivityResultContracts.RequestMultiplePermissions.ACTION_REQUEST_PERMISSIONS)) {
                String[] stringArrayExtra = createIntent.getStringArrayExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS);
                if (stringArrayExtra == null) {
                    stringArrayExtra = new String[0];
                }
                ActivityCompat.requestPermissions(getActivity(), stringArrayExtra, requestCode);
                return;
            }
        }
        ActivityCompat.startActivityForResult(getActivity(), createIntent, requestCode, bundle2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onLaunch$lambda$1(AppContextActivityResultRegistry this$0, int i, IntentSender.SendIntentException e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "$e");
        this$0.dispatchResult(i, 0, new Intent().setAction(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST).putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_SEND_INTENT_EXCEPTION, e));
    }

    public final <I extends Serializable, O> AppContextActivityResultLauncher<I, O> register(final String key, LifecycleOwner lifecycleOwner, final AppContextActivityResultContract<I, O> contract, final AppContextActivityResultFallbackCallback<I, O> fallbackCallback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(contract, "contract");
        Intrinsics.checkNotNullParameter(fallbackCallback, "fallbackCallback");
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "getLifecycle(...)");
        this.keyToCallbacksAndContract.put(key, new CallbacksAndContract<>(fallbackCallback, null, contract));
        if (this.keyToRequestCode.get(key) == null) {
            int generateRandomNumber = generateRandomNumber();
            this.requestCodeToKey.put(Integer.valueOf(generateRandomNumber), key);
            this.keyToRequestCode.put(key, Integer.valueOf(generateRandomNumber));
            Unit unit = Unit.INSTANCE;
        }
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: expo.modules.kotlin.activityresult.AppContextActivityResultRegistry$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                AppContextActivityResultRegistry.register$lambda$4(AppContextActivityResultRegistry.this, key, lifecycleOwner2, event);
            }
        };
        LifecycleContainer lifecycleContainer = this.keyToLifecycleContainers.get(key);
        if (lifecycleContainer == null) {
            lifecycleContainer = new LifecycleContainer(lifecycle);
        }
        lifecycleContainer.addObserver(lifecycleEventObserver);
        this.keyToLifecycleContainers.put(key, lifecycleContainer);
        return (AppContextActivityResultLauncher) new AppContextActivityResultLauncher<I, O>(contract, this, key, fallbackCallback) { // from class: expo.modules.kotlin.activityresult.AppContextActivityResultRegistry$register$2
            final /* synthetic */ AppContextActivityResultContract<I, O> $contract;
            final /* synthetic */ AppContextActivityResultFallbackCallback<I, O> $fallbackCallback;
            final /* synthetic */ String $key;
            private final AppContextActivityResultContract<I, O> contract;
            final /* synthetic */ AppContextActivityResultRegistry this$0;

            @Override // expo.modules.kotlin.activityresult.AppContextActivityResultLauncher
            public AppContextActivityResultContract<I, O> getContract() {
                return this.contract;
            }

            {
                this.$contract = contract;
                this.this$0 = this;
                this.$key = key;
                this.$fallbackCallback = fallbackCallback;
                this.contract = contract;
            }

            /* JADX WARN: Incorrect types in method signature: (TI;Landroidx/activity/result/ActivityResultCallback<TO;>;)V */
            @Override // expo.modules.kotlin.activityresult.AppContextActivityResultLauncher
            public void launch(Serializable input, ActivityResultCallback callback) {
                Map map;
                Map map2;
                Map map3;
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(callback, "callback");
                map = this.this$0.keyToRequestCode;
                Integer num = (Integer) map.get(this.$key);
                if (num != null) {
                    int intValue = num.intValue();
                    map2 = this.this$0.keyToCallbacksAndContract;
                    map2.put(this.$key, new AppContextActivityResultRegistry.CallbacksAndContract(this.$fallbackCallback, callback, this.$contract));
                    map3 = this.this$0.keyToInputParam;
                    map3.put(this.$key, input);
                    arrayList = this.this$0.launchedKeys;
                    arrayList.add(this.$key);
                    try {
                        this.this$0.onLaunch(intValue, this.$contract, input);
                        return;
                    } catch (Exception e) {
                        arrayList2 = this.this$0.launchedKeys;
                        arrayList2.remove(this.$key);
                        throw e;
                    }
                }
                throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + this.$contract + " and input " + input + ". You must ensure the ActivityResultLauncher is registered before calling launch()");
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void register$lambda$4(AppContextActivityResultRegistry this$0, String key, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Parcelable parcelable;
        Object parcelable2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            this$0.unregister(key);
            return;
        }
        CallbacksAndContract<?, ?> callbacksAndContract = this$0.keyToCallbacksAndContract.get(key);
        if (callbacksAndContract == null) {
            return;
        }
        Bundle bundle = this$0.pendingResults;
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable2 = bundle.getParcelable(key, ActivityResult.class);
            parcelable = (Parcelable) parcelable2;
        } else {
            parcelable = bundle.getParcelable(key);
        }
        ActivityResult activityResult = (ActivityResult) parcelable;
        if (activityResult != null) {
            this$0.pendingResults.remove(key);
            Serializable serializable = this$0.keyToInputParam.get(key);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type I of expo.modules.kotlin.activityresult.AppContextActivityResultRegistry.register$lambda$4$lambda$3");
            Serializable serializable2 = serializable;
            Object parseResult = callbacksAndContract.getContract().parseResult(serializable2, activityResult.getResultCode(), activityResult.getData());
            if (callbacksAndContract.getMainCallback() != null) {
                callbacksAndContract.getMainCallback().onActivityResult(parseResult);
            } else {
                callbacksAndContract.getFallbackCallback().onActivityResult(serializable2, parseResult);
            }
        }
    }

    public final void persistInstanceState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DataPersistor addStringToIntMap = new DataPersistor(context).addStringArrayList("launchedKeys", this.launchedKeys).addStringToIntMap("keyToRequestCode", this.keyToRequestCode);
        Map<String, Serializable> map = this.keyToInputParam;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Serializable> entry : map.entrySet()) {
            if (this.launchedKeys.contains(entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        addStringToIntMap.addStringToSerializableMap("keyToParamsForFallbackCallback", linkedHashMap).addBundle("pendingResult", this.pendingResults).addSerializable("random", this.random).persist();
    }

    public final void restoreInstanceState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DataPersistor dataPersistor = new DataPersistor(context);
        ArrayList<String> retrieveStringArrayList = dataPersistor.retrieveStringArrayList("launchedKeys");
        if (retrieveStringArrayList != null) {
            this.launchedKeys = retrieveStringArrayList;
        }
        Map<String, Serializable> retrieveStringToSerializableMap = dataPersistor.retrieveStringToSerializableMap("keyToParamsForFallbackCallback");
        if (retrieveStringToSerializableMap != null) {
            this.keyToInputParam.putAll(retrieveStringToSerializableMap);
        }
        Bundle retrieveBundle = dataPersistor.retrieveBundle("pendingResult");
        if (retrieveBundle != null) {
            this.pendingResults.putAll(retrieveBundle);
        }
        Serializable retrieveSerializable = dataPersistor.retrieveSerializable("random");
        if (retrieveSerializable != null) {
            this.random = (Random) retrieveSerializable;
        }
        Map<String, Integer> retrieveStringToIntMap = dataPersistor.retrieveStringToIntMap("keyToRequestCode");
        if (retrieveStringToIntMap != null) {
            Iterator<T> it = retrieveStringToIntMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                int intValue = ((Number) entry.getValue()).intValue();
                this.keyToRequestCode.put(str, Integer.valueOf(intValue));
                this.requestCodeToKey.put(Integer.valueOf(intValue), str);
            }
        }
    }

    public final void unregister(String key) {
        Parcelable parcelable;
        Object parcelable2;
        Integer remove;
        Intrinsics.checkNotNullParameter(key, "key");
        if (!this.launchedKeys.contains(key) && (remove = this.keyToRequestCode.remove(key)) != null) {
            this.requestCodeToKey.remove(Integer.valueOf(remove.intValue()));
        }
        this.keyToCallbacksAndContract.remove(key);
        if (this.pendingResults.containsKey(key)) {
            Bundle bundle = this.pendingResults;
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable2 = bundle.getParcelable(key, ActivityResult.class);
                parcelable = (Parcelable) parcelable2;
            } else {
                parcelable = bundle.getParcelable(key);
            }
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + key + " : " + parcelable);
            this.pendingResults.remove(key);
        }
        LifecycleContainer lifecycleContainer = this.keyToLifecycleContainers.get(key);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
            this.keyToLifecycleContainers.remove(key);
        }
    }

    public final boolean dispatchResult(int requestCode, int resultCode, Intent data) {
        String str = this.requestCodeToKey.get(Integer.valueOf(requestCode));
        if (str == null) {
            return false;
        }
        doDispatch(str, resultCode, data, this.keyToCallbacksAndContract.get(str));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <I extends Serializable, O> void doDispatch(String key, int resultCode, Intent data, CallbacksAndContract<I, O> callbacksAndContract) {
        Lifecycle lifecycle;
        LifecycleContainer lifecycleContainer = this.keyToLifecycleContainers.get(key);
        Lifecycle.State currentState = (lifecycleContainer == null || (lifecycle = lifecycleContainer.getLifecycle()) == null) ? null : lifecycle.getCurrentState();
        if ((callbacksAndContract != null ? callbacksAndContract.getMainCallback() : null) != null && this.launchedKeys.contains(key)) {
            Serializable serializable = this.keyToInputParam.get(key);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type I of expo.modules.kotlin.activityresult.AppContextActivityResultRegistry.doDispatch");
            callbacksAndContract.getMainCallback().onActivityResult(callbacksAndContract.getContract().parseResult(serializable, resultCode, data));
            this.launchedKeys.remove(key);
            return;
        }
        if (currentState != null && currentState.isAtLeast(Lifecycle.State.STARTED) && callbacksAndContract != null && this.launchedKeys.contains(key)) {
            Serializable serializable2 = this.keyToInputParam.get(key);
            Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type I of expo.modules.kotlin.activityresult.AppContextActivityResultRegistry.doDispatch");
            Serializable serializable3 = serializable2;
            callbacksAndContract.getFallbackCallback().onActivityResult(serializable3, callbacksAndContract.getContract().parseResult(serializable3, resultCode, data));
            this.launchedKeys.remove(key);
            return;
        }
        this.pendingResults.putParcelable(key, new ActivityResult(resultCode, data));
    }

    private final int generateRandomNumber() {
        int nextInt = this.random.nextInt(2147418112);
        while (true) {
            int i = nextInt + 65536;
            if (!this.requestCodeToKey.containsKey(Integer.valueOf(i))) {
                return i;
            }
            nextInt = this.random.nextInt(2147418112);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AppContextActivityResultRegistry.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004B=\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bHÆ\u0003J\u0015\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\nHÆ\u0003JS\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\b2\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\nHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lexpo/modules/kotlin/activityresult/AppContextActivityResultRegistry$CallbacksAndContract;", "I", "Ljava/io/Serializable;", "O", "", "fallbackCallback", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultFallbackCallback;", "mainCallback", "Landroidx/activity/result/ActivityResultCallback;", "contract", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "(Lexpo/modules/kotlin/activityresult/AppContextActivityResultFallbackCallback;Landroidx/activity/result/ActivityResultCallback;Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;)V", "getContract", "()Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "getFallbackCallback", "()Lexpo/modules/kotlin/activityresult/AppContextActivityResultFallbackCallback;", "getMainCallback", "()Landroidx/activity/result/ActivityResultCallback;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final /* data */ class CallbacksAndContract<I extends Serializable, O> {
        private final AppContextActivityResultContract<I, O> contract;
        private final AppContextActivityResultFallbackCallback<I, O> fallbackCallback;
        private final ActivityResultCallback<O> mainCallback;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CallbacksAndContract copy$default(CallbacksAndContract callbacksAndContract, AppContextActivityResultFallbackCallback appContextActivityResultFallbackCallback, ActivityResultCallback activityResultCallback, AppContextActivityResultContract appContextActivityResultContract, int i, Object obj) {
            if ((i & 1) != 0) {
                appContextActivityResultFallbackCallback = callbacksAndContract.fallbackCallback;
            }
            if ((i & 2) != 0) {
                activityResultCallback = callbacksAndContract.mainCallback;
            }
            if ((i & 4) != 0) {
                appContextActivityResultContract = callbacksAndContract.contract;
            }
            return callbacksAndContract.copy(appContextActivityResultFallbackCallback, activityResultCallback, appContextActivityResultContract);
        }

        public final AppContextActivityResultFallbackCallback<I, O> component1() {
            return this.fallbackCallback;
        }

        public final ActivityResultCallback<O> component2() {
            return this.mainCallback;
        }

        public final AppContextActivityResultContract<I, O> component3() {
            return this.contract;
        }

        public final CallbacksAndContract<I, O> copy(AppContextActivityResultFallbackCallback<I, O> fallbackCallback, ActivityResultCallback<O> mainCallback, AppContextActivityResultContract<I, O> contract) {
            Intrinsics.checkNotNullParameter(fallbackCallback, "fallbackCallback");
            Intrinsics.checkNotNullParameter(contract, "contract");
            return new CallbacksAndContract<>(fallbackCallback, mainCallback, contract);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CallbacksAndContract)) {
                return false;
            }
            CallbacksAndContract callbacksAndContract = (CallbacksAndContract) other;
            return Intrinsics.areEqual(this.fallbackCallback, callbacksAndContract.fallbackCallback) && Intrinsics.areEqual(this.mainCallback, callbacksAndContract.mainCallback) && Intrinsics.areEqual(this.contract, callbacksAndContract.contract);
        }

        public final AppContextActivityResultContract<I, O> getContract() {
            return this.contract;
        }

        public final AppContextActivityResultFallbackCallback<I, O> getFallbackCallback() {
            return this.fallbackCallback;
        }

        public final ActivityResultCallback<O> getMainCallback() {
            return this.mainCallback;
        }

        public int hashCode() {
            int hashCode = this.fallbackCallback.hashCode() * 31;
            ActivityResultCallback<O> activityResultCallback = this.mainCallback;
            return ((hashCode + (activityResultCallback == null ? 0 : activityResultCallback.hashCode())) * 31) + this.contract.hashCode();
        }

        public String toString() {
            return "CallbacksAndContract(fallbackCallback=" + this.fallbackCallback + ", mainCallback=" + this.mainCallback + ", contract=" + this.contract + ")";
        }

        public CallbacksAndContract(AppContextActivityResultFallbackCallback<I, O> fallbackCallback, ActivityResultCallback<O> activityResultCallback, AppContextActivityResultContract<I, O> contract) {
            Intrinsics.checkNotNullParameter(fallbackCallback, "fallbackCallback");
            Intrinsics.checkNotNullParameter(contract, "contract");
            this.fallbackCallback = fallbackCallback;
            this.mainCallback = activityResultCallback;
            this.contract = contract;
        }
    }

    /* compiled from: AppContextActivityResultRegistry.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tJ\u0006\u0010\u000e\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lexpo/modules/kotlin/activityresult/AppContextActivityResultRegistry$LifecycleContainer;", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "(Landroidx/lifecycle/Lifecycle;)V", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "observers", "Ljava/util/ArrayList;", "Landroidx/lifecycle/LifecycleEventObserver;", "Lkotlin/collections/ArrayList;", "addObserver", "", "observer", "clearObservers", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class LifecycleContainer {
        private final Lifecycle lifecycle;
        private final ArrayList<LifecycleEventObserver> observers;

        public final Lifecycle getLifecycle() {
            return this.lifecycle;
        }

        public LifecycleContainer(Lifecycle lifecycle) {
            Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
            this.lifecycle = lifecycle;
            this.observers = new ArrayList<>();
        }

        public final void addObserver(LifecycleEventObserver observer) {
            Intrinsics.checkNotNullParameter(observer, "observer");
            this.lifecycle.addObserver(observer);
            this.observers.add(observer);
        }

        public final void clearObservers() {
            Iterator<T> it = this.observers.iterator();
            while (it.hasNext()) {
                this.lifecycle.removeObserver((LifecycleEventObserver) it.next());
            }
            this.observers.clear();
        }
    }
}
