package expo.modules.filesystem;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import expo.modules.filesystem.FileSystemModule;
import expo.modules.kotlin.Promise;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: FileSystemModule.kt */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 9, 0})
@DebugMetadata(c = "expo.modules.filesystem.FileSystemModule$downloadResumableTask$2", f = "FileSystemModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FileSystemModule$downloadResumableTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ FileSystemModule.DownloadResumableTaskParams $params;
    int label;
    final /* synthetic */ FileSystemModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FileSystemModule$downloadResumableTask$2(FileSystemModule.DownloadResumableTaskParams downloadResumableTaskParams, FileSystemModule fileSystemModule, Continuation<? super FileSystemModule$downloadResumableTask$2> continuation) {
        super(2, continuation);
        this.$params = downloadResumableTaskParams;
        this.this$0 = fileSystemModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileSystemModule$downloadResumableTask$2(this.$params, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((FileSystemModule$downloadResumableTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        Bundle translateHeaders;
        String md5;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            FileSystemModule.DownloadResumableTaskParams downloadResumableTaskParams = this.$params;
            DownloadOptions options = downloadResumableTaskParams.getOptions();
            Call call = downloadResumableTaskParams.getCall();
            File file = downloadResumableTaskParams.getFile();
            boolean isResume = downloadResumableTaskParams.getIsResume();
            Promise promise = downloadResumableTaskParams.getPromise();
            try {
                Response execute = call.execute();
                ResponseBody body = execute.body();
                Intrinsics.checkNotNull(body);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(body.byteStream());
                FileOutputStream fileOutputStream = new FileOutputStream(file, isResume);
                byte[] bArr = new byte[1024];
                Ref.IntRef intRef = new Ref.IntRef();
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    intRef.element = read;
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, intRef.element);
                }
                Bundle bundle = new Bundle();
                FileSystemModule fileSystemModule = this.this$0;
                bundle.putString("uri", Uri.fromFile(file).toString());
                bundle.putInt("status", execute.code());
                translateHeaders = fileSystemModule.translateHeaders(execute.headers());
                bundle.putBundle("headers", translateHeaders);
                Boolean boxBoolean = Boxing.boxBoolean(options.getMd5());
                if (!boxBoolean.booleanValue()) {
                    boxBoolean = null;
                }
                if (boxBoolean != null) {
                    boxBoolean.booleanValue();
                    md5 = fileSystemModule.md5(file);
                    bundle.putString("md5", md5);
                }
                execute.close();
                promise.resolve(bundle);
            } catch (Exception e) {
                if (call.getCanceled()) {
                    promise.resolve((Object) null);
                    return null;
                }
                String message = e.getMessage();
                if (message != null) {
                    str2 = FileSystemModuleKt.TAG;
                    Boxing.boxInt(Log.e(str2, message));
                }
                str = FileSystemModuleKt.TAG;
                Intrinsics.checkNotNullExpressionValue(str, "access$getTAG$p(...)");
                promise.reject(str, e.getMessage(), e);
            }
            return null;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
