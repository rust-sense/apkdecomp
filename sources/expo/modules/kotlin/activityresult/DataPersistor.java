package expo.modules.kotlin.activityresult;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.core.os.BundleKt;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: DataPersistor.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0013J&\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0015j\b\u0012\u0004\u0012\u00020\u0010`\u0016J\"\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00190\u0018J\"\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00130\u0018J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u001e\u001a\u00020\u0006H\u0002J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00152\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00182\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lexpo/modules/kotlin/activityresult/DataPersistor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "accumulator", "Landroid/os/Bundle;", "retrievedData", "getRetrievedData", "()Landroid/os/Bundle;", "retrievedData$delegate", "Lkotlin/Lazy;", "sharedPreferences", "Landroid/content/SharedPreferences;", "addBundle", "key", "", "value", "addSerializable", "Ljava/io/Serializable;", "addStringArrayList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "addStringToIntMap", "", "", "addStringToSerializableMap", "persist", "", "retrieveBundle", "retrieveData", "retrieveSerializable", "retrieveStringArrayList", "retrieveStringToIntMap", "retrieveStringToSerializableMap", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DataPersistor {
    private final Bundle accumulator;

    /* renamed from: retrievedData$delegate, reason: from kotlin metadata */
    private final Lazy retrievedData;
    private final SharedPreferences sharedPreferences;

    public DataPersistor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences("expo.modules.kotlin.PersistentDataManager", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.sharedPreferences = sharedPreferences;
        this.accumulator = new Bundle();
        this.retrievedData = LazyKt.lazy(new Function0<Bundle>() { // from class: expo.modules.kotlin.activityresult.DataPersistor$retrievedData$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bundle invoke() {
                Bundle retrieveData;
                retrieveData = DataPersistor.this.retrieveData();
                return retrieveData;
            }
        });
    }

    private final Bundle getRetrievedData() {
        return (Bundle) this.retrievedData.getValue();
    }

    public final DataPersistor addStringArrayList(String key, ArrayList<String> value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.accumulator.putStringArrayList(key, value);
        return this;
    }

    public final ArrayList<String> retrieveStringArrayList(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getRetrievedData().getStringArrayList(key);
    }

    public final DataPersistor addStringToIntMap(String key, Map<String, Integer> value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Bundle bundle = this.accumulator;
        Pair[] pairArr = (Pair[]) MapsKt.toList(value).toArray(new Pair[0]);
        bundle.putBundle(key, BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length)));
        return this;
    }

    public final Map<String, Integer> retrieveStringToIntMap(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle bundle = getRetrievedData().getBundle(key);
        if (bundle == null) {
            return null;
        }
        Set<String> keySet = bundle.keySet();
        Intrinsics.checkNotNull(keySet);
        Set<String> set = keySet;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set, 10)), 16));
        for (Object obj : set) {
            linkedHashMap.put(obj, Integer.valueOf(bundle.getInt((String) obj)));
        }
        return linkedHashMap;
    }

    public final DataPersistor addStringToSerializableMap(String key, Map<String, ? extends Serializable> value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Bundle bundle = this.accumulator;
        Pair[] pairArr = (Pair[]) MapsKt.toList(value).toArray(new Pair[0]);
        bundle.putBundle(key, BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length)));
        return this;
    }

    public final Map<String, Serializable> retrieveStringToSerializableMap(String key) {
        Serializable serializable;
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle bundle = getRetrievedData().getBundle(key);
        if (bundle == null) {
            return null;
        }
        Set<String> keySet = bundle.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "keySet(...)");
        Set<String> set = keySet;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set, 10)), 16));
        for (Object obj : set) {
            LinkedHashMap linkedHashMap2 = linkedHashMap;
            String str = (String) obj;
            if (Build.VERSION.SDK_INT >= 33) {
                serializable = bundle.getSerializable(str, Serializable.class);
            } else {
                serializable = bundle.getSerializable(str);
            }
            if (serializable == null) {
                throw new IllegalStateException("For a key '" + str + "' there should be a serializable class available");
            }
            linkedHashMap2.put(obj, serializable);
        }
        return linkedHashMap;
    }

    public final DataPersistor addBundle(String key, Bundle value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.accumulator.putBundle(key, value);
        return this;
    }

    public final Bundle retrieveBundle(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getRetrievedData().getBundle(key);
    }

    public final DataPersistor addSerializable(String key, Serializable value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.accumulator.putSerializable(key, value);
        return this;
    }

    public final Serializable retrieveSerializable(String key) {
        Serializable serializable;
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle retrievedData = getRetrievedData();
        if (Build.VERSION.SDK_INT >= 33) {
            serializable = retrievedData.getSerializable(key, Serializable.class);
            return serializable;
        }
        return retrievedData.getSerializable(key);
    }

    public final void persist() {
        String base64;
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        base64 = DataPersistorKt.toBase64(this.accumulator);
        edit.putString("bundle", base64);
        edit.putLong(DataPersistorKt.EXPIRE_KEY, new Date().getTime() + DataPersistorKt.EXPIRATION_TIME);
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0027, code lost:
    
        r1 = expo.modules.kotlin.activityresult.DataPersistorKt.toBundle(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.os.Bundle retrieveData() {
        /*
            r5 = this;
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            android.content.SharedPreferences r1 = r5.sharedPreferences
            java.lang.String r2 = "expire"
            r3 = 0
            long r1 = r1.getLong(r2, r3)
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            long r3 = r3.getTime()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L2f
            android.content.SharedPreferences r1 = r5.sharedPreferences
            java.lang.String r2 = "bundle"
            r3 = 0
            java.lang.String r1 = r1.getString(r2, r3)
            if (r1 == 0) goto L2f
            android.os.Bundle r1 = expo.modules.kotlin.activityresult.DataPersistorKt.access$toBundle(r1)
            if (r1 != 0) goto L2e
            goto L2f
        L2e:
            r0 = r1
        L2f:
            android.content.SharedPreferences r1 = r5.sharedPreferences
            android.content.SharedPreferences$Editor r1 = r1.edit()
            android.content.SharedPreferences$Editor r1 = r1.clear()
            r1.apply()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.activityresult.DataPersistor.retrieveData():android.os.Bundle");
    }
}
