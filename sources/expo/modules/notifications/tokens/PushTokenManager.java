package expo.modules.notifications.tokens;

import expo.modules.core.interfaces.SingletonModule;
import expo.modules.notifications.service.delegates.FirebaseMessagingDelegate;
import expo.modules.notifications.tokens.interfaces.FirebaseTokenListener;
import expo.modules.notifications.tokens.interfaces.PushTokenListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
public class PushTokenManager implements SingletonModule, FirebaseTokenListener, expo.modules.notifications.tokens.interfaces.PushTokenManager {
    private static final String SINGLETON_NAME = "PushTokenManager";
    private String mLastToken;
    private WeakHashMap<PushTokenListener, WeakReference<PushTokenListener>> mListenerReferenceMap = new WeakHashMap<>();

    @Override // expo.modules.core.interfaces.SingletonModule
    public String getName() {
        return SINGLETON_NAME;
    }

    public PushTokenManager() {
        FirebaseMessagingDelegate.addTokenListener(this);
    }

    @Override // expo.modules.notifications.tokens.interfaces.PushTokenManager
    public void addListener(PushTokenListener pushTokenListener) {
        if (this.mListenerReferenceMap.containsKey(pushTokenListener)) {
            return;
        }
        this.mListenerReferenceMap.put(pushTokenListener, new WeakReference<>(pushTokenListener));
        String str = this.mLastToken;
        if (str != null) {
            pushTokenListener.onNewToken(str);
        }
    }

    @Override // expo.modules.notifications.tokens.interfaces.PushTokenManager
    public void removeListener(PushTokenListener pushTokenListener) {
        this.mListenerReferenceMap.remove(pushTokenListener);
    }

    @Override // expo.modules.notifications.tokens.interfaces.FirebaseTokenListener
    public void onNewToken(String str) {
        Iterator<WeakReference<PushTokenListener>> it = this.mListenerReferenceMap.values().iterator();
        while (it.hasNext()) {
            PushTokenListener pushTokenListener = it.next().get();
            if (pushTokenListener != null) {
                pushTokenListener.onNewToken(str);
            }
        }
        this.mLastToken = str;
    }
}
