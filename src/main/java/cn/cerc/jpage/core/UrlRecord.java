package cn.cerc.jpage.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UrlRecord {
    private String site;
    private String name;
    private String title;
    private String target;
    private Map<String, String> params = new HashMap<>();
    private String hintMsg;

    public UrlRecord() {
    }

    public UrlRecord(String site, String name) {
        super();
        this.site = site;
        this.name = name;
    }

    public UrlRecord addParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    public String getSite() {
        return site;
    }

    public UrlRecord setSite(String site) {
        this.site = site;
        return this;
    }

    public UrlRecord setSite(String format, Object... args) {
        this.site = String.format(format, args);
        return this;
    }

    public String getName() {
        return name;
    }

    /**
     * 
     * @return 请改为getName
     */
    @Deprecated
    public String getCaption() {
        return name;
    }

    public UrlRecord setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        StringBuffer sl = new StringBuffer();
        if (site != null)
            sl.append(site);

        int i = 0;
        for (String key : params.keySet()) {
            i++;
            sl.append(i == 1 ? "?" : "&");
            sl.append(key);
            sl.append("=");
            String value = params.get(key);
            if (value != null)
                sl.append(encodeUTF8(value));
        }
        return sl.toString();
    }

    private String encodeUTF8(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHintMsg() {
        return hintMsg;
    }

    public void setHintMsg(String hintMsg) {
        this.hintMsg = hintMsg;
    }

}
