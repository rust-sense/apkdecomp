package com.facebook.react.bridge.queue;

/* loaded from: classes.dex */
public class MessageQueueThreadSpec {
    public static final long DEFAULT_STACK_SIZE_BYTES = 0;
    private static final MessageQueueThreadSpec MAIN_UI_SPEC = new MessageQueueThreadSpec(ThreadType.MAIN_UI, "main_ui");
    private final String mName;
    private final long mStackSize;
    private final ThreadType mThreadType;

    protected enum ThreadType {
        MAIN_UI,
        NEW_BACKGROUND
    }

    public static MessageQueueThreadSpec mainThreadSpec() {
        return MAIN_UI_SPEC;
    }

    public String getName() {
        return this.mName;
    }

    public long getStackSize() {
        return this.mStackSize;
    }

    public ThreadType getThreadType() {
        return this.mThreadType;
    }

    public static MessageQueueThreadSpec newUIBackgroundTreadSpec(String str) {
        return new MessageQueueThreadSpec(ThreadType.NEW_BACKGROUND, str);
    }

    public static MessageQueueThreadSpec newBackgroundThreadSpec(String str) {
        return new MessageQueueThreadSpec(ThreadType.NEW_BACKGROUND, str);
    }

    public static MessageQueueThreadSpec newBackgroundThreadSpec(String str, long j) {
        return new MessageQueueThreadSpec(ThreadType.NEW_BACKGROUND, str, j);
    }

    private MessageQueueThreadSpec(ThreadType threadType, String str) {
        this(threadType, str, 0L);
    }

    private MessageQueueThreadSpec(ThreadType threadType, String str, long j) {
        this.mThreadType = threadType;
        this.mName = str;
        this.mStackSize = j;
    }
}
