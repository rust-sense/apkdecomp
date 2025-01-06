package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;

/* compiled from: AnnotationLoader.kt */
/* loaded from: classes3.dex */
public interface AnnotationLoader<A> {
    List<A> loadCallableAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind);

    List<A> loadClassAnnotations(ProtoContainer.Class r1);

    List<A> loadEnumEntryAnnotations(ProtoContainer protoContainer, ProtoBuf.EnumEntry enumEntry);

    List<A> loadExtensionReceiverParameterAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind);

    List<A> loadPropertyBackingFieldAnnotations(ProtoContainer protoContainer, ProtoBuf.Property property);

    List<A> loadPropertyDelegateFieldAnnotations(ProtoContainer protoContainer, ProtoBuf.Property property);

    List<A> loadTypeAnnotations(ProtoBuf.Type type, NameResolver nameResolver);

    List<A> loadTypeParameterAnnotations(ProtoBuf.TypeParameter typeParameter, NameResolver nameResolver);

    List<A> loadValueParameterAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind, int i, ProtoBuf.ValueParameter valueParameter);
}
