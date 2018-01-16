package cn.cerc.jui.phone;

import cn.cerc.jpage.core.HtmlWriter;
import cn.cerc.jpage.core.UrlRecord;
import cn.cerc.jui.parts.UIComponent;
import cn.cerc.jui.vcl.UIImage;

/**
 * 右侧带数据角标 + 导航链接
 * 
 * @author HuangRongjun
 *
 */

public class Block127 extends UIComponent {
    private String text;
    private int number;
    private UIImage icon = new UIImage();
    private UrlRecord url;

    public Block127(UIComponent owner) {
        super(owner);
        url = new UrlRecord();
        text = "(text)";
        number = 20;
        icon.setSrc("jui/phone/block301-rightIcon.png");
        icon.setRole("right");
    }

    @Override
    public void output(HtmlWriter html) {
        html.println("<!-- %s -->", this.getClass().getName());
        html.print("<div class='block127'>");

        html.print("<a href='%s'>", url.getUrl());
        html.println("<span role='text'>%s</span>", text);
        html.println("<span role='number'>%s</span>", number);

        icon.output(html);
        html.print("</a>");

        html.println("</div>");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public UIImage getIcon() {
        return icon;
    }

    public UrlRecord getUrl() {
        return url;
    }

    public void setUrl(UrlRecord url) {
        this.url = url;
    }

}
