package javax.portlet;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.namespace.QName;

/* loaded from: classes2.dex */
public abstract class GenericPortlet implements Portlet, PortletConfig, EventPortlet, ResourceServingPortlet, HeaderPortlet {
    public static final String AUTOMATIC_RESOURCE_DISPATCH = "javax.portlet.automaticResourceDispatching";
    private transient PortletConfig config;
    private transient Map<String, Method> processActionHandlingMethodsMap = new HashMap();
    private transient Map<String, Method> processEventHandlingMethodsMap = new HashMap();
    private transient Map<String, Method> renderModeHandlingMethodsMap = new HashMap();

    @Override // javax.portlet.Portlet
    public void destroy() {
    }

    protected void doHeaders(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
    }

    protected Collection<PortletMode> getNextPossiblePortletModes(RenderRequest renderRequest) {
        return null;
    }

    public PortletConfig getPortletConfig() {
        return this.config;
    }

    public void init() throws PortletException {
    }

    @Override // javax.portlet.HeaderPortlet
    public void renderHeaders(HeaderRequest headerRequest, HeaderResponse headerResponse) throws PortletException, IOException {
    }

    @Override // javax.portlet.Portlet
    public void init(PortletConfig portletConfig) throws PortletException {
        this.config = portletConfig;
        cacheAnnotatedMethods();
        init();
    }

