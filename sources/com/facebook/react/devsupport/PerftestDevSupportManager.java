package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.devsupport.DevInternalSettings;

/* loaded from: classes.dex */
public final class PerftestDevSupportManager extends DisabledDevSupportManager {
    private final DevServerHelper mDevServerHelper;
    private final DevInternalSettings mDevSettings;

    @Override // com.facebook.react.devsupport.DisabledDevSupportManager, com.facebook.react.devsupport.interfaces.DevSupportManager
    public DevInternalSettings getDevSettings() {
        return this.mDevSettings;
    }

    public PerftestDevSupportManager(Context context) {
        DevInternalSettings devInternalSettings = new DevInternalSettings(context, new DevInternalSettings.Listener() { // from class: com.facebook.react.devsupport.PerftestDevSupportManager.1
            @Override // com.facebook.react.devsupport.DevInternalSettings.Listener
            public void onInternalSettingsChanged() {
            }
        });
        this.mDevSettings = devInternalSettings;
        this.mDevServerHelper = new DevServerHelper(devInternalSettings, context.getPackageName(), devInternalSettings.getPackagerConnectionSettings());
    }

    @Override // com.facebook.react.devsupport.DisabledDevSupportManager, com.facebook.react.devsupport.interfaces.DevSupportManager
    public void startInspector() {
        this.mDevServerHelper.openInspectorConnection();
    }

    @Override // com.facebook.react.devsupport.DisabledDevSupportManager, com.facebook.react.devsupport.interfaces.DevSupportManager
    public void stopInspector() {
        this.mDevServerHelper.closeInspectorConnection();
    }
}
