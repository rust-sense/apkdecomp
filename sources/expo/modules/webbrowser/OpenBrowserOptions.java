package expo.modules.webbrowser;

import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebBrowserOptions.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b1\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0080\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\b¢\u0006\u0002\u0010\rJ\u0010\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00100\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u00101\u001a\u00020\bHÆ\u0003J\t\u00102\u001a\u00020\bHÆ\u0003J\t\u00103\u001a\u00020\bHÆ\u0003J\t\u00104\u001a\u00020\bHÆ\u0003J\t\u00105\u001a\u00020\bHÆ\u0003Jd\u00106\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u00107J\u0013\u00108\u001a\u00020\b2\b\u00109\u001a\u0004\u0018\u00010:HÖ\u0003J\t\u0010;\u001a\u00020\u0003HÖ\u0001J\t\u0010<\u001a\u00020\u0006HÖ\u0001R&\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\n\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R(\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010!\u0012\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\"\u0010\u000f\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018R$\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b%\u0010\u000f\u001a\u0004\b&\u0010\u0016\"\u0004\b'\u0010\u0018R$\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b(\u0010\u000f\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018R(\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010!\u0012\u0004\b+\u0010\u000f\u001a\u0004\b,\u0010\u001e\"\u0004\b-\u0010 ¨\u0006="}, d2 = {"Lexpo/modules/webbrowser/OpenBrowserOptions;", "Lexpo/modules/kotlin/records/Record;", "toolbarColor", "", "secondaryToolbarColor", "browserPackage", "", "showTitle", "", "enableDefaultShareMenuItem", "enableBarCollapsing", "showInRecents", "shouldCreateTask", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;ZZZZZ)V", "getBrowserPackage$annotations", "()V", "getBrowserPackage", "()Ljava/lang/String;", "setBrowserPackage", "(Ljava/lang/String;)V", "getEnableBarCollapsing$annotations", "getEnableBarCollapsing", "()Z", "setEnableBarCollapsing", "(Z)V", "getEnableDefaultShareMenuItem$annotations", "getEnableDefaultShareMenuItem", "setEnableDefaultShareMenuItem", "getSecondaryToolbarColor$annotations", "getSecondaryToolbarColor", "()Ljava/lang/Integer;", "setSecondaryToolbarColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getShouldCreateTask$annotations", "getShouldCreateTask", "setShouldCreateTask", "getShowInRecents$annotations", "getShowInRecents", "setShowInRecents", "getShowTitle$annotations", "getShowTitle", "setShowTitle", "getToolbarColor$annotations", "getToolbarColor", "setToolbarColor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;ZZZZZ)Lexpo/modules/webbrowser/OpenBrowserOptions;", "equals", "other", "", "hashCode", "toString", "expo-web-browser_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class OpenBrowserOptions implements Record {
    private String browserPackage;
    private boolean enableBarCollapsing;
    private boolean enableDefaultShareMenuItem;
    private Integer secondaryToolbarColor;
    private boolean shouldCreateTask;
    private boolean showInRecents;
    private boolean showTitle;
    private Integer toolbarColor;

    public OpenBrowserOptions() {
        this(null, null, null, false, false, false, false, false, 255, null);
    }

    @Field
    public static /* synthetic */ void getBrowserPackage$annotations() {
    }

    @Field
    public static /* synthetic */ void getEnableBarCollapsing$annotations() {
    }

    @Field
    public static /* synthetic */ void getEnableDefaultShareMenuItem$annotations() {
    }

    @Field
    public static /* synthetic */ void getSecondaryToolbarColor$annotations() {
    }

    @Field(key = "createTask")
    public static /* synthetic */ void getShouldCreateTask$annotations() {
    }

    @Field
    public static /* synthetic */ void getShowInRecents$annotations() {
    }

    @Field
    public static /* synthetic */ void getShowTitle$annotations() {
    }

    @Field
    public static /* synthetic */ void getToolbarColor$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getToolbarColor() {
        return this.toolbarColor;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getSecondaryToolbarColor() {
        return this.secondaryToolbarColor;
    }

    /* renamed from: component3, reason: from getter */
    public final String getBrowserPackage() {
        return this.browserPackage;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getShowTitle() {
        return this.showTitle;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getEnableDefaultShareMenuItem() {
        return this.enableDefaultShareMenuItem;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getEnableBarCollapsing() {
        return this.enableBarCollapsing;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getShowInRecents() {
        return this.showInRecents;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getShouldCreateTask() {
        return this.shouldCreateTask;
    }

    public final OpenBrowserOptions copy(Integer toolbarColor, Integer secondaryToolbarColor, String browserPackage, boolean showTitle, boolean enableDefaultShareMenuItem, boolean enableBarCollapsing, boolean showInRecents, boolean shouldCreateTask) {
        return new OpenBrowserOptions(toolbarColor, secondaryToolbarColor, browserPackage, showTitle, enableDefaultShareMenuItem, enableBarCollapsing, showInRecents, shouldCreateTask);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OpenBrowserOptions)) {
            return false;
        }
        OpenBrowserOptions openBrowserOptions = (OpenBrowserOptions) other;
        return Intrinsics.areEqual(this.toolbarColor, openBrowserOptions.toolbarColor) && Intrinsics.areEqual(this.secondaryToolbarColor, openBrowserOptions.secondaryToolbarColor) && Intrinsics.areEqual(this.browserPackage, openBrowserOptions.browserPackage) && this.showTitle == openBrowserOptions.showTitle && this.enableDefaultShareMenuItem == openBrowserOptions.enableDefaultShareMenuItem && this.enableBarCollapsing == openBrowserOptions.enableBarCollapsing && this.showInRecents == openBrowserOptions.showInRecents && this.shouldCreateTask == openBrowserOptions.shouldCreateTask;
    }

    public final String getBrowserPackage() {
        return this.browserPackage;
    }

    public final boolean getEnableBarCollapsing() {
        return this.enableBarCollapsing;
    }

    public final boolean getEnableDefaultShareMenuItem() {
        return this.enableDefaultShareMenuItem;
    }

    public final Integer getSecondaryToolbarColor() {
        return this.secondaryToolbarColor;
    }

    public final boolean getShouldCreateTask() {
        return this.shouldCreateTask;
    }

    public final boolean getShowInRecents() {
        return this.showInRecents;
    }

    public final boolean getShowTitle() {
        return this.showTitle;
    }

    public final Integer getToolbarColor() {
        return this.toolbarColor;
    }

    public int hashCode() {
        Integer num = this.toolbarColor;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.secondaryToolbarColor;
        int hashCode2 = (hashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str = this.browserPackage;
        return ((((((((((hashCode2 + (str != null ? str.hashCode() : 0)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.showTitle)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.enableDefaultShareMenuItem)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.enableBarCollapsing)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.showInRecents)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.shouldCreateTask);
    }

    public final void setBrowserPackage(String str) {
        this.browserPackage = str;
    }

    public final void setEnableBarCollapsing(boolean z) {
        this.enableBarCollapsing = z;
    }

    public final void setEnableDefaultShareMenuItem(boolean z) {
        this.enableDefaultShareMenuItem = z;
    }

    public final void setSecondaryToolbarColor(Integer num) {
        this.secondaryToolbarColor = num;
    }

    public final void setShouldCreateTask(boolean z) {
        this.shouldCreateTask = z;
    }

    public final void setShowInRecents(boolean z) {
        this.showInRecents = z;
    }

    public final void setShowTitle(boolean z) {
        this.showTitle = z;
    }

    public final void setToolbarColor(Integer num) {
        this.toolbarColor = num;
    }

    public String toString() {
        return "OpenBrowserOptions(toolbarColor=" + this.toolbarColor + ", secondaryToolbarColor=" + this.secondaryToolbarColor + ", browserPackage=" + this.browserPackage + ", showTitle=" + this.showTitle + ", enableDefaultShareMenuItem=" + this.enableDefaultShareMenuItem + ", enableBarCollapsing=" + this.enableBarCollapsing + ", showInRecents=" + this.showInRecents + ", shouldCreateTask=" + this.shouldCreateTask + ")";
    }

    public OpenBrowserOptions(Integer num, Integer num2, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.toolbarColor = num;
        this.secondaryToolbarColor = num2;
        this.browserPackage = str;
        this.showTitle = z;
        this.enableDefaultShareMenuItem = z2;
        this.enableBarCollapsing = z3;
        this.showInRecents = z4;
        this.shouldCreateTask = z5;
    }

    public /* synthetic */ OpenBrowserOptions(Integer num, Integer num2, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) == 0 ? str : null, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? false : z3, (i & 64) == 0 ? z4 : false, (i & 128) != 0 ? true : z5);
    }
}
