package io.sentry;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface JsonSerializable {
    void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException;
}