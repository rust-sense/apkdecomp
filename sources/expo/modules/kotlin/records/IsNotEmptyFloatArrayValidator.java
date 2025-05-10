package expo.modules.kotlin.records;

import expo.modules.kotlin.exception.ValidationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FieldValidator.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lexpo/modules/kotlin/records/IsNotEmptyFloatArrayValidator;", "Lexpo/modules/kotlin/records/FieldValidator;", "", "()V", "validate", "", "value", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class IsNotEmptyFloatArrayValidator implements FieldValidator<float[]> {
    @Override // expo.modules.kotlin.records.FieldValidator
    public void validate(float[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.length == 0) {
            throw new ValidationException("Array is empty");
        }
    }
}
