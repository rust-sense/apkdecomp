package expo.modules.kotlin.objects;

import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.EventsDefinition;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.FunctionBuilder;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.Enumerable;
import expo.modules.kotlin.types.LazyKType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
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
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;

/* compiled from: ObjectDefinitionBuilder.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0005J,\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0\u000fH\u0086\bø\u0001\u0000J+\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u00052\u0010\b\u0004\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000fH\u0087\bø\u0001\u0000¢\u0006\u0002\b+JI\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u00012\u0006\u0010(\u001a\u00020\u00052#\b\u0004\u0010*\u001a\u001d\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0004\u0012\u0002H)0-H\u0086\bø\u0001\u0000Jf\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u00012\u0006\u0010(\u001a\u00020\u000528\b\u0004\u0010*\u001a2\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002H)01H\u0086\bø\u0001\u0000J\u0083\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u00012\u0006\u0010(\u001a\u00020\u00052M\b\u0004\u0010*\u001aG\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0004\u0012\u0002H)04H\u0086\bø\u0001\u0000J \u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u00012\u0006\u0010(\u001a\u00020\u00052b\b\u0004\u0010*\u001a\\\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0004\u0012\u0002H)07H\u0086\bø\u0001\u0000J½\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u00012\u0006\u0010(\u001a\u00020\u00052w\b\u0004\u0010*\u001aq\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002H)0:H\u0086\bø\u0001\u0000JÜ\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u008d\u0001\b\u0004\u0010*\u001a\u0086\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002H)0=H\u0086\bø\u0001\u0000Jù\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u00012\u0006\u0010(\u001a\u00020\u00052¢\u0001\b\u0004\u0010*\u001a\u009b\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0004\u0012\u0002H)0@H\u0086\bø\u0001\u0000J\u0096\u0002\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u0001\"\u0006\b\b\u0010B\u0018\u00012\u0006\u0010(\u001a\u00020\u00052·\u0001\b\u0004\u0010*\u001a°\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0013\u0012\u0011HB¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(D\u0012\u0004\u0012\u0002H)0CH\u0086\bø\u0001\u0000J\u0093\u0002\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u00012\u0006\u0010(\u001a\u00020\u00052·\u0001\b\u0004\u0010*\u001a°\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110E¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(D\u0012\u0004\u0012\u0002H)0CH\u0087\bø\u0001\u0000¢\u0006\u0002\bFJö\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u00012\u0006\u0010(\u001a\u00020\u00052¢\u0001\b\u0004\u0010*\u001a\u009b\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110E¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0004\u0012\u0002H)0@H\u0087\bø\u0001\u0000¢\u0006\u0002\bFJÙ\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u008d\u0001\b\u0004\u0010*\u001a\u0086\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110E¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002H)0=H\u0087\bø\u0001\u0000¢\u0006\u0002\bFJº\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u00012\u0006\u0010(\u001a\u00020\u00052w\b\u0004\u0010*\u001aq\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110E¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002H)0:H\u0087\bø\u0001\u0000¢\u0006\u0002\bFJ\u009d\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u00012\u0006\u0010(\u001a\u00020\u00052b\b\u0004\u0010*\u001a\\\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110E¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0004\u0012\u0002H)07H\u0087\bø\u0001\u0000¢\u0006\u0002\bFJ\u0080\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u00012\u0006\u0010(\u001a\u00020\u00052M\b\u0004\u0010*\u001aG\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u00110E¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0004\u0012\u0002H)04H\u0087\bø\u0001\u0000¢\u0006\u0002\bFJc\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u00012\u0006\u0010(\u001a\u00020\u000528\b\u0004\u0010*\u001a2\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u00110E¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002H)01H\u0087\bø\u0001\u0000¢\u0006\u0002\bFJ\"\u0010G\u001a\u00020H2\u001a\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00100\u000fJ;\u0010G\u001a\u00020H2.\u0010I\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010K0J\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010K¢\u0006\u0002\u0010LJ\u001f\u0010M\u001a\u00020H\"\u0014\b\u0000\u0010N\u0018\u0001*\u00020O*\b\u0012\u0004\u0012\u0002HN0PH\u0086\bJ\u001d\u0010M\u001a\u00020H2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00050JH\u0007¢\u0006\u0004\bR\u0010SJ\u001f\u0010M\u001a\u00020H2\u0012\u0010Q\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050J\"\u00020\u0005¢\u0006\u0002\u0010SJ\u000e\u0010T\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0005J,\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0\u000fH\u0086\bø\u0001\u0000J+\u0010T\u001a\u00020#2\u0006\u0010(\u001a\u00020\u00052\u0010\b\u0004\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000fH\u0087\bø\u0001\u0000¢\u0006\u0002\bUJI\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u00012\u0006\u0010(\u001a\u00020\u00052#\b\u0004\u0010*\u001a\u001d\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0004\u0012\u0002H)0-H\u0086\bø\u0001\u0000Jf\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u00012\u0006\u0010(\u001a\u00020\u000528\b\u0004\u0010*\u001a2\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002H)01H\u0086\bø\u0001\u0000J\u0083\u0001\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u00012\u0006\u0010(\u001a\u00020\u00052M\b\u0004\u0010*\u001aG\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0004\u0012\u0002H)04H\u0086\bø\u0001\u0000J \u0001\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u00012\u0006\u0010(\u001a\u00020\u00052b\b\u0004\u0010*\u001a\\\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0004\u0012\u0002H)07H\u0086\bø\u0001\u0000J½\u0001\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u00012\u0006\u0010(\u001a\u00020\u00052w\b\u0004\u0010*\u001aq\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002H)0:H\u0086\bø\u0001\u0000JÜ\u0001\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u008d\u0001\b\u0004\u0010*\u001a\u0086\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002H)0=H\u0086\bø\u0001\u0000Jù\u0001\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u00012\u0006\u0010(\u001a\u00020\u00052¢\u0001\b\u0004\u0010*\u001a\u009b\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0004\u0012\u0002H)0@H\u0086\bø\u0001\u0000J\u0096\u0002\u0010T\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u0001\"\u0006\b\b\u0010B\u0018\u00012\u0006\u0010(\u001a\u00020\u00052·\u0001\b\u0004\u0010*\u001a°\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0013\u0012\u0011HB¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(D\u0012\u0004\u0012\u0002H)0CH\u0086\bø\u0001\u0000J\u001c\u0010V\u001a\u00020H2\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u00020H0\u000fH\u0086\bø\u0001\u0000J\u001c\u0010W\u001a\u00020H2\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u00020H0\u000fH\u0086\bø\u0001\u0000J\u0010\u0010X\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0005H\u0016J*\u0010X\u001a\u00020\u0019\"\u0004\b\u0000\u0010N2\u0006\u0010(\u001a\u00020\u00052\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u0002HN0\u000fH\u0086\bø\u0001\u0000J\u0006\u0010Y\u001a\u00020ZR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R0\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00190\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\u0002\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR0\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001e0\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001f\u0010\u0002\u001a\u0004\b \u0010\u000b\"\u0004\b!\u0010\rR0\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020#0\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b$\u0010\u0002\u001a\u0004\b%\u0010\u000b\"\u0004\b&\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006["}, d2 = {"Lexpo/modules/kotlin/objects/ObjectDefinitionBuilder;", "", "()V", "asyncFunctionBuilders", "", "", "Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "asyncFunctions", "Lexpo/modules/kotlin/functions/AsyncFunction;", "getAsyncFunctions$annotations", "getAsyncFunctions", "()Ljava/util/Map;", "setAsyncFunctions", "(Ljava/util/Map;)V", "constantsProvider", "Lkotlin/Function0;", "", "eventsDefinition", "Lexpo/modules/kotlin/events/EventsDefinition;", "getEventsDefinition$annotations", "getEventsDefinition", "()Lexpo/modules/kotlin/events/EventsDefinition;", "setEventsDefinition", "(Lexpo/modules/kotlin/events/EventsDefinition;)V", "properties", "Lexpo/modules/kotlin/objects/PropertyComponentBuilder;", "getProperties$annotations", "getProperties", "setProperties", "syncFunctionBuilder", "Lexpo/modules/kotlin/functions/FunctionBuilder;", "getSyncFunctionBuilder$annotations", "getSyncFunctionBuilder", "setSyncFunctionBuilder", "syncFunctions", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getSyncFunctions$annotations", "getSyncFunctions", "setSyncFunctions", "AsyncFunction", "name", "R", "body", "AsyncFunctionWithoutArgs", "P0", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "p0", "P1", "Lkotlin/Function2;", "p1", "P2", "Lkotlin/Function3;", "p2", "P3", "Lkotlin/Function4;", "p3", "P4", "Lkotlin/Function5;", "p4", "P5", "Lkotlin/Function6;", "p5", "P6", "Lkotlin/Function7;", "p6", "P7", "Lkotlin/Function8;", "p7", "Lexpo/modules/kotlin/Promise;", "AsyncFunctionWithPromise", "Constants", "", "constants", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)V", "Events", ExifInterface.GPS_DIRECTION_TRUE, "Lexpo/modules/kotlin/types/Enumerable;", "", "events", "EventsWithArray", "([Ljava/lang/String;)V", "Function", "FunctionWithoutArgs", "OnStartObserving", "OnStopObserving", "Property", "buildObject", "Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ObjectDefinitionBuilder {
    private EventsDefinition eventsDefinition;
    private Function0<? extends Map<String, ? extends Object>> constantsProvider = new Function0<Map<String, ? extends Object>>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$constantsProvider$1
        @Override // kotlin.jvm.functions.Function0
        public final Map<String, ? extends Object> invoke() {
            return MapsKt.emptyMap();
        }
    };
    private Map<String, SyncFunctionComponent> syncFunctions = new LinkedHashMap();
    private Map<String, FunctionBuilder> syncFunctionBuilder = new LinkedHashMap();
    private Map<String, AsyncFunction> asyncFunctions = new LinkedHashMap();
    private Map<String, AsyncFunctionBuilder> asyncFunctionBuilders = new LinkedHashMap();
    private Map<String, PropertyComponentBuilder> properties = new LinkedHashMap();

    public static /* synthetic */ void getAsyncFunctions$annotations() {
    }

    public static /* synthetic */ void getEventsDefinition$annotations() {
    }

    public static /* synthetic */ void getProperties$annotations() {
    }

    public static /* synthetic */ void getSyncFunctionBuilder$annotations() {
    }

    public static /* synthetic */ void getSyncFunctions$annotations() {
    }

    public final void Constants(Function0<? extends Map<String, ? extends Object>> constantsProvider) {
        Intrinsics.checkNotNullParameter(constantsProvider, "constantsProvider");
        this.constantsProvider = constantsProvider;
    }

    public final Map<String, AsyncFunction> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final EventsDefinition getEventsDefinition() {
        return this.eventsDefinition;
    }

    public final Map<String, PropertyComponentBuilder> getProperties() {
        return this.properties;
    }

    public final Map<String, FunctionBuilder> getSyncFunctionBuilder() {
        return this.syncFunctionBuilder;
    }

    public final Map<String, SyncFunctionComponent> getSyncFunctions() {
        return this.syncFunctions;
    }

    public final void setAsyncFunctions(Map<String, AsyncFunction> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.asyncFunctions = map;
    }

    public final void setEventsDefinition(EventsDefinition eventsDefinition) {
        this.eventsDefinition = eventsDefinition;
    }

    public final void setProperties(Map<String, PropertyComponentBuilder> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.properties = map;
    }

    public final void setSyncFunctionBuilder(Map<String, FunctionBuilder> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.syncFunctionBuilder = map;
    }

    public final void setSyncFunctions(Map<String, SyncFunctionComponent> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.syncFunctions = map;
    }

    public final void Constants(final Pair<String, ? extends Object>... constants) {
        Intrinsics.checkNotNullParameter(constants, "constants");
        this.constantsProvider = new Function0<Map<String, ? extends Object>>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Constants$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends Object> invoke() {
                return MapsKt.toMap(constants);
            }
        };
    }

    public final FunctionBuilder Function(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        FunctionBuilder functionBuilder = new FunctionBuilder(name);
        this.syncFunctionBuilder.put(name, functionBuilder);
        return functionBuilder;
    }

    public final SyncFunctionComponent FunctionWithoutArgs(String name, final Function0<? extends Object> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$2
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
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R> SyncFunctionComponent Function(String name, final Function0<? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return body.invoke();
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> SyncFunctionComponent Function(String name, final Function1<? super P0, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.needClassReification();
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$3 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$3 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$3
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$3))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0]);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> SyncFunctionComponent Function(String name, final Function2<? super P0, ? super P1, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.needClassReification();
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$1 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$1 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$1
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$2 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$2 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$2
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$1)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$2))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1]);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> SyncFunctionComponent Function(String name, final Function3<? super P0, ? super P1, ? super P2, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.needClassReification();
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$4 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$4 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$4
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$5 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$5 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$5
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$6 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$6 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$6
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$4)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$5)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$6))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2]);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> SyncFunctionComponent Function(String name, final Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.needClassReification();
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$7 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$7 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$7
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$8 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$8 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$8
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$9 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$9 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$9
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$10 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$10 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$10
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$7)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$8)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$9)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$10))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> SyncFunctionComponent Function(String name, final Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.needClassReification();
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$11 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$11 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$11
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$12 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$12 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$12
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$13 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$13 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$13
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$14 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$14 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$14
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$15 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$15 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$15
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$11)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$12)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$13)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$14)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$15))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$14
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SyncFunctionComponent Function(String name, final Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.needClassReification();
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$16 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$16 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$16
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$17 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$17 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$17
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$18 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$18 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$18
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$19 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$19 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$19
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$20 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$20 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$20
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$21 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$21 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$21
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$16)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$17)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$18)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$19)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$20)), new AnyType(new LazyKType(orCreateKotlinClass6, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$21))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$16
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SyncFunctionComponent Function(String name, final Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> body) {
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$22 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$22 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$22
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$23 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$23 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$23
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$24 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$24 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$24
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$25 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$25 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$25
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$26 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$26 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$26
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$27 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$27 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$27
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$28 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$28 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$28
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$25)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$26)), new AnyType(new LazyKType(orCreateKotlinClass6, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$27)), new AnyType(new LazyKType(orCreateKotlinClass7, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$28))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$18
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SyncFunctionComponent Function(String name, final Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> body) {
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$29 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$29 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$29
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$30 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$30 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$30
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$31 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$31 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$31
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$32 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$32 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$32
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$33 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$33 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$33
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$34 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$34 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$34
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$35 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$35 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$35
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
        ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$36 objectDefinitionBuilder$Function$$inlined$toArgsArray$default$36 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$$inlined$toArgsArray$default$36
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P7");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$29)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$30)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$31)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$32)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$33)), new AnyType(new LazyKType(orCreateKotlinClass6, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$34)), new AnyType(new LazyKType(orCreateKotlinClass7, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$35)), new AnyType(new LazyKType(orCreateKotlinClass8, false, objectDefinitionBuilder$Function$$inlined$toArgsArray$default$36))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$20
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final AsyncFunction AsyncFunctionWithoutArgs(String name, final Function0<? extends Object> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(name, new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$1
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
        if (Intrinsics.areEqual(Object.class, Promise.class)) {
            Intrinsics.needClassReification();
            asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$5
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
            ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$1 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$1 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    Intrinsics.reifiedOperationMarker(6, "P0");
                    return null;
                }
            };
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$1))};
            Intrinsics.needClassReification();
            Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$6
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$7 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$7 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$7
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$7))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$10
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$11 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$11 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$11
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$12 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$12 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$12
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$11)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$12))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$14
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$2 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$2 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$2
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$3 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$3 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$3
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$4 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$4 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$4
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$2)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$3)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$4))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$18
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$22 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$22 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$22
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$23 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$23 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$23
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$24 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$24 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$24
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$25 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$25 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$25
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$25))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$22
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$32 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$32 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$32
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$33 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$33 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$33
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$34 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$34 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$34
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$35 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$35 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$35
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$36 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$36 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$36
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$32)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$33)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$34)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$35)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$36))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$26
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$44 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$44 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$44
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$45 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$45 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$45
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$46 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$46 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$46
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$47 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$47 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$47
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$48 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$48 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$48
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$49 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$49 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$49
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$44)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$45)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$46)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$47)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$48)), new AnyType(new LazyKType(orCreateKotlinClass6, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$49))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$30
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$58 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$58 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$58
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$59 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$59 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$59
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$60 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$60 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$60
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$61 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$61 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$61
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$62 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$62 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$62
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$63 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$63 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$63
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$64 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$64 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$64
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$58)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$59)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$60)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$61)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$62)), new AnyType(new LazyKType(orCreateKotlinClass6, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$63)), new AnyType(new LazyKType(orCreateKotlinClass7, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$64))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$34
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
        this.asyncFunctionBuilders.put(name, asyncFunctionBuilder);
        return asyncFunctionBuilder;
    }

    public final void Events(String... events) {
        Intrinsics.checkNotNullParameter(events, "events");
        this.eventsDefinition = new EventsDefinition(events);
    }

    public final void EventsWithArray(String[] events) {
        Intrinsics.checkNotNullParameter(events, "events");
        this.eventsDefinition = new EventsDefinition(events);
    }

    public final /* synthetic */ <T extends Enum<T> & Enumerable> void Events() {
        ArrayList arrayList;
        List<KParameter> parameters;
        Object obj;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KFunction primaryConstructor = KClasses.getPrimaryConstructor(Reflection.getOrCreateKotlinClass(Enum.class));
        if (primaryConstructor != null && (parameters = primaryConstructor.getParameters()) != null && parameters.size() == 1) {
            String name = ((KParameter) CollectionsKt.first((List) primaryConstructor.getParameters())).getName();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Iterator it = KClasses.getDeclaredMemberProperties(Reflection.getOrCreateKotlinClass(Enum.class)).iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (Intrinsics.areEqual(((KProperty1) obj).getName(), name)) {
                        break;
                    }
                }
            }
            KProperty1 kProperty1 = (KProperty1) obj;
            if (kProperty1 == null) {
                throw new IllegalArgumentException(("Cannot find a property for " + name + " parameter").toString());
            }
            if (!Intrinsics.areEqual(kProperty1.getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(String.class))) {
                throw new IllegalArgumentException("The enum parameter has to be a string.".toString());
            }
            Intrinsics.reifiedOperationMarker(5, ExifInterface.GPS_DIRECTION_TRUE);
            arrayList = new ArrayList(0);
        } else {
            Intrinsics.reifiedOperationMarker(5, ExifInterface.GPS_DIRECTION_TRUE);
            arrayList = new ArrayList(0);
        }
        setEventsDefinition(new EventsDefinition((String[]) arrayList.toArray(new String[0])));
    }

    public PropertyComponentBuilder Property(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        PropertyComponentBuilder propertyComponentBuilder = new PropertyComponentBuilder(name);
        this.properties.put(name, propertyComponentBuilder);
        return propertyComponentBuilder;
    }

    public final <T> PropertyComponentBuilder Property(String name, Function0<? extends T> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        PropertyComponentBuilder propertyComponentBuilder = new PropertyComponentBuilder(name);
        propertyComponentBuilder.setGetter(new SyncFunctionComponent("get", new AnyType[0], new PropertyComponentBuilder$get$1$1(body)));
        getProperties().put(name, propertyComponentBuilder);
        return propertyComponentBuilder;
    }

    public final ObjectDefinitionData buildObject() {
        Function0<? extends Map<String, ? extends Object>> function0 = this.constantsProvider;
        Map<String, SyncFunctionComponent> map = this.syncFunctions;
        Map<String, FunctionBuilder> map2 = this.syncFunctionBuilder;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map2.size()));
        Iterator<T> it = map2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), ((FunctionBuilder) entry.getValue()).build$expo_modules_core_release());
        }
        Map plus = MapsKt.plus(map, linkedHashMap);
        Map<String, AsyncFunction> map3 = this.asyncFunctions;
        Map<String, AsyncFunctionBuilder> map4 = this.asyncFunctionBuilders;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(map4.size()));
        Iterator<T> it2 = map4.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            linkedHashMap2.put(entry2.getKey(), ((AsyncFunctionBuilder) entry2.getValue()).build$expo_modules_core_release());
        }
        Map plus2 = MapsKt.plus(map3, linkedHashMap2);
        EventsDefinition eventsDefinition = this.eventsDefinition;
        Map<String, PropertyComponentBuilder> map5 = this.properties;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(map5.size()));
        Iterator<T> it3 = map5.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry entry3 = (Map.Entry) it3.next();
            linkedHashMap3.put(entry3.getKey(), ((PropertyComponentBuilder) entry3.getValue()).build());
        }
        return new ObjectDefinitionData(function0, plus, plus2, eventsDefinition, linkedHashMap3);
    }

    public final /* synthetic */ <R> AsyncFunction AsyncFunction(String name, final Function0<? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        AnyType[] anyTypeArr = new AnyType[0];
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$3
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$5 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$5 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$5
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$6 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$6 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$6
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$5)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$6))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$8
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$8 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$8 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$8
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$9 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$9 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$9
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$10 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$10 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$10
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$8)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$9)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$10))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$12
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$13 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$13 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$13
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$14 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$14 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$14
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$15 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$15 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$15
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$16 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$16 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$16
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$13)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$14)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$15)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$16))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$16
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$17 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$17 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$17
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$18 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$18 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$18
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$19 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$19 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$19
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$20 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$20 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$20
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$21 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$21 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$21
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$17)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$18)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$19)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$20)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$21))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$20
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$26 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$26 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$26
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$27 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$27 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$27
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$28 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$28 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$28
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$29 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$29 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$29
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$30 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$30 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$30
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$31 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$31 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$31
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$26)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$27)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$28)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$29)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$30)), new AnyType(new LazyKType(orCreateKotlinClass6, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$31))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$24
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$37 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$37 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$37
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$38 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$38 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$38
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$39 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$39 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$39
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$40 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$40 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$40
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$41 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$41 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$41
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$42 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$42 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$42
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$43 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$43 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$43
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$37)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$38)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$39)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$40)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$41)), new AnyType(new LazyKType(orCreateKotlinClass6, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$42)), new AnyType(new LazyKType(orCreateKotlinClass7, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$43))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$28
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$50 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$50 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$50
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$51 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$51 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$51
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$52 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$52 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$52
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$53 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$53 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$53
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$54 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$54 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$54
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$55 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$55 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$55
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$56 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$56 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$56
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
        ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$57 objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$57 = new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$57
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P7");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$50)), new AnyType(new LazyKType(orCreateKotlinClass2, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$51)), new AnyType(new LazyKType(orCreateKotlinClass3, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$52)), new AnyType(new LazyKType(orCreateKotlinClass4, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$53)), new AnyType(new LazyKType(orCreateKotlinClass5, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$54)), new AnyType(new LazyKType(orCreateKotlinClass6, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$55)), new AnyType(new LazyKType(orCreateKotlinClass7, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$56)), new AnyType(new LazyKType(orCreateKotlinClass8, false, objectDefinitionBuilder$AsyncFunction$$inlined$toArgsArray$default$57))};
        Intrinsics.needClassReification();
        Function1<Object[], R> function1 = new Function1<Object[], R>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$32
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

    public final void OnStartObserving(final Function0<Unit> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        if (Intrinsics.areEqual(String.class, Promise.class)) {
            asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("startObserving", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$OnStartObserving$$inlined$AsyncFunction$1
                {
                    super(2);
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function0.this.invoke();
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$OnStartObserving$$inlined$AsyncFunction$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            }))};
            Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$OnStartObserving$$inlined$AsyncFunction$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Function0.this.invoke();
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                asyncFunctionComponent = new StringAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                            } else {
                                asyncFunctionComponent = new AsyncFunctionComponent("startObserving", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new FloatAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new DoubleAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new BoolAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                }
            } else {
                asyncFunctionComponent = new IntAsyncFunctionComponent("startObserving", anyTypeArr, function1);
            }
            asyncFunctionWithPromiseComponent = asyncFunctionComponent;
        }
        getAsyncFunctions().put("startObserving", asyncFunctionWithPromiseComponent);
    }

    public final void OnStopObserving(final Function0<Unit> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        Intrinsics.checkNotNullParameter(body, "body");
        if (Intrinsics.areEqual(String.class, Promise.class)) {
            asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("stopObserving", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$OnStopObserving$$inlined$AsyncFunction$1
                {
                    super(2);
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function0.this.invoke();
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$OnStopObserving$$inlined$AsyncFunction$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            }))};
            Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$OnStopObserving$$inlined$AsyncFunction$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Function0.this.invoke();
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                asyncFunctionComponent = new StringAsyncFunctionComponent("stopObserving", anyTypeArr, function1);
                            } else {
                                asyncFunctionComponent = new AsyncFunctionComponent("stopObserving", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new FloatAsyncFunctionComponent("stopObserving", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new DoubleAsyncFunctionComponent("stopObserving", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new BoolAsyncFunctionComponent("stopObserving", anyTypeArr, function1);
                }
            } else {
                asyncFunctionComponent = new IntAsyncFunctionComponent("stopObserving", anyTypeArr, function1);
            }
            asyncFunctionWithPromiseComponent = asyncFunctionComponent;
        }
        getAsyncFunctions().put("stopObserving", asyncFunctionWithPromiseComponent);
    }
}
