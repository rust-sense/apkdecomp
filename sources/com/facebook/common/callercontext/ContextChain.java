package com.facebook.common.callercontext;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.internal.Objects;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ContextChain implements Parcelable {
    public static final Parcelable.Creator<ContextChain> CREATOR = new Parcelable.Creator<ContextChain>() { // from class: com.facebook.common.callercontext.ContextChain.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContextChain createFromParcel(Parcel parcel) {
            return new ContextChain(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContextChain[] newArray(int i) {
            return new ContextChain[i];
        }
    };
    private static final char PARENT_SEPARATOR = '/';
    public static final String TAG_INFRA = "i";
    public static final String TAG_PRODUCT = "p";
    public static final String TAG_PRODUCT_AND_INFRA = "pi";
    private static boolean sUseConcurrentHashMap = false;

    @Nullable
    private Map<String, Object> mExtraData;
    private final String mName;

    @Nullable
    private final ContextChain mParent;

    @Nullable
    private String mSerializedChainString;
    private String mSerializedNodeString;
    private final String mTag;

    public static void setUseConcurrentHashMap(boolean z) {
        sUseConcurrentHashMap = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public Map<String, Object> getExtraData() {
        return this.mExtraData;
    }

    public String getName() {
        return this.mName;
    }

    protected String getNodeString() {
        return this.mSerializedNodeString;
    }

    @Nullable
    public ContextChain getParent() {
        return this.mParent;
    }

    public String getTag() {
        return this.mTag;
    }

    public ContextChain(String str, String str2, @Nullable Map<String, String> map, @Nullable ContextChain contextChain) {
        this.mTag = str;
        this.mName = str2;
        this.mSerializedNodeString = str + ":" + str2;
        this.mParent = contextChain;
        Map<String, Object> extraData = contextChain != null ? contextChain.getExtraData() : null;
        if (extraData != null) {
            if (sUseConcurrentHashMap) {
                this.mExtraData = new ConcurrentHashMap(extraData);
            } else {
                this.mExtraData = new HashMap(extraData);
            }
        }
        if (map != null) {
            if (this.mExtraData == null) {
                if (sUseConcurrentHashMap) {
                    this.mExtraData = new ConcurrentHashMap();
                } else {
                    this.mExtraData = new HashMap();
                }
            }
            this.mExtraData.putAll(map);
        }
    }

    public ContextChain(String str, @Nullable ContextChain contextChain) {
        this("serialized_tag", "serialized_name", null, contextChain);
        this.mSerializedNodeString = str;
    }

    public ContextChain(String str, String str2, @Nullable ContextChain contextChain) {
        this(str, str2, null, contextChain);
    }

    protected ContextChain(Parcel parcel) {
        this.mTag = parcel.readString();
        this.mName = parcel.readString();
        this.mSerializedNodeString = parcel.readString();
        this.mParent = (ContextChain) parcel.readParcelable(ContextChain.class.getClassLoader());
    }

    public ContextChain getRootContextChain() {
        ContextChain contextChain = this.mParent;
        return contextChain == null ? this : contextChain.getRootContextChain();
    }

    @Nullable
    public String getStringExtra(String str) {
        Object obj;
        Map<String, Object> map = this.mExtraData;
        if (map == null) {
            return null;
        }
        if ((sUseConcurrentHashMap && str == null) || (obj = map.get(str)) == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    public void putObjectExtra(String str, Object obj) {
        boolean z = sUseConcurrentHashMap;
        if (z && (str == null || obj == null)) {
            return;
        }
        if (this.mExtraData == null) {
            if (z) {
                this.mExtraData = new ConcurrentHashMap();
            } else {
                this.mExtraData = new HashMap();
            }
        }
        this.mExtraData.put(str, obj);
    }

    public String toString() {
        if (this.mSerializedChainString == null) {
            this.mSerializedChainString = getNodeString();
            if (this.mParent != null) {
                this.mSerializedChainString = this.mParent.toString() + '/' + this.mSerializedChainString;
            }
        }
        return this.mSerializedChainString;
    }

    public String[] toStringArray() {
        return toString().split(String.valueOf('/'));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContextChain contextChain = (ContextChain) obj;
        return Objects.equal(getNodeString(), contextChain.getNodeString()) && Objects.equal(this.mParent, contextChain.mParent);
    }

    public int hashCode() {
        return (super.hashCode() * 31) + getNodeString().hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTag);
        parcel.writeString(this.mName);
        parcel.writeString(getNodeString());
        parcel.writeParcelable(this.mParent, i);
    }
}
