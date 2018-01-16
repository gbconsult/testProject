package cn.cerc.jui.phone;

import cn.cerc.jpage.core.HtmlWriter;
import cn.cerc.jui.parts.UIComponent;
import cn.cerc.jui.vcl.UIButton;
import cn.cerc.jui.vcl.UITextBox;
import cn.cerc.jui.vcl.ext.UISpan;

public class Block110 extends UIComponent {
    private UISpan label = new UISpan();
    private UITextBox input = new UITextBox();
    private UIButton search = new UIButton();

    /**
     * 文本 + 输入框 + 查询按钮
     * 
     * @param owner
     *            内容显示区
     */
    public Block110(UIComponent owner) {
        super(owner);
        label.setText("(label)");
        search.setText("查询");
    }

    @Override
    public void output(HtmlWriter html) {
        html.println("<!-- %s -->", this.getClass().getName());
        html.print("<div class='block110'>");
        label.output(html);
        input.output(html);
        search.output(html);
        html.println("</div>");
    }

    public UISpan getLabel() {
        return label;
    }

    public UITextBox getInput() {
        return input;
    }

    public UIButton getSearch() {
        return search;
    }
}