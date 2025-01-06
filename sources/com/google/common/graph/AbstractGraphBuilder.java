package com.google.common.graph;

import com.google.common.base.Optional;

/* loaded from: classes2.dex */
abstract class AbstractGraphBuilder<N> {
    final boolean directed;
    boolean allowsSelfLoops = false;
    ElementOrder<N> nodeOrder = ElementOrder.insertion();
    Optional<Integer> expectedNodeCount = Optional.absent();

    AbstractGraphBuilder(boolean z) {
        this.directed = z;
    }
}