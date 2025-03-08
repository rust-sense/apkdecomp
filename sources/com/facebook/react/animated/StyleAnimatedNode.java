package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
class StyleAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final Map<String, Integer> mPropMapping;

    StyleAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableMap map = readableMap.getMap("style");
        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        this.mPropMapping = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.mPropMapping.put(nextKey, Integer.valueOf(map.getInt(nextKey)));
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        for (Map.Entry<String, Integer> entry : this.mPropMapping.entrySet()) {
            AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(entry.getValue().intValue());
            if (nodeById == null) {
                throw new IllegalArgumentException("Mapped style node does not exist");
            }
            if (nodeById instanceof TransformAnimatedNode) {
                ((TransformAnimatedNode) nodeById).collectViewUpdates(javaOnlyMap);
            } else if (nodeById instanceof ValueAnimatedNode) {
                ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                Object animatedObject = valueAnimatedNode.getAnimatedObject();
                if (animatedObject instanceof Integer) {
                    javaOnlyMap.putInt(entry.getKey(), ((Integer) animatedObject).intValue());
                } else if (animatedObject instanceof String) {
                    javaOnlyMap.putString(entry.getKey(), (String) animatedObject);
                } else {
                    javaOnlyMap.putDouble(entry.getKey(), valueAnimatedNode.getValue());
                }
            } else if (nodeById instanceof ColorAnimatedNode) {
                javaOnlyMap.putInt(entry.getKey(), ((ColorAnimatedNode) nodeById).getColor());
            } else if (nodeById instanceof ObjectAnimatedNode) {
                ((ObjectAnimatedNode) nodeById).collectViewUpdates(entry.getKey(), javaOnlyMap);
            } else {
                throw new IllegalArgumentException("Unsupported type of node used in property node " + nodeById.getClass());
            }
        }
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder("StyleAnimatedNode[");
        sb.append(this.mTag);
        sb.append("] mPropMapping: ");
        Map<String, Integer> map = this.mPropMapping;
        sb.append(map != null ? map.toString() : "null");
        return sb.toString();
    }
}
