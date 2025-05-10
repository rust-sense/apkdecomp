package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

/* compiled from: AbstractTypeConstructor.kt */
/* loaded from: classes3.dex */
public abstract class AbstractTypeConstructor extends ClassifierBasedTypeConstructor {
    private final boolean shouldReportCyclicScopeWithCompanionWarning;
    private final NotNullLazyValue<Supertypes> supertypes;

    protected abstract Collection<KotlinType> computeSupertypes();

    protected KotlinType defaultSupertypeIfEmpty() {
        return null;
    }

    protected boolean getShouldReportCyclicScopeWithCompanionWarning() {
        return this.shouldReportCyclicScopeWithCompanionWarning;
    }

    protected abstract SupertypeLoopChecker getSupertypeLoopChecker();

    protected List<KotlinType> processSupertypesWithoutCycles(List<KotlinType> supertypes) {
        Intrinsics.checkNotNullParameter(supertypes, "supertypes");
        return supertypes;
    }

    protected void reportScopesLoopError(KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
    }

    protected void reportSupertypeLoopError(KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
    }

    public AbstractTypeConstructor(StorageManager storageManager) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        this.supertypes = storageManager.createLazyValueWithPostCompute(new Function0<Supertypes>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final AbstractTypeConstructor.Supertypes invoke() {
                return new AbstractTypeConstructor.Supertypes(AbstractTypeConstructor.this.computeSupertypes());
            }
        }, new Function1<Boolean, Supertypes>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ AbstractTypeConstructor.Supertypes invoke(Boolean bool) {
                return invoke(bool.booleanValue());
            }

