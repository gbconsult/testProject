package cn.cerc.jmis.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cerc.jbean.core.AbstractHandle;
import cn.cerc.jbean.core.CustomHandle;
import cn.cerc.jbean.form.IClient;
import cn.cerc.jbean.form.IForm;
import cn.cerc.jmis.core.ClientDevice;

public abstract class AbstractForm extends AbstractHandle implements IForm {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private IClient client;
    private Map<String, String> params = new HashMap<>();
    private String caption;
    private String parent;
    private String permission;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void init(AbstractForm owner) {
        this.setHandle(owner.getHandle());
        this.setClient(owner.getClient());
        this.setRequest(owner.getRequest());
        this.setResponse(owner.getResponse());
    }

    @Override
    public boolean logon() {
        if (getHandle() == null)
            return false;
        CustomHandle sess = (CustomHandle) getHandle().getProperty(null);
        return sess.logon();
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public HttpServletRequest getRequest() {
        return this.request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    @Override
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public Object getProperty(String key) {
        if ("request".equals(key))
            return this.getRequest();
        if ("session".equals(key))
            return this.getRequest().getSession();

        return handle.getProperty(key);
    }

    @Override
    @Deprecated
    public String getTitle() {
        return getCaption();
    }

    @Override
    public IClient getClient() {
        if (client == null) {
            client = new ClientDevice(this);
        }
        return client;
    }

    @Override
    public void setClient(IClient client) {
        this.client = client;
    }

    @Override
    public void setParam(String key, String value) {
        params.put(key, value);
    }

    @Override
    public String getParam(String key, String def) {
        if (params.containsKey(key))
            return params.get(key);
        else {
            return def;
        }
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