    @Override // javax.portlet.Portlet
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
        Throwable cause;
        try {
            Method method = this.processActionHandlingMethodsMap.get(actionRequest.getParameter(ActionRequest.ACTION_NAME));
            if (method != null) {
                method.invoke(this, actionRequest, actionResponse);
                return;
            }
            throw new PortletException("processAction method not implemented");
        } catch (Exception e) {
            if ((e instanceof InvocationTargetException) && (cause = e.getCause()) != null) {
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                if (cause instanceof PortletException) {
                    throw ((PortletException) cause);
                }
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
            }
            throw new PortletException(e);
        }
    }

    @Override // javax.portlet.Portlet
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        Object attribute = renderRequest.getAttribute(PortletRequest.RENDER_PART);
        if (attribute != null) {
            if (attribute.equals(PortletRequest.RENDER_HEADERS)) {
                doHeaders(renderRequest, renderResponse);
                Collection<PortletMode> nextPossiblePortletModes = getNextPossiblePortletModes(renderRequest);
                if (nextPossiblePortletModes != null) {
                    renderResponse.setNextPossiblePortletModes(nextPossiblePortletModes);
                }
                renderResponse.setTitle(getTitle(renderRequest));
                return;
            }
            if (attribute.equals(PortletRequest.RENDER_MARKUP)) {
                doDispatch(renderRequest, renderResponse);
                return;
            }
            throw new PortletException("Unknown value of the 'javax.portlet.render_part' request attribute");
        }
        doHeaders(renderRequest, renderResponse);
        Collection<PortletMode> nextPossiblePortletModes2 = getNextPossiblePortletModes(renderRequest);
        if (nextPossiblePortletModes2 != null) {
            renderResponse.setNextPossiblePortletModes(nextPossiblePortletModes2);
        }
        renderResponse.setTitle(getTitle(renderRequest));
        doDispatch(renderRequest, renderResponse);
    }

    protected String getTitle(RenderRequest renderRequest) {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getResourceBundle(renderRequest.getLocale()).getString("javax.portlet.title");
    }

    protected void doDispatch(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        Throwable cause;
        if (renderRequest.getWindowState().equals(WindowState.MINIMIZED)) {
            return;
        }
        PortletMode portletMode = renderRequest.getPortletMode();
        try {
            Method method = this.renderModeHandlingMethodsMap.get(portletMode.toString());
            if (method != null) {
                method.invoke(this, renderRequest, renderResponse);
                return;
            }
            if (portletMode.equals(PortletMode.VIEW)) {
                doView(renderRequest, renderResponse);
                return;
            }
            if (portletMode.equals(PortletMode.EDIT)) {
                doEdit(renderRequest, renderResponse);
            } else if (portletMode.equals(PortletMode.HELP)) {
                doHelp(renderRequest, renderResponse);
            } else {
                throw new PortletException("unknown portlet mode: " + portletMode);
            }
        } catch (Exception e) {
            if ((e instanceof InvocationTargetException) && (cause = e.getCause()) != null) {
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                if (cause instanceof PortletException) {
                    throw ((PortletException) cause);
                }
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
            }
            throw new PortletException(e);
        }
    }

    protected void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        throw new PortletException("doView method not implemented");
    }

    protected void doEdit(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        throw new PortletException("doEdit method not implemented");
    }

    protected void doHelp(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        throw new PortletException("doHelp method not implemented");
    }

    @Override // javax.portlet.PortletConfig
    public String getPortletName() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getPortletName();
    }

    @Override // javax.portlet.PortletConfig
    public PortletContext getPortletContext() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getPortletContext();
    }

    @Override // javax.portlet.PortletConfig
    public ResourceBundle getResourceBundle(Locale locale) {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getResourceBundle(locale);
    }

    @Override // javax.portlet.PortletConfig
    public String getInitParameter(String str) {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getInitParameter(str);
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<String> getInitParameterNames() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getInitParameterNames();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<QName> getProcessingEventQNames() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getProcessingEventQNames();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<QName> getPublishingEventQNames() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getPublishingEventQNames();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<Locale> getSupportedLocales() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getSupportedLocales();
    }

    @Override // javax.portlet.PortletConfig
    public Map<String, String[]> getContainerRuntimeOptions() {
        return this.config.getContainerRuntimeOptions();
    }

    @Override // javax.portlet.ResourceServingPortlet
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException {
        PortletRequestDispatcher requestDispatcher;
        String initParameter = getInitParameter(AUTOMATIC_RESOURCE_DISPATCH);
        if (initParameter == null || !initParameter.equalsIgnoreCase("true") || resourceRequest.getResourceID() == null || (requestDispatcher = getPortletConfig().getPortletContext().getRequestDispatcher(resourceRequest.getResourceID())) == null) {
            return;
        }
        requestDispatcher.forward(resourceRequest, resourceResponse);
    }

    @Override // javax.portlet.EventPortlet
    public void processEvent(EventRequest eventRequest, EventResponse eventResponse) throws PortletException, IOException {
        Throwable cause;
        String qName = eventRequest.getEvent().getQName().toString();
        try {
            Method method = this.processEventHandlingMethodsMap.get(qName);
            if (method != null) {
                method.invoke(this, eventRequest, eventResponse);
                return;
            }
            int indexOf = qName.indexOf(125);
            for (int lastIndexOf = qName.lastIndexOf(46); lastIndexOf > indexOf; lastIndexOf = qName.lastIndexOf(46, lastIndexOf - 1)) {
                Method method2 = this.processEventHandlingMethodsMap.get(qName.substring(0, lastIndexOf + 1));
                if (method2 != null) {
                    method2.invoke(this, eventRequest, eventResponse);
                    return;
                } else {
                    if (lastIndexOf == 0) {
                        break;
                    }
                }
            }
            eventResponse.setRenderParameters(eventRequest);
        } catch (Exception e) {
            if ((e instanceof InvocationTargetException) && (cause = e.getCause()) != null) {
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                if (cause instanceof PortletException) {
                    throw ((PortletException) cause);
                }
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
            }
            throw new PortletException(e);
        }
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<String> getPublicRenderParameterNames() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getPublicRenderParameterNames();
    }

    @Override // javax.portlet.PortletConfig
    public String getDefaultNamespace() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getDefaultNamespace();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<PortletMode> getPortletModes(String str) {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getPortletModes(str);
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<WindowState> getWindowStates(String str) {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getWindowStates(str);
    }

    @Override // javax.portlet.PortletConfig
    public Map<String, QName> getPublicRenderParameterDefinitions() {
        PortletConfig portletConfig = this.config;
        if (portletConfig == null) {
            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
        }
        return portletConfig.getPublicRenderParameterDefinitions();
    }

    protected boolean dispatchAnnotatedActionMethod(String str, ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
        Throwable cause;
        try {
            Method method = this.processActionHandlingMethodsMap.get(str);
            if (method == null) {
                return false;
            }
            method.invoke(this, actionRequest, actionResponse);
            return true;
        } catch (Exception e) {
            if ((e instanceof InvocationTargetException) && (cause = e.getCause()) != null) {
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                if (cause instanceof PortletException) {
                    throw ((PortletException) cause);
                }
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
            }
            throw new PortletException(e);
        }
    }

    protected boolean dispatchAnnotatedEventMethod(String str, EventRequest eventRequest, EventResponse eventResponse) throws PortletException, IOException {
        Throwable cause;
        try {
            Method method = this.processEventHandlingMethodsMap.get(eventRequest.getEvent().getQName().toString());
            if (method == null) {
                return false;
            }
            method.invoke(this, eventRequest, eventResponse);
            return true;
        } catch (Exception e) {
            if ((e instanceof InvocationTargetException) && (cause = e.getCause()) != null) {
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                if (cause instanceof PortletException) {
                    throw ((PortletException) cause);
                }
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
            }
            throw new PortletException(e);
        }
    }

    protected boolean dispatchAnnotatedRenderMethod(String str, RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        Throwable cause;
        try {
            Method method = this.renderModeHandlingMethodsMap.get(str);
            if (method == null) {
                return false;
            }
            method.invoke(this, renderRequest, renderResponse);
            return true;
        } catch (Exception e) {
            if ((e instanceof InvocationTargetException) && (cause = e.getCause()) != null) {
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                if (cause instanceof PortletException) {
                    throw ((PortletException) cause);
                }
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
            }
            throw new PortletException(e);
        }
    }

    private void cacheAnnotatedMethods() {
        String name;
        for (Method method : getClass().getMethods()) {
            Annotation[] annotations = method.getAnnotations();
            if (annotations != null) {
                for (Annotation annotation : annotations) {
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    if (ProcessAction.class.equals(annotationType)) {
                        String name2 = ((ProcessAction) annotation).name();
                        if (name2 != null && name2.length() > 0) {
                            addToMap(this.processActionHandlingMethodsMap, name2, method);
                        }
                    } else if (ProcessEvent.class.equals(annotationType)) {
                        ProcessEvent processEvent = (ProcessEvent) annotation;
                        String qname = processEvent.qname();
                        if (qname == null || qname.length() <= 0) {
                            if (this.config == null) {
                                throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
                            }
                            String name3 = processEvent.name();
                            if (name3 != null && name3.length() > 0) {
                                addToMap(this.processEventHandlingMethodsMap, new QName(this.config.getDefaultNamespace(), name3).toString(), method);
                            }
                        } else {
                            addToMap(this.processEventHandlingMethodsMap, qname, method);
                        }
                    } else if (RenderMode.class.equals(annotationType) && (name = ((RenderMode) annotation).name()) != null && name.length() > 0) {
                        addToMap(this.renderModeHandlingMethodsMap, name.toLowerCase(), method);
                    }
                }
            }
        }
    }

    private void addToMap(Map<String, Method> map, String str, Method method) {
        Method method2 = map.get(str);
        if (method2 != null && method != null) {
            ArrayList arrayList = new ArrayList();
            for (Class<?> cls = getClass(); cls != null; cls = cls.getSuperclass()) {
                arrayList.add(cls);
            }
            if (arrayList.indexOf(method2.getDeclaringClass()) <= arrayList.indexOf(method.getDeclaringClass())) {
                return;
            }
        }
        map.put(str, method);
    }
}
