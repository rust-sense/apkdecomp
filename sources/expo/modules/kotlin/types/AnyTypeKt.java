package expo.modules.kotlin.types;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: AnyType.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\u0086\b\u001a,\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0000\u0010\u0005\u0018\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0086\b¢\u0006\u0002\u0010\b\u001aD\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0000\u0010\u0005\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0086\b¢\u0006\u0002\u0010\u000b\u001a\\\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0000\u0010\u0005\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u0007H\u0086\b¢\u0006\u0002\u0010\u000e\u001at\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0000\u0010\u0005\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u00072\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007H\u0086\b¢\u0006\u0002\u0010\u0011\u001a\u008c\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0000\u0010\u0005\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u00072\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00072\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0007H\u0086\b¢\u0006\u0002\u0010\u0014\u001a¤\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0000\u0010\u0005\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u0001\"\u0006\b\u0005\u0010\u0015\u0018\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u00072\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00072\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00072\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0007H\u0086\b¢\u0006\u0002\u0010\u0017\u001a¼\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0000\u0010\u0005\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u0001\"\u0006\b\u0005\u0010\u0015\u0018\u0001\"\u0006\b\u0006\u0010\u0018\u0018\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u00072\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00072\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00072\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00072\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0007H\u0086\b¢\u0006\u0002\u0010\u001a\u001aÔ\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0000\u0010\u0005\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u0001\"\u0006\b\u0005\u0010\u0015\u0018\u0001\"\u0006\b\u0006\u0010\u0018\u0018\u0001\"\u0006\b\u0007\u0010\u001b\u0018\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u00072\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00072\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00072\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00072\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u00072\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u0007H\u0086\b¢\u0006\u0002\u0010\u001d\u001a\u001e\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "}, d2 = {"toAnyType", "Lexpo/modules/kotlin/types/AnyType;", ExifInterface.GPS_DIRECTION_TRUE, "toArgsArray", "", "P0", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)[Lexpo/modules/kotlin/types/AnyType;", "P1", "p1", "(Ljava/lang/Class;Ljava/lang/Class;)[Lexpo/modules/kotlin/types/AnyType;", "P2", "p2", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)[Lexpo/modules/kotlin/types/AnyType;", "P3", "p3", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)[Lexpo/modules/kotlin/types/AnyType;", "P4", "p4", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)[Lexpo/modules/kotlin/types/AnyType;", "P5", "p5", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)[Lexpo/modules/kotlin/types/AnyType;", "P6", "p6", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)[Lexpo/modules/kotlin/types/AnyType;", "P7", "p7", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)[Lexpo/modules/kotlin/types/AnyType;", "Lkotlin/Function0;", "Lkotlin/reflect/KType;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AnyTypeKt {
    public static final /* synthetic */ <T> AnyType toAnyType(Function0<? extends KType> function0) {
        Intrinsics.checkNotNullParameter(function0, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        return new AnyType(new LazyKType(orCreateKotlinClass, false, function0));
    }

    public static final /* synthetic */ <T> AnyType toAnyType() {
        Intrinsics.needClassReification();
        AnyTypeKt$toAnyType$1 anyTypeKt$toAnyType$1 = new Function0<KType>() { // from class: expo.modules.kotlin.types.AnyTypeKt$toAnyType$1
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        return new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toAnyType$1));
    }

    public static /* synthetic */ AnyType[] toArgsArray$default(Class p0, int i, Object obj) {
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P0");
            p0 = Object.class;
        }
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$1 anyTypeKt$toArgsArray$$inlined$toAnyType$1 = AnyTypeKt$toArgsArray$$inlined$toAnyType$1.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$1))};
    }

    public static /* synthetic */ AnyType[] toArgsArray$default(Class p0, Class p1, int i, Object obj) {
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P0");
            p0 = Object.class;
        }
        if ((i & 2) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P1");
            p1 = Object.class;
        }
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$2 anyTypeKt$toArgsArray$$inlined$toAnyType$2 = AnyTypeKt$toArgsArray$$inlined$toAnyType$2.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$3 anyTypeKt$toArgsArray$$inlined$toAnyType$3 = AnyTypeKt$toArgsArray$$inlined$toAnyType$3.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$2)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$3))};
    }

    public static /* synthetic */ AnyType[] toArgsArray$default(Class p0, Class p1, Class p2, int i, Object obj) {
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P0");
            p0 = Object.class;
        }
        if ((i & 2) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P1");
            p1 = Object.class;
        }
        if ((i & 4) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P2");
            p2 = Object.class;
        }
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$4 anyTypeKt$toArgsArray$$inlined$toAnyType$4 = AnyTypeKt$toArgsArray$$inlined$toAnyType$4.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$5 anyTypeKt$toArgsArray$$inlined$toAnyType$5 = AnyTypeKt$toArgsArray$$inlined$toAnyType$5.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$6 anyTypeKt$toArgsArray$$inlined$toAnyType$6 = AnyTypeKt$toArgsArray$$inlined$toAnyType$6.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$4)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$5)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$6))};
    }

    public static /* synthetic */ AnyType[] toArgsArray$default(Class p0, Class p1, Class p2, Class p3, int i, Object obj) {
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P0");
            p0 = Object.class;
        }
        if ((i & 2) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P1");
            p1 = Object.class;
        }
        if ((i & 4) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P2");
            p2 = Object.class;
        }
        if ((i & 8) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P3");
            p3 = Object.class;
        }
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$7 anyTypeKt$toArgsArray$$inlined$toAnyType$7 = AnyTypeKt$toArgsArray$$inlined$toAnyType$7.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$8 anyTypeKt$toArgsArray$$inlined$toAnyType$8 = AnyTypeKt$toArgsArray$$inlined$toAnyType$8.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$9 anyTypeKt$toArgsArray$$inlined$toAnyType$9 = AnyTypeKt$toArgsArray$$inlined$toAnyType$9.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$10 anyTypeKt$toArgsArray$$inlined$toAnyType$10 = AnyTypeKt$toArgsArray$$inlined$toAnyType$10.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$7)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$8)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$9)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$10))};
    }

    public static /* synthetic */ AnyType[] toArgsArray$default(Class p0, Class p1, Class p2, Class p3, Class p4, int i, Object obj) {
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P0");
            p0 = Object.class;
        }
        if ((i & 2) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P1");
            p1 = Object.class;
        }
        if ((i & 4) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P2");
            p2 = Object.class;
        }
        if ((i & 8) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P3");
            p3 = Object.class;
        }
        if ((i & 16) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P4");
            p4 = Object.class;
        }
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$11 anyTypeKt$toArgsArray$$inlined$toAnyType$11 = AnyTypeKt$toArgsArray$$inlined$toAnyType$11.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$12 anyTypeKt$toArgsArray$$inlined$toAnyType$12 = AnyTypeKt$toArgsArray$$inlined$toAnyType$12.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$13 anyTypeKt$toArgsArray$$inlined$toAnyType$13 = AnyTypeKt$toArgsArray$$inlined$toAnyType$13.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$14 anyTypeKt$toArgsArray$$inlined$toAnyType$14 = AnyTypeKt$toArgsArray$$inlined$toAnyType$14.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$15 anyTypeKt$toArgsArray$$inlined$toAnyType$15 = AnyTypeKt$toArgsArray$$inlined$toAnyType$15.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$11)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$12)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$13)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$14)), new AnyType(new LazyKType(orCreateKotlinClass5, false, anyTypeKt$toArgsArray$$inlined$toAnyType$15))};
    }

    public static /* synthetic */ AnyType[] toArgsArray$default(Class p0, Class p1, Class p2, Class p3, Class p4, Class p5, int i, Object obj) {
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P0");
            p0 = Object.class;
        }
        if ((i & 2) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P1");
            p1 = Object.class;
        }
        if ((i & 4) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P2");
            p2 = Object.class;
        }
        if ((i & 8) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P3");
            p3 = Object.class;
        }
        if ((i & 16) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P4");
            p4 = Object.class;
        }
        if ((i & 32) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P5");
            p5 = Object.class;
        }
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.checkNotNullParameter(p5, "p5");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$16 anyTypeKt$toArgsArray$$inlined$toAnyType$16 = AnyTypeKt$toArgsArray$$inlined$toAnyType$16.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$17 anyTypeKt$toArgsArray$$inlined$toAnyType$17 = AnyTypeKt$toArgsArray$$inlined$toAnyType$17.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$18 anyTypeKt$toArgsArray$$inlined$toAnyType$18 = AnyTypeKt$toArgsArray$$inlined$toAnyType$18.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$19 anyTypeKt$toArgsArray$$inlined$toAnyType$19 = AnyTypeKt$toArgsArray$$inlined$toAnyType$19.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$20 anyTypeKt$toArgsArray$$inlined$toAnyType$20 = AnyTypeKt$toArgsArray$$inlined$toAnyType$20.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$21 anyTypeKt$toArgsArray$$inlined$toAnyType$21 = AnyTypeKt$toArgsArray$$inlined$toAnyType$21.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$16)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$17)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$18)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$19)), new AnyType(new LazyKType(orCreateKotlinClass5, false, anyTypeKt$toArgsArray$$inlined$toAnyType$20)), new AnyType(new LazyKType(orCreateKotlinClass6, false, anyTypeKt$toArgsArray$$inlined$toAnyType$21))};
    }

    public static /* synthetic */ AnyType[] toArgsArray$default(Class p0, Class p1, Class p2, Class p3, Class p4, Class p5, Class p6, int i, Object obj) {
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P0");
            p0 = Object.class;
        }
        if ((i & 2) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P1");
            p1 = Object.class;
        }
        if ((i & 4) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P2");
            p2 = Object.class;
        }
        if ((i & 8) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P3");
            p3 = Object.class;
        }
        if ((i & 16) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P4");
            p4 = Object.class;
        }
        if ((i & 32) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P5");
            p5 = Object.class;
        }
        if ((i & 64) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P6");
            p6 = Object.class;
        }
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.checkNotNullParameter(p5, "p5");
        Intrinsics.checkNotNullParameter(p6, "p6");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$22 anyTypeKt$toArgsArray$$inlined$toAnyType$22 = AnyTypeKt$toArgsArray$$inlined$toAnyType$22.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$23 anyTypeKt$toArgsArray$$inlined$toAnyType$23 = AnyTypeKt$toArgsArray$$inlined$toAnyType$23.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$24 anyTypeKt$toArgsArray$$inlined$toAnyType$24 = AnyTypeKt$toArgsArray$$inlined$toAnyType$24.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$25 anyTypeKt$toArgsArray$$inlined$toAnyType$25 = AnyTypeKt$toArgsArray$$inlined$toAnyType$25.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$26 anyTypeKt$toArgsArray$$inlined$toAnyType$26 = AnyTypeKt$toArgsArray$$inlined$toAnyType$26.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$27 anyTypeKt$toArgsArray$$inlined$toAnyType$27 = AnyTypeKt$toArgsArray$$inlined$toAnyType$27.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$28 anyTypeKt$toArgsArray$$inlined$toAnyType$28 = AnyTypeKt$toArgsArray$$inlined$toAnyType$28.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$25)), new AnyType(new LazyKType(orCreateKotlinClass5, false, anyTypeKt$toArgsArray$$inlined$toAnyType$26)), new AnyType(new LazyKType(orCreateKotlinClass6, false, anyTypeKt$toArgsArray$$inlined$toAnyType$27)), new AnyType(new LazyKType(orCreateKotlinClass7, false, anyTypeKt$toArgsArray$$inlined$toAnyType$28))};
    }

    public static /* synthetic */ AnyType[] toArgsArray$default(Class cls, Class cls2, Class cls3, Class cls4, Class cls5, Class cls6, Class cls7, Class cls8, int i, Object obj) {
        Class p0;
        Class p1;
        Class p2;
        Class p3;
        Class p4;
        Class p5;
        Class p6;
        Class p7;
        if ((i & 1) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P0");
            p0 = Object.class;
        } else {
            p0 = cls;
        }
        if ((i & 2) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P1");
            p1 = Object.class;
        } else {
            p1 = cls2;
        }
        if ((i & 4) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P2");
            p2 = Object.class;
        } else {
            p2 = cls3;
        }
        if ((i & 8) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P3");
            p3 = Object.class;
        } else {
            p3 = cls4;
        }
        if ((i & 16) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P4");
            p4 = Object.class;
        } else {
            p4 = cls5;
        }
        if ((i & 32) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P5");
            p5 = Object.class;
        } else {
            p5 = cls6;
        }
        if ((i & 64) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P6");
            p6 = Object.class;
        } else {
            p6 = cls7;
        }
        if ((i & 128) != 0) {
            Intrinsics.reifiedOperationMarker(4, "P7");
            p7 = Object.class;
        } else {
            p7 = cls8;
        }
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.checkNotNullParameter(p5, "p5");
        Intrinsics.checkNotNullParameter(p6, "p6");
        Intrinsics.checkNotNullParameter(p7, "p7");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$29 anyTypeKt$toArgsArray$$inlined$toAnyType$29 = AnyTypeKt$toArgsArray$$inlined$toAnyType$29.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$30 anyTypeKt$toArgsArray$$inlined$toAnyType$30 = AnyTypeKt$toArgsArray$$inlined$toAnyType$30.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$31 anyTypeKt$toArgsArray$$inlined$toAnyType$31 = AnyTypeKt$toArgsArray$$inlined$toAnyType$31.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$32 anyTypeKt$toArgsArray$$inlined$toAnyType$32 = AnyTypeKt$toArgsArray$$inlined$toAnyType$32.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$33 anyTypeKt$toArgsArray$$inlined$toAnyType$33 = AnyTypeKt$toArgsArray$$inlined$toAnyType$33.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$34 anyTypeKt$toArgsArray$$inlined$toAnyType$34 = AnyTypeKt$toArgsArray$$inlined$toAnyType$34.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$35 anyTypeKt$toArgsArray$$inlined$toAnyType$35 = AnyTypeKt$toArgsArray$$inlined$toAnyType$35.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$36 anyTypeKt$toArgsArray$$inlined$toAnyType$36 = AnyTypeKt$toArgsArray$$inlined$toAnyType$36.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$29)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$30)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$31)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$32)), new AnyType(new LazyKType(orCreateKotlinClass5, false, anyTypeKt$toArgsArray$$inlined$toAnyType$33)), new AnyType(new LazyKType(orCreateKotlinClass6, false, anyTypeKt$toArgsArray$$inlined$toAnyType$34)), new AnyType(new LazyKType(orCreateKotlinClass7, false, anyTypeKt$toArgsArray$$inlined$toAnyType$35)), new AnyType(new LazyKType(orCreateKotlinClass8, false, anyTypeKt$toArgsArray$$inlined$toAnyType$36))};
    }

    public static final /* synthetic */ <P0> AnyType[] toArgsArray(Class<P0> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$1 anyTypeKt$toArgsArray$$inlined$toAnyType$1 = AnyTypeKt$toArgsArray$$inlined$toAnyType$1.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$1))};
    }

    public static final /* synthetic */ <P0, P1> AnyType[] toArgsArray(Class<P0> p0, Class<P1> p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$2 anyTypeKt$toArgsArray$$inlined$toAnyType$2 = AnyTypeKt$toArgsArray$$inlined$toAnyType$2.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$3 anyTypeKt$toArgsArray$$inlined$toAnyType$3 = AnyTypeKt$toArgsArray$$inlined$toAnyType$3.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$2)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$3))};
    }

    public static final /* synthetic */ <P0, P1, P2> AnyType[] toArgsArray(Class<P0> p0, Class<P1> p1, Class<P2> p2) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$4 anyTypeKt$toArgsArray$$inlined$toAnyType$4 = AnyTypeKt$toArgsArray$$inlined$toAnyType$4.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$5 anyTypeKt$toArgsArray$$inlined$toAnyType$5 = AnyTypeKt$toArgsArray$$inlined$toAnyType$5.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$6 anyTypeKt$toArgsArray$$inlined$toAnyType$6 = AnyTypeKt$toArgsArray$$inlined$toAnyType$6.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$4)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$5)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$6))};
    }

    public static final /* synthetic */ <P0, P1, P2, P3> AnyType[] toArgsArray(Class<P0> p0, Class<P1> p1, Class<P2> p2, Class<P3> p3) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$7 anyTypeKt$toArgsArray$$inlined$toAnyType$7 = AnyTypeKt$toArgsArray$$inlined$toAnyType$7.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$8 anyTypeKt$toArgsArray$$inlined$toAnyType$8 = AnyTypeKt$toArgsArray$$inlined$toAnyType$8.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$9 anyTypeKt$toArgsArray$$inlined$toAnyType$9 = AnyTypeKt$toArgsArray$$inlined$toAnyType$9.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$10 anyTypeKt$toArgsArray$$inlined$toAnyType$10 = AnyTypeKt$toArgsArray$$inlined$toAnyType$10.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$7)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$8)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$9)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$10))};
    }

    public static final /* synthetic */ <P0, P1, P2, P3, P4> AnyType[] toArgsArray(Class<P0> p0, Class<P1> p1, Class<P2> p2, Class<P3> p3, Class<P4> p4) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$11 anyTypeKt$toArgsArray$$inlined$toAnyType$11 = AnyTypeKt$toArgsArray$$inlined$toAnyType$11.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$12 anyTypeKt$toArgsArray$$inlined$toAnyType$12 = AnyTypeKt$toArgsArray$$inlined$toAnyType$12.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$13 anyTypeKt$toArgsArray$$inlined$toAnyType$13 = AnyTypeKt$toArgsArray$$inlined$toAnyType$13.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$14 anyTypeKt$toArgsArray$$inlined$toAnyType$14 = AnyTypeKt$toArgsArray$$inlined$toAnyType$14.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$15 anyTypeKt$toArgsArray$$inlined$toAnyType$15 = AnyTypeKt$toArgsArray$$inlined$toAnyType$15.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$11)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$12)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$13)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$14)), new AnyType(new LazyKType(orCreateKotlinClass5, false, anyTypeKt$toArgsArray$$inlined$toAnyType$15))};
    }

    public static final /* synthetic */ <P0, P1, P2, P3, P4, P5> AnyType[] toArgsArray(Class<P0> p0, Class<P1> p1, Class<P2> p2, Class<P3> p3, Class<P4> p4, Class<P5> p5) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.checkNotNullParameter(p5, "p5");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$16 anyTypeKt$toArgsArray$$inlined$toAnyType$16 = AnyTypeKt$toArgsArray$$inlined$toAnyType$16.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$17 anyTypeKt$toArgsArray$$inlined$toAnyType$17 = AnyTypeKt$toArgsArray$$inlined$toAnyType$17.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$18 anyTypeKt$toArgsArray$$inlined$toAnyType$18 = AnyTypeKt$toArgsArray$$inlined$toAnyType$18.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$19 anyTypeKt$toArgsArray$$inlined$toAnyType$19 = AnyTypeKt$toArgsArray$$inlined$toAnyType$19.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$20 anyTypeKt$toArgsArray$$inlined$toAnyType$20 = AnyTypeKt$toArgsArray$$inlined$toAnyType$20.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$21 anyTypeKt$toArgsArray$$inlined$toAnyType$21 = AnyTypeKt$toArgsArray$$inlined$toAnyType$21.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$16)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$17)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$18)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$19)), new AnyType(new LazyKType(orCreateKotlinClass5, false, anyTypeKt$toArgsArray$$inlined$toAnyType$20)), new AnyType(new LazyKType(orCreateKotlinClass6, false, anyTypeKt$toArgsArray$$inlined$toAnyType$21))};
    }

    public static final /* synthetic */ <P0, P1, P2, P3, P4, P5, P6> AnyType[] toArgsArray(Class<P0> p0, Class<P1> p1, Class<P2> p2, Class<P3> p3, Class<P4> p4, Class<P5> p5, Class<P6> p6) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.checkNotNullParameter(p5, "p5");
        Intrinsics.checkNotNullParameter(p6, "p6");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$22 anyTypeKt$toArgsArray$$inlined$toAnyType$22 = AnyTypeKt$toArgsArray$$inlined$toAnyType$22.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$23 anyTypeKt$toArgsArray$$inlined$toAnyType$23 = AnyTypeKt$toArgsArray$$inlined$toAnyType$23.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$24 anyTypeKt$toArgsArray$$inlined$toAnyType$24 = AnyTypeKt$toArgsArray$$inlined$toAnyType$24.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$25 anyTypeKt$toArgsArray$$inlined$toAnyType$25 = AnyTypeKt$toArgsArray$$inlined$toAnyType$25.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$26 anyTypeKt$toArgsArray$$inlined$toAnyType$26 = AnyTypeKt$toArgsArray$$inlined$toAnyType$26.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$27 anyTypeKt$toArgsArray$$inlined$toAnyType$27 = AnyTypeKt$toArgsArray$$inlined$toAnyType$27.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$28 anyTypeKt$toArgsArray$$inlined$toAnyType$28 = AnyTypeKt$toArgsArray$$inlined$toAnyType$28.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$25)), new AnyType(new LazyKType(orCreateKotlinClass5, false, anyTypeKt$toArgsArray$$inlined$toAnyType$26)), new AnyType(new LazyKType(orCreateKotlinClass6, false, anyTypeKt$toArgsArray$$inlined$toAnyType$27)), new AnyType(new LazyKType(orCreateKotlinClass7, false, anyTypeKt$toArgsArray$$inlined$toAnyType$28))};
    }

    public static final /* synthetic */ <P0, P1, P2, P3, P4, P5, P6, P7> AnyType[] toArgsArray(Class<P0> p0, Class<P1> p1, Class<P2> p2, Class<P3> p3, Class<P4> p4, Class<P5> p5, Class<P6> p6, Class<P7> p7) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.checkNotNullParameter(p5, "p5");
        Intrinsics.checkNotNullParameter(p6, "p6");
        Intrinsics.checkNotNullParameter(p7, "p7");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$29 anyTypeKt$toArgsArray$$inlined$toAnyType$29 = AnyTypeKt$toArgsArray$$inlined$toAnyType$29.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$30 anyTypeKt$toArgsArray$$inlined$toAnyType$30 = AnyTypeKt$toArgsArray$$inlined$toAnyType$30.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$31 anyTypeKt$toArgsArray$$inlined$toAnyType$31 = AnyTypeKt$toArgsArray$$inlined$toAnyType$31.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$32 anyTypeKt$toArgsArray$$inlined$toAnyType$32 = AnyTypeKt$toArgsArray$$inlined$toAnyType$32.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$33 anyTypeKt$toArgsArray$$inlined$toAnyType$33 = AnyTypeKt$toArgsArray$$inlined$toAnyType$33.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$34 anyTypeKt$toArgsArray$$inlined$toAnyType$34 = AnyTypeKt$toArgsArray$$inlined$toAnyType$34.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$35 anyTypeKt$toArgsArray$$inlined$toAnyType$35 = AnyTypeKt$toArgsArray$$inlined$toAnyType$35.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        Intrinsics.needClassReification();
        AnyTypeKt$toArgsArray$$inlined$toAnyType$36 anyTypeKt$toArgsArray$$inlined$toAnyType$36 = AnyTypeKt$toArgsArray$$inlined$toAnyType$36.INSTANCE;
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        return new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, anyTypeKt$toArgsArray$$inlined$toAnyType$29)), new AnyType(new LazyKType(orCreateKotlinClass2, false, anyTypeKt$toArgsArray$$inlined$toAnyType$30)), new AnyType(new LazyKType(orCreateKotlinClass3, false, anyTypeKt$toArgsArray$$inlined$toAnyType$31)), new AnyType(new LazyKType(orCreateKotlinClass4, false, anyTypeKt$toArgsArray$$inlined$toAnyType$32)), new AnyType(new LazyKType(orCreateKotlinClass5, false, anyTypeKt$toArgsArray$$inlined$toAnyType$33)), new AnyType(new LazyKType(orCreateKotlinClass6, false, anyTypeKt$toArgsArray$$inlined$toAnyType$34)), new AnyType(new LazyKType(orCreateKotlinClass7, false, anyTypeKt$toArgsArray$$inlined$toAnyType$35)), new AnyType(new LazyKType(orCreateKotlinClass8, false, anyTypeKt$toArgsArray$$inlined$toAnyType$36))};
    }
}
