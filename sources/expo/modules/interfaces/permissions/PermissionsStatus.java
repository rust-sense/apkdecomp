package expo.modules.interfaces.permissions;

/* loaded from: classes2.dex */
public enum PermissionsStatus {
    GRANTED(PermissionsResponse.GRANTED_KEY),
    UNDETERMINED("undetermined"),
    DENIED("denied");

    private final String status;

    public String getStatus() {
        return this.status;
    }

    PermissionsStatus(String str) {
        this.status = str;
    }
}
