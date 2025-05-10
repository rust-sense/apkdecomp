package expo.modules.kotlin.functions;

import expo.modules.kotlin.Promise;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u000e\b\u0004\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\u0086\bø\u0001\u0000J#\u0010\u0010\u001a\u00020\u00112\u0010\b\u0004\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0014H\u0087\bø\u0001\u0000¢\u0006\u0002\b\u0015JA\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u00012#\b\u0004\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u0002H\u00120\u0017H\u0086\bø\u0001\u0000J^\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u000128\b\u0004\u0010\u0013\u001a2\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\u00120\u001bH\u0086\bø\u0001\u0000J{\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u00012M\b\u0004\u0010\u0013\u001aG\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u0002H\u00120\u001eH\u0086\bø\u0001\u0000J\u0098\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u00012b\b\u0004\u0010\u0013\u001a\\\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u00120!H\u0086\bø\u0001\u0000Jµ\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u00012w\b\u0004\u0010\u0013\u001aq\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002H\u00120$H\u0086\bø\u0001\u0000JÔ\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u00012\u008d\u0001\b\u0004\u0010\u0013\u001a\u0086\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0004\u0012\u0002H\u00120'H\u0086\bø\u0001\u0000Jñ\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u00012¢\u0001\b\u0004\u0010\u0013\u001a\u009b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\u00120*H\u0086\bø\u0001\u0000J\u008e\u0002\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u0001\"\u0006\b\b\u0010,\u0018\u00012·\u0001\b\u0004\u0010\u0013\u001a°\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(.\u0012\u0004\u0012\u0002H\u00120-H\u0086\bø\u0001\u0000J\u008b\u0002\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u00012·\u0001\b\u0004\u0010\u0013\u001a°\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(.\u0012\u0004\u0012\u0002H\u00120-H\u0087\bø\u0001\u0000¢\u0006\u0002\b0Jî\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u00012¢\u0001\b\u0004\u0010\u0013\u001a\u009b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\u00120*H\u0087\bø\u0001\u0000¢\u0006\u0002\b0JÑ\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u00012\u008d\u0001\b\u0004\u0010\u0013\u001a\u0086\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0004\u0012\u0002H\u00120'H\u0087\bø\u0001\u0000¢\u0006\u0002\b0J²\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u00012w\b\u0004\u0010\u0013\u001aq\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002H\u00120$H\u0087\bø\u0001\u0000¢\u0006\u0002\b0J\u0095\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u00012b\b\u0004\u0010\u0013\u001a\\\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u00120!H\u0087\bø\u0001\u0000¢\u0006\u0002\b0Jx\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u00012M\b\u0004\u0010\u0013\u001aG\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u0002H\u00120\u001eH\u0087\bø\u0001\u0000¢\u0006\u0002\b0J[\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u000128\b\u0004\u0010\u0013\u001a2\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\u00120\u001bH\u0087\bø\u0001\u0000¢\u0006\u0002\b0J6\u00101\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u001e\b\u0004\u00102\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0017H\u0086\b¢\u0006\u0002\u00104JS\u00101\u001a\u000205\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u000123\b\u0004\u00102\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001bH\u0086\b¢\u0006\u0002\u00106Jp\u00101\u001a\u000205\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u00012H\b\u0004\u00102\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001eH\u0086\b¢\u0006\u0002\u00107J\u008d\u0001\u00101\u001a\u000205\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u00012]\b\u0004\u00102\u001aW\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010!H\u0086\b¢\u0006\u0002\u00108Jª\u0001\u00101\u001a\u000205\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u00012r\b\u0004\u00102\u001al\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010$H\u0086\b¢\u0006\u0002\u00109JÉ\u0001\u00101\u001a\u000205\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u00012\u0088\u0001\b\u0004\u00102\u001a\u0081\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010'H\u0086\b¢\u0006\u0002\u0010:Jæ\u0001\u00101\u001a\u000205\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u00012\u009d\u0001\b\u0004\u00102\u001a\u0096\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010*H\u0086\b¢\u0006\u0002\u0010;J\u0083\u0002\u00101\u001a\u000205\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u00012²\u0001\b\u0004\u00102\u001a«\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010-H\u0086\b¢\u0006\u0002\u0010<J \u0002\u00101\u001a\u000205\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u0001\"\u0006\b\b\u0010,\u0018\u00012Ç\u0001\b\u0004\u00102\u001aÀ\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001203\u0012\u0006\u0012\u0004\u0018\u00010\u00010=H\u0086\b¢\u0006\u0002\u0010>J\r\u0010?\u001a\u00020\u0006H\u0000¢\u0006\u0002\b@R&\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006A"}, d2 = {"Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "", "name", "", "(Ljava/lang/String;)V", "asyncFunctionComponent", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "getAsyncFunctionComponent$annotations", "()V", "getAsyncFunctionComponent", "()Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "setAsyncFunctionComponent", "(Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;)V", "getName$annotations", "getName", "()Ljava/lang/String;", "AsyncBody", "Lexpo/modules/kotlin/functions/AsyncFunction;", "R", "body", "Lkotlin/Function0;", "AsyncBodyWithoutArgs", "P0", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "p0", "P1", "Lkotlin/Function2;", "p1", "P2", "Lkotlin/Function3;", "p2", "P3", "Lkotlin/Function4;", "p3", "P4", "Lkotlin/Function5;", "p4", "P5", "Lkotlin/Function6;", "p5", "P6", "Lkotlin/Function7;", "p6", "P7", "Lkotlin/Function8;", "p7", "Lexpo/modules/kotlin/Promise;", "AsyncFunctionWithPromise", "SuspendBody", "block", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function2;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function3;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function4;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function5;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function6;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function7;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function8;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "Lkotlin/Function9;", "(Lkotlin/jvm/functions/Function9;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", OperatingSystem.JsonKeys.BUILD, "build$expo_modules_core_release", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AsyncFunctionBuilder {
    private BaseAsyncFunctionComponent asyncFunctionComponent;
    private final String name;

    public static /* synthetic */ void getAsyncFunctionComponent$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public final BaseAsyncFunctionComponent getAsyncFunctionComponent() {
        return this.asyncFunctionComponent;
    }

    public final String getName() {
        return this.name;
    }

    public final void setAsyncFunctionComponent(BaseAsyncFunctionComponent baseAsyncFunctionComponent) {
        this.asyncFunctionComponent = baseAsyncFunctionComponent;
    }

    public AsyncFunctionBuilder(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    public final /* synthetic */ <R> BaseAsyncFunctionComponent SuspendBody(Function1<? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(getName(), new AnyType[0], new AsyncFunctionBuilder$SuspendBody$1(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0> SuspendFunctionComponent SuspendBody(Function2<? super P0, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$1 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$1 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$1
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$1))}, new AsyncFunctionBuilder$SuspendBody$3(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> SuspendFunctionComponent SuspendBody(Function3<? super P0, ? super P1, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$2 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$2 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$2
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$3 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$3 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$3
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$2)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$3))}, new AsyncFunctionBuilder$SuspendBody$5(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> SuspendFunctionComponent SuspendBody(Function4<? super P0, ? super P1, ? super P2, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$4 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$4 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$4
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$5 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$5 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$5
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$6 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$6 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$6
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$4)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$5)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$6))}, new AsyncFunctionBuilder$SuspendBody$7(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> SuspendFunctionComponent SuspendBody(Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$7 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$7 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$7
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$8 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$8 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$8
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$9 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$9 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$9
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$10 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$10 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$10
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$7)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$8)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$9)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$10))}, new AsyncFunctionBuilder$SuspendBody$9(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> SuspendFunctionComponent SuspendBody(Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$11 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$11 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$11
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$12 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$12 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$12
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$13 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$13 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$13
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$14 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$14 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$14
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$15 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$15 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$15
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$11)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$12)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$13)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$14)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$15))}, new AsyncFunctionBuilder$SuspendBody$11(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SuspendFunctionComponent SuspendBody(Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$16 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$16 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$16
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$17 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$17 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$17
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$18 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$18 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$18
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$19 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$19 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$19
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$20 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$20 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$20
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$21 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$21 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$21
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$16)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$17)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$18)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$19)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$20)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$21))}, new AsyncFunctionBuilder$SuspendBody$13(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SuspendFunctionComponent SuspendBody(Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$22 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$22 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$22
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$23 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$23 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$23
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$24 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$24 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$24
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$25 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$25 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$25
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$26 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$26 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$26
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$27 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$27 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$27
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$28 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$28 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$28
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$25)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$26)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$27)), new AnyType(new LazyKType(orCreateKotlinClass7, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$28))}, new AsyncFunctionBuilder$SuspendBody$15(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SuspendFunctionComponent SuspendBody(Function9<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.reifiedOperationMarker(4, "P7");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$29 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$29 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$29
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$30 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$30 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$30
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$31 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$31 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$31
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$32 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$32 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$32
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$33 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$33 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$33
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$34 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$34 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$34
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$35 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$35 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$35
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$36 asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$36 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$36
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P7");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$29)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$30)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$31)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$32)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$33)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$34)), new AnyType(new LazyKType(orCreateKotlinClass7, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$35)), new AnyType(new LazyKType(orCreateKotlinClass8, false, asyncFunctionBuilder$SuspendBody$$inlined$toArgsArray$default$36))}, new AsyncFunctionBuilder$SuspendBody$17(block, null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final AsyncFunction AsyncBodyWithoutArgs(final Function0<? extends Object> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(getName(), new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return body.invoke();
            }
        });
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R> AsyncFunction AsyncBody(final Function0<? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        AnyType[] anyTypeArr = new AnyType[0];
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final R invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return body.invoke();
            }
        };
        Intrinsics.reifiedOperationMarker(3, "R");
        Intrinsics.reifiedOperationMarker(4, "R");
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, String.class)) {
            asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
        }
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> AsyncFunction AsyncBody(final Function1<? super P0, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        if (Object.class != Promise.class) {
            String name = getName();
            Intrinsics.reifiedOperationMarker(4, "P0");
            Intrinsics.needClassReification();
            AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$1 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$1 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    Intrinsics.reifiedOperationMarker(6, "P0");
                    return null;
                }
            };
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$1))};
            Intrinsics.needClassReification();
            Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final R invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    return body.invoke(objArr[0]);
                }
            };
            Intrinsics.reifiedOperationMarker(3, "R");
            Intrinsics.reifiedOperationMarker(4, "R");
            if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
                asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
            } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
                asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
            } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
                asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
            } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
                asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
            } else if (Intrinsics.areEqual(Object.class, String.class)) {
                asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
            } else {
                asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
            }
            asyncFunctionWithPromiseComponent = asyncFunctionComponent;
        } else {
            Intrinsics.needClassReification();
            asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(getName(), new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function1<P0, R> function12 = body;
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    function12.invoke(promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        }
        setAsyncFunctionComponent(asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1> AsyncFunction AsyncBody(final Function2<? super P0, ? super P1, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$2 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$2 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$2
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$3 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$3 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$3
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$2)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$3))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final R invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1]);
            }
        };
        Intrinsics.reifiedOperationMarker(3, "R");
        Intrinsics.reifiedOperationMarker(4, "R");
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, String.class)) {
            asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
        }
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> AsyncFunction AsyncFunctionWithPromise(final Function2<? super P0, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$4 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$4 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$4
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$4))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object[] objArr, Promise promise) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                Intrinsics.checkNotNullParameter(promise, "promise");
                body.invoke(objArr[0], promise);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                invoke2(objArr, promise);
                return Unit.INSTANCE;
            }
        });
        setAsyncFunctionComponent(asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> AsyncFunction AsyncBody(final Function3<? super P0, ? super P1, ? super P2, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$5 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$5 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$5
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$6 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$6 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$6
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$7 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$7 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$7
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$5)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$6)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$7))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final R invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2]);
            }
        };
        Intrinsics.reifiedOperationMarker(3, "R");
        Intrinsics.reifiedOperationMarker(4, "R");
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, String.class)) {
            asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
        }
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> AsyncFunction AsyncFunctionWithPromise(final Function3<? super P0, ? super P1, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$8 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$8 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$8
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$9 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$9 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$9
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$8)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$9))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$14
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object[] objArr, Promise promise) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                Intrinsics.checkNotNullParameter(promise, "promise");
                body.invoke(objArr[0], objArr[1], promise);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                invoke2(objArr, promise);
                return Unit.INSTANCE;
            }
        });
        setAsyncFunctionComponent(asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> AsyncFunction AsyncBody(final Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$10 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$10 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$10
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$11 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$11 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$11
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$12 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$12 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$12
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$13 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$13 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$13
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$10)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$11)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$12)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$13))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$16
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final R invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
        };
        Intrinsics.reifiedOperationMarker(3, "R");
        Intrinsics.reifiedOperationMarker(4, "R");
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, String.class)) {
            asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
        }
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> AsyncFunction AsyncFunctionWithPromise(final Function4<? super P0, ? super P1, ? super P2, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$14 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$14 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$14
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$15 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$15 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$15
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$16 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$16 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$16
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$14)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$15)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$16))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$18
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object[] objArr, Promise promise) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                Intrinsics.checkNotNullParameter(promise, "promise");
                body.invoke(objArr[0], objArr[1], objArr[2], promise);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                invoke2(objArr, promise);
                return Unit.INSTANCE;
            }
        });
        setAsyncFunctionComponent(asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> AsyncFunction AsyncBody(final Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$17 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$17 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$17
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$18 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$18 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$18
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$19 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$19 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$19
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$20 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$20 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$20
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$21 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$21 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$21
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$17)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$18)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$19)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$20)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$21))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$20
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final R invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
        };
        Intrinsics.reifiedOperationMarker(3, "R");
        Intrinsics.reifiedOperationMarker(4, "R");
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, String.class)) {
            asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
        }
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> AsyncFunction AsyncFunctionWithPromise(final Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$22 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$22 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$22
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$23 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$23 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$23
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$24 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$24 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$24
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$25 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$25 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$25
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$25))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$22
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object[] objArr, Promise promise) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                Intrinsics.checkNotNullParameter(promise, "promise");
                body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], promise);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                invoke2(objArr, promise);
                return Unit.INSTANCE;
            }
        });
        setAsyncFunctionComponent(asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> AsyncFunction AsyncBody(final Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$26 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$26 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$26
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$27 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$27 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$27
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$28 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$28 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$28
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$29 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$29 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$29
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$30 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$30 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$30
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$31 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$31 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$31
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$26)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$27)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$28)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$29)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$30)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$31))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$24
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final R invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
        };
        Intrinsics.reifiedOperationMarker(3, "R");
        Intrinsics.reifiedOperationMarker(4, "R");
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, String.class)) {
            asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
        }
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> AsyncFunction AsyncFunctionWithPromise(final Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$32 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$32 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$32
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$33 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$33 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$33
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$34 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$34 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$34
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$35 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$35 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$35
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$36 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$36 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$36
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$32)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$33)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$34)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$35)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$36))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$26
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object[] objArr, Promise promise) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                Intrinsics.checkNotNullParameter(promise, "promise");
                body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], promise);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                invoke2(objArr, promise);
                return Unit.INSTANCE;
            }
        });
        setAsyncFunctionComponent(asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> AsyncFunction AsyncBody(final Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$37 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$37 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$37
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$38 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$38 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$38
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$39 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$39 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$39
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$40 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$40 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$40
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$41 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$41 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$41
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$42 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$42 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$42
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$43 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$43 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$43
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$37)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$38)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$39)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$40)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$41)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$42)), new AnyType(new LazyKType(orCreateKotlinClass7, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$43))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$28
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final R invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
        };
        Intrinsics.reifiedOperationMarker(3, "R");
        Intrinsics.reifiedOperationMarker(4, "R");
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, String.class)) {
            asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
        }
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> AsyncFunction AsyncFunctionWithPromise(final Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$44 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$44 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$44
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$45 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$45 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$45
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$46 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$46 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$46
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$47 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$47 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$47
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$48 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$48 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$48
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$49 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$49 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$49
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$44)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$45)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$46)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$47)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$48)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$49))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$30
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object[] objArr, Promise promise) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                Intrinsics.checkNotNullParameter(promise, "promise");
                body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], promise);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                invoke2(objArr, promise);
                return Unit.INSTANCE;
            }
        });
        setAsyncFunctionComponent(asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> AsyncFunction AsyncBody(final Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.reifiedOperationMarker(4, "P7");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$50 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$50 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$50
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$51 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$51 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$51
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$52 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$52 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$52
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$53 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$53 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$53
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$54 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$54 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$54
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$55 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$55 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$55
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$56 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$56 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$56
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$57 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$57 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$57
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P7");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$50)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$51)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$52)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$53)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$54)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$55)), new AnyType(new LazyKType(orCreateKotlinClass7, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$56)), new AnyType(new LazyKType(orCreateKotlinClass8, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$57))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$32
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final R invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
        };
        Intrinsics.reifiedOperationMarker(3, "R");
        Intrinsics.reifiedOperationMarker(4, "R");
        if (Intrinsics.areEqual(Object.class, Integer.TYPE)) {
            asyncFunctionComponent = new IntAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Boolean.TYPE)) {
            asyncFunctionComponent = new BoolAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Double.TYPE)) {
            asyncFunctionComponent = new DoubleAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, Float.TYPE)) {
            asyncFunctionComponent = new FloatAsyncFunctionComponent(name, anyTypeArr, function1);
        } else if (Intrinsics.areEqual(Object.class, String.class)) {
            asyncFunctionComponent = new StringAsyncFunctionComponent(name, anyTypeArr, function1);
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, function1);
        }
        setAsyncFunctionComponent(asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> AsyncFunction AsyncFunctionWithPromise(final Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        String name = getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$58 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$58 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$58
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$59 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$59 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$59
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$60 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$60 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$60
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$61 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$61 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$61
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$62 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$62 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$62
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$63 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$63 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$63
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$64 asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$64 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$64
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$58)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$59)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$60)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$61)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$62)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$63)), new AnyType(new LazyKType(orCreateKotlinClass7, false, asyncFunctionBuilder$AsyncBody$$inlined$toArgsArray$default$64))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilder$AsyncBody$34
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object[] objArr, Promise promise) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                Intrinsics.checkNotNullParameter(promise, "promise");
                body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], promise);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                invoke2(objArr, promise);
                return Unit.INSTANCE;
            }
        });
        setAsyncFunctionComponent(asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final BaseAsyncFunctionComponent build$expo_modules_core_release() {
        BaseAsyncFunctionComponent baseAsyncFunctionComponent = this.asyncFunctionComponent;
        if (baseAsyncFunctionComponent != null) {
            return baseAsyncFunctionComponent;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }
}
