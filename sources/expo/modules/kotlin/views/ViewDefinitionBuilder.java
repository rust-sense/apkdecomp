package expo.modules.kotlin.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.defaultmodules.ErrorManagerModule;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.Queues;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.modules.DefinitionMarker;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import io.sentry.protocol.OperatingSystem;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: ViewDefinitionBuilder.kt */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u000bJ,\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u00012\u0006\u00106\u001a\u00020\u000b2\u000e\b\u0004\u00108\u001a\b\u0012\u0004\u0012\u0002H709H\u0086\bø\u0001\u0000J+\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020\u000b2\u0010\b\u0004\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000309H\u0087\bø\u0001\u0000¢\u0006\u0002\b:JI\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u00012\u0006\u00106\u001a\u00020\u000b2#\b\u0004\u00108\u001a\u001d\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0004\u0012\u0002H70\u0018H\u0086\bø\u0001\u0000Jf\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u00012\u0006\u00106\u001a\u00020\u000b28\b\u0004\u00108\u001a2\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0004\u0012\u0002H70?H\u0086\bø\u0001\u0000J\u0083\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u00012\u0006\u00106\u001a\u00020\u000b2M\b\u0004\u00108\u001aG\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H70BH\u0086\bø\u0001\u0000J \u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u00012\u0006\u00106\u001a\u00020\u000b2b\b\u0004\u00108\u001a\\\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0004\u0012\u0002H70EH\u0086\bø\u0001\u0000J½\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u00012\u0006\u00106\u001a\u00020\u000b2w\b\u0004\u00108\u001aq\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0004\u0012\u0002H70HH\u0086\bø\u0001\u0000JÜ\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u0001\"\u0006\b\u0007\u0010J\u0018\u00012\u0006\u00106\u001a\u00020\u000b2\u008d\u0001\b\u0004\u00108\u001a\u0086\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0004\u0012\u0002H70KH\u0086\bø\u0001\u0000Jù\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u0001\"\u0006\b\u0007\u0010J\u0018\u0001\"\u0006\b\b\u0010M\u0018\u00012\u0006\u00106\u001a\u00020\u000b2¢\u0001\b\u0004\u00108\u001a\u009b\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0013\u0012\u0011HM¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(O\u0012\u0004\u0012\u0002H70NH\u0086\bø\u0001\u0000J\u0096\u0002\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u0001\"\u0006\b\u0007\u0010J\u0018\u0001\"\u0006\b\b\u0010M\u0018\u0001\"\u0006\b\t\u0010P\u0018\u00012\u0006\u00106\u001a\u00020\u000b2·\u0001\b\u0004\u00108\u001a°\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0013\u0012\u0011HM¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(O\u0012\u0013\u0012\u0011HP¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(R\u0012\u0004\u0012\u0002H70QH\u0086\bø\u0001\u0000J\u0093\u0002\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u0001\"\u0006\b\u0007\u0010J\u0018\u0001\"\u0006\b\b\u0010M\u0018\u00012\u0006\u00106\u001a\u00020\u000b2·\u0001\b\u0004\u00108\u001a°\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0013\u0012\u0011HM¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(O\u0012\u0013\u0012\u00110S¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(R\u0012\u0004\u0012\u0002H70QH\u0087\bø\u0001\u0000¢\u0006\u0002\bTJö\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u0001\"\u0006\b\u0007\u0010J\u0018\u00012\u0006\u00106\u001a\u00020\u000b2¢\u0001\b\u0004\u00108\u001a\u009b\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110S¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(O\u0012\u0004\u0012\u0002H70NH\u0087\bø\u0001\u0000¢\u0006\u0002\bTJÙ\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u00012\u0006\u00106\u001a\u00020\u000b2\u008d\u0001\b\u0004\u00108\u001a\u0086\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u00110S¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0004\u0012\u0002H70KH\u0087\bø\u0001\u0000¢\u0006\u0002\bTJº\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u00012\u0006\u00106\u001a\u00020\u000b2w\b\u0004\u00108\u001aq\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u00110S¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0004\u0012\u0002H70HH\u0087\bø\u0001\u0000¢\u0006\u0002\bTJ\u009d\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u00012\u0006\u00106\u001a\u00020\u000b2b\b\u0004\u00108\u001a\\\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u00110S¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0004\u0012\u0002H70EH\u0087\bø\u0001\u0000¢\u0006\u0002\bTJ\u0080\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u00012\u0006\u00106\u001a\u00020\u000b2M\b\u0004\u00108\u001aG\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110S¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H70BH\u0087\bø\u0001\u0000¢\u0006\u0002\bTJc\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u00012\u0006\u00106\u001a\u00020\u000b28\b\u0004\u00108\u001a2\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u00110S¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0004\u0012\u0002H70?H\u0087\bø\u0001\u0000¢\u0006\u0002\bTJ\u001d\u0010U\u001a\u00020\u00192\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u000b0WH\u0007¢\u0006\u0004\bX\u0010YJ\u001f\u0010U\u001a\u00020\u00192\u0012\u0010V\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0W\"\u00020\u000b¢\u0006\u0002\u0010YJ7\u0010Z\u001a\u00020\u0019\"\n\b\u0001\u0010[\u0018\u0001*\u00020\\2\u001d\u00108\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H[0]\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b^H\u0086\bø\u0001\u0000J1\u0010_\u001a\u00020\u00192#\b\u0004\u00108\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(`\u0012\u0004\u0012\u00020\u00190\u0018H\u0086\bø\u0001\u0000JB\u0010_\u001a\u00020\u0019\"\n\b\u0001\u0010a\u0018\u0001*\u00028\u00002#\b\b\u00108\u001a\u001d\u0012\u0013\u0012\u0011Ha¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(`\u0012\u0004\u0012\u00020\u00190\u0018H\u0087\bø\u0001\u0000¢\u0006\u0002\bbJ1\u0010c\u001a\u00020\u00192#\b\u0004\u00108\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(`\u0012\u0004\u0012\u00020\u00190\u0018H\u0086\bø\u0001\u0000JB\u0010c\u001a\u00020\u0019\"\n\b\u0001\u0010a\u0018\u0001*\u00028\u00002#\b\b\u00108\u001a\u001d\u0012\u0013\u0012\u0011Ha¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(`\u0012\u0004\u0012\u00020\u00190\u0018H\u0087\bø\u0001\u0000¢\u0006\u0002\bdJV\u0010e\u001a\u00020\u0019\"\u0006\b\u0001\u0010f\u0018\u00012\u0006\u00106\u001a\u00020\u000b28\b\b\u00108\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(`\u0012\u0013\u0012\u0011Hf¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(g\u0012\u0004\u0012\u00020\u00190?H\u0086\bø\u0001\u0000Jg\u0010e\u001a\u00020\u0019\"\n\b\u0001\u0010a\u0018\u0001*\u00020\u0002\"\u0006\b\u0002\u0010f\u0018\u00012\u0006\u00106\u001a\u00020\u000b28\b\b\u00108\u001a2\u0012\u0013\u0012\u0011Ha¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(`\u0012\u0013\u0012\u0011Hf¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(g\u0012\u0004\u0012\u00020\u00190?H\u0087\bø\u0001\u0000¢\u0006\u0002\bhJ¨\u0001\u0010i\u001a\u00020\u0019\"\n\b\u0001\u0010a\u0018\u0001*\u00020\u0002\"\u0006\b\u0002\u0010f\u0018\u0001\"\u0006\b\u0003\u0010j\u0018\u00012*\u0010#\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002Hj0k0W\"\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002Hj0k2M\b\b\u00108\u001aG\u0012\u0013\u0012\u0011Ha¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(`\u0012\u0013\u0012\u0011Hj¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(l\u0012\u0013\u0012\u0011Hf¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(g\u0012\u0004\u0012\u00020\u00190BH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010mJ\u0006\u0010n\u001a\u00020oJ\u001a\u0010p\u001a\u0014\u0012\u0004\u0012\u00020q\u0012\u0004\u0012\u00020r\u0012\u0004\u0012\u00020\u00020?H\u0002J \u0010s\u001a\u00020\u00022\u0006\u0010t\u001a\u00020q2\u0006\u0010u\u001a\u00020r2\u0006\u0010v\u001a\u00020wH\u0002R0\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00160\nX\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00188\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR2\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00188\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b \u0010\u000e\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR0\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020$0\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b%\u0010\u000e\u001a\u0004\b&\u0010\u0010\"\u0004\b'\u0010\u0012R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010\u000e\u001a\u0004\b)\u0010*R&\u0010+\u001a\u0004\u0018\u00010,8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\u000e\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u0010\u0006\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010\u000e\u001a\u0004\b3\u00104\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006x"}, d2 = {"Lexpo/modules/kotlin/views/ViewDefinitionBuilder;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "", "viewClass", "Lkotlin/reflect/KClass;", "viewType", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KType;)V", "asyncFunctions", "", "", "Lexpo/modules/kotlin/functions/AsyncFunction;", "getAsyncFunctions$annotations", "()V", "getAsyncFunctions", "()Ljava/util/Map;", "setAsyncFunctions", "(Ljava/util/Map;)V", "callbacksDefinition", "Lexpo/modules/kotlin/views/CallbacksDefinition;", "functionBuilders", "Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "onViewDestroys", "Lkotlin/Function1;", "", "getOnViewDestroys$annotations", "getOnViewDestroys", "()Lkotlin/jvm/functions/Function1;", "setOnViewDestroys", "(Lkotlin/jvm/functions/Function1;)V", "onViewDidUpdateProps", "getOnViewDidUpdateProps$annotations", "getOnViewDidUpdateProps", "setOnViewDidUpdateProps", "props", "Lexpo/modules/kotlin/views/AnyViewProp;", "getProps$annotations", "getProps", "setProps", "getViewClass$annotations", "getViewClass", "()Lkotlin/reflect/KClass;", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewGroupDefinition$annotations", "getViewGroupDefinition", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "setViewGroupDefinition", "(Lexpo/modules/kotlin/views/ViewGroupDefinition;)V", "getViewType$annotations", "getViewType", "()Lkotlin/reflect/KType;", "AsyncFunction", "name", "R", "body", "Lkotlin/Function0;", "AsyncFunctionWithoutArgs", "P0", "Lkotlin/ParameterName;", "p0", "P1", "Lkotlin/Function2;", "p1", "P2", "Lkotlin/Function3;", "p2", "P3", "Lkotlin/Function4;", "p3", "P4", "Lkotlin/Function5;", "p4", "P5", "Lkotlin/Function6;", "p5", "P6", "Lkotlin/Function7;", "p6", "P7", "Lkotlin/Function8;", "p7", "Lexpo/modules/kotlin/Promise;", "AsyncFunctionWithPromise", "Events", "callbacks", "", "EventsWithArray", "([Ljava/lang/String;)V", "GroupView", "ParentType", "Landroid/view/ViewGroup;", "Lexpo/modules/kotlin/views/ViewGroupDefinitionBuilder;", "Lkotlin/ExtensionFunctionType;", "OnViewDestroys", "view", "ViewType", "OnViewDestroysGeneric", "OnViewDidUpdateProps", "OnViewDidUpdatePropsGeneric", "Prop", "PropType", "prop", "PropGeneric", "PropGroup", "CustomValueType", "Lkotlin/Pair;", "value", "([Lkotlin/Pair;Lkotlin/jvm/functions/Function3;)V", OperatingSystem.JsonKeys.BUILD, "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "createViewFactory", "Landroid/content/Context;", "Lexpo/modules/kotlin/AppContext;", "handleFailureDuringViewCreation", "context", "appContext", "e", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@DefinitionMarker
/* loaded from: classes2.dex */
public final class ViewDefinitionBuilder<T extends View> {
    private Map<String, AsyncFunction> asyncFunctions;
    private CallbacksDefinition callbacksDefinition;
    private Map<String, AsyncFunctionBuilder> functionBuilders;
    private Function1<? super View, Unit> onViewDestroys;
    private Function1<? super View, Unit> onViewDidUpdateProps;
    private Map<String, AnyViewProp> props;
    private final KClass<T> viewClass;
    private ViewGroupDefinition viewGroupDefinition;
    private final KType viewType;

