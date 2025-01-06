package com.facebook.react.packagerconnection;

import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.ReconnectingWebSocket;
import com.google.firebase.messaging.Constants;
import io.sentry.protocol.App;
import io.sentry.protocol.Device;
import io.sentry.protocol.Message;
import io.sentry.protocol.Request;
import java.util.Map;
import okio.ByteString;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class JSPackagerClient implements ReconnectingWebSocket.MessageCallback {
    private static final int PROTOCOL_VERSION = 2;
    private static final String TAG = "JSPackagerClient";
    private Map<String, RequestHandler> mRequestHandlers;
    private ReconnectingWebSocket mWebSocket;

    private class ResponderImpl implements Responder {
        private Object mId;

        public ResponderImpl(Object obj) {
            this.mId = obj;
        }

        @Override // com.facebook.react.packagerconnection.Responder
        public void respond(Object obj) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("version", 2);
                jSONObject.put("id", this.mId);
                jSONObject.put("result", obj);
                JSPackagerClient.this.mWebSocket.sendMessage(jSONObject.toString());
            } catch (Exception e) {
                FLog.e(JSPackagerClient.TAG, "Responding failed", e);
            }
        }

        @Override // com.facebook.react.packagerconnection.Responder
        public void error(Object obj) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("version", 2);
                jSONObject.put("id", this.mId);
                jSONObject.put(Constants.IPC_BUNDLE_KEY_SEND_ERROR, obj);
                JSPackagerClient.this.mWebSocket.sendMessage(jSONObject.toString());
            } catch (Exception e) {
                FLog.e(JSPackagerClient.TAG, "Responding with error failed", e);
            }
        }
    }

    public JSPackagerClient(String str, PackagerConnectionSettings packagerConnectionSettings, Map<String, RequestHandler> map) {
        this(str, packagerConnectionSettings, map, null);
    }

    public JSPackagerClient(String str, PackagerConnectionSettings packagerConnectionSettings, Map<String, RequestHandler> map, ReconnectingWebSocket.ConnectionCallback connectionCallback) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("ws").encodedAuthority(packagerConnectionSettings.getDebugServerHost()).appendPath("message").appendQueryParameter(Device.TYPE, AndroidInfoHelpers.getFriendlyDeviceName()).appendQueryParameter(App.TYPE, packagerConnectionSettings.getPackageName()).appendQueryParameter("clientid", str);
        this.mWebSocket = new ReconnectingWebSocket(builder.build().toString(), this, connectionCallback);
        this.mRequestHandlers = map;
    }

    public void init() {
        this.mWebSocket.connect();
    }

    public void close() {
        this.mWebSocket.closeQuietly();
    }

    @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.MessageCallback
    public void onMessage(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("version");
            String optString = jSONObject.optString(Request.JsonKeys.METHOD);
            Object opt = jSONObject.opt("id");
            Object opt2 = jSONObject.opt(Message.JsonKeys.PARAMS);
            if (optInt != 2) {
                FLog.e(TAG, "Message with incompatible or missing version of protocol received: " + optInt);
                return;
            }
            if (optString == null) {
                abortOnMessage(opt, "No method provided");
                return;
            }
            RequestHandler requestHandler = this.mRequestHandlers.get(optString);
            if (requestHandler == null) {
                abortOnMessage(opt, "No request handler for method: " + optString);
            } else if (opt == null) {
                requestHandler.onNotification(opt2);
            } else {
                requestHandler.onRequest(opt2, new ResponderImpl(opt));
            }
        } catch (Exception e) {
            FLog.e(TAG, "Handling the message failed", e);
        }
    }

    @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.MessageCallback
    public void onMessage(ByteString byteString) {
        FLog.w(TAG, "Websocket received message with payload of unexpected type binary");
    }

    private void abortOnMessage(Object obj, String str) {
        if (obj != null) {
            new ResponderImpl(obj).error(str);
        }
        FLog.e(TAG, "Handling the message failed with reason: " + str);
    }
}
