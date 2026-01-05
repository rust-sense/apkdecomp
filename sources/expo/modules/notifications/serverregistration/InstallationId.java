package expo.modules.notifications.serverregistration;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes2.dex */
public class InstallationId {
    public static final String LEGACY_PREFERENCES_FILE_NAME = "host.exp.exponent.SharedPreferences";
    public static final String LEGACY_PREFERENCES_UUID_KEY = "uuid";
    public static final String LEGACY_UUID_FILE_NAME = "expo_installation_uuid.txt";
    private static final String TAG = "InstallationId";
    public static final String UUID_FILE_NAME = "expo_notifications_installation_uuid.txt";
    private Context mContext;
    private SharedPreferences mLegacySharedPreferences;
    private String mUuid;

    public InstallationId(Context context) {
        this.mContext = context;
        this.mLegacySharedPreferences = context.getSharedPreferences(LEGACY_PREFERENCES_FILE_NAME, 0);
    }

    public String getUUID() {
        String str = this.mUuid;
        if (str != null) {
            return str;
        }
        String readUUIDFromFile = readUUIDFromFile(new File(this.mContext.getNoBackupFilesDir(), UUID_FILE_NAME));
        this.mUuid = readUUIDFromFile;
        if (readUUIDFromFile != null) {
            return readUUIDFromFile;
        }
        String string = this.mLegacySharedPreferences.getString("uuid", null);
        this.mUuid = string;
        if (string != null) {
            try {
                saveUUID(string);
                this.mLegacySharedPreferences.edit().remove("uuid").apply();
            } catch (IOException e) {
                Log.e(TAG, "Error while migrating UUID from legacy storage. " + e);
            }
            return this.mUuid;
        }
        String readUUIDFromFile2 = readUUIDFromFile(new File(this.mContext.getNoBackupFilesDir(), LEGACY_UUID_FILE_NAME));
        this.mUuid = readUUIDFromFile2;
        if (readUUIDFromFile2 == null) {
            return readUUIDFromFile2;
        }
        try {
            saveUUID(readUUIDFromFile2);
        } catch (IOException e2) {
            Log.e(TAG, "Error while migrating UUID from legacy storage. " + e2);
        }
        return this.mUuid;
    }

    public String getOrCreateUUID() {
        String uuid = getUUID();
        if (uuid != null) {
            return uuid;
        }
        String uuid2 = UUID.randomUUID().toString();
        this.mUuid = uuid2;
        try {
            saveUUID(uuid2);
        } catch (IOException e) {
            Log.e(TAG, "Error while writing new UUID. " + e);
        }
        return this.mUuid;
    }

    protected String readUUIDFromFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                try {
                    String uuid = UUID.fromString(bufferedReader.readLine()).toString();
                    bufferedReader.close();
                    fileReader.close();
                    return uuid;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | IllegalArgumentException unused) {
            return null;
        }
    }

    protected void saveUUID(String str) throws IOException {
        FileWriter fileWriter = new FileWriter(getNonBackedUpUuidFile());
        try {
            fileWriter.write(str);
            fileWriter.close();
        } catch (Throwable th) {
            try {
                fileWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    protected File getNonBackedUpUuidFile() {
        return new File(this.mContext.getNoBackupFilesDir(), UUID_FILE_NAME);
    }
}
