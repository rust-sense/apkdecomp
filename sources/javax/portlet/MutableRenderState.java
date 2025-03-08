package javax.portlet;

/* loaded from: classes2.dex */
public interface MutableRenderState extends RenderState, Mutable {
    @Override // javax.portlet.RenderState
    MutableRenderParameters getRenderParameters();

    void setPortletMode(PortletMode portletMode) throws PortletModeException;

    void setWindowState(WindowState windowState) throws WindowStateException;
}
