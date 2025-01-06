package expo.modules.updates.loader;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.google.common.net.HttpHeaders;
import expo.modules.easclient.EASClientID;
import expo.modules.manifests.core.Manifest;
import expo.modules.structuredheaders.Dictionary;
import expo.modules.structuredheaders.OuterList;
import expo.modules.structuredheaders.StringItem;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.codesigning.CodeSigningConfiguration;
import expo.modules.updates.codesigning.ExpoProjectInformation;
import expo.modules.updates.codesigning.SignatureValidationResult;
import expo.modules.updates.codesigning.ValidationResult;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.launcher.NoDatabaseLauncher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.manifest.ResponseHeaderData;
import expo.modules.updates.manifest.ResponsePartHeaderData;
import expo.modules.updates.manifest.ResponsePartInfo;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.manifest.UpdateFactory;
import expo.modules.updates.selectionpolicy.SelectionPolicies;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryEvent;
import io.sentry.protocol.Response;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.brotli.BrotliInterceptor;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.ParameterParser;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FileDownloader.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 :2\u00020\u0001:\u00069:;<=>B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013J*\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u001cH\u0002J \u0010\u001b\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010\u001f\u001a\u00020\r2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\"J\"\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0012\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0018H\u0002J,\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020%2\b\u0010-\u001a\u0004\u0018\u00010!2\b\u0010&\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0012\u001a\u00020.H\u0002J(\u0010/\u001a\u00020\r2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\"H\u0002J\u001d\u00105\u001a\u00020\r2\u0006\u00106\u001a\u0002072\u0006\u0010\u0012\u001a\u00020\"H\u0000¢\u0006\u0002\b8R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lexpo/modules/updates/loader/FileDownloader;", "", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "client", "Lokhttp3/OkHttpClient;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lokhttp3/OkHttpClient;)V", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;)V", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/updates/logging/UpdatesLogger;", "downloadAsset", "", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "destinationDirectory", "Ljava/io/File;", "callback", "Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "downloadAssetAndVerifyHashAndWriteToPath", SentryBaseEvent.JsonKeys.REQUEST, "Lokhttp3/Request;", "expectedBase64URLEncodedSHA256Hash", "", "destination", "Lexpo/modules/updates/loader/FileDownloader$FileDownloadCallback;", "downloadData", "Lokhttp3/Callback;", "isRetry", "", "downloadRemoteUpdate", "extraHeaders", "Lorg/json/JSONObject;", "Lexpo/modules/updates/loader/FileDownloader$RemoteUpdateDownloadCallback;", "parseDirective", "directiveResponsePartInfo", "Lexpo/modules/updates/manifest/ResponsePartInfo;", "certificateChainFromManifestResponse", "Lexpo/modules/updates/loader/FileDownloader$ParseDirectiveCallback;", "parseHeaders", "Lokhttp3/Headers;", "text", "parseManifest", "manifestResponseInfo", "extensions", "Lexpo/modules/updates/loader/FileDownloader$ParseManifestCallback;", "parseMultipartRemoteUpdateResponse", "responseBody", "Lokhttp3/ResponseBody;", "responseHeaderData", "Lexpo/modules/updates/manifest/ResponseHeaderData;", "boundary", "parseRemoteUpdateResponse", Response.TYPE, "Lokhttp3/Response;", "parseRemoteUpdateResponse$expo_updates_release", "AssetDownloadCallback", "Companion", "FileDownloadCallback", "ParseDirectiveCallback", "ParseManifestCallback", "RemoteUpdateDownloadCallback", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FileDownloader {
    private static final String CRLF = "\r\n";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "FileDownloader";
    private OkHttpClient client;
    private final UpdatesConfiguration configuration;
    private final UpdatesLogger logger;

    /* compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "assetEntity", "Lexpo/modules/updates/db/entity/AssetEntity;", "onSuccess", "isNew", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface AssetDownloadCallback {
        void onFailure(Exception e, AssetEntity assetEntity);

        void onSuccess(AssetEntity assetEntity, boolean isNew);
    }

    /* compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lexpo/modules/updates/loader/FileDownloader$FileDownloadCallback;", "", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "file", "Ljava/io/File;", "hash", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface FileDownloadCallback {
        void onFailure(Exception e);

        void onSuccess(File file, byte[] hash);
    }

    /* compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lexpo/modules/updates/loader/FileDownloader$ParseDirectiveCallback;", "", "onFailure", "", "message", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "directiveUpdateResponsePart", "Lexpo/modules/updates/loader/UpdateResponsePart$DirectiveUpdateResponsePart;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ParseDirectiveCallback {
        void onFailure(String message, Exception e);

        void onSuccess(UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart);
    }

    /* compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lexpo/modules/updates/loader/FileDownloader$ParseManifestCallback;", "", "onFailure", "", "message", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "manifestUpdateResponsePart", "Lexpo/modules/updates/loader/UpdateResponsePart$ManifestUpdateResponsePart;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ParseManifestCallback {
        void onFailure(String message, Exception e);

        void onSuccess(UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart);
    }

    /* compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lexpo/modules/updates/loader/FileDownloader$RemoteUpdateDownloadCallback;", "", "onFailure", "", "message", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "updateResponse", "Lexpo/modules/updates/loader/UpdateResponse;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface RemoteUpdateDownloadCallback {
        void onFailure(String message, Exception e);

        void onSuccess(UpdateResponse updateResponse);
    }

    public FileDownloader(Context context, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.configuration = configuration;
        this.client = new OkHttpClient.Builder().cache(INSTANCE.getCache(context)).connectTimeout(Math.max(configuration.getLaunchWaitMs(), 10000L), TimeUnit.MILLISECONDS).readTimeout(Math.max(configuration.getLaunchWaitMs(), 10000L), TimeUnit.MILLISECONDS).addInterceptor(BrotliInterceptor.INSTANCE).build();
        this.logger = new UpdatesLogger(context);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileDownloader(Context context, UpdatesConfiguration configuration, OkHttpClient client) {
        this(context, configuration);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
    }

    private final void downloadAssetAndVerifyHashAndWriteToPath(final Request request, final String expectedBase64URLEncodedSHA256Hash, final File destination, final FileDownloadCallback callback) {
        downloadData(request, new Callback() { // from class: expo.modules.updates.loader.FileDownloader$downloadAssetAndVerifyHashAndWriteToPath$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                UpdatesLogger updatesLogger;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                updatesLogger = FileDownloader.this.logger;
                IOException iOException = e;
                updatesLogger.error("Failed to download asset from " + request.url(), UpdatesErrorCode.AssetsFailedToLoad, iOException);
                callback.onFailure(iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                UpdatesLogger updatesLogger;
                UpdatesLogger updatesLogger2;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    ResponseBody body = response.body();
                    Intrinsics.checkNotNull(body);
                    Exception exc = new Exception("Network request failed: " + body.string());
                    updatesLogger2 = FileDownloader.this.logger;
                    updatesLogger2.error("Failed to download file from " + request.url(), UpdatesErrorCode.AssetsFailedToLoad, exc);
                    callback.onFailure(exc);
                    return;
                }
                try {
                    ResponseBody body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    InputStream byteStream = body2.byteStream();
                    File file = destination;
                    String str = expectedBase64URLEncodedSHA256Hash;
                    try {
                        callback.onSuccess(file, UpdatesUtils.INSTANCE.verifySHA256AndWriteToFile(byteStream, file, str));
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(byteStream, null);
                    } finally {
                    }
                } catch (Exception e) {
                    updatesLogger = FileDownloader.this.logger;
                    updatesLogger.error("Failed to write file from " + request.url() + " to destination " + destination, UpdatesErrorCode.AssetsFailedToLoad, e);
                    callback.onFailure(e);
                }
            }
        });
    }

    public final void parseRemoteUpdateResponse$expo_updates_release(okhttp3.Response response, final RemoteUpdateDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Headers headers = response.headers();
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData(headers.get("expo-protocol-version"), headers.get("expo-server-defined-headers"), headers.get("expo-manifest-filters"));
        ResponseBody body = response.body();
        if (response.code() == 204 || body == null) {
            if (responseHeaderData.getProtocolVersion() != null && responseHeaderData.getProtocolVersion().intValue() > 0) {
                callback.onSuccess(new UpdateResponse(responseHeaderData, null, null));
                return;
            } else {
                UpdatesLogger.error$default(this.logger, "Missing body in remote update", UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                callback.onFailure("Missing body in remote update", new IOException("Missing body in remote update"));
                return;
            }
        }
        String header$default = okhttp3.Response.header$default(response, "content-type", null, 2, null);
        if (header$default == null) {
            header$default = "";
        }
        if (StringsKt.startsWith(header$default, FileUploadBase.MULTIPART, true)) {
            String str = new ParameterParser().parse(header$default, ';').get("boundary");
            if (str == null) {
                UpdatesLogger.error$default(this.logger, "Missing boundary in multipart remote update content-type", UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                callback.onFailure("Missing boundary in multipart remote update content-type", new IOException("Missing boundary in multipart remote update content-type"));
                return;
            } else {
                parseMultipartRemoteUpdateResponse(body, responseHeaderData, str, callback);
                return;
            }
        }
        ResponsePartHeaderData responsePartHeaderData = new ResponsePartHeaderData(headers.get("expo-signature"));
        ResponseBody body2 = response.body();
        Intrinsics.checkNotNull(body2);
        parseManifest(new ResponsePartInfo(responseHeaderData, responsePartHeaderData, body2.string()), null, null, new ParseManifestCallback() { // from class: expo.modules.updates.loader.FileDownloader$parseRemoteUpdateResponse$1
            @Override // expo.modules.updates.loader.FileDownloader.ParseManifestCallback
            public void onFailure(String message, Exception e) {
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(e, "e");
                FileDownloader.RemoteUpdateDownloadCallback.this.onFailure(message, e);
            }

            @Override // expo.modules.updates.loader.FileDownloader.ParseManifestCallback
            public void onSuccess(UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart) {
                Intrinsics.checkNotNullParameter(manifestUpdateResponsePart, "manifestUpdateResponsePart");
                FileDownloader.RemoteUpdateDownloadCallback.this.onSuccess(new UpdateResponse(responseHeaderData, manifestUpdateResponsePart, null));
            }
        });
    }

    private final Headers parseHeaders(String text) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : StringsKt.split$default((CharSequence) text, new String[]{"\r\n"}, false, 0, 6, (Object) null)) {
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str, ":", 0, false, 6, (Object) null);
            if (indexOf$default != -1) {
                String substring = str.substring(0, indexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                String obj = StringsKt.trim((CharSequence) substring).toString();
                String substring2 = str.substring(indexOf$default + 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                linkedHashMap.put(obj, StringsKt.trim((CharSequence) substring2).toString());
            }
        }
        return Headers.INSTANCE.of(linkedHashMap);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    private final void parseMultipartRemoteUpdateResponse(ResponseBody responseBody, final ResponseHeaderData responseHeaderData, String boundary, final RemoteUpdateDownloadCallback callback) {
        JSONObject jSONObject;
        String str;
        InputStream byteStream = responseBody.byteStream();
        byte[] bytes = boundary.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        MultipartStream multipartStream = new MultipartStream(byteStream, bytes);
        try {
            ResponsePartInfo responsePartInfo = null;
            String str2 = null;
            Pair pair = null;
            Pair pair2 = null;
            String str3 = null;
            for (boolean skipPreamble = multipartStream.skipPreamble(); skipPreamble; skipPreamble = multipartStream.readBoundary()) {
                String readHeaders = multipartStream.readHeaders();
                Intrinsics.checkNotNullExpressionValue(readHeaders, "readHeaders(...)");
                Headers parseHeaders = parseHeaders(readHeaders);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                multipartStream.readBodyData(byteArrayOutputStream);
                String str4 = parseHeaders.get("content-disposition");
                if (str4 != null && (str = new ParameterParser().parse(str4, ';').get("name")) != null) {
                    switch (str.hashCode()) {
                        case -1809421292:
                            if (str.equals("extensions")) {
                                str2 = byteArrayOutputStream.toString();
                                break;
                            } else {
                                break;
                            }
                        case -1044926951:
                            if (str.equals("certificate_chain")) {
                                str3 = byteArrayOutputStream.toString();
                                break;
                            } else {
                                break;
                            }
                        case -962590641:
                            if (str.equals("directive")) {
                                String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                                Intrinsics.checkNotNullExpressionValue(byteArrayOutputStream2, "toString(...)");
                                pair2 = new Pair(byteArrayOutputStream2, parseHeaders);
                                break;
                            } else {
                                break;
                            }
                        case 130625071:
                            if (str.equals("manifest")) {
                                String byteArrayOutputStream3 = byteArrayOutputStream.toString();
                                Intrinsics.checkNotNullExpressionValue(byteArrayOutputStream3, "toString(...)");
                                pair = new Pair(byteArrayOutputStream3, parseHeaders);
                                break;
                            } else {
                                break;
                            }
                    }
                }
            }
            if (str2 != null) {
                try {
                    jSONObject = new JSONObject(str2);
                } catch (Exception e) {
                    UpdatesLogger.error$default(this.logger, "Failed to parse multipart remote update extensions", UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                    callback.onFailure("Failed to parse multipart remote update extensions", e);
                    return;
                }
            } else {
                jSONObject = null;
            }
            if (this.configuration.getEnableExpoUpdatesProtocolV0CompatibilityMode() && pair == null) {
                UpdatesLogger.error$default(this.logger, "Multipart response missing manifest part. Manifest is required in version 0 of the expo-updates protocol. This may be due to the update being a rollback or other directive.", UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                callback.onFailure("Multipart response missing manifest part. Manifest is required in version 0 of the expo-updates protocol. This may be due to the update being a rollback or other directive.", new IOException("Multipart response missing manifest part. Manifest is required in version 0 of the expo-updates protocol. This may be due to the update being a rollback or other directive."));
                return;
            }
            ResponsePartInfo responsePartInfo2 = pair != null ? new ResponsePartInfo(responseHeaderData, new ResponsePartHeaderData(((Headers) pair.getSecond()).get("expo-signature")), (String) pair.getFirst()) : null;
            if (!this.configuration.getEnableExpoUpdatesProtocolV0CompatibilityMode() && pair2 != null) {
                responsePartInfo = new ResponsePartInfo(responseHeaderData, new ResponsePartHeaderData(((Headers) pair2.getSecond()).get("expo-signature")), (String) pair2.getFirst());
            }
            final ResponsePartInfo responsePartInfo3 = responsePartInfo;
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            final ResponsePartInfo responsePartInfo4 = responsePartInfo2;
            JSONObject jSONObject2 = jSONObject;
            final Function0<Unit> function0 = new Function0<Unit>() { // from class: expo.modules.updates.loader.FileDownloader$parseMultipartRemoteUpdateResponse$maybeFinish$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    if (Ref.BooleanRef.this.element) {
                        return;
                    }
                    boolean z = responsePartInfo4 == null || objectRef.element != null;
                    boolean z2 = responsePartInfo3 == null || objectRef2.element != null;
                    if (z && z2) {
                        callback.onSuccess(new UpdateResponse(responseHeaderData, objectRef.element, objectRef2.element));
                    }
                }
            };
            if (responsePartInfo3 != null) {
                parseDirective(responsePartInfo3, str3, new ParseDirectiveCallback() { // from class: expo.modules.updates.loader.FileDownloader$parseMultipartRemoteUpdateResponse$1
                    @Override // expo.modules.updates.loader.FileDownloader.ParseDirectiveCallback
                    public void onFailure(String message, Exception e2) {
                        Intrinsics.checkNotNullParameter(message, "message");
                        Intrinsics.checkNotNullParameter(e2, "e");
                        if (Ref.BooleanRef.this.element) {
                            return;
                        }
                        Ref.BooleanRef.this.element = true;
                        callback.onFailure(message, e2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // expo.modules.updates.loader.FileDownloader.ParseDirectiveCallback
                    public void onSuccess(UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart) {
                        Intrinsics.checkNotNullParameter(directiveUpdateResponsePart, "directiveUpdateResponsePart");
                        objectRef2.element = directiveUpdateResponsePart;
                        function0.invoke();
                    }
                });
            }
            if (responsePartInfo2 != null) {
                parseManifest(responsePartInfo2, jSONObject2, str3, new ParseManifestCallback() { // from class: expo.modules.updates.loader.FileDownloader$parseMultipartRemoteUpdateResponse$2
                    @Override // expo.modules.updates.loader.FileDownloader.ParseManifestCallback
                    public void onFailure(String message, Exception e2) {
                        Intrinsics.checkNotNullParameter(message, "message");
                        Intrinsics.checkNotNullParameter(e2, "e");
                        if (Ref.BooleanRef.this.element) {
                            return;
                        }
                        Ref.BooleanRef.this.element = true;
                        callback.onFailure(message, e2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // expo.modules.updates.loader.FileDownloader.ParseManifestCallback
                    public void onSuccess(UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart) {
                        Intrinsics.checkNotNullParameter(manifestUpdateResponsePart, "manifestUpdateResponsePart");
                        objectRef.element = manifestUpdateResponsePart;
                        function0.invoke();
                    }
                });
            }
            if (responsePartInfo2 == null && responsePartInfo3 == null) {
                function0.invoke();
            }
        } catch (Exception e2) {
            this.logger.error("Error while reading multipart remote update response", UpdatesErrorCode.UpdateFailedToLoad, e2);
            callback.onFailure("Error while reading multipart remote update response", e2);
        }
    }

    private final void parseDirective(ResponsePartInfo directiveResponsePartInfo, String certificateChainFromManifestResponse, ParseDirectiveCallback callback) {
        try {
            String body = directiveResponsePartInfo.getBody();
            try {
                CodeSigningConfiguration codeSigningConfiguration = this.configuration.getCodeSigningConfiguration();
                if (codeSigningConfiguration != null) {
                    String signature = directiveResponsePartInfo.getResponsePartHeaderData().getSignature();
                    byte[] bytes = body.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    SignatureValidationResult validateSignature = codeSigningConfiguration.validateSignature(signature, bytes, certificateChainFromManifestResponse);
                    if (validateSignature.getValidationResult() == ValidationResult.INVALID) {
                        throw new IOException("Directive download was successful, but signature was incorrect");
                    }
                    if (validateSignature.getValidationResult() != ValidationResult.SKIPPED) {
                        UpdateDirective fromJSONString = UpdateDirective.INSTANCE.fromJSONString(body);
                        ExpoProjectInformation expoProjectInformation = validateSignature.getExpoProjectInformation();
                        if (expoProjectInformation != null) {
                            String projectId = expoProjectInformation.getProjectId();
                            SigningInfo signingInfo = fromJSONString.getSigningInfo();
                            if (!Intrinsics.areEqual(projectId, signingInfo != null ? signingInfo.getEasProjectId() : null) || !Intrinsics.areEqual(expoProjectInformation.getScopeKey(), fromJSONString.getSigningInfo().getScopeKey())) {
                                throw new CertificateException("Invalid certificate for directive project ID or scope key");
                            }
                        }
                    }
                }
                callback.onSuccess(new UpdateResponsePart.DirectiveUpdateResponsePart(UpdateDirective.INSTANCE.fromJSONString(body)));
            } catch (Exception e) {
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                callback.onFailure(message, e);
            }
        } catch (Exception e2) {
            String str = "Failed to parse directive data: " + e2.getLocalizedMessage();
            this.logger.error(str, UpdatesErrorCode.UpdateFailedToLoad, e2);
            callback.onFailure(str, e2);
        }
    }

    private final void parseManifest(ResponsePartInfo manifestResponseInfo, JSONObject extensions, String certificateChainFromManifestResponse, ParseManifestCallback callback) {
        try {
            INSTANCE.checkCodeSigningAndCreateManifest(manifestResponseInfo.getBody(), new JSONObject(manifestResponseInfo.getBody()), manifestResponseInfo.getResponseHeaderData(), manifestResponseInfo.getResponsePartHeaderData(), extensions, certificateChainFromManifestResponse, this.configuration, this.logger, callback);
        } catch (Exception e) {
            String str = "Failed to parse manifest data: " + e.getLocalizedMessage();
            this.logger.error(str, UpdatesErrorCode.UpdateFailedToLoad, e);
            callback.onFailure(str, e);
        }
    }

    public final void downloadRemoteUpdate(JSONObject extraHeaders, Context context, final RemoteUpdateDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            downloadData(INSTANCE.createRequestForRemoteUpdate$expo_updates_release(extraHeaders, this.configuration, context), new Callback() { // from class: expo.modules.updates.loader.FileDownloader$downloadRemoteUpdate$1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    UpdatesConfiguration updatesConfiguration;
                    UpdatesLogger updatesLogger;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(e, "e");
                    updatesConfiguration = FileDownloader.this.configuration;
                    String str = "Failed to download remote update from URL: " + updatesConfiguration.getUpdateUrl() + ": " + e.getLocalizedMessage();
                    updatesLogger = FileDownloader.this.logger;
                    IOException iOException = e;
                    updatesLogger.error(str, UpdatesErrorCode.UpdateFailedToLoad, iOException);
                    callback.onFailure(str, iOException);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    UpdatesConfiguration updatesConfiguration;
                    UpdatesLogger updatesLogger;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (!response.isSuccessful()) {
                        updatesConfiguration = FileDownloader.this.configuration;
                        String str = "Failed to download remote update from URL: " + updatesConfiguration.getUpdateUrl();
                        updatesLogger = FileDownloader.this.logger;
                        UpdatesLogger.error$default(updatesLogger, str, UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                        FileDownloader.RemoteUpdateDownloadCallback remoteUpdateDownloadCallback = callback;
                        ResponseBody body = response.body();
                        Intrinsics.checkNotNull(body);
                        remoteUpdateDownloadCallback.onFailure(str, new Exception(body.string()));
                        return;
                    }
                    FileDownloader.this.parseRemoteUpdateResponse$expo_updates_release(response, callback);
                }
            });
        } catch (Exception e) {
            String str = "Failed to download remote update from URL: " + this.configuration.getUpdateUrl() + ": " + e.getLocalizedMessage();
            this.logger.error(str, UpdatesErrorCode.UpdateFailedToLoad, e);
            callback.onFailure(str, e);
        }
    }

    public final void downloadAsset(final AssetEntity asset, File destinationDirectory, Context context, final AssetDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (asset.getUrl() == null) {
            String str = "Could not download asset " + asset.getKey() + " with no URL";
            UpdatesLogger.error$default(this.logger, str, UpdatesErrorCode.AssetsFailedToLoad, null, 4, null);
            callback.onFailure(new Exception(str), asset);
            return;
        }
        final String createFilenameForAsset = UpdatesUtils.INSTANCE.createFilenameForAsset(asset);
        File file = new File(destinationDirectory, createFilenameForAsset);
        if (file.exists()) {
            asset.setRelativePath(createFilenameForAsset);
            callback.onSuccess(asset, false);
            return;
        }
        try {
            downloadAssetAndVerifyHashAndWriteToPath(INSTANCE.createRequestForAsset$expo_updates_release(asset, this.configuration, context), asset.getExpectedHash(), file, new FileDownloadCallback() { // from class: expo.modules.updates.loader.FileDownloader$downloadAsset$1
                @Override // expo.modules.updates.loader.FileDownloader.FileDownloadCallback
                public void onFailure(Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    FileDownloader.AssetDownloadCallback.this.onFailure(e, asset);
                }

                @Override // expo.modules.updates.loader.FileDownloader.FileDownloadCallback
                public void onSuccess(File file2, byte[] hash) {
                    Intrinsics.checkNotNullParameter(file2, "file");
                    Intrinsics.checkNotNullParameter(hash, "hash");
                    asset.setDownloadTime(new Date());
                    asset.setRelativePath(createFilenameForAsset);
                    asset.setHash(hash);
                    FileDownloader.AssetDownloadCallback.this.onSuccess(asset, true);
                }
            });
        } catch (Exception e) {
            this.logger.error("Failed to download asset " + asset.getKey() + ": " + e.getLocalizedMessage(), UpdatesErrorCode.AssetsFailedToLoad, e);
            callback.onFailure(e, asset);
        }
    }

    private final void downloadData(Request request, Callback callback) {
        downloadData(request, callback, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadData(final Request request, final Callback callback, final boolean isRetry) {
        this.client.newCall(request).enqueue(new Callback() { // from class: expo.modules.updates.loader.FileDownloader$downloadData$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                if (!isRetry) {
                    this.downloadData(request, callback, true);
                } else {
                    callback.onFailure(call, e);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                callback.onResponse(call, response);
            }
        });
    }

    /* compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eJ'\u0010\u001f\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b!J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J*\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010*J\u0016\u0010,\u001a\u00020-*\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lexpo/modules/updates/loader/FileDownloader$Companion;", "", "()V", "CRLF", "", "TAG", "kotlin.jvm.PlatformType", "checkCodeSigningAndCreateManifest", "", "bodyString", "preManifest", "Lorg/json/JSONObject;", "responseHeaderData", "Lexpo/modules/updates/manifest/ResponseHeaderData;", "responsePartHeaderData", "Lexpo/modules/updates/manifest/ResponsePartHeaderData;", "extensions", "certificateChainFromManifestResponse", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/updates/logging/UpdatesLogger;", "callback", "Lexpo/modules/updates/loader/FileDownloader$ParseManifestCallback;", "createRequestForAsset", "Lokhttp3/Request;", "assetEntity", "Lexpo/modules/updates/db/entity/AssetEntity;", "context", "Landroid/content/Context;", "createRequestForAsset$expo_updates_release", "createRequestForRemoteUpdate", "extraHeaders", "createRequestForRemoteUpdate$expo_updates_release", "getCache", "Lokhttp3/Cache;", "getCacheDirectory", "Ljava/io/File;", "getExtraHeadersForRemoteUpdateRequest", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "embeddedUpdate", "addHeadersFromJSONObject", "Lokhttp3/Request$Builder;", "headers", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void checkCodeSigningAndCreateManifest(String bodyString, JSONObject preManifest, ResponseHeaderData responseHeaderData, ResponsePartHeaderData responsePartHeaderData, JSONObject extensions, String certificateChainFromManifestResponse, UpdatesConfiguration configuration, UpdatesLogger logger, ParseManifestCallback callback) throws Exception {
            preManifest.put("isVerified", false);
            try {
                CodeSigningConfiguration codeSigningConfiguration = configuration.getCodeSigningConfiguration();
                if (codeSigningConfiguration != null) {
                    String signature = responsePartHeaderData.getSignature();
                    byte[] bytes = bodyString.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    SignatureValidationResult validateSignature = codeSigningConfiguration.validateSignature(signature, bytes, certificateChainFromManifestResponse);
                    if (validateSignature.getValidationResult() == ValidationResult.INVALID) {
                        throw new IOException("Manifest download was successful, but signature was incorrect");
                    }
                    if (validateSignature.getValidationResult() != ValidationResult.SKIPPED) {
                        Manifest manifest = UpdateFactory.INSTANCE.getUpdate(preManifest, responseHeaderData, extensions, configuration).getManifest();
                        ExpoProjectInformation expoProjectInformation = validateSignature.getExpoProjectInformation();
                        if (expoProjectInformation != null && (!Intrinsics.areEqual(expoProjectInformation.getProjectId(), manifest.getEASProjectID()) || !Intrinsics.areEqual(expoProjectInformation.getScopeKey(), manifest.getScopeKey()))) {
                            throw new CertificateException("Invalid certificate for manifest project ID or scope key");
                        }
                        UpdatesLogger.info$default(logger, "Update code signature verified successfully", null, 2, null);
                        preManifest.put("isVerified", true);
                    }
                }
                Update update = UpdateFactory.INSTANCE.getUpdate(preManifest, responseHeaderData, extensions, configuration);
                SelectionPolicies selectionPolicies = SelectionPolicies.INSTANCE;
                UpdateEntity updateEntity = update.getUpdateEntity();
                Intrinsics.checkNotNull(updateEntity);
                if (!selectionPolicies.matchesFilters(updateEntity, responseHeaderData.getManifestFilters())) {
                    callback.onFailure("Downloaded manifest is invalid; provides filters that do not match its content", new Exception("Downloaded manifest is invalid; provides filters that do not match its content"));
                } else {
                    callback.onSuccess(new UpdateResponsePart.ManifestUpdateResponsePart(update));
                }
            } catch (Exception e) {
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                UpdatesLogger.error$default(logger, message, UpdatesErrorCode.UpdateCodeSigningError, null, 4, null);
                String message2 = e.getMessage();
                Intrinsics.checkNotNull(message2);
                callback.onFailure(message2, e);
            }
        }

        private final Request.Builder addHeadersFromJSONObject(Request.Builder builder, JSONObject jSONObject) {
            JSONObject jSONObject2;
            if (jSONObject == null) {
                return builder;
            }
            Iterator<String> keys = jSONObject.keys();
            Intrinsics.checkNotNullExpressionValue(keys, "keys(...)");
            for (String str : SequencesKt.asSequence(keys)) {
                Intrinsics.checkNotNull(str);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                    String string = jSONObject.getString(str);
                    if (string == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                    }
                    jSONObject2 = string;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    jSONObject2 = Double.valueOf(jSONObject.getDouble(str));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    jSONObject2 = Integer.valueOf(jSONObject.getInt(str));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    jSONObject2 = Long.valueOf(jSONObject.getLong(str));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    jSONObject2 = Boolean.valueOf(jSONObject.getBoolean(str));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = jSONObject.getJSONArray(str);
                    if (jSONArray == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                    }
                    jSONObject2 = jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject(str);
                    if (jSONObject3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                    }
                    jSONObject2 = jSONObject3;
                } else {
                    jSONObject2 = jSONObject.get(str);
                    if (jSONObject2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                    }
                }
                builder.header(str, jSONObject2.toString());
            }
            return builder;
        }

        public final Request createRequestForAsset$expo_updates_release(AssetEntity assetEntity, UpdatesConfiguration configuration, Context context) {
            Intrinsics.checkNotNullParameter(assetEntity, "assetEntity");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            Intrinsics.checkNotNullParameter(context, "context");
            Request.Builder builder = new Request.Builder();
            Uri url = assetEntity.getUrl();
            Intrinsics.checkNotNull(url);
            String uri = url.toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            Request.Builder header = addHeadersFromJSONObject(builder.url(uri), assetEntity.getExtraRequestHeaders()).header("Expo-Platform", "android").header("Expo-Protocol-Version", "1").header("Expo-API-Version", "1").header("Expo-Updates-Environment", "BARE");
            String uuid = new EASClientID(context).getUuid().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
            Request.Builder header2 = header.header("EAS-Client-ID", uuid);
            for (Map.Entry<String, String> entry : configuration.getRequestHeaders().entrySet()) {
                header2.header(entry.getKey(), entry.getValue());
            }
            return header2.build();
        }

        public final Request createRequestForRemoteUpdate$expo_updates_release(JSONObject extraHeaders, UpdatesConfiguration configuration, Context context) {
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            Intrinsics.checkNotNullParameter(context, "context");
            Request.Builder builder = new Request.Builder();
            String uri = configuration.getUpdateUrl().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            Request.Builder header = addHeadersFromJSONObject(builder.url(uri), extraHeaders).header(HttpHeaders.ACCEPT, "multipart/mixed,application/expo+json,application/json").header("Expo-Platform", "android").header("Expo-Protocol-Version", "1").header("Expo-API-Version", "1").header("Expo-Updates-Environment", "BARE").header("Expo-JSON-Error", "true");
            String uuid = new EASClientID(context).getUuid().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
            Request.Builder header2 = header.header("EAS-Client-ID", uuid);
            String runtimeVersionRaw = configuration.getRuntimeVersionRaw();
            String str = runtimeVersionRaw;
            if (str != null && str.length() != 0) {
                header2.header("Expo-Runtime-Version", runtimeVersionRaw);
            }
            String consumeErrorLog = NoDatabaseLauncher.INSTANCE.consumeErrorLog(context);
            if (consumeErrorLog != null) {
                String substring = consumeErrorLog.substring(0, Math.min(1024, consumeErrorLog.length()));
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                header2.header("Expo-Fatal-Error", substring);
            }
            for (Map.Entry<String, String> entry : configuration.getRequestHeaders().entrySet()) {
                header2.header(entry.getKey(), entry.getValue());
            }
            CodeSigningConfiguration codeSigningConfiguration = configuration.getCodeSigningConfiguration();
            if (codeSigningConfiguration != null) {
                header2.header("expo-expect-signature", codeSigningConfiguration.getAcceptSignatureHeader());
            }
            return header2.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Cache getCache(Context context) {
            return new Cache(getCacheDirectory(context), 52428800);
        }

        private final File getCacheDirectory(Context context) {
            return new File(context.getCacheDir(), "okhttp");
        }

        public final JSONObject getExtraHeadersForRemoteUpdateRequest(UpdatesDatabase database, UpdatesConfiguration configuration, UpdateEntity launchedUpdate, UpdateEntity embeddedUpdate) {
            Intrinsics.checkNotNullParameter(database, "database");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            JSONObject serverDefinedHeaders = ManifestMetadata.getServerDefinedHeaders(database, configuration);
            if (serverDefinedHeaders == null) {
                serverDefinedHeaders = new JSONObject();
            }
            Map<String, String> extraParams = ManifestMetadata.INSTANCE.getExtraParams(database, configuration);
            if (extraParams != null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(extraParams.size()));
                Iterator<T> it = extraParams.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    linkedHashMap.put(entry.getKey(), StringItem.valueOf((String) entry.getValue()));
                }
                serverDefinedHeaders.put("Expo-Extra-Params", Dictionary.valueOf(linkedHashMap).serialize());
            }
            if (launchedUpdate != null) {
                String uuid = launchedUpdate.getId().toString();
                Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
                String lowerCase = uuid.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                serverDefinedHeaders.put("Expo-Current-Update-ID", lowerCase);
            }
            if (embeddedUpdate != null) {
                String uuid2 = embeddedUpdate.getId().toString();
                Intrinsics.checkNotNullExpressionValue(uuid2, "toString(...)");
                String lowerCase2 = uuid2.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                serverDefinedHeaders.put("Expo-Embedded-Update-ID", lowerCase2);
            }
            List<UUID> loadRecentUpdateIdsWithFailedLaunch = database.updateDao().loadRecentUpdateIdsWithFailedLaunch();
            if (!loadRecentUpdateIdsWithFailedLaunch.isEmpty()) {
                List<UUID> list = loadRecentUpdateIdsWithFailedLaunch;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList.add(StringItem.valueOf(((UUID) it2.next()).toString()));
                }
                serverDefinedHeaders.put("Expo-Recent-Failed-Update-IDs", OuterList.valueOf(arrayList).serialize());
            }
            return serverDefinedHeaders;
        }
    }
}
