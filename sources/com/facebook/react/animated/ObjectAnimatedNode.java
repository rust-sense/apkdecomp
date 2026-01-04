package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

/* loaded from: classes.dex */
class ObjectAnimatedNode extends AnimatedNode {
    private static final String NODE_TAG_KEY = "nodeTag";
    private static final String VALUE_KEY = "value";
    private final JavaOnlyMap mConfig;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    ObjectAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mConfig = JavaOnlyMap.deepClone(readableMap);
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(String str, JavaOnlyMap javaOnlyMap) {
        ReadableType type = this.mConfig.getType("value");
        if (type == ReadableType.Map) {
            javaOnlyMap.putMap(str, collectViewUpdatesHelper(this.mConfig.getMap("value")));
        } else {
            if (type == ReadableType.Array) {
                javaOnlyMap.putArray(str, collectViewUpdatesHelper(this.mConfig.getArray("value")));
                return;
            }
            throw new IllegalArgumentException("Invalid value type for ObjectAnimatedNode");
        }
    }

    private JavaOnlyArray collectViewUpdatesHelper(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    javaOnlyArray.pushNull();
                    break;
                case 2:
                    javaOnlyArray.pushBoolean(readableArray.getBoolean(i));
                    break;
                case 3:
                    javaOnlyArray.pushDouble(readableArray.getDouble(i));
                    break;
                case 4:
                    javaOnlyArray.pushString(readableArray.getString(i));
                    break;
                case 5:
                    ReadableMap map = readableArray.getMap(i);
                    if (map.hasKey(NODE_TAG_KEY) && map.getType(NODE_TAG_KEY) == ReadableType.Number) {
                        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(map.getInt(NODE_TAG_KEY));
                        if (nodeById == null) {
                            throw new IllegalArgumentException("Mapped value node does not exist");
                        }
                        if (nodeById instanceof ValueAnimatedNode) {
                            ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                            Object animatedObject = valueAnimatedNode.getAnimatedObject();
                            if (animatedObject instanceof Integer) {
                                javaOnlyArray.pushInt(((Integer) animatedObject).intValue());
                                break;
                            } else if (animatedObject instanceof String) {
                                javaOnlyArray.pushString((String) animatedObject);
                                break;
                            } else {
                                javaOnlyArray.pushDouble(valueAnimatedNode.getValue());
                                break;
                            }
                        } else if (nodeById instanceof ColorAnimatedNode) {
                            javaOnlyArray.pushInt(((ColorAnimatedNode) nodeById).getColor());
                            break;
                        } else {
                            break;
                        }
                    } else {
                        javaOnlyArray.pushMap(collectViewUpdatesHelper(readableArray.getMap(i)));
                        break;
                    }
                case 6:
                    javaOnlyArray.pushArray(collectViewUpdatesHelper(readableArray.getArray(i)));
                    break;
            }
        }
        return javaOnlyArray;
    }

    /* renamed from: com.facebook.react.animated.ObjectAnimatedNode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private JavaOnlyMap collectViewUpdatesHelper(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(nextKey).ordinal()]) {
                case 1:
                    javaOnlyMap.putNull(nextKey);
                    break;
                case 2:
                    javaOnlyMap.putBoolean(nextKey, readableMap.getBoolean(nextKey));
                    break;
                case 3:
                    javaOnlyMap.putDouble(nextKey, readableMap.getDouble(nextKey));
                    break;
                case 4:
                    javaOnlyMap.putString(nextKey, readableMap.getString(nextKey));
                    break;
                case 5:
                    ReadableMap map = readableMap.getMap(nextKey);
                    if (map != null && map.hasKey(NODE_TAG_KEY) && map.getType(NODE_TAG_KEY) == ReadableType.Number) {
                        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(map.getInt(NODE_TAG_KEY));
                        if (nodeById == null) {
                            throw new IllegalArgumentException("Mapped value node does not exist");
                        }
                        if (nodeById instanceof ValueAnimatedNode) {
                            ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                            Object animatedObject = valueAnimatedNode.getAnimatedObject();
                            if (animatedObject instanceof Integer) {
                                javaOnlyMap.putInt(nextKey, ((Integer) animatedObject).intValue());
                                break;
                            } else if (animatedObject instanceof String) {
                                javaOnlyMap.putString(nextKey, (String) animatedObject);
                                break;
                            } else {
                                javaOnlyMap.putDouble(nextKey, valueAnimatedNode.getValue());
                                break;
                            }
                        } else if (!(nodeById instanceof ColorAnimatedNode)) {
                            break;
                        } else {
                            javaOnlyMap.putInt(nextKey, ((ColorAnimatedNode) nodeById).getColor());
                            break;
                        }
                    } else {
                        javaOnlyMap.putMap(nextKey, collectViewUpdatesHelper(map));
                        break;
                    }
                case 6:
                    javaOnlyMap.putArray(nextKey, collectViewUpdatesHelper(readableMap.getArray(nextKey)));
                    break;
            }
        }
        return javaOnlyMap;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder("ObjectAnimatedNode[");
        sb.append(this.mTag);
        sb.append("]: mConfig: ");
        JavaOnlyMap javaOnlyMap = this.mConfig;
        sb.append(javaOnlyMap != null ? javaOnlyMap.toString() : "null");
        return sb.toString();
    }
}
