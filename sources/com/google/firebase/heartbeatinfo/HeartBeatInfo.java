package com.google.firebase.heartbeatinfo;

import com.google.android.gms.tasks.Task;
import java.util.List;

/* loaded from: classes2.dex */
public interface HeartBeatInfo {
    Task<List<HeartBeatResult>> getAndClearStoredHeartBeatInfo();

    HeartBeat getHeartBeatCode(String str);

    Task<Void> storeHeartBeatInfo(String str);

    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);

        private final int code;

        public int getCode() {
            return this.code;
        }

        HeartBeat(int i) {
            this.code = i;
        }
    }
}
