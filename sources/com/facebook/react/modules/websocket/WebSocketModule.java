package com.facebook.react.modules.websocket;

import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.CustomClientBuilder;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import io.sentry.clientreport.DiscardedEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okio.ByteString;

@ReactModule(name = NativeWebSocketModuleSpec.NAME)
/* loaded from: classes.dex */
public final class WebSocketModule extends NativeWebSocketModuleSpec {
    private static CustomClientBuilder customClientBuilder;
    private final Map<Integer, ContentHandler> mContentHandlers;
    private ForwardingCookieHandler mCookieHandler;
    private final Map<Integer, WebSocket> mWebSocketConnections;

    public interface ContentHandler {
        void onMessage(String str, WritableMap writableMap);

        void onMessage(ByteString byteString, WritableMap writableMap);
    }

    public static void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        customClientBuilder = customClientBuilder2;
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void removeListeners(double d) {
    }

    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mWebSocketConnections = new ConcurrentHashMap();
        this.mContentHandlers = new ConcurrentHashMap();
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
    }

    private static void applyCustomBuilder(OkHttpClient.Builder builder) {
        CustomClientBuilder customClientBuilder2 = customClientBuilder;
        if (customClientBuilder2 != null) {
            customClientBuilder2.apply(builder);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        Iterator<WebSocket> it = this.mWebSocketConnections.values().iterator();
        while (it.hasNext()) {
            it.next().close(1001, null);
        }
        this.mWebSocketConnections.clear();
        this.mContentHandlers.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext.hasActiveReactInstance()) {
            reactApplicationContext.emitDeviceEvent(str, writableMap);
        }
    }

    public void setContentHandler(int i, ContentHandler contentHandler) {
        if (contentHandler != null) {
            this.mContentHandlers.put(Integer.valueOf(i), contentHandler);
        } else {
            this.mContentHandlers.remove(Integer.valueOf(i));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a0, code lost:
    
        if (r5 == false) goto L22;
     */
    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void connect(java.lang.String r10, com.facebook.react.bridge.ReadableArray r11, com.facebook.react.bridge.ReadableMap r12, double r13) {
        /*
            r9 = this;
            int r13 = (int) r13
            okhttp3.OkHttpClient$Builder r14 = new okhttp3.OkHttpClient$Builder
            r14.<init>()
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
            r1 = 10
            okhttp3.OkHttpClient$Builder r14 = r14.connectTimeout(r1, r0)
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
            okhttp3.OkHttpClient$Builder r14 = r14.writeTimeout(r1, r0)
            r0 = 0
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES
            okhttp3.OkHttpClient$Builder r14 = r14.readTimeout(r0, r2)
            applyCustomBuilder(r14)
            okhttp3.OkHttpClient r14 = r14.build()
            okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
            r0.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            okhttp3.Request$Builder r0 = r0.tag(r1)
            okhttp3.Request$Builder r0 = r0.url(r10)
            java.lang.String r1 = r9.getCookie(r10)
            if (r1 == 0) goto L3f
            java.lang.String r2 = "Cookie"
            r0.addHeader(r2, r1)
        L3f:
            java.lang.String r1 = "origin"
            r2 = 0
            r3 = 1
            if (r12 == 0) goto La2
            java.lang.String r4 = "headers"
            boolean r5 = r12.hasKey(r4)
            if (r5 == 0) goto La2
            com.facebook.react.bridge.ReadableType r5 = r12.getType(r4)
            com.facebook.react.bridge.ReadableType r6 = com.facebook.react.bridge.ReadableType.Map
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto La2
            com.facebook.react.bridge.ReadableMap r12 = r12.getMap(r4)
            com.facebook.react.bridge.ReadableMapKeySetIterator r4 = r12.keySetIterator()
            r5 = r2
        L62:
            boolean r6 = r4.hasNextKey()
            if (r6 == 0) goto La0
            java.lang.String r6 = r4.nextKey()
            com.facebook.react.bridge.ReadableType r7 = com.facebook.react.bridge.ReadableType.String
            com.facebook.react.bridge.ReadableType r8 = r12.getType(r6)
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L87
            boolean r7 = r6.equalsIgnoreCase(r1)
            if (r7 == 0) goto L7f
            r5 = r3
        L7f:
            java.lang.String r7 = r12.getString(r6)
            r0.addHeader(r6, r7)
            goto L62
        L87:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Ignoring: requested "
            r7.<init>(r8)
            r7.append(r6)
            java.lang.String r6 = ", value not a string"
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "ReactNative"
            com.facebook.common.logging.FLog.w(r7, r6)
            goto L62
        La0:
            if (r5 != 0) goto La9
        La2:
            java.lang.String r10 = getDefaultOrigin(r10)
            r0.addHeader(r1, r10)
        La9:
            if (r11 == 0) goto Lf8
            int r10 = r11.size()
            if (r10 <= 0) goto Lf8
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r12 = ""
            r10.<init>(r12)
        Lb8:
            int r1 = r11.size()
            if (r2 >= r1) goto Ldd
            java.lang.String r1 = r11.getString(r2)
            java.lang.String r1 = r1.trim()
            boolean r4 = r1.isEmpty()
            if (r4 != 0) goto Lda
            java.lang.String r4 = ","
            boolean r5 = r1.contains(r4)
            if (r5 != 0) goto Lda
            r10.append(r1)
            r10.append(r4)
        Lda:
            int r2 = r2 + 1
            goto Lb8
        Ldd:
            int r11 = r10.length()
            if (r11 <= 0) goto Lf8
            int r11 = r10.length()
            int r11 = r11 - r3
            int r1 = r10.length()
            r10.replace(r11, r1, r12)
            java.lang.String r11 = "Sec-WebSocket-Protocol"
            java.lang.String r10 = r10.toString()
            r0.addHeader(r11, r10)
        Lf8:
            okhttp3.Request r10 = r0.build()
            com.facebook.react.modules.websocket.WebSocketModule$1 r11 = new com.facebook.react.modules.websocket.WebSocketModule$1
            r11.<init>()
            r14.newWebSocket(r10, r11)
            okhttp3.Dispatcher r10 = r14.dispatcher()
            java.util.concurrent.ExecutorService r10 = r10.executorService()
            r10.shutdown()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.websocket.WebSocketModule.connect(java.lang.String, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableMap, double):void");
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void close(double d, String str, double d2) {
        int i = (int) d2;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            return;
        }
        try {
            webSocket.close((int) d, str);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
        } catch (Exception e) {
            FLog.e(ReactConstants.TAG, "Could not close WebSocket connection for id " + i, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void send(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString(DiscardedEvent.JsonKeys.REASON, "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void sendBinary(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString(DiscardedEvent.JsonKeys.REASON, "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.decodeBase64(str));
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void sendBinary(ByteString byteString, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString(DiscardedEvent.JsonKeys.REASON, "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(byteString);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void ping(double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString("message", "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString(DiscardedEvent.JsonKeys.REASON, "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWebSocketFailed(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", i);
        createMap.putString("message", str);
        sendEvent("websocketFailed", createMap);
    }

    private static String getDefaultOrigin(String str) {
        char c;
        try {
            URI uri = new URI(str);
            String scheme = uri.getScheme();
            int hashCode = scheme.hashCode();
            String str2 = "https";
            if (hashCode == 3804) {
                if (scheme.equals("ws")) {
                    c = 1;
                }
                c = 65535;
            } else if (hashCode == 118039) {
                if (scheme.equals("wss")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 3213448) {
                if (hashCode == 99617003 && scheme.equals("https")) {
                    c = 3;
                }
                c = 65535;
            } else {
                if (scheme.equals("http")) {
                    c = 2;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c != 1) {
                    str2 = "";
                    if (c == 2 || c == 3) {
                        str2 = "" + uri.getScheme();
                    }
                } else {
                    str2 = "http";
                }
            }
            return uri.getPort() != -1 ? String.format("%s://%s:%s", str2, uri.getHost(), Integer.valueOf(uri.getPort())) : String.format("%s://%s", str2, uri.getHost());
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to set " + str + " as default origin header");
        }
    }

    private String getCookie(String str) {
        try {
            List<String> list = this.mCookieHandler.get(new URI(getDefaultOrigin(str)), new HashMap()).get("Cookie");
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (IOException | URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        }
    }
}
