package expo.modules.filesystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.tracing.Trace;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.BaseJavaModule;
import com.google.common.net.HttpHeaders;
import expo.modules.core.errors.ModuleDestroyedException;
import expo.modules.filesystem.FileSystemModule;
import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import expo.modules.interfaces.filesystem.FilePermissionModuleInterface;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.OnActivityResultPayload;
import expo.modules.kotlin.exception.Exceptions;
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
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import io.sentry.protocol.Message;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KType;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import okio.Source;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/* compiled from: FileSystemModule.kt */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001:\u0005VWXYZB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J(\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010%\u001a\u00020&H\u0017J\u0018\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*H\u0082@¢\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u001aH\u0002J\u0018\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\u00162\u0006\u00101\u001a\u000202H\u0002J \u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\u00162\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0012H\u0002J\u0010\u00104\u001a\u00020-2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u00105\u001a\u0002062\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u00107\u001a\u0002082\u0006\u00100\u001a\u00020\u0016H\u0002J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u000208H\u0002J\u0012\u0010<\u001a\u0004\u0018\u00010=2\u0006\u00100\u001a\u00020\u0016H\u0002J\u0010\u0010>\u001a\u00020?2\u0006\u00100\u001a\u00020\u0016H\u0002J\u0010\u0010@\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010A\u001a\u0002082\u0006\u00100\u001a\u00020\u0016H\u0002J\u0012\u0010B\u001a\u0002082\b\u0010C\u001a\u0004\u0018\u00010\u0012H\u0003J\u0010\u0010D\u001a\u00020\u00122\u0006\u0010E\u001a\u00020\u0012H\u0002J\u001a\u0010F\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010G2\b\u0010H\u001a\u0004\u0018\u00010\u0012H\u0002J\u0016\u0010I\u001a\b\u0012\u0004\u0012\u0002020G2\u0006\u00100\u001a\u00020\u0016H\u0002J\u0018\u0010J\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010G2\u0006\u00100\u001a\u00020\u0016H\u0002J \u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020=2\u0006\u0010M\u001a\u00020\u001a2\u0006\u0010N\u001a\u00020\u0015H\u0002J\u0010\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0002J\f\u0010S\u001a\u00020-*\u00020\u0016H\u0002J\f\u0010T\u001a\u00020-*\u00020\u0016H\u0002J\f\u0010U\u001a\u00020\u001a*\u00020\u0016H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\u00020\u0015*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0017¨\u0006["}, d2 = {"Lexpo/modules/filesystem/FileSystemModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "client", "Lokhttp3/OkHttpClient;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "dirPermissionsRequest", "Lexpo/modules/kotlin/Promise;", "moduleCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "okHttpClient", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "taskHandlers", "", "", "Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "isSAFUri", "", "Landroid/net/Uri;", "(Landroid/net/Uri;)Z", "contentUriFromFile", "file", "Ljava/io/File;", "createRequestBody", "Lokhttp3/RequestBody;", "options", "Lexpo/modules/filesystem/FileSystemUploadOptions;", "decorator", "Lexpo/modules/filesystem/RequestBodyDecorator;", "createUploadRequest", "Lokhttp3/Request;", "url", "fileUriString", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "downloadResumableTask", "", Message.JsonKeys.PARAMS, "Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;", "(Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureDirExists", "", "dir", "ensurePermission", "uri", "permission", "Lexpo/modules/interfaces/filesystem/Permission;", "errorMsg", "forceDelete", "getFileSize", "", "getInputStream", "Ljava/io/InputStream;", "getInputStreamBytes", "", "inputStream", "getNearestSAFFile", "Landroidx/documentfile/provider/DocumentFile;", "getOutputStream", "Ljava/io/OutputStream;", "md5", "openAssetInputStream", "openResourceInputStream", "resourceName", "parseFileUri", "uriStr", "permissionsForPath", "Ljava/util/EnumSet;", "path", "permissionsForSAFUri", "permissionsForUri", "transformFilesFromSAF", "documentFile", "outputDir", "copy", "translateHeaders", "Landroid/os/Bundle;", "headers", "Lokhttp3/Headers;", "checkIfFileDirExists", "checkIfFileExists", "toFile", "DownloadResumableTaskParams", "DownloadTaskHandler", "ProgressListener", "ProgressResponseBody", "TaskHandler", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class FileSystemModule extends Module {
    private OkHttpClient client;
    private Promise dirPermissionsRequest;
    private final Map<String, TaskHandler> taskHandlers = new HashMap();
    private final CoroutineScope moduleCoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());

    /* compiled from: FileSystemModule.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$ProgressListener;", "", "update", "", "bytesRead", "", "contentLength", "done", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }

    /* compiled from: FileSystemModule.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FileSystemUploadType.values().length];
            try {
                iArr[FileSystemUploadType.BINARY_CONTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FileSystemUploadType.MULTIPART.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.AppContextLost();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        AsyncFunctionComponent asyncFunctionComponent4;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        AsyncFunctionComponent asyncFunctionComponent5;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        AsyncFunctionComponent asyncFunctionComponent6;
        AsyncFunctionComponent asyncFunctionComponent7;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3;
        AsyncFunctionComponent asyncFunctionComponent8;
        AsyncFunctionComponent asyncFunctionComponent9;
        AsyncFunctionComponent asyncFunctionComponent10;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent4;
        AsyncFunctionComponent asyncFunctionComponent11;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent5;
        AsyncFunctionComponent asyncFunctionComponent12;
        AsyncFunctionComponent asyncFunctionComponent13;
        AsyncFunctionComponent asyncFunctionComponent14;
        AsyncFunctionComponent asyncFunctionComponent15;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent6;
        FileSystemModule fileSystemModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (fileSystemModule.getClass() + ".ModuleDefinition"));
        try {
            final ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(fileSystemModule);
            moduleDefinitionBuilder.Name("ExponentFileSystem");
            moduleDefinitionBuilder.Constants(TuplesKt.to("documentDirectory", Uri.fromFile(getContext().getFilesDir()).toString() + "/"), TuplesKt.to("cacheDirectory", Uri.fromFile(getContext().getCacheDir()).toString() + "/"), TuplesKt.to("bundleDirectory", "asset:///"));
            moduleDefinitionBuilder.Events("expo-file-system.downloadProgress", "expo-file-system.uploadProgress");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$OnCreate$1
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
                    try {
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        File filesDir = fileSystemModule2.getContext().getFilesDir();
                        Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
                        fileSystemModule2.ensureDirExists(filesDir);
                        FileSystemModule fileSystemModule3 = FileSystemModule.this;
                        File cacheDir = fileSystemModule3.getContext().getCacheDir();
                        Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
                        fileSystemModule3.ensureDirExists(cacheDir);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(InfoOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(InfoOptions.class);
                }
            }))};
            Function1<Object[], Bundle> function1 = new Function1<Object[], Bundle>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$3
                {
                    super(1);
                }

                /* JADX WARN: Removed duplicated region for block: B:38:0x0142 A[Catch: FileNotFoundException -> 0x0184, TryCatch #0 {FileNotFoundException -> 0x0184, blocks: (B:27:0x00ff, B:29:0x0105, B:34:0x0114, B:36:0x011a, B:38:0x0142, B:40:0x0167, B:43:0x017e, B:44:0x0183, B:45:0x0129, B:48:0x0130, B:49:0x013a), top: B:26:0x00ff }] */
                /* JADX WARN: Removed duplicated region for block: B:43:0x017e A[Catch: FileNotFoundException -> 0x0184, TryCatch #0 {FileNotFoundException -> 0x0184, blocks: (B:27:0x00ff, B:29:0x0105, B:34:0x0114, B:36:0x011a, B:38:0x0142, B:40:0x0167, B:43:0x017e, B:44:0x0183, B:45:0x0129, B:48:0x0130, B:49:0x013a), top: B:26:0x00ff }] */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final android.os.Bundle invoke(java.lang.Object[] r17) {
                    /*
                        Method dump skipped, instructions count: 401
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$3.invoke(java.lang.Object[]):java.lang.Object");
                }
            };
            if (!Intrinsics.areEqual(Bundle.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Bundle.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Bundle.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Bundle.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Bundle.class, String.class)) {
                                asyncFunctionComponent = new StringAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                            } else {
                                asyncFunctionComponent = new AsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new FloatAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new DoubleAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new BoolAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
                }
            } else {
                asyncFunctionComponent = new IntAsyncFunctionComponent("getInfoAsync", anyTypeArr, function1);
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getInfoAsync", asyncFunctionComponent);
            moduleDefinitionBuilder.getAsyncFunctions().put("readAsStringAsync", new AsyncFunctionComponent("readAsStringAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$4
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$5
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(ReadingOptions.class);
                }
            }))}, new Function1<Object[], String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$6
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] objArr) {
                    String slashifyFilePath;
                    boolean isSAFUri;
                    InputStream openResourceInputStream;
                    InputStream openAssetInputStream;
                    File file;
                    InputStream inputStream;
                    byte[] inputStreamBytes;
                    String encodeToString;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Object obj = objArr[0];
                    ReadingOptions readingOptions = (ReadingOptions) objArr[1];
                    String str = (String) obj;
                    slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
                    Uri parse = Uri.parse(slashifyFilePath);
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemModule2.ensurePermission(parse, Permission.READ);
                    if (readingOptions.getEncoding() == EncodingType.BASE64) {
                        inputStream = FileSystemModule.this.getInputStream(parse);
                        InputStream inputStream2 = inputStream;
                        try {
                            InputStream inputStream3 = inputStream2;
                            if (readingOptions.getLength() == null || readingOptions.getPosition() == null) {
                                inputStreamBytes = FileSystemModule.this.getInputStreamBytes(inputStream3);
                                encodeToString = Base64.encodeToString(inputStreamBytes, 2);
                            } else {
                                byte[] bArr = new byte[readingOptions.getLength().intValue()];
                                inputStream3.skip(readingOptions.getPosition().intValue());
                                encodeToString = Base64.encodeToString(bArr, 0, inputStream3.read(bArr, 0, readingOptions.getLength().intValue()), 2);
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(inputStream2, null);
                            return encodeToString;
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(inputStream2, th);
                                throw th2;
                            }
                        }
                    }
                    if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                        file = FileSystemModule.this.toFile(parse);
                        return IOUtils.toString(new FileInputStream(file));
                    }
                    if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
                        openAssetInputStream = FileSystemModule.this.openAssetInputStream(parse);
                        return IOUtils.toString(openAssetInputStream);
                    }
                    if (parse.getScheme() == null) {
                        openResourceInputStream = FileSystemModule.this.openResourceInputStream(str);
                        return IOUtils.toString(openResourceInputStream);
                    }
                    isSAFUri = FileSystemModule.this.isSAFUri(parse);
                    if (isSAFUri) {
                        return IOUtils.toString(FileSystemModule.this.getContext().getContentResolver().openInputStream(parse));
                    }
                    throw new IOException("Unsupported scheme for location '" + parse + "'.");
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$7
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$8
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(WritingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$9
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(WritingOptions.class);
                }
            }))};
            Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$10
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    String slashifyFilePath;
                    OutputStream outputStream;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    WritingOptions writingOptions = (WritingOptions) objArr[2];
                    String str = (String) obj2;
                    slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) obj);
                    Uri parse = Uri.parse(slashifyFilePath);
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemModule2.ensurePermission(parse, Permission.WRITE);
                    EncodingType encoding = writingOptions.getEncoding();
                    outputStream = FileSystemModule.this.getOutputStream(parse);
                    OutputStreamWriter outputStreamWriter = outputStream;
                    try {
                        OutputStream outputStream2 = outputStreamWriter;
                        if (encoding == EncodingType.BASE64) {
                            outputStream2.write(Base64.decode(str, 0));
                        } else {
                            outputStreamWriter = new OutputStreamWriter(outputStream2);
                            try {
                                outputStreamWriter.write(str);
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(outputStreamWriter, null);
                            } finally {
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(outputStreamWriter, null);
                        return Unit.INSTANCE;
                    } finally {
                    }
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                asyncFunctionComponent2 = new StringAsyncFunctionComponent("writeAsStringAsync", anyTypeArr2, function12);
                            } else {
                                asyncFunctionComponent2 = new AsyncFunctionComponent("writeAsStringAsync", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new FloatAsyncFunctionComponent("writeAsStringAsync", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("writeAsStringAsync", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new BoolAsyncFunctionComponent("writeAsStringAsync", anyTypeArr2, function12);
                }
            } else {
                asyncFunctionComponent2 = new IntAsyncFunctionComponent("writeAsStringAsync", anyTypeArr2, function12);
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("writeAsStringAsync", asyncFunctionComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr3 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$11
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DeletingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$12
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(DeletingOptions.class);
                }
            }))};
            Function1<Object[], Unit> function13 = new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$13
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    String slashifyFilePath;
                    boolean isSAFUri;
                    DocumentFile nearestSAFFile;
                    File file;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Object obj = objArr[0];
                    DeletingOptions deletingOptions = (DeletingOptions) objArr[1];
                    slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) obj);
                    Uri parse = Uri.parse(slashifyFilePath);
                    Uri withAppendedPath = Uri.withAppendedPath(parse, "..");
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    Intrinsics.checkNotNull(withAppendedPath);
                    fileSystemModule2.ensurePermission(withAppendedPath, Permission.WRITE, "Location '" + parse + "' isn't deletable.");
                    if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                        FileSystemModule fileSystemModule3 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        file = fileSystemModule3.toFile(parse);
                        if (file.exists()) {
                            if (Build.VERSION.SDK_INT < 26) {
                                FileSystemModule.this.forceDelete(file);
                            } else {
                                FileUtils.forceDelete(file);
                            }
                        } else if (!deletingOptions.getIdempotent()) {
                            throw new FileSystemFileNotFoundException(parse);
                        }
                    } else {
                        FileSystemModule fileSystemModule4 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        isSAFUri = fileSystemModule4.isSAFUri(parse);
                        if (isSAFUri) {
                            nearestSAFFile = FileSystemModule.this.getNearestSAFFile(parse);
                            if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                nearestSAFFile.delete();
                            } else if (!deletingOptions.getIdempotent()) {
                                throw new FileSystemFileNotFoundException(parse);
                            }
                        } else {
                            throw new IOException("Unsupported scheme for location '" + parse + "'.");
                        }
                    }
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                asyncFunctionComponent3 = new StringAsyncFunctionComponent("deleteAsync", anyTypeArr3, function13);
                            } else {
                                asyncFunctionComponent3 = new AsyncFunctionComponent("deleteAsync", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent3 = new FloatAsyncFunctionComponent("deleteAsync", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent3 = new DoubleAsyncFunctionComponent("deleteAsync", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent3 = new BoolAsyncFunctionComponent("deleteAsync", anyTypeArr3, function13);
                }
            } else {
                asyncFunctionComponent3 = new IntAsyncFunctionComponent("deleteAsync", anyTypeArr3, function13);
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("deleteAsync", asyncFunctionComponent3);
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(RelocatingOptions.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("moveAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$14
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        String slashifyFilePath;
                        String slashifyFilePath2;
                        boolean isSAFUri;
                        DocumentFile nearestSAFFile;
                        File file;
                        File file2;
                        File file3;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        RelocatingOptions relocatingOptions = (RelocatingOptions) promise;
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath(relocatingOptions.getFrom());
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Uri withAppendedPath = Uri.withAppendedPath(parse, "..");
                        Intrinsics.checkNotNullExpressionValue(withAppendedPath, "withAppendedPath(...)");
                        fileSystemModule2.ensurePermission(withAppendedPath, Permission.WRITE, "Location '" + parse + "' isn't movable.");
                        slashifyFilePath2 = FileSystemModuleKt.slashifyFilePath(relocatingOptions.getTo());
                        Uri parse2 = Uri.parse(slashifyFilePath2);
                        FileSystemModule fileSystemModule3 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse2);
                        fileSystemModule3.ensurePermission(parse2, Permission.WRITE);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            FileSystemModule fileSystemModule4 = FileSystemModule.this;
                            Intrinsics.checkNotNull(parse);
                            file2 = fileSystemModule4.toFile(parse);
                            file3 = FileSystemModule.this.toFile(parse2);
                            if (!file2.renameTo(file3)) {
                                throw new FileSystemCannotMoveFileException(parse, parse2);
                            }
                            return;
                        }
                        FileSystemModule fileSystemModule5 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        isSAFUri = fileSystemModule5.isSAFUri(parse);
                        if (isSAFUri) {
                            nearestSAFFile = FileSystemModule.this.getNearestSAFFile(parse);
                            if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                file = FileSystemModule.this.toFile(parse2);
                                FileSystemModule.this.transformFilesFromSAF(nearestSAFFile, file, false);
                                return;
                            }
                            throw new FileSystemCannotMoveFileException(parse, parse2);
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr4 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RelocatingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$15
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(RelocatingOptions.class);
                    }
                }))};
                Function1<Object[], Unit> function14 = new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$16
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        String slashifyFilePath;
                        String slashifyFilePath2;
                        boolean isSAFUri;
                        DocumentFile nearestSAFFile;
                        File file;
                        File file2;
                        File file3;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        RelocatingOptions relocatingOptions = (RelocatingOptions) objArr[0];
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath(relocatingOptions.getFrom());
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Uri withAppendedPath = Uri.withAppendedPath(parse, "..");
                        Intrinsics.checkNotNullExpressionValue(withAppendedPath, "withAppendedPath(...)");
                        fileSystemModule2.ensurePermission(withAppendedPath, Permission.WRITE, "Location '" + parse + "' isn't movable.");
                        slashifyFilePath2 = FileSystemModuleKt.slashifyFilePath(relocatingOptions.getTo());
                        Uri parse2 = Uri.parse(slashifyFilePath2);
                        FileSystemModule fileSystemModule3 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse2);
                        fileSystemModule3.ensurePermission(parse2, Permission.WRITE);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            FileSystemModule fileSystemModule4 = FileSystemModule.this;
                            Intrinsics.checkNotNull(parse);
                            file2 = fileSystemModule4.toFile(parse);
                            file3 = FileSystemModule.this.toFile(parse2);
                            if (!file2.renameTo(file3)) {
                                throw new FileSystemCannotMoveFileException(parse, parse2);
                            }
                        } else {
                            FileSystemModule fileSystemModule5 = FileSystemModule.this;
                            Intrinsics.checkNotNull(parse);
                            isSAFUri = fileSystemModule5.isSAFUri(parse);
                            if (isSAFUri) {
                                nearestSAFFile = FileSystemModule.this.getNearestSAFFile(parse);
                                if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                    file = FileSystemModule.this.toFile(parse2);
                                    FileSystemModule.this.transformFilesFromSAF(nearestSAFFile, file, false);
                                } else {
                                    throw new FileSystemCannotMoveFileException(parse, parse2);
                                }
                            } else {
                                throw new IOException("Unsupported scheme for location '" + parse + "'.");
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent4 = new StringAsyncFunctionComponent("moveAsync", anyTypeArr4, function14);
                                } else {
                                    asyncFunctionComponent4 = new AsyncFunctionComponent("moveAsync", anyTypeArr4, function14);
                                }
                            } else {
                                asyncFunctionComponent4 = new FloatAsyncFunctionComponent("moveAsync", anyTypeArr4, function14);
                            }
                        } else {
                            asyncFunctionComponent4 = new DoubleAsyncFunctionComponent("moveAsync", anyTypeArr4, function14);
                        }
                    } else {
                        asyncFunctionComponent4 = new BoolAsyncFunctionComponent("moveAsync", anyTypeArr4, function14);
                    }
                } else {
                    asyncFunctionComponent4 = new IntAsyncFunctionComponent("moveAsync", anyTypeArr4, function14);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent4;
            }
            moduleDefinitionBuilder5.getAsyncFunctions().put("moveAsync", asyncFunctionWithPromiseComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder6 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(RelocatingOptions.class, Promise.class)) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("copyAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$17
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        String slashifyFilePath;
                        String slashifyFilePath2;
                        boolean isSAFUri;
                        InputStream openResourceInputStream;
                        File file;
                        InputStream openAssetInputStream;
                        File file2;
                        File file3;
                        DocumentFile nearestSAFFile;
                        File file4;
                        File file5;
                        File file6;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        RelocatingOptions relocatingOptions = (RelocatingOptions) promise;
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath(relocatingOptions.getFrom());
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemModule2.ensurePermission(parse, Permission.READ, "Location '" + parse + "' isn't readable.");
                        slashifyFilePath2 = FileSystemModuleKt.slashifyFilePath(relocatingOptions.getTo());
                        Uri parse2 = Uri.parse(slashifyFilePath2);
                        FileSystemModule fileSystemModule3 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse2);
                        fileSystemModule3.ensurePermission(parse2, Permission.WRITE);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file5 = FileSystemModule.this.toFile(parse);
                            file6 = FileSystemModule.this.toFile(parse2);
                            if (file5.isDirectory()) {
                                FileUtils.copyDirectory(file5, file6);
                                return;
                            } else {
                                FileUtils.copyFile(file5, file6);
                                return;
                            }
                        }
                        isSAFUri = FileSystemModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            nearestSAFFile = FileSystemModule.this.getNearestSAFFile(parse);
                            if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                file4 = FileSystemModule.this.toFile(parse2);
                                FileSystemModule.this.transformFilesFromSAF(nearestSAFFile, file4, true);
                                return;
                            }
                            throw new FileSystemCopyFailedException(parse);
                        }
                        if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME)) {
                            InputStream openInputStream = FileSystemModule.this.getContext().getContentResolver().openInputStream(parse);
                            file3 = FileSystemModule.this.toFile(parse2);
                            IOUtils.copy(openInputStream, new FileOutputStream(file3));
                        } else if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
                            openAssetInputStream = FileSystemModule.this.openAssetInputStream(parse);
                            file2 = FileSystemModule.this.toFile(parse2);
                            IOUtils.copy(openAssetInputStream, new FileOutputStream(file2));
                        } else if (parse.getScheme() == null) {
                            openResourceInputStream = FileSystemModule.this.openResourceInputStream(relocatingOptions.getFrom());
                            file = FileSystemModule.this.toFile(parse2);
                            IOUtils.copy(openResourceInputStream, new FileOutputStream(file));
                        } else {
                            throw new IOException("Unsupported scheme for location '" + parse + "'.");
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr5 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RelocatingOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$18
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(RelocatingOptions.class);
                    }
                }))};
                Function1<Object[], Object> function15 = new Function1<Object[], Object>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$19
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object[] objArr) {
                        String slashifyFilePath;
                        String slashifyFilePath2;
                        boolean isSAFUri;
                        InputStream openResourceInputStream;
                        File file;
                        InputStream openAssetInputStream;
                        File file2;
                        File file3;
                        DocumentFile nearestSAFFile;
                        File file4;
                        File file5;
                        File file6;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        RelocatingOptions relocatingOptions = (RelocatingOptions) objArr[0];
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath(relocatingOptions.getFrom());
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemModule2.ensurePermission(parse, Permission.READ, "Location '" + parse + "' isn't readable.");
                        slashifyFilePath2 = FileSystemModuleKt.slashifyFilePath(relocatingOptions.getTo());
                        Uri parse2 = Uri.parse(slashifyFilePath2);
                        FileSystemModule fileSystemModule3 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse2);
                        fileSystemModule3.ensurePermission(parse2, Permission.WRITE);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file5 = FileSystemModule.this.toFile(parse);
                            file6 = FileSystemModule.this.toFile(parse2);
                            if (file5.isDirectory()) {
                                FileUtils.copyDirectory(file5, file6);
                            } else {
                                FileUtils.copyFile(file5, file6);
                            }
                            return Unit.INSTANCE;
                        }
                        isSAFUri = FileSystemModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            nearestSAFFile = FileSystemModule.this.getNearestSAFFile(parse);
                            if (nearestSAFFile != null && nearestSAFFile.exists()) {
                                file4 = FileSystemModule.this.toFile(parse2);
                                FileSystemModule.this.transformFilesFromSAF(nearestSAFFile, file4, true);
                                return Unit.INSTANCE;
                            }
                            throw new FileSystemCopyFailedException(parse);
                        }
                        if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME)) {
                            InputStream openInputStream = FileSystemModule.this.getContext().getContentResolver().openInputStream(parse);
                            file3 = FileSystemModule.this.toFile(parse2);
                            return Integer.valueOf(IOUtils.copy(openInputStream, new FileOutputStream(file3)));
                        }
                        if (Intrinsics.areEqual(parse.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
                            openAssetInputStream = FileSystemModule.this.openAssetInputStream(parse);
                            file2 = FileSystemModule.this.toFile(parse2);
                            return Integer.valueOf(IOUtils.copy(openAssetInputStream, new FileOutputStream(file2)));
                        }
                        if (parse.getScheme() == null) {
                            openResourceInputStream = FileSystemModule.this.openResourceInputStream(relocatingOptions.getFrom());
                            file = FileSystemModule.this.toFile(parse2);
                            return Integer.valueOf(IOUtils.copy(openResourceInputStream, new FileOutputStream(file)));
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                };
                if (!Intrinsics.areEqual(Object.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Object.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Object.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Object.class, String.class)) {
                                    asyncFunctionComponent5 = new StringAsyncFunctionComponent("copyAsync", anyTypeArr5, function15);
                                } else {
                                    asyncFunctionComponent5 = new AsyncFunctionComponent("copyAsync", anyTypeArr5, function15);
                                }
                            } else {
                                asyncFunctionComponent5 = new FloatAsyncFunctionComponent("copyAsync", anyTypeArr5, function15);
                            }
                        } else {
                            asyncFunctionComponent5 = new DoubleAsyncFunctionComponent("copyAsync", anyTypeArr5, function15);
                        }
                    } else {
                        asyncFunctionComponent5 = new BoolAsyncFunctionComponent("copyAsync", anyTypeArr5, function15);
                    }
                } else {
                    asyncFunctionComponent5 = new IntAsyncFunctionComponent("copyAsync", anyTypeArr5, function15);
                }
                asyncFunctionWithPromiseComponent2 = asyncFunctionComponent5;
            }
            moduleDefinitionBuilder6.getAsyncFunctions().put("copyAsync", asyncFunctionWithPromiseComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder7 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr6 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$20
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(MakeDirectoryOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$21
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(MakeDirectoryOptions.class);
                }
            }))};
            Function1<Object[], Unit> function16 = new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$22
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    String slashifyFilePath;
                    File file;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Object obj = objArr[0];
                    MakeDirectoryOptions makeDirectoryOptions = (MakeDirectoryOptions) objArr[1];
                    slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) obj);
                    Uri parse = Uri.parse(slashifyFilePath);
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemModule2.ensurePermission(parse, Permission.WRITE);
                    if (!Intrinsics.areEqual(parse.getScheme(), "file")) {
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                    file = FileSystemModule.this.toFile(parse);
                    boolean isDirectory = file.isDirectory();
                    boolean intermediates = makeDirectoryOptions.getIntermediates();
                    if (!(intermediates ? file.mkdirs() : file.mkdir()) && (!intermediates || !isDirectory)) {
                        throw new FileSystemCannotCreateDirectoryException(parse);
                    }
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                asyncFunctionComponent6 = new StringAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr6, function16);
                            } else {
                                asyncFunctionComponent6 = new AsyncFunctionComponent("makeDirectoryAsync", anyTypeArr6, function16);
                            }
                        } else {
                            asyncFunctionComponent6 = new FloatAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr6, function16);
                        }
                    } else {
                        asyncFunctionComponent6 = new DoubleAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr6, function16);
                    }
                } else {
                    asyncFunctionComponent6 = new BoolAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr6, function16);
                }
            } else {
                asyncFunctionComponent6 = new IntAsyncFunctionComponent("makeDirectoryAsync", anyTypeArr6, function16);
            }
            moduleDefinitionBuilder7.getAsyncFunctions().put("makeDirectoryAsync", asyncFunctionComponent6);
            ModuleDefinitionBuilder moduleDefinitionBuilder8 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("readDirectoryAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$23
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        String slashifyFilePath;
                        boolean isSAFUri;
                        File file;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) promise);
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemModule2.ensurePermission(parse, Permission.READ);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemModule.this.toFile(parse);
                            File[] listFiles = file.listFiles();
                            if (listFiles == null) {
                                throw new FileSystemCannotReadDirectoryException(parse);
                            }
                            ArrayList arrayList = new ArrayList(listFiles.length);
                            int length = listFiles.length;
                            for (int i = 0; i < length; i++) {
                                File file2 = listFiles[i];
                                arrayList.add(file2 != null ? file2.getName() : null);
                            }
                            return;
                        }
                        isSAFUri = FileSystemModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            throw new FileSystemUnsupportedSchemeException();
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr7 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$24
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }))};
                Function1<Object[], List<? extends String>> function17 = new Function1<Object[], List<? extends String>>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$25
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final List<? extends String> invoke(Object[] objArr) {
                        String slashifyFilePath;
                        boolean isSAFUri;
                        File file;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) objArr[0]);
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemModule2.ensurePermission(parse, Permission.READ);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemModule.this.toFile(parse);
                            File[] listFiles = file.listFiles();
                            if (listFiles == null) {
                                throw new FileSystemCannotReadDirectoryException(parse);
                            }
                            ArrayList arrayList = new ArrayList(listFiles.length);
                            int length = listFiles.length;
                            for (int i = 0; i < length; i++) {
                                File file2 = listFiles[i];
                                arrayList.add(file2 != null ? file2.getName() : null);
                            }
                            return arrayList;
                        }
                        isSAFUri = FileSystemModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            throw new FileSystemUnsupportedSchemeException();
                        }
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                };
                if (!Intrinsics.areEqual(List.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(List.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(List.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(List.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(List.class, String.class)) {
                                    asyncFunctionComponent7 = new StringAsyncFunctionComponent("readDirectoryAsync", anyTypeArr7, function17);
                                } else {
                                    asyncFunctionComponent7 = new AsyncFunctionComponent("readDirectoryAsync", anyTypeArr7, function17);
                                }
                            } else {
                                asyncFunctionComponent7 = new FloatAsyncFunctionComponent("readDirectoryAsync", anyTypeArr7, function17);
                            }
                        } else {
                            asyncFunctionComponent7 = new DoubleAsyncFunctionComponent("readDirectoryAsync", anyTypeArr7, function17);
                        }
                    } else {
                        asyncFunctionComponent7 = new BoolAsyncFunctionComponent("readDirectoryAsync", anyTypeArr7, function17);
                    }
                } else {
                    asyncFunctionComponent7 = new IntAsyncFunctionComponent("readDirectoryAsync", anyTypeArr7, function17);
                }
                asyncFunctionWithPromiseComponent3 = asyncFunctionComponent7;
            }
            moduleDefinitionBuilder8.getAsyncFunctions().put("readDirectoryAsync", asyncFunctionWithPromiseComponent3);
            ModuleDefinitionBuilder moduleDefinitionBuilder9 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr8 = new AnyType[0];
            Function1<Object[], Double> function18 = new Function1<Object[], Double>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$26
                @Override // kotlin.jvm.functions.Function1
                public final Double invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
                    return Double.valueOf(RangesKt.coerceAtMost(BigInteger.valueOf(statFs.getBlockCountLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).doubleValue(), Math.pow(2.0d, 53.0d) - 1));
                }
            };
            if (!Intrinsics.areEqual(Double.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Double.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Double.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Double.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Double.class, String.class)) {
                                asyncFunctionComponent8 = new StringAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr8, function18);
                            } else {
                                asyncFunctionComponent8 = new AsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr8, function18);
                            }
                        } else {
                            asyncFunctionComponent8 = new FloatAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr8, function18);
                        }
                    } else {
                        asyncFunctionComponent8 = new DoubleAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr8, function18);
                    }
                } else {
                    asyncFunctionComponent8 = new BoolAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr8, function18);
                }
            } else {
                asyncFunctionComponent8 = new IntAsyncFunctionComponent("getTotalDiskCapacityAsync", anyTypeArr8, function18);
            }
            moduleDefinitionBuilder9.getAsyncFunctions().put("getTotalDiskCapacityAsync", asyncFunctionComponent8);
            ModuleDefinitionBuilder moduleDefinitionBuilder10 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr9 = new AnyType[0];
            Function1<Object[], Double> function19 = new Function1<Object[], Double>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$27
                @Override // kotlin.jvm.functions.Function1
                public final Double invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
                    return Double.valueOf(RangesKt.coerceAtMost(BigInteger.valueOf(statFs.getAvailableBlocksLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).doubleValue(), Math.pow(2.0d, 53.0d) - 1));
                }
            };
            if (!Intrinsics.areEqual(Double.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Double.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Double.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Double.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Double.class, String.class)) {
                                asyncFunctionComponent9 = new StringAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr9, function19);
                            } else {
                                asyncFunctionComponent9 = new AsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr9, function19);
                            }
                        } else {
                            asyncFunctionComponent9 = new FloatAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr9, function19);
                        }
                    } else {
                        asyncFunctionComponent9 = new DoubleAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr9, function19);
                    }
                } else {
                    asyncFunctionComponent9 = new BoolAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr9, function19);
                }
            } else {
                asyncFunctionComponent9 = new IntAsyncFunctionComponent("getFreeDiskStorageAsync", anyTypeArr9, function19);
            }
            moduleDefinitionBuilder10.getAsyncFunctions().put("getFreeDiskStorageAsync", asyncFunctionComponent9);
            ModuleDefinitionBuilder moduleDefinitionBuilder11 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent4 = new AsyncFunctionWithPromiseComponent("getContentUriAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$28
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        String slashifyFilePath;
                        File file;
                        Uri contentUriFromFile;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemModule2.ensurePermission(parse, Permission.WRITE);
                        FileSystemModule.this.ensurePermission(parse, Permission.READ);
                        FileSystemModule.this.checkIfFileDirExists(parse);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemModule.this.toFile(parse);
                            contentUriFromFile = FileSystemModule.this.contentUriFromFile(file);
                            contentUriFromFile.toString();
                            return;
                        }
                        throw new FileSystemUnreadableDirectoryException(str);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr10 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$29
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))};
                Function1<Object[], String> function110 = new Function1<Object[], String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$30
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(Object[] objArr) {
                        String slashifyFilePath;
                        File file;
                        Uri contentUriFromFile;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        String str = (String) objArr[0];
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemModule2.ensurePermission(parse, Permission.WRITE);
                        FileSystemModule.this.ensurePermission(parse, Permission.READ);
                        FileSystemModule.this.checkIfFileDirExists(parse);
                        if (Intrinsics.areEqual(parse.getScheme(), "file")) {
                            file = FileSystemModule.this.toFile(parse);
                            contentUriFromFile = FileSystemModule.this.contentUriFromFile(file);
                            return contentUriFromFile.toString();
                        }
                        throw new FileSystemUnreadableDirectoryException(str);
                    }
                };
                if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(String.class, String.class)) {
                                    asyncFunctionComponent10 = new StringAsyncFunctionComponent("getContentUriAsync", anyTypeArr10, function110);
                                } else {
                                    asyncFunctionComponent10 = new AsyncFunctionComponent("getContentUriAsync", anyTypeArr10, function110);
                                }
                            } else {
                                asyncFunctionComponent10 = new FloatAsyncFunctionComponent("getContentUriAsync", anyTypeArr10, function110);
                            }
                        } else {
                            asyncFunctionComponent10 = new DoubleAsyncFunctionComponent("getContentUriAsync", anyTypeArr10, function110);
                        }
                    } else {
                        asyncFunctionComponent10 = new BoolAsyncFunctionComponent("getContentUriAsync", anyTypeArr10, function110);
                    }
                } else {
                    asyncFunctionComponent10 = new IntAsyncFunctionComponent("getContentUriAsync", anyTypeArr10, function110);
                }
                asyncFunctionWithPromiseComponent4 = asyncFunctionComponent10;
            }
            moduleDefinitionBuilder11.getAsyncFunctions().put("getContentUriAsync", asyncFunctionWithPromiseComponent4);
            ModuleDefinitionBuilder moduleDefinitionBuilder12 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent5 = new AsyncFunctionWithPromiseComponent("readSAFDirectoryAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$31
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        String slashifyFilePath;
                        boolean isSAFUri;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) promise);
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemModule2.ensurePermission(parse, Permission.READ);
                        isSAFUri = FileSystemModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            DocumentFile fromTreeUri = DocumentFile.fromTreeUri(FileSystemModule.this.getContext(), parse);
                            if (fromTreeUri == null || !fromTreeUri.exists() || !fromTreeUri.isDirectory()) {
                                throw new FileSystemCannotReadDirectoryException(parse);
                            }
                            DocumentFile[] listFiles = fromTreeUri.listFiles();
                            Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
                            ArrayList arrayList = new ArrayList(listFiles.length);
                            for (DocumentFile documentFile : listFiles) {
                                arrayList.add(documentFile.getUri().toString());
                            }
                            return;
                        }
                        throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI. Try using FileSystem.readDirectoryAsync instead.");
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr11 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$32
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))};
                Function1<Object[], List<? extends String>> function111 = new Function1<Object[], List<? extends String>>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$33
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final List<? extends String> invoke(Object[] objArr) {
                        String slashifyFilePath;
                        boolean isSAFUri;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) objArr[0]);
                        Uri parse = Uri.parse(slashifyFilePath);
                        FileSystemModule fileSystemModule2 = FileSystemModule.this;
                        Intrinsics.checkNotNull(parse);
                        fileSystemModule2.ensurePermission(parse, Permission.READ);
                        isSAFUri = FileSystemModule.this.isSAFUri(parse);
                        if (isSAFUri) {
                            DocumentFile fromTreeUri = DocumentFile.fromTreeUri(FileSystemModule.this.getContext(), parse);
                            if (fromTreeUri == null || !fromTreeUri.exists() || !fromTreeUri.isDirectory()) {
                                throw new FileSystemCannotReadDirectoryException(parse);
                            }
                            DocumentFile[] listFiles = fromTreeUri.listFiles();
                            Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
                            ArrayList arrayList = new ArrayList(listFiles.length);
                            for (DocumentFile documentFile : listFiles) {
                                arrayList.add(documentFile.getUri().toString());
                            }
                            return arrayList;
                        }
                        throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI. Try using FileSystem.readDirectoryAsync instead.");
                    }
                };
                if (!Intrinsics.areEqual(List.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(List.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(List.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(List.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(List.class, String.class)) {
                                    asyncFunctionComponent11 = new StringAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr11, function111);
                                } else {
                                    asyncFunctionComponent11 = new AsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr11, function111);
                                }
                            } else {
                                asyncFunctionComponent11 = new FloatAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr11, function111);
                            }
                        } else {
                            asyncFunctionComponent11 = new DoubleAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr11, function111);
                        }
                    } else {
                        asyncFunctionComponent11 = new BoolAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr11, function111);
                    }
                } else {
                    asyncFunctionComponent11 = new IntAsyncFunctionComponent("readSAFDirectoryAsync", anyTypeArr11, function111);
                }
                asyncFunctionWithPromiseComponent5 = asyncFunctionComponent11;
            }
            moduleDefinitionBuilder12.getAsyncFunctions().put("readSAFDirectoryAsync", asyncFunctionWithPromiseComponent5);
            ModuleDefinitionBuilder moduleDefinitionBuilder13 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr12 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$34
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$35
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))};
            Function1<Object[], String> function112 = new Function1<Object[], String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$36
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] objArr) {
                    String slashifyFilePath;
                    boolean isSAFUri;
                    DocumentFile nearestSAFFile;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Object obj = objArr[0];
                    String str = (String) objArr[1];
                    slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) obj);
                    Uri parse = Uri.parse(slashifyFilePath);
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemModule2.ensurePermission(parse, Permission.WRITE);
                    isSAFUri = FileSystemModule.this.isSAFUri(parse);
                    if (isSAFUri) {
                        nearestSAFFile = FileSystemModule.this.getNearestSAFFile(parse);
                        if (nearestSAFFile != null && !nearestSAFFile.isDirectory()) {
                            throw new FileSystemCannotCreateDirectoryException(parse);
                        }
                        DocumentFile createDirectory = nearestSAFFile != null ? nearestSAFFile.createDirectory(str) : null;
                        if (createDirectory == null) {
                            throw new FileSystemCannotCreateDirectoryException(null);
                        }
                        Intrinsics.checkNotNull(createDirectory);
                        return createDirectory.getUri().toString();
                    }
                    throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI. Try using FileSystem.makeDirectoryAsync instead.");
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent12 = new StringAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr12, function112);
                            } else {
                                asyncFunctionComponent12 = new AsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr12, function112);
                            }
                        } else {
                            asyncFunctionComponent12 = new FloatAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr12, function112);
                        }
                    } else {
                        asyncFunctionComponent12 = new DoubleAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr12, function112);
                    }
                } else {
                    asyncFunctionComponent12 = new BoolAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr12, function112);
                }
            } else {
                asyncFunctionComponent12 = new IntAsyncFunctionComponent("makeSAFDirectoryAsync", anyTypeArr12, function112);
            }
            moduleDefinitionBuilder13.getAsyncFunctions().put("makeSAFDirectoryAsync", asyncFunctionComponent12);
            ModuleDefinitionBuilder moduleDefinitionBuilder14 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr13 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$37
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$38
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$39
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))};
            Function1<Object[], String> function113 = new Function1<Object[], String>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$40
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] objArr) {
                    String slashifyFilePath;
                    boolean isSAFUri;
                    DocumentFile nearestSAFFile;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    String str = (String) objArr[2];
                    String str2 = (String) obj2;
                    slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) obj);
                    Uri parse = Uri.parse(slashifyFilePath);
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemModule2.ensurePermission(parse, Permission.WRITE);
                    isSAFUri = FileSystemModule.this.isSAFUri(parse);
                    if (isSAFUri) {
                        nearestSAFFile = FileSystemModule.this.getNearestSAFFile(parse);
                        if (nearestSAFFile == null || !nearestSAFFile.isDirectory()) {
                            throw new FileSystemCannotCreateFileException(parse);
                        }
                        DocumentFile createFile = nearestSAFFile.createFile(str, str2);
                        if (createFile == null) {
                            throw new FileSystemCannotCreateFileException(null);
                        }
                        Intrinsics.checkNotNull(createFile);
                        return createFile.getUri().toString();
                    }
                    throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI.");
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent13 = new StringAsyncFunctionComponent("createSAFFileAsync", anyTypeArr13, function113);
                            } else {
                                asyncFunctionComponent13 = new AsyncFunctionComponent("createSAFFileAsync", anyTypeArr13, function113);
                            }
                        } else {
                            asyncFunctionComponent13 = new FloatAsyncFunctionComponent("createSAFFileAsync", anyTypeArr13, function113);
                        }
                    } else {
                        asyncFunctionComponent13 = new DoubleAsyncFunctionComponent("createSAFFileAsync", anyTypeArr13, function113);
                    }
                } else {
                    asyncFunctionComponent13 = new BoolAsyncFunctionComponent("createSAFFileAsync", anyTypeArr13, function113);
                }
            } else {
                asyncFunctionComponent13 = new IntAsyncFunctionComponent("createSAFFileAsync", anyTypeArr13, function113);
            }
            moduleDefinitionBuilder14.getAsyncFunctions().put("createSAFFileAsync", asyncFunctionComponent13);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent7 = new AsyncFunctionWithPromiseComponent("requestDirectoryPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$2
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Promise promise2;
                    String slashifyFilePath;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    String str = (String) objArr[0];
                    Activity currentActivity = FileSystemModule.this.getAppContext().getCurrentActivity();
                    if (currentActivity != null) {
                        promise2 = FileSystemModule.this.dirPermissionsRequest;
                        if (promise2 != null) {
                            throw new FileSystemPendingPermissionsRequestException();
                        }
                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
                        if (Build.VERSION.SDK_INT >= 26 && str != null) {
                            slashifyFilePath = FileSystemModuleKt.slashifyFilePath(str);
                            Uri parse = Uri.parse(slashifyFilePath);
                            if (parse != null) {
                                intent.putExtra("android.provider.extra.INITIAL_URI", parse);
                            }
                        }
                        FileSystemModule.this.dirPermissionsRequest = promise;
                        currentActivity.startActivityForResult(intent, 5394);
                        return;
                    }
                    throw new Exceptions.MissingActivity();
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("requestDirectoryPermissionsAsync", asyncFunctionWithPromiseComponent7);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent8 = asyncFunctionWithPromiseComponent7;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent9 = new AsyncFunctionWithPromiseComponent("uploadAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$4
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$5
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(FileSystemUploadOptions.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Request createUploadRequest;
                    OkHttpClient okHttpClient;
                    Unit unit;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    FileSystemUploadOptions fileSystemUploadOptions = (FileSystemUploadOptions) objArr[2];
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    createUploadRequest = fileSystemModule2.createUploadRequest((String) obj, (String) obj2, fileSystemUploadOptions, new RequestBodyDecorator() { // from class: expo.modules.filesystem.FileSystemModule$definition$1$17$request$1
                        @Override // expo.modules.filesystem.RequestBodyDecorator
                        public final RequestBody decorate(RequestBody requestBody) {
                            Intrinsics.checkNotNullParameter(requestBody, "requestBody");
                            return requestBody;
                        }
                    });
                    okHttpClient = FileSystemModule.this.getOkHttpClient();
                    if (okHttpClient != null) {
                        Call newCall = okHttpClient.newCall(createUploadRequest);
                        final FileSystemModule fileSystemModule3 = FileSystemModule.this;
                        newCall.enqueue(new Callback() { // from class: expo.modules.filesystem.FileSystemModule$definition$1$17$1$1
                            @Override // okhttp3.Callback
                            public void onFailure(Call call, IOException e) {
                                String str;
                                String str2;
                                Intrinsics.checkNotNullParameter(call, "call");
                                Intrinsics.checkNotNullParameter(e, "e");
                                str = FileSystemModuleKt.TAG;
                                Log.e(str, String.valueOf(e.getMessage()));
                                Promise promise2 = Promise.this;
                                str2 = FileSystemModuleKt.TAG;
                                Intrinsics.checkNotNullExpressionValue(str2, "access$getTAG$p(...)");
                                promise2.reject(str2, e.getMessage(), e);
                            }

                            @Override // okhttp3.Callback
                            public void onResponse(Call call, Response response) {
                                Bundle translateHeaders;
                                Intrinsics.checkNotNullParameter(call, "call");
                                Intrinsics.checkNotNullParameter(response, "response");
                                Bundle bundle = new Bundle();
                                FileSystemModule fileSystemModule4 = fileSystemModule3;
                                ResponseBody body = response.body();
                                bundle.putString("body", body != null ? body.string() : null);
                                bundle.putInt("status", response.code());
                                translateHeaders = fileSystemModule4.translateHeaders(response.headers());
                                bundle.putBundle("headers", translateHeaders);
                                response.close();
                                Promise.this.resolve(bundle);
                            }
                        });
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit == null) {
                        promise.reject(new FileSystemOkHttpNullException());
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("uploadAsync", asyncFunctionWithPromiseComponent9);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent10 = asyncFunctionWithPromiseComponent9;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent11 = new AsyncFunctionWithPromiseComponent("uploadTaskStartAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$7
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$8
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$9
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$10
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(FileSystemUploadOptions.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$11
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Request createUploadRequest;
                    OkHttpClient okHttpClient;
                    Map map;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    Object obj3 = objArr[2];
                    FileSystemUploadOptions fileSystemUploadOptions = (FileSystemUploadOptions) objArr[3];
                    final String str = (String) obj3;
                    final FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    final CountingRequestListener countingRequestListener = new CountingRequestListener() { // from class: expo.modules.filesystem.FileSystemModule$definition$1$18$progressListener$1
                        private long mLastUpdate = -1;

                        @Override // expo.modules.filesystem.CountingRequestListener
                        public void onProgress(long bytesWritten, long contentLength) {
                            Bundle bundle = new Bundle();
                            Bundle bundle2 = new Bundle();
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis > this.mLastUpdate + 100 || bytesWritten == contentLength) {
                                this.mLastUpdate = currentTimeMillis;
                                bundle2.putDouble("totalBytesSent", bytesWritten);
                                bundle2.putDouble("totalBytesExpectedToSend", contentLength);
                                bundle.putString("uuid", str);
                                bundle.putBundle("data", bundle2);
                                fileSystemModule2.sendEvent("expo-file-system.uploadProgress", bundle);
                            }
                        }
                    };
                    FileSystemModule fileSystemModule3 = FileSystemModule.this;
                    createUploadRequest = fileSystemModule3.createUploadRequest((String) obj, (String) obj2, fileSystemUploadOptions, new RequestBodyDecorator() { // from class: expo.modules.filesystem.FileSystemModule$definition$1$18$request$1
                        @Override // expo.modules.filesystem.RequestBodyDecorator
                        public final RequestBody decorate(RequestBody requestBody) {
                            Intrinsics.checkNotNullParameter(requestBody, "requestBody");
                            return new CountingRequestBody(requestBody, CountingRequestListener.this);
                        }
                    });
                    okHttpClient = FileSystemModule.this.getOkHttpClient();
                    Intrinsics.checkNotNull(okHttpClient);
                    Call newCall = okHttpClient.newCall(createUploadRequest);
                    map = FileSystemModule.this.taskHandlers;
                    map.put(str, new FileSystemModule.TaskHandler(newCall));
                    final FileSystemModule fileSystemModule4 = FileSystemModule.this;
                    newCall.enqueue(new Callback() { // from class: expo.modules.filesystem.FileSystemModule$definition$1$18$1
                        @Override // okhttp3.Callback
                        public void onFailure(Call call, IOException e) {
                            String str2;
                            String str3;
                            Intrinsics.checkNotNullParameter(call, "call");
                            Intrinsics.checkNotNullParameter(e, "e");
                            if (call.getCanceled()) {
                                Promise.this.resolve((Object) null);
                                return;
                            }
                            str2 = FileSystemModuleKt.TAG;
                            Log.e(str2, String.valueOf(e.getMessage()));
                            Promise promise2 = Promise.this;
                            str3 = FileSystemModuleKt.TAG;
                            Intrinsics.checkNotNullExpressionValue(str3, "access$getTAG$p(...)");
                            promise2.reject(str3, e.getMessage(), e);
                        }

                        @Override // okhttp3.Callback
                        public void onResponse(Call call, Response response) {
                            Bundle translateHeaders;
                            Intrinsics.checkNotNullParameter(call, "call");
                            Intrinsics.checkNotNullParameter(response, "response");
                            Bundle bundle = new Bundle();
                            ResponseBody body = response.body();
                            FileSystemModule fileSystemModule5 = fileSystemModule4;
                            bundle.putString("body", body != null ? body.string() : null);
                            bundle.putInt("status", response.code());
                            translateHeaders = fileSystemModule5.translateHeaders(response.headers());
                            bundle.putBundle("headers", translateHeaders);
                            response.close();
                            Promise.this.resolve(bundle);
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("uploadTaskStartAsync", asyncFunctionWithPromiseComponent11);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent12 = asyncFunctionWithPromiseComponent11;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent13 = new AsyncFunctionWithPromiseComponent("downloadAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$12
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$13
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$14
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(DownloadOptions.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$15
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    String slashifyFilePath;
                    OkHttpClient okHttpClient;
                    Call newCall;
                    File file;
                    String md5;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    final DownloadOptions downloadOptions = (DownloadOptions) objArr[2];
                    String str = (String) obj;
                    slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) obj2);
                    final Uri parse = Uri.parse(slashifyFilePath);
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemModule2.ensurePermission(parse, Permission.WRITE);
                    FileSystemModule.this.checkIfFileDirExists(parse);
                    Unit unit = null;
                    unit = null;
                    if (!StringsKt.contains$default((CharSequence) str, (CharSequence) ":", false, 2, (Object) null)) {
                        Context context = FileSystemModule.this.getContext();
                        InputStream openRawResource = context.getResources().openRawResource(context.getResources().getIdentifier(str, "raw", context.getPackageName()));
                        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
                        BufferedSource buffer = Okio.buffer(Okio.source(openRawResource));
                        file = FileSystemModule.this.toFile(parse);
                        file.delete();
                        BufferedSink buffer2 = Okio.buffer(Okio__JvmOkioKt.sink$default(file, false, 1, null));
                        buffer2.writeAll(buffer);
                        buffer2.close();
                        Bundle bundle = new Bundle();
                        bundle.putString("uri", Uri.fromFile(file).toString());
                        Boolean valueOf = Boolean.valueOf(downloadOptions.getMd5());
                        Boolean bool = valueOf.booleanValue() ? valueOf : null;
                        if (bool != null) {
                            bool.booleanValue();
                            md5 = FileSystemModule.this.md5(file);
                            bundle.putString("md5", md5);
                        }
                        promise.resolve(bundle);
                        return;
                    }
                    if (Intrinsics.areEqual("file", parse.getScheme())) {
                        Request.Builder url = new Request.Builder().url(str);
                        if (downloadOptions.getHeaders() != null) {
                            for (Map.Entry<String, String> entry : downloadOptions.getHeaders().entrySet()) {
                                url.addHeader(entry.getKey(), entry.getValue());
                            }
                        }
                        okHttpClient = FileSystemModule.this.getOkHttpClient();
                        if (okHttpClient != null && (newCall = okHttpClient.newCall(url.build())) != null) {
                            final FileSystemModule fileSystemModule3 = FileSystemModule.this;
                            newCall.enqueue(new Callback() { // from class: expo.modules.filesystem.FileSystemModule$definition$1$19$4
                                @Override // okhttp3.Callback
                                public void onFailure(Call call, IOException e) {
                                    String str2;
                                    String str3;
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(e, "e");
                                    str2 = FileSystemModuleKt.TAG;
                                    Log.e(str2, String.valueOf(e.getMessage()));
                                    Promise promise2 = Promise.this;
                                    str3 = FileSystemModuleKt.TAG;
                                    Intrinsics.checkNotNullExpressionValue(str3, "access$getTAG$p(...)");
                                    promise2.reject(str3, e.getMessage(), e);
                                }

                                @Override // okhttp3.Callback
                                public void onResponse(Call call, Response response) throws IOException {
                                    File file2;
                                    Bundle translateHeaders;
                                    String md52;
                                    Intrinsics.checkNotNullParameter(call, "call");
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    FileSystemModule fileSystemModule4 = fileSystemModule3;
                                    Uri uri = parse;
                                    Intrinsics.checkNotNullExpressionValue(uri, "$uri");
                                    file2 = fileSystemModule4.toFile(uri);
                                    file2.delete();
                                    BufferedSink buffer3 = Okio.buffer(Okio__JvmOkioKt.sink$default(file2, false, 1, null));
                                    ResponseBody body = response.body();
                                    Intrinsics.checkNotNull(body);
                                    buffer3.writeAll(body.getSource());
                                    buffer3.close();
                                    Bundle bundle2 = new Bundle();
                                    FileSystemModule fileSystemModule5 = fileSystemModule3;
                                    DownloadOptions downloadOptions2 = downloadOptions;
                                    bundle2.putString("uri", Uri.fromFile(file2).toString());
                                    bundle2.putInt("status", response.code());
                                    translateHeaders = fileSystemModule5.translateHeaders(response.headers());
                                    bundle2.putBundle("headers", translateHeaders);
                                    if (downloadOptions2.getMd5()) {
                                        md52 = fileSystemModule5.md5(file2);
                                        bundle2.putString("md5", md52);
                                    }
                                    response.close();
                                    Promise.this.resolve(bundle2);
                                }
                            });
                            unit = Unit.INSTANCE;
                        }
                        if (unit == null) {
                            promise.reject(new FileSystemOkHttpNullException());
                            return;
                        }
                        return;
                    }
                    throw new IOException("Unsupported scheme for location '" + parse + "'.");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("downloadAsync", asyncFunctionWithPromiseComponent13);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent14 = asyncFunctionWithPromiseComponent13;
            ModuleDefinitionBuilder moduleDefinitionBuilder15 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionComponent14 = new AsyncFunctionWithPromiseComponent("networkTaskCancelAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$41
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Map map;
                        Call call;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        map = FileSystemModule.this.taskHandlers;
                        FileSystemModule.TaskHandler taskHandler = (FileSystemModule.TaskHandler) map.get((String) promise);
                        if (taskHandler == null || (call = taskHandler.getCall()) == null) {
                            return;
                        }
                        call.cancel();
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                asyncFunctionComponent14 = new AsyncFunctionComponent("networkTaskCancelAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$42
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))}, new Function1<Object[], Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$43
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Map map;
                        Call call;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        String str = (String) objArr[0];
                        map = FileSystemModule.this.taskHandlers;
                        FileSystemModule.TaskHandler taskHandler = (FileSystemModule.TaskHandler) map.get(str);
                        if (taskHandler == null || (call = taskHandler.getCall()) == null) {
                            return null;
                        }
                        call.cancel();
                        return Unit.INSTANCE;
                    }
                });
            }
            moduleDefinitionBuilder15.getAsyncFunctions().put("networkTaskCancelAsync", asyncFunctionComponent14);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent15 = new AsyncFunctionWithPromiseComponent("downloadResumableStartAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$16
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$17
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$18
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptions.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$19
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(DownloadOptions.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$20
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithPromise$21
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    String slashifyFilePath;
                    OkHttpClient okHttpClient;
                    Map map;
                    File file;
                    CoroutineScope coroutineScope;
                    OkHttpClient.Builder newBuilder;
                    OkHttpClient.Builder addInterceptor;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    Object obj3 = objArr[2];
                    Object obj4 = objArr[3];
                    final String str = (String) objArr[4];
                    DownloadOptions downloadOptions = (DownloadOptions) obj4;
                    final String str2 = (String) obj3;
                    String str3 = (String) obj;
                    slashifyFilePath = FileSystemModuleKt.slashifyFilePath((String) obj2);
                    Uri parse = Uri.parse(slashifyFilePath);
                    FileSystemModule fileSystemModule2 = FileSystemModule.this;
                    Intrinsics.checkNotNull(parse);
                    fileSystemModule2.checkIfFileDirExists(parse);
                    if (!Intrinsics.areEqual(parse.getScheme(), "file")) {
                        throw new IOException("Unsupported scheme for location '" + parse + "'.");
                    }
                    final FileSystemModule fileSystemModule3 = FileSystemModule.this;
                    final FileSystemModule.ProgressListener progressListener = new FileSystemModule.ProgressListener() { // from class: expo.modules.filesystem.FileSystemModule$definition$1$21$progressListener$1
                        private long mLastUpdate = -1;

                        public final long getMLastUpdate() {
                            return this.mLastUpdate;
                        }

                        public final void setMLastUpdate(long j) {
                            this.mLastUpdate = j;
                        }

                        @Override // expo.modules.filesystem.FileSystemModule.ProgressListener
                        public void update(long bytesRead, long contentLength, boolean done) {
                            Bundle bundle = new Bundle();
                            Bundle bundle2 = new Bundle();
                            String str4 = str;
                            long parseLong = bytesRead + (str4 != null ? Long.parseLong(str4) : 0L);
                            String str5 = str;
                            long parseLong2 = contentLength + (str5 != null ? Long.parseLong(str5) : 0L);
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis > this.mLastUpdate + 100 || parseLong == parseLong2) {
                                this.mLastUpdate = currentTimeMillis;
                                bundle2.putDouble("totalBytesWritten", parseLong);
                                bundle2.putDouble("totalBytesExpectedToWrite", parseLong2);
                                bundle.putString("uuid", str2);
                                bundle.putBundle("data", bundle2);
                                fileSystemModule3.sendEvent("expo-file-system.downloadProgress", bundle);
                            }
                        }
                    };
                    okHttpClient = FileSystemModule.this.getOkHttpClient();
                    OkHttpClient build = (okHttpClient == null || (newBuilder = okHttpClient.newBuilder()) == null || (addInterceptor = newBuilder.addInterceptor(new Interceptor() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$lambda$42$$inlined$-addInterceptor$1
                        @Override // okhttp3.Interceptor
                        public final Response intercept(Interceptor.Chain chain) {
                            Intrinsics.checkNotNullParameter(chain, "chain");
                            Response proceed = chain.proceed(chain.request());
                            return proceed.newBuilder().body(new FileSystemModule.ProgressResponseBody(proceed.body(), FileSystemModule.ProgressListener.this)).build();
                        }
                    })) == null) ? null : addInterceptor.build();
                    if (build == null) {
                        promise.reject(new FileSystemOkHttpNullException());
                        return;
                    }
                    Request.Builder builder = new Request.Builder();
                    if (str != null) {
                        builder.addHeader(HttpHeaders.RANGE, "bytes=" + str + "-");
                    }
                    if (downloadOptions.getHeaders() != null) {
                        for (Map.Entry<String, String> entry : downloadOptions.getHeaders().entrySet()) {
                            builder.addHeader(entry.getKey(), entry.getValue());
                        }
                    }
                    Call newCall = build.newCall(builder.url(str3).build());
                    map = FileSystemModule.this.taskHandlers;
                    map.put(str2, new FileSystemModule.DownloadTaskHandler(parse, newCall));
                    file = FileSystemModule.this.toFile(parse);
                    FileSystemModule.DownloadResumableTaskParams downloadResumableTaskParams = new FileSystemModule.DownloadResumableTaskParams(downloadOptions, newCall, file, str != null, promise);
                    coroutineScope = FileSystemModule.this.moduleCoroutineScope;
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new FileSystemModule$definition$1$21$3(FileSystemModule.this, downloadResumableTaskParams, null), 3, null);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("downloadResumableStartAsync", asyncFunctionWithPromiseComponent15);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent16 = asyncFunctionWithPromiseComponent15;
            ModuleDefinitionBuilder moduleDefinitionBuilder16 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent6 = new AsyncFunctionWithPromiseComponent("downloadResumablePauseAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$44
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Map map;
                        Map map2;
                        File file;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        map = FileSystemModule.this.taskHandlers;
                        FileSystemModule.TaskHandler taskHandler = (FileSystemModule.TaskHandler) map.get(str);
                        if (taskHandler == null) {
                            throw new IOException("No download object available");
                        }
                        if (!(taskHandler instanceof FileSystemModule.DownloadTaskHandler)) {
                            throw new FileSystemCannotFindTaskException();
                        }
                        taskHandler.getCall().cancel();
                        map2 = FileSystemModule.this.taskHandlers;
                        map2.remove(str);
                        file = FileSystemModule.this.toFile(((FileSystemModule.DownloadTaskHandler) taskHandler).getFileUri());
                        new Bundle().putString("resumeData", String.valueOf(file.length()));
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr14 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$45
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))};
                Function1<Object[], Bundle> function114 = new Function1<Object[], Bundle>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$46
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Bundle invoke(Object[] objArr) {
                        Map map;
                        Map map2;
                        File file;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        String str = (String) objArr[0];
                        map = FileSystemModule.this.taskHandlers;
                        FileSystemModule.TaskHandler taskHandler = (FileSystemModule.TaskHandler) map.get(str);
                        if (taskHandler == null) {
                            throw new IOException("No download object available");
                        }
                        if (!(taskHandler instanceof FileSystemModule.DownloadTaskHandler)) {
                            throw new FileSystemCannotFindTaskException();
                        }
                        taskHandler.getCall().cancel();
                        map2 = FileSystemModule.this.taskHandlers;
                        map2.remove(str);
                        file = FileSystemModule.this.toFile(((FileSystemModule.DownloadTaskHandler) taskHandler).getFileUri());
                        Bundle bundle = new Bundle();
                        bundle.putString("resumeData", String.valueOf(file.length()));
                        return bundle;
                    }
                };
                if (!Intrinsics.areEqual(Bundle.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Bundle.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Bundle.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Bundle.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Bundle.class, String.class)) {
                                    asyncFunctionComponent15 = new StringAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr14, function114);
                                } else {
                                    asyncFunctionComponent15 = new AsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr14, function114);
                                }
                            } else {
                                asyncFunctionComponent15 = new FloatAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr14, function114);
                            }
                        } else {
                            asyncFunctionComponent15 = new DoubleAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr14, function114);
                        }
                    } else {
                        asyncFunctionComponent15 = new BoolAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr14, function114);
                    }
                } else {
                    asyncFunctionComponent15 = new IntAsyncFunctionComponent("downloadResumablePauseAsync", anyTypeArr14, function114);
                }
                asyncFunctionWithPromiseComponent6 = asyncFunctionComponent15;
            }
            moduleDefinitionBuilder16.getAsyncFunctions().put("downloadResumablePauseAsync", asyncFunctionWithPromiseComponent6);
            moduleDefinitionBuilder.getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new Function2<Activity, OnActivityResultPayload, Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$OnActivityResult$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Activity activity, OnActivityResultPayload onActivityResultPayload) {
                    invoke2(activity, onActivityResultPayload);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Activity sender, OnActivityResultPayload payload) {
                    Promise promise;
                    Promise promise2;
                    Intrinsics.checkNotNullParameter(sender, "sender");
                    Intrinsics.checkNotNullParameter(payload, "payload");
                    int requestCode = payload.getRequestCode();
                    int resultCode = payload.getResultCode();
                    Intent data = payload.getData();
                    if (requestCode == 5394) {
                        promise = FileSystemModule.this.dirPermissionsRequest;
                        if (promise != null) {
                            Activity currentActivity = FileSystemModule.this.getAppContext().getCurrentActivity();
                            if (currentActivity == null) {
                                throw new Exceptions.MissingActivity();
                            }
                            Bundle bundle = new Bundle();
                            if (resultCode == -1 && data != null) {
                                Uri data2 = data.getData();
                                int flags = data.getFlags() & 3;
                                if (data2 != null) {
                                    currentActivity.getContentResolver().takePersistableUriPermission(data2, flags);
                                }
                                bundle.putBoolean(PermissionsResponse.GRANTED_KEY, true);
                                bundle.putString("directoryUri", String.valueOf(data2));
                            } else {
                                bundle.putBoolean(PermissionsResponse.GRANTED_KEY, false);
                            }
                            promise2 = FileSystemModule.this.dirPermissionsRequest;
                            if (promise2 != null) {
                                promise2.resolve(bundle);
                            }
                            FileSystemModule.this.dirPermissionsRequest = null;
                        }
                    }
                }
            }));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new Function0<Unit>() { // from class: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$OnDestroy$1
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
                    String str;
                    CoroutineScope coroutineScope;
                    try {
                        coroutineScope = FileSystemModule.this.moduleCoroutineScope;
                        CoroutineScopeKt.cancel(coroutineScope, new ModuleDestroyedException(null, 1, null));
                    } catch (IllegalStateException unused) {
                        str = FileSystemModuleKt.TAG;
                        Log.e(str, "The scope does not have a job in it");
                    }
                }
            }));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    private final void checkIfFileExists(Uri uri) throws IOException {
        File file = toFile(uri);
        if (file.exists()) {
            return;
        }
        throw new IOException("Directory for '" + file.getPath() + "' doesn't exist.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkIfFileDirExists(Uri uri) throws IOException {
        File file = toFile(uri);
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            throw new IOException("Directory for '" + file.getPath() + "' doesn't exist. Please make sure directory '" + file.getParent() + "' exists before calling downloadAsync.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensureDirExists(File dir) throws IOException {
        if (dir.isDirectory() || dir.mkdirs()) {
            return;
        }
        throw new IOException("Couldn't create directory '" + dir + "'");
    }

    private final EnumSet<Permission> permissionsForPath(String path) {
        FilePermissionModuleInterface filePermission = getAppContext().getFilePermission();
        if (filePermission != null) {
            return filePermission.getPathPermissions(getContext(), path);
        }
        return null;
    }

    private final EnumSet<Permission> permissionsForUri(Uri uri) {
        if (isSAFUri(uri)) {
            return permissionsForSAFUri(uri);
        }
        if (!Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME) && !Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
            return Intrinsics.areEqual(uri.getScheme(), "file") ? permissionsForPath(uri.getPath()) : uri.getScheme() == null ? EnumSet.of(Permission.READ) : EnumSet.noneOf(Permission.class);
        }
        return EnumSet.of(Permission.READ);
    }

    private final EnumSet<Permission> permissionsForSAFUri(Uri uri) {
        DocumentFile nearestSAFFile = getNearestSAFFile(uri);
        EnumSet<Permission> noneOf = EnumSet.noneOf(Permission.class);
        if (nearestSAFFile != null) {
            if (nearestSAFFile.canRead()) {
                noneOf.add(Permission.READ);
            }
            if (nearestSAFFile.canWrite()) {
                noneOf.add(Permission.WRITE);
            }
        }
        Intrinsics.checkNotNullExpressionValue(noneOf, "apply(...)");
        return noneOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensurePermission(Uri uri, Permission permission, String errorMsg) throws IOException {
        EnumSet<Permission> permissionsForUri = permissionsForUri(uri);
        if (permissionsForUri == null || !permissionsForUri.contains(permission)) {
            throw new IOException(errorMsg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensurePermission(Uri uri, Permission permission) throws IOException {
        if (permission == Permission.READ) {
            ensurePermission(uri, permission, "Location '" + uri + "' isn't readable.");
        }
        if (permission == Permission.WRITE) {
            ensurePermission(uri, permission, "Location '" + uri + "' isn't writable.");
        }
        ensurePermission(uri, permission, "Location '" + uri + "' doesn't have permission '" + permission.name() + "'.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InputStream openAssetInputStream(Uri uri) throws IOException {
        String path = uri.getPath();
        if (path == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Intrinsics.checkNotNullExpressionValue(path, "requireNotNull(...)");
        String substring = path.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        InputStream open = getContext().getAssets().open(substring);
        Intrinsics.checkNotNullExpressionValue(open, "open(...)");
        return open;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InputStream openResourceInputStream(String resourceName) throws IOException {
        int identifier = getContext().getResources().getIdentifier(resourceName, "raw", getContext().getPackageName());
        if (identifier == 0 && (identifier = getContext().getResources().getIdentifier(resourceName, "drawable", getContext().getPackageName())) == 0) {
            throw new FileNotFoundException("No resource found with the name '" + resourceName + "'");
        }
        InputStream openRawResource = getContext().getResources().openRawResource(identifier);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
        return openRawResource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void transformFilesFromSAF(DocumentFile documentFile, File outputDir, boolean copy) throws IOException {
        File file;
        if (!documentFile.exists()) {
            return;
        }
        if (!outputDir.isDirectory()) {
            File parentFile = outputDir.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("Couldn't create folder in output dir.");
            }
        } else if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Couldn't create folder in output dir.");
        }
        if (documentFile.isDirectory()) {
            DocumentFile[] listFiles = documentFile.listFiles();
            Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
            for (DocumentFile documentFile2 : listFiles) {
                Intrinsics.checkNotNull(documentFile2);
                transformFilesFromSAF(documentFile2, outputDir, copy);
            }
            if (copy) {
                return;
            }
            documentFile.delete();
            return;
        }
        String name = documentFile.getName();
        if (name == null) {
            return;
        }
        if (outputDir.isDirectory()) {
            file = new File(outputDir.getPath(), name);
        } else {
            file = new File(outputDir.getPath());
        }
        FileOutputStream openInputStream = getContext().getContentResolver().openInputStream(documentFile.getUri());
        try {
            InputStream inputStream = openInputStream;
            openInputStream = new FileOutputStream(file);
            try {
                IOUtils.copy(inputStream, openInputStream);
                CloseableKt.closeFinally(openInputStream, null);
                CloseableKt.closeFinally(openInputStream, null);
                if (copy) {
                    return;
                }
                documentFile.delete();
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Uri contentUriFromFile(File file) {
        Activity currentActivity = getAppContext().getCurrentActivity();
        if (currentActivity == null) {
            throw new Exceptions.MissingActivity();
        }
        Uri uriForFile = FileProvider.getUriForFile(currentActivity.getApplication(), currentActivity.getApplication().getPackageName() + ".FileSystemFileProvider", file);
        Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(...)");
        return uriForFile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Request createUploadRequest(String url, String fileUriString, FileSystemUploadOptions options, RequestBodyDecorator decorator) throws IOException {
        String slashifyFilePath;
        slashifyFilePath = FileSystemModuleKt.slashifyFilePath(fileUriString);
        Uri parse = Uri.parse(slashifyFilePath);
        Intrinsics.checkNotNull(parse);
        ensurePermission(parse, Permission.READ);
        checkIfFileExists(parse);
        Request.Builder url2 = new Request.Builder().url(url);
        Map<String, String> headers = options.getHeaders();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                url2.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return url2.method(options.getHttpMethod().getValue(), createRequestBody(options, decorator, toFile(parse))).build();
    }

    private final RequestBody createRequestBody(FileSystemUploadOptions options, RequestBodyDecorator decorator, File file) {
        int i = WhenMappings.$EnumSwitchMapping$0[options.getUploadType().ordinal()];
        if (i == 1) {
            return decorator.decorate(RequestBody.INSTANCE.create(file, (MediaType) null));
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        MultipartBody.Builder type = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM);
        Map<String, String> parameters = options.getParameters();
        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                type.addFormDataPart(entry.getKey(), entry.getValue().toString());
            }
        }
        String mimeType = options.getMimeType();
        if (mimeType == null) {
            mimeType = URLConnection.guessContentTypeFromName(file.getName());
            Intrinsics.checkNotNullExpressionValue(mimeType, "guessContentTypeFromName(...)");
        }
        String fieldName = options.getFieldName();
        if (fieldName == null) {
            fieldName = file.getName();
        }
        Intrinsics.checkNotNull(fieldName);
        type.addFormDataPart(fieldName, file.getName(), decorator.decorate(RequestBody.INSTANCE.create(file, MediaType.INSTANCE.parse(mimeType))));
        return type.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemModule.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u000bHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;", "", "options", "Lexpo/modules/filesystem/DownloadOptions;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "file", "Ljava/io/File;", "isResume", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "(Lexpo/modules/filesystem/DownloadOptions;Lokhttp3/Call;Ljava/io/File;ZLexpo/modules/kotlin/Promise;)V", "getCall", "()Lokhttp3/Call;", "getFile", "()Ljava/io/File;", "()Z", "getOptions", "()Lexpo/modules/filesystem/DownloadOptions;", "getPromise", "()Lexpo/modules/kotlin/Promise;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final /* data */ class DownloadResumableTaskParams {
        private final Call call;
        private final File file;
        private final boolean isResume;
        private final DownloadOptions options;
        private final Promise promise;

        public static /* synthetic */ DownloadResumableTaskParams copy$default(DownloadResumableTaskParams downloadResumableTaskParams, DownloadOptions downloadOptions, Call call, File file, boolean z, Promise promise, int i, Object obj) {
            if ((i & 1) != 0) {
                downloadOptions = downloadResumableTaskParams.options;
            }
            if ((i & 2) != 0) {
                call = downloadResumableTaskParams.call;
            }
            Call call2 = call;
            if ((i & 4) != 0) {
                file = downloadResumableTaskParams.file;
            }
            File file2 = file;
            if ((i & 8) != 0) {
                z = downloadResumableTaskParams.isResume;
            }
            boolean z2 = z;
            if ((i & 16) != 0) {
                promise = downloadResumableTaskParams.promise;
            }
            return downloadResumableTaskParams.copy(downloadOptions, call2, file2, z2, promise);
        }

        /* renamed from: component1, reason: from getter */
        public final DownloadOptions getOptions() {
            return this.options;
        }

        /* renamed from: component2, reason: from getter */
        public final Call getCall() {
            return this.call;
        }

        /* renamed from: component3, reason: from getter */
        public final File getFile() {
            return this.file;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsResume() {
            return this.isResume;
        }

        /* renamed from: component5, reason: from getter */
        public final Promise getPromise() {
            return this.promise;
        }

        public final DownloadResumableTaskParams copy(DownloadOptions options, Call call, File file, boolean isResume, Promise promise) {
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(promise, "promise");
            return new DownloadResumableTaskParams(options, call, file, isResume, promise);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadResumableTaskParams)) {
                return false;
            }
            DownloadResumableTaskParams downloadResumableTaskParams = (DownloadResumableTaskParams) other;
            return Intrinsics.areEqual(this.options, downloadResumableTaskParams.options) && Intrinsics.areEqual(this.call, downloadResumableTaskParams.call) && Intrinsics.areEqual(this.file, downloadResumableTaskParams.file) && this.isResume == downloadResumableTaskParams.isResume && Intrinsics.areEqual(this.promise, downloadResumableTaskParams.promise);
        }

        public final Call getCall() {
            return this.call;
        }

        public final File getFile() {
            return this.file;
        }

        public final DownloadOptions getOptions() {
            return this.options;
        }

        public final Promise getPromise() {
            return this.promise;
        }

        public int hashCode() {
            return (((((((this.options.hashCode() * 31) + this.call.hashCode()) * 31) + this.file.hashCode()) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isResume)) * 31) + this.promise.hashCode();
        }

        public final boolean isResume() {
            return this.isResume;
        }

        public String toString() {
            return "DownloadResumableTaskParams(options=" + this.options + ", call=" + this.call + ", file=" + this.file + ", isResume=" + this.isResume + ", promise=" + this.promise + ")";
        }

        public DownloadResumableTaskParams(DownloadOptions options, Call call, File file, boolean z, Promise promise) {
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(promise, "promise");
            this.options = options;
            this.call = call;
            this.file = file;
            this.isResume = z;
            this.promise = promise;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object downloadResumableTask(DownloadResumableTaskParams downloadResumableTaskParams, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new FileSystemModule$downloadResumableTask$2(downloadResumableTaskParams, this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemModule.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0012\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "(Lokhttp3/Call;)V", "getCall", "()Lokhttp3/Call;", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static class TaskHandler {
        private final Call call;

        public final Call getCall() {
            return this.call;
        }

        public TaskHandler(Call call) {
            Intrinsics.checkNotNullParameter(call, "call");
            this.call = call;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$DownloadTaskHandler;", "Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "fileUri", "Landroid/net/Uri;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "(Landroid/net/Uri;Lokhttp3/Call;)V", "getFileUri", "()Landroid/net/Uri;", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class DownloadTaskHandler extends TaskHandler {
        private final Uri fileUri;

        public final Uri getFileUri() {
            return this.fileUri;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadTaskHandler(Uri fileUri, Call call) {
            super(call);
            Intrinsics.checkNotNullParameter(fileUri, "fileUri");
            Intrinsics.checkNotNullParameter(call, "call");
            this.fileUri = fileUri;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileSystemModule.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$ProgressResponseBody;", "Lokhttp3/ResponseBody;", "responseBody", "progressListener", "Lexpo/modules/filesystem/FileSystemModule$ProgressListener;", "(Lokhttp3/ResponseBody;Lexpo/modules/filesystem/FileSystemModule$ProgressListener;)V", "bufferedSource", "Lokio/BufferedSource;", "contentLength", "", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY, "Lokhttp3/MediaType;", "source", "Lokio/Source;", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class ProgressResponseBody extends ResponseBody {
        private BufferedSource bufferedSource;
        private final ProgressListener progressListener;
        private final ResponseBody responseBody;

        public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            Intrinsics.checkNotNullParameter(progressListener, "progressListener");
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override // okhttp3.ResponseBody
        /* renamed from: contentType */
        public MediaType get$contentType() {
            ResponseBody responseBody = this.responseBody;
            if (responseBody != null) {
                return responseBody.get$contentType();
            }
            return null;
        }

        @Override // okhttp3.ResponseBody
        /* renamed from: contentLength */
        public long getContentLength() {
            ResponseBody responseBody = this.responseBody;
            if (responseBody != null) {
                return responseBody.getContentLength();
            }
            return -1L;
        }

        @Override // okhttp3.ResponseBody
        /* renamed from: source */
        public BufferedSource getSource() {
            BufferedSource bufferedSource = this.bufferedSource;
            if (bufferedSource != null) {
                return bufferedSource;
            }
            ResponseBody responseBody = this.responseBody;
            Intrinsics.checkNotNull(responseBody);
            return Okio.buffer(source(responseBody.getSource()));
        }

        private final Source source(final Source source) {
            return new ForwardingSource(source) { // from class: expo.modules.filesystem.FileSystemModule$ProgressResponseBody$source$1
                private long totalBytesRead;

                public final long getTotalBytesRead() {
                    return this.totalBytesRead;
                }

                public final void setTotalBytesRead(long j) {
                    this.totalBytesRead = j;
                }

                @Override // okio.ForwardingSource, okio.Source
                public long read(Buffer sink, long byteCount) throws IOException {
                    FileSystemModule.ProgressListener progressListener;
                    ResponseBody responseBody;
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    long read = super.read(sink, byteCount);
                    this.totalBytesRead += read != -1 ? read : 0L;
                    progressListener = this.progressListener;
                    long j = this.totalBytesRead;
                    responseBody = this.responseBody;
                    progressListener.update(j, responseBody != null ? responseBody.getContentLength() : -1L, read == -1);
                    return read;
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized OkHttpClient getOkHttpClient() {
        if (this.client == null) {
            this.client = new OkHttpClient.Builder().connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build();
        }
        return this.client;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String md5(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            char[] encodeHex = Hex.encodeHex(DigestUtils.md5(fileInputStream));
            Intrinsics.checkNotNullExpressionValue(encodeHex, "encodeHex(...)");
            String str = new String(encodeHex);
            CloseableKt.closeFinally(fileInputStream, null);
            return str;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("Failed to list contents of " + file);
            }
            IOException e = null;
            for (File file2 : listFiles) {
                try {
                    Intrinsics.checkNotNull(file2);
                    forceDelete(file2);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            if (e != null) {
                throw e;
            }
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file + ".");
        }
        if (file.delete()) {
            return;
        }
        throw new IOException("Unable to delete file: " + file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getFileSize(File file) {
        Object obj;
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        ArrayList arrayList = new ArrayList(listFiles.length);
        for (File file2 : listFiles) {
            Intrinsics.checkNotNull(file2);
            arrayList.add(Long.valueOf(getFileSize(file2)));
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = Long.valueOf(((Number) next).longValue() + ((Number) it.next()).longValue());
            }
            obj = next;
        } else {
            obj = null;
        }
        Long l = (Long) obj;
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InputStream getInputStream(Uri uri) throws IOException {
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            return new FileInputStream(toFile(uri));
        }
        if (Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_ASSET_SCHEME)) {
            return openAssetInputStream(uri);
        }
        if (isSAFUri(uri)) {
            InputStream openInputStream = getContext().getContentResolver().openInputStream(uri);
            Intrinsics.checkNotNull(openInputStream);
            return openInputStream;
        }
        throw new IOException("Unsupported scheme for location '" + uri + "'.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OutputStream getOutputStream(Uri uri) throws IOException {
        FileOutputStream openOutputStream;
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            openOutputStream = new FileOutputStream(toFile(uri));
        } else {
            if (!isSAFUri(uri)) {
                throw new IOException("Unsupported scheme for location '" + uri + "'.");
            }
            openOutputStream = getContext().getContentResolver().openOutputStream(uri);
            Intrinsics.checkNotNull(openOutputStream);
        }
        Intrinsics.checkNotNull(openOutputStream);
        return openOutputStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DocumentFile getNearestSAFFile(Uri uri) {
        DocumentFile fromSingleUri = DocumentFile.fromSingleUri(getContext(), uri);
        return (fromSingleUri == null || !fromSingleUri.isFile()) ? DocumentFile.fromTreeUri(getContext(), uri) : fromSingleUri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File toFile(Uri uri) {
        if (uri.getPath() != null) {
            String path = uri.getPath();
            Intrinsics.checkNotNull(path);
            return new File(path);
        }
        throw new IOException("Invalid Uri: " + uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSAFUri(Uri uri) {
        String host;
        return Intrinsics.areEqual(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME) && (host = uri.getHost()) != null && StringsKt.startsWith$default(host, "com.android.externalstorage", false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String parseFileUri(String uriStr) {
        String substring = uriStr.substring(StringsKt.indexOf$default((CharSequence) uriStr, ':', 0, false, 6, (Object) null) + 3);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] getInputStreamBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bundle translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            if (bundle.containsKey(name)) {
                bundle.putString(name, bundle.getString(name) + ", " + headers.value(i));
            } else {
                bundle.putString(name, headers.value(i));
            }
        }
        return bundle;
    }
}
