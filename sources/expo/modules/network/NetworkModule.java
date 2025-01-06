package expo.modules.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
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
import expo.modules.network.NetworkModule;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: NetworkModule.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lexpo/modules/network/NetworkModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "wifiInfo", "Landroid/net/wifi/WifiInfo;", "getWifiInfo", "()Landroid/net/wifi/WifiInfo;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getConnectionType", "Lexpo/modules/network/NetworkModule$NetworkStateType;", "netCapabilities", "Landroid/net/NetworkCapabilities;", "netInfo", "Landroid/net/NetworkInfo;", "rawIpToString", "", "ipAddress", "", "NetworkStateType", "expo-network_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkModule extends Module {
    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        NetworkModule networkModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (networkModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(networkModule);
            moduleDefinitionBuilder.Name("ExpoNetwork");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("getNetworkStateAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.network.NetworkModule$definition$lambda$5$$inlined$AsyncFunction$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Context context;
                        String value;
                        NetworkModule.NetworkStateType connectionType;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        Bundle bundle = new Bundle();
                        context = NetworkModule.this.getContext();
                        Object systemService = context.getSystemService("connectivity");
                        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
                        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                        try {
                            if (Build.VERSION.SDK_INT < 29) {
                                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                                connectionType = NetworkModule.this.getConnectionType(activeNetworkInfo);
                                Intrinsics.checkNotNull(activeNetworkInfo);
                                bundle.putBoolean("isInternetReachable", activeNetworkInfo.isConnected());
                                bundle.putString("type", connectionType.getValue());
                                bundle.putBoolean("isConnected", connectionType.isDefined());
                                promise.resolve(bundle);
                                return;
                            }
                            Network activeNetwork = connectivityManager.getActiveNetwork();
                            boolean z = true;
                            boolean z2 = activeNetwork != null;
                            NetworkModule.NetworkStateType connectionType2 = z2 ? NetworkModule.this.getConnectionType(connectivityManager.getNetworkCapabilities(activeNetwork)) : null;
                            if (connectionType2 == null || (value = connectionType2.getValue()) == null) {
                                value = NetworkModule.NetworkStateType.NONE.getValue();
                            }
                            bundle.putString("type", value);
                            bundle.putBoolean("isInternetReachable", z2);
                            if (connectionType2 == null || !connectionType2.isDefined()) {
                                z = false;
                            }
                            bundle.putBoolean("isConnected", z);
                            promise.resolve(bundle);
                        } catch (Exception e) {
                            throw new NetworkAccessException(e);
                        }
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.network.NetworkModule$definition$lambda$5$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.network.NetworkModule$definition$lambda$5$$inlined$AsyncFunction$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Context context;
                        String value;
                        NetworkModule.NetworkStateType connectionType;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        boolean z = false;
                        Promise promise = (Promise) objArr[0];
                        Bundle bundle = new Bundle();
                        context = NetworkModule.this.getContext();
                        Object systemService = context.getSystemService("connectivity");
                        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
                        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                        try {
                            if (Build.VERSION.SDK_INT < 29) {
                                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                                connectionType = NetworkModule.this.getConnectionType(activeNetworkInfo);
                                Intrinsics.checkNotNull(activeNetworkInfo);
                                bundle.putBoolean("isInternetReachable", activeNetworkInfo.isConnected());
                                bundle.putString("type", connectionType.getValue());
                                bundle.putBoolean("isConnected", connectionType.isDefined());
                                promise.resolve(bundle);
                            } else {
                                Network activeNetwork = connectivityManager.getActiveNetwork();
                                boolean z2 = activeNetwork != null;
                                NetworkModule.NetworkStateType connectionType2 = z2 ? NetworkModule.this.getConnectionType(connectivityManager.getNetworkCapabilities(activeNetwork)) : null;
                                if (connectionType2 == null || (value = connectionType2.getValue()) == null) {
                                    value = NetworkModule.NetworkStateType.NONE.getValue();
                                }
                                bundle.putString("type", value);
                                bundle.putBoolean("isInternetReachable", z2);
                                if (connectionType2 != null && connectionType2.isDefined()) {
                                    z = true;
                                }
                                bundle.putBoolean("isConnected", z);
                                promise.resolve(bundle);
                            }
                            return Unit.INSTANCE;
                        } catch (Exception e) {
                            throw new NetworkAccessException(e);
                        }
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent = new StringAsyncFunctionComponent("getNetworkStateAsync", anyTypeArr, function1);
                                } else {
                                    asyncFunctionComponent = new AsyncFunctionComponent("getNetworkStateAsync", anyTypeArr, function1);
                                }
                            } else {
                                asyncFunctionComponent = new FloatAsyncFunctionComponent("getNetworkStateAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new DoubleAsyncFunctionComponent("getNetworkStateAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new BoolAsyncFunctionComponent("getNetworkStateAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new IntAsyncFunctionComponent("getNetworkStateAsync", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent;
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getNetworkStateAsync", asyncFunctionWithPromiseComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr2 = new AnyType[0];
            Function1<Object[], String> function12 = new Function1<Object[], String>() { // from class: expo.modules.network.NetworkModule$definition$lambda$5$$inlined$AsyncFunction$4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    WifiInfo wifiInfo;
                    String rawIpToString;
                    Intrinsics.checkNotNullParameter(it, "it");
                    NetworkModule networkModule2 = NetworkModule.this;
                    wifiInfo = networkModule2.getWifiInfo();
                    rawIpToString = networkModule2.rawIpToString(wifiInfo.getIpAddress());
                    return rawIpToString;
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent2 = new StringAsyncFunctionComponent("getIpAddressAsync", anyTypeArr2, function12);
                            } else {
                                asyncFunctionComponent2 = new AsyncFunctionComponent("getIpAddressAsync", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new FloatAsyncFunctionComponent("getIpAddressAsync", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("getIpAddressAsync", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new BoolAsyncFunctionComponent("getIpAddressAsync", anyTypeArr2, function12);
                }
            } else {
                asyncFunctionComponent2 = new IntAsyncFunctionComponent("getIpAddressAsync", anyTypeArr2, function12);
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("getIpAddressAsync", asyncFunctionComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr3 = new AnyType[0];
            Function1<Object[], Boolean> function13 = new Function1<Object[], Boolean>() { // from class: expo.modules.network.NetworkModule$definition$lambda$5$$inlined$AsyncFunction$5
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object[] it) {
                    Context context;
                    Intrinsics.checkNotNullParameter(it, "it");
                    context = NetworkModule.this.getContext();
                    return Boolean.valueOf(Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0);
                }
            };
            if (!Intrinsics.areEqual(Boolean.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Boolean.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Boolean.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Boolean.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Boolean.class, String.class)) {
                                asyncFunctionComponent3 = new StringAsyncFunctionComponent("isAirplaneModeEnabledAsync", anyTypeArr3, function13);
                            } else {
                                asyncFunctionComponent3 = new AsyncFunctionComponent("isAirplaneModeEnabledAsync", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent3 = new FloatAsyncFunctionComponent("isAirplaneModeEnabledAsync", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent3 = new DoubleAsyncFunctionComponent("isAirplaneModeEnabledAsync", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent3 = new BoolAsyncFunctionComponent("isAirplaneModeEnabledAsync", anyTypeArr3, function13);
                }
            } else {
                asyncFunctionComponent3 = new IntAsyncFunctionComponent("isAirplaneModeEnabledAsync", anyTypeArr3, function13);
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("isAirplaneModeEnabledAsync", asyncFunctionComponent3);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: NetworkModule.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lexpo/modules/network/NetworkModule$NetworkStateType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "isDefined", "", "()Z", "getValue", "()Ljava/lang/String;", "NONE", "UNKNOWN", "CELLULAR", "WIFI", "BLUETOOTH", "ETHERNET", "WIMAX", "VPN", "OTHER", "expo-network_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NetworkStateType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ NetworkStateType[] $VALUES;
        private final String value;
        public static final NetworkStateType NONE = new NetworkStateType("NONE", 0, "NONE");
        public static final NetworkStateType UNKNOWN = new NetworkStateType("UNKNOWN", 1, "UNKNOWN");
        public static final NetworkStateType CELLULAR = new NetworkStateType("CELLULAR", 2, "CELLULAR");
        public static final NetworkStateType WIFI = new NetworkStateType("WIFI", 3, "WIFI");
        public static final NetworkStateType BLUETOOTH = new NetworkStateType("BLUETOOTH", 4, "BLUETOOTH");
        public static final NetworkStateType ETHERNET = new NetworkStateType("ETHERNET", 5, "ETHERNET");
        public static final NetworkStateType WIMAX = new NetworkStateType("WIMAX", 6, "WIMAX");
        public static final NetworkStateType VPN = new NetworkStateType("VPN", 7, "VPN");
        public static final NetworkStateType OTHER = new NetworkStateType("OTHER", 8, "OTHER");

        private static final /* synthetic */ NetworkStateType[] $values() {
            return new NetworkStateType[]{NONE, UNKNOWN, CELLULAR, WIFI, BLUETOOTH, ETHERNET, WIMAX, VPN, OTHER};
        }

        public static EnumEntries<NetworkStateType> getEntries() {
            return $ENTRIES;
        }

        public static NetworkStateType valueOf(String str) {
            return (NetworkStateType) Enum.valueOf(NetworkStateType.class, str);
        }

        public static NetworkStateType[] values() {
            return (NetworkStateType[]) $VALUES.clone();
        }

        public final String getValue() {
            return this.value;
        }

        private NetworkStateType(String str, int i, String str2) {
            this.value = str2;
        }

        static {
            NetworkStateType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public final boolean isDefined() {
            return (Intrinsics.areEqual(this.value, "NONE") || Intrinsics.areEqual(this.value, "UNKNOWN")) ? false : true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WifiInfo getWifiInfo() {
        String str;
        try {
            Object systemService = getContext().getSystemService("wifi");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
            WifiInfo connectionInfo = ((WifiManager) systemService).getConnectionInfo();
            Intrinsics.checkNotNull(connectionInfo);
            return connectionInfo;
        } catch (Exception e) {
            str = NetworkModuleKt.TAG;
            String message = e.getMessage();
            if (message == null) {
                message = "Wi-Fi information could not be acquired";
            }
            Log.e(str, message);
            throw new NetworkWifiException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NetworkStateType getConnectionType(NetworkInfo netInfo) {
        Integer valueOf = netInfo != null ? Integer.valueOf(netInfo.getType()) : null;
        return ((valueOf != null && valueOf.intValue() == 0) || (valueOf != null && valueOf.intValue() == 4)) ? NetworkStateType.CELLULAR : (valueOf != null && valueOf.intValue() == 1) ? NetworkStateType.WIFI : (valueOf != null && valueOf.intValue() == 7) ? NetworkStateType.BLUETOOTH : (valueOf != null && valueOf.intValue() == 9) ? NetworkStateType.ETHERNET : (valueOf != null && valueOf.intValue() == 6) ? NetworkStateType.WIMAX : (valueOf != null && valueOf.intValue() == 17) ? NetworkStateType.VPN : NetworkStateType.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NetworkStateType getConnectionType(NetworkCapabilities netCapabilities) {
        if (netCapabilities == null) {
            return NetworkStateType.UNKNOWN;
        }
        if (netCapabilities.hasTransport(0)) {
            return NetworkStateType.CELLULAR;
        }
        if (netCapabilities.hasTransport(1) || netCapabilities.hasTransport(5)) {
            return NetworkStateType.WIFI;
        }
        return netCapabilities.hasTransport(2) ? NetworkStateType.BLUETOOTH : netCapabilities.hasTransport(3) ? NetworkStateType.ETHERNET : netCapabilities.hasTransport(4) ? NetworkStateType.VPN : NetworkStateType.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String rawIpToString(int ipAddress) {
        if (Intrinsics.areEqual(ByteOrder.nativeOrder(), ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }
        byte[] byteArray = BigInteger.valueOf(ipAddress).toByteArray();
        if (byteArray.length < 4) {
            Intrinsics.checkNotNull(byteArray);
            byteArray = NetworkUtilsKt.frontPadWithZeros(byteArray);
        }
        try {
            String hostAddress = InetAddress.getByAddress(byteArray).getHostAddress();
            Intrinsics.checkNotNull(hostAddress, "null cannot be cast to non-null type kotlin.String");
            return hostAddress;
        } catch (UnknownHostException unused) {
            return "0.0.0.0";
        }
    }
}