            public final AbstractTypeConstructor.Supertypes invoke(boolean z) {
                return new AbstractTypeConstructor.Supertypes(CollectionsKt.listOf(ErrorUtils.INSTANCE.getErrorTypeForLoopInSupertypes()));
            }
        }, new Function1<Supertypes, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AbstractTypeConstructor.Supertypes supertypes) {
                invoke2(supertypes);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AbstractTypeConstructor.Supertypes supertypes) {
                Intrinsics.checkNotNullParameter(supertypes, "supertypes");
                SupertypeLoopChecker supertypeLoopChecker = AbstractTypeConstructor.this.getSupertypeLoopChecker();
                AbstractTypeConstructor abstractTypeConstructor = AbstractTypeConstructor.this;
                Collection<KotlinType> allSupertypes = supertypes.getAllSupertypes();
                final AbstractTypeConstructor abstractTypeConstructor2 = AbstractTypeConstructor.this;
                Function1<TypeConstructor, Iterable<? extends KotlinType>> function1 = new Function1<TypeConstructor, Iterable<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Iterable<KotlinType> invoke(TypeConstructor it) {
                        Collection computeNeighbours;
                        Intrinsics.checkNotNullParameter(it, "it");
                        computeNeighbours = AbstractTypeConstructor.this.computeNeighbours(it, false);
                        return computeNeighbours;
                    }
                };
                final AbstractTypeConstructor abstractTypeConstructor3 = AbstractTypeConstructor.this;
                List findLoopsInSupertypesAndDisconnect = supertypeLoopChecker.findLoopsInSupertypesAndDisconnect(abstractTypeConstructor, allSupertypes, function1, new Function1<KotlinType, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                        invoke2(kotlinType);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KotlinType it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        AbstractTypeConstructor.this.reportSupertypeLoopError(it);
                    }
                });
                if (findLoopsInSupertypesAndDisconnect.isEmpty()) {
                    KotlinType defaultSupertypeIfEmpty = AbstractTypeConstructor.this.defaultSupertypeIfEmpty();
                    List listOf = defaultSupertypeIfEmpty != null ? CollectionsKt.listOf(defaultSupertypeIfEmpty) : null;
                    if (listOf == null) {
                        listOf = CollectionsKt.emptyList();
                    }
                    findLoopsInSupertypesAndDisconnect = listOf;
                }
                if (AbstractTypeConstructor.this.getShouldReportCyclicScopeWithCompanionWarning()) {
                    SupertypeLoopChecker supertypeLoopChecker2 = AbstractTypeConstructor.this.getSupertypeLoopChecker();
                    AbstractTypeConstructor abstractTypeConstructor4 = AbstractTypeConstructor.this;
                    final AbstractTypeConstructor abstractTypeConstructor5 = AbstractTypeConstructor.this;
                    Function1<TypeConstructor, Iterable<? extends KotlinType>> function12 = new Function1<TypeConstructor, Iterable<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Iterable<KotlinType> invoke(TypeConstructor it) {
                            Collection computeNeighbours;
                            Intrinsics.checkNotNullParameter(it, "it");
                            computeNeighbours = AbstractTypeConstructor.this.computeNeighbours(it, true);
                            return computeNeighbours;
                        }
                    };
                    final AbstractTypeConstructor abstractTypeConstructor6 = AbstractTypeConstructor.this;
                    supertypeLoopChecker2.findLoopsInSupertypesAndDisconnect(abstractTypeConstructor4, findLoopsInSupertypesAndDisconnect, function12, new Function1<KotlinType, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.3
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                            invoke2(kotlinType);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KotlinType it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            AbstractTypeConstructor.this.reportScopesLoopError(it);
                        }
                    });
                }
                AbstractTypeConstructor abstractTypeConstructor7 = AbstractTypeConstructor.this;
                List<KotlinType> list = findLoopsInSupertypesAndDisconnect instanceof List ? (List) findLoopsInSupertypesAndDisconnect : null;
                if (list == null) {
                    list = CollectionsKt.toList(findLoopsInSupertypesAndDisconnect);
                }
                supertypes.setSupertypesWithoutCycles(abstractTypeConstructor7.processSupertypesWithoutCycles(list));
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* renamed from: getSupertypes */
    public List<KotlinType> mo2190getSupertypes() {
        return this.supertypes.invoke().getSupertypesWithoutCycles();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return new ModuleViewTypeConstructor(this, kotlinTypeRefiner);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractTypeConstructor.kt */
    final class ModuleViewTypeConstructor implements TypeConstructor {
        private final KotlinTypeRefiner kotlinTypeRefiner;
        private final Lazy refinedSupertypes$delegate;
        final /* synthetic */ AbstractTypeConstructor this$0;

        public ModuleViewTypeConstructor(final AbstractTypeConstructor abstractTypeConstructor, KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            this.this$0 = abstractTypeConstructor;
            this.kotlinTypeRefiner = kotlinTypeRefiner;
            this.refinedSupertypes$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<List<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$ModuleViewTypeConstructor$refinedSupertypes$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final List<? extends KotlinType> invoke() {
                    KotlinTypeRefiner kotlinTypeRefiner2;
                    kotlinTypeRefiner2 = AbstractTypeConstructor.ModuleViewTypeConstructor.this.kotlinTypeRefiner;
                    return KotlinTypeRefinerKt.refineTypes(kotlinTypeRefiner2, abstractTypeConstructor.mo2190getSupertypes());
                }
            });
        }

        private final List<KotlinType> getRefinedSupertypes() {
            return (List) this.refinedSupertypes$delegate.getValue();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List<TypeParameterDescriptor> getParameters() {
            List<TypeParameterDescriptor> parameters = this.this$0.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
            return parameters;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        /* renamed from: getSupertypes */
        public List<KotlinType> mo2190getSupertypes() {
            return getRefinedSupertypes();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return this.this$0.isDenotable();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        /* renamed from: getDeclarationDescriptor */
        public ClassifierDescriptor mo2189getDeclarationDescriptor() {
            return this.this$0.mo2189getDeclarationDescriptor();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public KotlinBuiltIns getBuiltIns() {
            KotlinBuiltIns builtIns = this.this$0.getBuiltIns();
            Intrinsics.checkNotNullExpressionValue(builtIns, "getBuiltIns(...)");
            return builtIns;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            return this.this$0.refine(kotlinTypeRefiner);
        }

        public boolean equals(Object obj) {
            return this.this$0.equals(obj);
        }

        public int hashCode() {
            return this.this$0.hashCode();
        }

        public String toString() {
            return this.this$0.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractTypeConstructor.kt */
    static final class Supertypes {
        private final Collection<KotlinType> allSupertypes;
        private List<? extends KotlinType> supertypesWithoutCycles;

        public final Collection<KotlinType> getAllSupertypes() {
            return this.allSupertypes;
        }

        public final List<KotlinType> getSupertypesWithoutCycles() {
            return this.supertypesWithoutCycles;
        }

        public final void setSupertypesWithoutCycles(List<? extends KotlinType> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.supertypesWithoutCycles = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Supertypes(Collection<? extends KotlinType> allSupertypes) {
            Intrinsics.checkNotNullParameter(allSupertypes, "allSupertypes");
            this.allSupertypes = allSupertypes;
            this.supertypesWithoutCycles = CollectionsKt.listOf(ErrorUtils.INSTANCE.getErrorTypeForLoopInSupertypes());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<KotlinType> computeNeighbours(TypeConstructor typeConstructor, boolean z) {
        List plus;
        AbstractTypeConstructor abstractTypeConstructor = typeConstructor instanceof AbstractTypeConstructor ? (AbstractTypeConstructor) typeConstructor : null;
        if (abstractTypeConstructor != null && (plus = CollectionsKt.plus((Collection) abstractTypeConstructor.supertypes.invoke().getAllSupertypes(), (Iterable) abstractTypeConstructor.getAdditionalNeighboursInSupertypeGraph(z))) != null) {
            return plus;
        }
        Collection<KotlinType> mo2190getSupertypes = typeConstructor.mo2190getSupertypes();
        Intrinsics.checkNotNullExpressionValue(mo2190getSupertypes, "getSupertypes(...)");
        return mo2190getSupertypes;
    }

    protected Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean z) {
        return CollectionsKt.emptyList();
    }
}
