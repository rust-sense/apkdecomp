package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Iterator;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;

/* compiled from: ClassDeserializer.kt */
/* loaded from: classes3.dex */
public final class ClassDeserializer {
    private final Function1<ClassKey, ClassDescriptor> classes;
    private final DeserializationComponents components;
    public static final Companion Companion = new Companion(null);
    private static final Set<ClassId> BLACK_LIST = SetsKt.setOf(ClassId.topLevel(StandardNames.FqNames.cloneable.toSafe()));

    public ClassDeserializer(DeserializationComponents components) {
        Intrinsics.checkNotNullParameter(components, "components");
        this.components = components;
        this.classes = components.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<ClassKey, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer$classes$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ClassDescriptor invoke(ClassDeserializer.ClassKey key) {
                ClassDescriptor createClass;
                Intrinsics.checkNotNullParameter(key, "key");
                createClass = ClassDeserializer.this.createClass(key);
                return createClass;
            }
        });
    }

    public static /* synthetic */ ClassDescriptor deserializeClass$default(ClassDeserializer classDeserializer, ClassId classId, ClassData classData, int i, Object obj) {
        if ((i & 2) != 0) {
            classData = null;
        }
        return classDeserializer.deserializeClass(classId, classData);
    }

    public final ClassDescriptor deserializeClass(ClassId classId, ClassData classData) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        return this.classes.invoke(new ClassKey(classId, classData));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor createClass(ClassKey classKey) {
        Object obj;
        DeserializationContext createContext;
        ClassId classId = classKey.getClassId();
        Iterator<ClassDescriptorFactory> it = this.components.getFictitiousClassDescriptorFactories().iterator();
        while (it.hasNext()) {
            ClassDescriptor createClass = it.next().createClass(classId);
            if (createClass != null) {
                return createClass;
            }
        }
        if (BLACK_LIST.contains(classId)) {
            return null;
        }
        ClassData classData = classKey.getClassData();
        if (classData == null && (classData = this.components.getClassDataFinder().findClassData(classId)) == null) {
            return null;
        }
        NameResolver component1 = classData.component1();
        ProtoBuf.Class component2 = classData.component2();
        BinaryVersion component3 = classData.component3();
        SourceElement component4 = classData.component4();
        ClassId outerClassId = classId.getOuterClassId();
        if (outerClassId != null) {
            ClassDescriptor deserializeClass$default = deserializeClass$default(this, outerClassId, null, 2, null);
            DeserializedClassDescriptor deserializedClassDescriptor = deserializeClass$default instanceof DeserializedClassDescriptor ? (DeserializedClassDescriptor) deserializeClass$default : null;
            if (deserializedClassDescriptor == null) {
                return null;
            }
            Name shortClassName = classId.getShortClassName();
            Intrinsics.checkNotNullExpressionValue(shortClassName, "getShortClassName(...)");
            if (!deserializedClassDescriptor.hasNestedClass$deserialization(shortClassName)) {
                return null;
            }
            createContext = deserializedClassDescriptor.getC();
        } else {
            PackageFragmentProvider packageFragmentProvider = this.components.getPackageFragmentProvider();
            FqName packageFqName = classId.getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(packageFqName, "getPackageFqName(...)");
            Iterator<T> it2 = PackageFragmentProviderKt.packageFragments(packageFragmentProvider, packageFqName).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) obj;
                if (!(packageFragmentDescriptor instanceof DeserializedPackageFragment)) {
                    break;
                }
                Name shortClassName2 = classId.getShortClassName();
                Intrinsics.checkNotNullExpressionValue(shortClassName2, "getShortClassName(...)");
                if (((DeserializedPackageFragment) packageFragmentDescriptor).hasTopLevelClass(shortClassName2)) {
                    break;
                }
            }
            PackageFragmentDescriptor packageFragmentDescriptor2 = (PackageFragmentDescriptor) obj;
            if (packageFragmentDescriptor2 == null) {
                return null;
            }
            DeserializationComponents deserializationComponents = this.components;
            ProtoBuf.TypeTable typeTable = component2.getTypeTable();
            Intrinsics.checkNotNullExpressionValue(typeTable, "getTypeTable(...)");
            TypeTable typeTable2 = new TypeTable(typeTable);
            VersionRequirementTable.Companion companion = VersionRequirementTable.Companion;
            ProtoBuf.VersionRequirementTable versionRequirementTable = component2.getVersionRequirementTable();
            Intrinsics.checkNotNullExpressionValue(versionRequirementTable, "getVersionRequirementTable(...)");
            createContext = deserializationComponents.createContext(packageFragmentDescriptor2, component1, typeTable2, companion.create(versionRequirementTable), component3, null);
        }
        return new DeserializedClassDescriptor(createContext, component2, component1, component3, component4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ClassDeserializer.kt */
    static final class ClassKey {
        private final ClassData classData;
        private final ClassId classId;

        public final ClassData getClassData() {
            return this.classData;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public ClassKey(ClassId classId, ClassData classData) {
            Intrinsics.checkNotNullParameter(classId, "classId");
            this.classId = classId;
            this.classData = classData;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ClassKey) && Intrinsics.areEqual(this.classId, ((ClassKey) obj).classId);
        }

        public int hashCode() {
            return this.classId.hashCode();
        }
    }

    /* compiled from: ClassDeserializer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<ClassId> getBLACK_LIST() {
            return ClassDeserializer.BLACK_LIST;
        }
    }
}
