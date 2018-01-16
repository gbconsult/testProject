package cn.cerc.jpage.other;

import java.text.DecimalFormat;

import cn.cerc.jpage.core.HtmlWriter;
import cn.cerc.jui.parts.UIComponent;

public class StrongItem extends UIComponent {
    private String name;
    private Double value;

    public StrongItem(UIComponent owner) {
        super(owner);
    }

    public String getName() {
        return name;
    }

    public StrongItem setName(String name) {
        this.name = name;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public StrongItem setValue(Double value) {
        this.value = value;
        return this;
    }

    @Override
    public void output(HtmlWriter html) {
        DecimalFormat df = new DecimalFormat("0.##");
        html.print("%s：", this.getName());
        html.print("<strong");
        if (this.getId() != null)
            html.print(" id=\"%s\"", this.getId());
        html.print(">");
        html.print(df.format(this.value));
        html.print("</strong>");
    }
}