    public static /* synthetic */ void getAsyncFunctions$annotations() {
    }

    public static /* synthetic */ void getOnViewDestroys$annotations() {
    }

    public static /* synthetic */ void getOnViewDidUpdateProps$annotations() {
    }

    public static /* synthetic */ void getProps$annotations() {
    }

    public static /* synthetic */ void getViewClass$annotations() {
    }

    public static /* synthetic */ void getViewGroupDefinition$annotations() {
    }

    public static /* synthetic */ void getViewType$annotations() {
    }

    public final Map<String, AsyncFunction> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final Function1<View, Unit> getOnViewDestroys() {
        return this.onViewDestroys;
    }

    public final Function1<View, Unit> getOnViewDidUpdateProps() {
        return this.onViewDidUpdateProps;
    }

    public final Map<String, AnyViewProp> getProps() {
        return this.props;
    }

    public final KClass<T> getViewClass() {
        return this.viewClass;
    }

    public final ViewGroupDefinition getViewGroupDefinition() {
        return this.viewGroupDefinition;
    }

    public final KType getViewType() {
        return this.viewType;
    }

    public final void setAsyncFunctions(Map<String, AsyncFunction> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.asyncFunctions = map;
    }

    public final void setOnViewDestroys(Function1<? super View, Unit> function1) {
        this.onViewDestroys = function1;
    }

