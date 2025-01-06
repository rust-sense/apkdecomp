package expo.modules.updates.db.dao;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.entity.JSONDataEntity;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JSONDataDao.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H'J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH'J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H'J\u0018\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J \u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0017J$\u0010\u0010\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0007\u001a\u00020\u0006H\u0017J=\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062#\u0010\u0014\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00060\u0015H\u0017¨\u0006\u0019"}, d2 = {"Lexpo/modules/updates/db/dao/JSONDataDao;", "", "()V", "_deleteJSONDataForKey", "", "key", "", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "_insertJSONData", "jsonDataEntity", "Lexpo/modules/updates/db/entity/JSONDataEntity;", "_loadJSONDataForKey", "", "loadJSONStringForKey", "setJSONStringForKey", "value", "setMultipleFields", "fields", "", "updateJSONStringForKey", "updater", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "previousValue", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class JSONDataDao {
    public abstract void _deleteJSONDataForKey(String key, String scopeKey);

    public abstract void _insertJSONData(JSONDataEntity jsonDataEntity);

    public abstract List<JSONDataEntity> _loadJSONDataForKey(String key, String scopeKey);

    public final String loadJSONStringForKey(String key, String scopeKey) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        List<JSONDataEntity> _loadJSONDataForKey = _loadJSONDataForKey(key, scopeKey);
        if (_loadJSONDataForKey.isEmpty()) {
            return null;
        }
        return _loadJSONDataForKey.get(0).getValue();
    }

    public void setJSONStringForKey(String key, String value, String scopeKey) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        _deleteJSONDataForKey(key, scopeKey);
        _insertJSONData(new JSONDataEntity(key, value, new Date(), scopeKey));
    }

    public void setMultipleFields(Map<String, String> fields, String scopeKey) {
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            _deleteJSONDataForKey(entry.getKey(), scopeKey);
            _insertJSONData(new JSONDataEntity(entry.getKey(), entry.getValue(), new Date(), scopeKey));
        }
    }

    public void updateJSONStringForKey(String key, String scopeKey, Function1<? super String, String> updater) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        Intrinsics.checkNotNullParameter(updater, "updater");
        String loadJSONStringForKey = loadJSONStringForKey(key, scopeKey);
        _deleteJSONDataForKey(key, scopeKey);
        _insertJSONData(new JSONDataEntity(key, updater.invoke(loadJSONStringForKey), new Date(), scopeKey));
    }
}