    public final void setOnViewDidUpdateProps(Function1<? super View, Unit> function1) {
        this.onViewDidUpdateProps = function1;
    }

    public final void setProps(Map<String, AnyViewProp> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.props = map;
    }

    public final void setViewGroupDefinition(ViewGroupDefinition viewGroupDefinition) {
        this.viewGroupDefinition = viewGroupDefinition;
    }

    public ViewDefinitionBuilder(KClass<T> viewClass, KType viewType) {
        Intrinsics.checkNotNullParameter(viewClass, "viewClass");
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        this.viewClass = viewClass;
        this.viewType = viewType;
        this.props = new LinkedHashMap();
        this.asyncFunctions = new LinkedHashMap();
        this.functionBuilders = new LinkedHashMap();
    }

    public final void OnViewDestroys(final Function1<? super T, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        setOnViewDestroys(new Function1<View, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$OnViewDestroys$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                body.invoke(it);
            }
        });
    }

    public final /* synthetic */ <ViewType extends T> void OnViewDestroysGeneric(final Function1<? super ViewType, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setOnViewDestroys(new Function1<View, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$OnViewDestroys$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1<ViewType, Unit> function1 = body;
                Intrinsics.reifiedOperationMarker(1, "ViewType");
                function1.invoke(it);
            }
        });
    }

    public final void OnViewDidUpdateProps(final Function1<? super T, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        setOnViewDidUpdateProps(new Function1<View, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$OnViewDidUpdateProps$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                body.invoke(it);
            }
        });
    }

    public final /* synthetic */ <ViewType extends T> void OnViewDidUpdatePropsGeneric(final Function1<? super ViewType, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setOnViewDidUpdateProps(new Function1<View, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$OnViewDidUpdateProps$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1<ViewType, Unit> function1 = body;
                Intrinsics.reifiedOperationMarker(1, "ViewType");
                function1.invoke(it);
            }
        });
    }

    public final /* synthetic */ <PropType> void Prop(String name, Function2<? super T, ? super PropType, Unit> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Map<String, AnyViewProp> props = getProps();
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$Prop$$inlined$toAnyType$1 viewDefinitionBuilder$Prop$$inlined$toAnyType$1 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$Prop$$inlined$toAnyType$1
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "PropType");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "PropType");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "PropType");
        props.put(name, new ConcreteViewProp(name, new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$Prop$$inlined$toAnyType$1)), body));
    }

    public final /* synthetic */ <ViewType extends View, PropType> void PropGeneric(String name, Function2<? super ViewType, ? super PropType, Unit> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Map<String, AnyViewProp> props = getProps();
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$Prop$$inlined$toAnyType$2 viewDefinitionBuilder$Prop$$inlined$toAnyType$2 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$Prop$$inlined$toAnyType$2
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "PropType");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "PropType");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "PropType");
        props.put(name, new ConcreteViewProp(name, new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$Prop$$inlined$toAnyType$2)), body));
    }

    public final /* synthetic */ <ViewType extends View, PropType, CustomValueType> void PropGroup(Pair<String, ? extends CustomValueType>[] props, final Function3<? super ViewType, ? super CustomValueType, ? super PropType, Unit> body) {
        Intrinsics.checkNotNullParameter(props, "props");
        Intrinsics.checkNotNullParameter(body, "body");
        for (Pair<String, ? extends CustomValueType> pair : props) {
            String component1 = pair.component1();
            final CustomValueType component2 = pair.component2();
            Intrinsics.needClassReification();
            Function2 function2 = new Function2<ViewType, PropType, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$PropGroup$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object obj, Object obj2) {
                    invoke((View) obj, obj2);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Incorrect types in method signature: (TViewType;TPropType;)V */
                public final void invoke(View view, Object obj) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    body.invoke(view, component2, obj);
                }
            };
            Map<String, AnyViewProp> props2 = getProps();
            Intrinsics.needClassReification();
            ViewDefinitionBuilder$PropGroup$$inlined$PropGeneric$1 viewDefinitionBuilder$PropGroup$$inlined$PropGeneric$1 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$PropGroup$$inlined$PropGeneric$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    Intrinsics.reifiedOperationMarker(6, "PropType");
                    return null;
                }
            };
            Intrinsics.reifiedOperationMarker(4, "PropType");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "PropType");
            props2.put(component1, new ConcreteViewProp(component1, new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$PropGroup$$inlined$PropGeneric$1)), function2));
        }
    }

    public final void Events(String... callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        this.callbacksDefinition = new CallbacksDefinition(callbacks);
    }

    public final void EventsWithArray(String[] callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        this.callbacksDefinition = new CallbacksDefinition(callbacks);
    }

    public final /* synthetic */ <ParentType extends ViewGroup> void GroupView(Function1<? super ViewGroupDefinitionBuilder<ParentType>, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        KClass<T> viewClass = getViewClass();
        Intrinsics.reifiedOperationMarker(4, "ParentType");
        Intrinsics.areEqual(viewClass, Reflection.getOrCreateKotlinClass(ViewGroup.class));
        if (getViewGroupDefinition() != null) {
            throw new IllegalArgumentException("The viewManager definition may have exported only one groupView definition.".toString());
        }
        ViewGroupDefinitionBuilder viewGroupDefinitionBuilder = new ViewGroupDefinitionBuilder();
        body.invoke(viewGroupDefinitionBuilder);
        setViewGroupDefinition(viewGroupDefinitionBuilder.build());
    }

    public final AsyncFunction AsyncFunctionWithoutArgs(String name, final Function0<? extends Object> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(name, new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$1
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> AsyncFunction AsyncFunction(String name, final Function1<? super P0, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        if (Object.class == Promise.class) {
            Intrinsics.needClassReification();
            asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function1<P0, R> function1 = body;
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    function1.invoke(promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(4, "P0");
            Intrinsics.needClassReification();
            ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$1 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$1 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    Intrinsics.reifiedOperationMarker(6, "P0");
                    return null;
                }
            };
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$1))};
            Intrinsics.needClassReification();
            Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$6
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
        }
        getAsyncFunctions().put(name, asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0> AsyncFunction AsyncFunctionWithPromise(String name, final Function2<? super P0, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$4 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$4 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$4
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$4))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$10
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
        getAsyncFunctions().put(name, asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1> AsyncFunction AsyncFunctionWithPromise(String name, final Function3<? super P0, ? super P1, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$8 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$8 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$8
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$9 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$9 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$9
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$8)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$9))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$14
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
        getAsyncFunctions().put(name, asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> AsyncFunction AsyncFunctionWithPromise(String name, final Function4<? super P0, ? super P1, ? super P2, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$14 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$14 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$14
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$15 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$15 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$15
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$16 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$16 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$16
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$14)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$15)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$16))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$18
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
        getAsyncFunctions().put(name, asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> AsyncFunction AsyncFunctionWithPromise(String name, final Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$22 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$22 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$22
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$23 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$23 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$23
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$24 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$24 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$24
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$25 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$25 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$25
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$25))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$22
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
        getAsyncFunctions().put(name, asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> AsyncFunction AsyncFunctionWithPromise(String name, final Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$32 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$32 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$32
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$33 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$33 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$33
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$34 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$34 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$34
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$35 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$35 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$35
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$36 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$36 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$36
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$32)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$33)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$34)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$35)), new AnyType(new LazyKType(orCreateKotlinClass5, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$36))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$26
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
        getAsyncFunctions().put(name, asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> AsyncFunction AsyncFunctionWithPromise(String name, final Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$44 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$44 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$44
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$45 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$45 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$45
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$46 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$46 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$46
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$47 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$47 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$47
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$48 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$48 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$48
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$49 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$49 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$49
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$44)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$45)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$46)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$47)), new AnyType(new LazyKType(orCreateKotlinClass5, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$48)), new AnyType(new LazyKType(orCreateKotlinClass6, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$49))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$30
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
        getAsyncFunctions().put(name, asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> AsyncFunction AsyncFunctionWithPromise(String name, final Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super Promise, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$58 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$58 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$58
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$59 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$59 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$59
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$60 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$60 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$60
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$61 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$61 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$61
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$62 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$62 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$62
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$63 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$63 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$63
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$64 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$64 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$64
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$58)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$59)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$60)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$61)), new AnyType(new LazyKType(orCreateKotlinClass5, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$62)), new AnyType(new LazyKType(orCreateKotlinClass6, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$63)), new AnyType(new LazyKType(orCreateKotlinClass7, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$64))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$34
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
        getAsyncFunctions().put(name, asyncFunctionWithPromiseComponent);
        return asyncFunctionWithPromiseComponent;
    }

    public final AsyncFunctionBuilder AsyncFunction(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        AsyncFunctionBuilder asyncFunctionBuilder = new AsyncFunctionBuilder(name);
        this.functionBuilders.put(name, asyncFunctionBuilder);
        return asyncFunctionBuilder;
    }

    private final Function2<Context, AppContext, View> createViewFactory() {
        return new Function2<Context, AppContext, View>(this) { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$createViewFactory$1
            final /* synthetic */ ViewDefinitionBuilder<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function2
            public final View invoke(Context context, AppContext appContext) {
                Constructor constructor;
                View handleFailureDuringViewCreation;
                View handleFailureDuringViewCreation2;
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(appContext, "appContext");
                Constructor constructor2 = null;
                try {
                    constructor = JvmClassMappingKt.getJavaClass(this.this$0.getViewClass()).getConstructor(Context.class, AppContext.class);
                } catch (NoSuchMethodException unused) {
                    constructor = null;
                }
                if (constructor != null) {
                    ViewDefinitionBuilder<T> viewDefinitionBuilder = this.this$0;
                    try {
                        Object newInstance = constructor.newInstance(context, appContext);
                        Intrinsics.checkNotNull(newInstance);
                        return (View) newInstance;
                    } catch (Throwable th) {
                        handleFailureDuringViewCreation2 = viewDefinitionBuilder.handleFailureDuringViewCreation(context, appContext, th);
                        return handleFailureDuringViewCreation2;
                    }
                }
                try {
                    constructor2 = JvmClassMappingKt.getJavaClass(this.this$0.getViewClass()).getConstructor(Context.class);
                } catch (NoSuchMethodException unused2) {
                }
                if (constructor2 != null) {
                    ViewDefinitionBuilder<T> viewDefinitionBuilder2 = this.this$0;
                    try {
                        Object newInstance2 = constructor2.newInstance(context);
                        Intrinsics.checkNotNull(newInstance2);
                        return (View) newInstance2;
                    } catch (Throwable th2) {
                        handleFailureDuringViewCreation = viewDefinitionBuilder2.handleFailureDuringViewCreation(context, appContext, th2);
                        return handleFailureDuringViewCreation;
                    }
                }
                throw new IllegalStateException("Didn't find a correct constructor for " + this.this$0.getViewClass());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final View handleFailureDuringViewCreation(Context context, AppContext appContext, Throwable e) {
        UnexpectedException unexpectedException;
        Log.e("ExpoModulesCore", "Couldn't create view of type " + this.viewClass, e);
        ErrorManagerModule errorManager = appContext.getErrorManager();
        if (errorManager != null) {
            if (e instanceof CodedException) {
                unexpectedException = (CodedException) e;
            } else {
                unexpectedException = new UnexpectedException(e);
            }
            errorManager.reportExceptionToLogBox(unexpectedException);
        }
        if (ViewGroup.class.isAssignableFrom(JvmClassMappingKt.getJavaClass((KClass) this.viewClass))) {
            return new ErrorGroupView(context);
        }
        return new ErrorView(context);
    }

    public final ViewManagerDefinition build() {
        Map<String, AsyncFunction> map = this.asyncFunctions;
        Map<String, AsyncFunctionBuilder> map2 = this.functionBuilders;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map2.size()));
        Iterator<T> it = map2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), ((AsyncFunctionBuilder) entry.getValue()).build$expo_modules_core_release());
        }
        Map plus = MapsKt.plus(map, linkedHashMap);
        Iterator it2 = plus.entrySet().iterator();
        while (it2.hasNext()) {
            BaseAsyncFunctionComponent baseAsyncFunctionComponent = (BaseAsyncFunctionComponent) ((Map.Entry) it2.next()).getValue();
            baseAsyncFunctionComponent.runOnQueue(Queues.MAIN);
            baseAsyncFunctionComponent.setOwnerType(this.viewType);
            baseAsyncFunctionComponent.setCanTakeOwner(true);
        }
        return new ViewManagerDefinition(createViewFactory(), JvmClassMappingKt.getJavaClass((KClass) this.viewClass), this.props, this.onViewDestroys, this.callbacksDefinition, this.viewGroupDefinition, this.onViewDidUpdateProps, CollectionsKt.toList(plus.values()));
    }

    public final /* synthetic */ <R> AsyncFunction AsyncFunction(String name, final Function0<? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        AnyType[] anyTypeArr = new AnyType[0];
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$3
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> AsyncFunction AsyncFunction(String name, final Function2<? super P0, ? super P1, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$2 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$2 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$2
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$3 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$3 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$3
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$2)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$3))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$8
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> AsyncFunction AsyncFunction(String name, final Function3<? super P0, ? super P1, ? super P2, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$5 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$5 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$5
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$6 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$6 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$6
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$7 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$7 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$7
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$5)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$6)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$7))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$12
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> AsyncFunction AsyncFunction(String name, final Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$10 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$10 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$10
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$11 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$11 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$11
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$12 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$12 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$12
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$13 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$13 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$13
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$10)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$11)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$12)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$13))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$16
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> AsyncFunction AsyncFunction(String name, final Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$17 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$17 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$17
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$18 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$18 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$18
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$19 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$19 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$19
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$20 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$20 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$20
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$21 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$21 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$21
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$17)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$18)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$19)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$20)), new AnyType(new LazyKType(orCreateKotlinClass5, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$21))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$20
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> AsyncFunction AsyncFunction(String name, final Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$26 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$26 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$26
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$27 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$27 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$27
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$28 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$28 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$28
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$29 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$29 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$29
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$30 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$30 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$30
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$31 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$31 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$31
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$26)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$27)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$28)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$29)), new AnyType(new LazyKType(orCreateKotlinClass5, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$30)), new AnyType(new LazyKType(orCreateKotlinClass6, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$31))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$24
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> AsyncFunction AsyncFunction(String name, final Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$37 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$37 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$37
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$38 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$38 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$38
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$39 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$39 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$39
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$40 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$40 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$40
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$41 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$41 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$41
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$42 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$42 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$42
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$43 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$43 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$43
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$37)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$38)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$39)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$40)), new AnyType(new LazyKType(orCreateKotlinClass5, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$41)), new AnyType(new LazyKType(orCreateKotlinClass6, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$42)), new AnyType(new LazyKType(orCreateKotlinClass7, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$43))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$28
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> AsyncFunction AsyncFunction(String name, final Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.reifiedOperationMarker(4, "P7");
        Intrinsics.needClassReification();
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$50 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$50 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$50
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$51 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$51 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$51
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$52 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$52 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$52
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$53 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$53 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$53
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$54 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$54 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$54
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$55 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$55 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$55
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$56 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$56 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$56
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
        ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$57 viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$57 = new Function0<KType>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$57
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P7");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$50)), new AnyType(new LazyKType(orCreateKotlinClass2, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$51)), new AnyType(new LazyKType(orCreateKotlinClass3, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$52)), new AnyType(new LazyKType(orCreateKotlinClass4, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$53)), new AnyType(new LazyKType(orCreateKotlinClass5, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$54)), new AnyType(new LazyKType(orCreateKotlinClass6, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$55)), new AnyType(new LazyKType(orCreateKotlinClass7, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$56)), new AnyType(new LazyKType(orCreateKotlinClass8, false, viewDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$57))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$AsyncFunction$32
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
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }
}
