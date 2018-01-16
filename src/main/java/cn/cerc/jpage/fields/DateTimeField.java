package cn.cerc.jpage.fields;

import cn.cerc.jdb.core.Record;
import cn.cerc.jpage.core.HtmlWriter;
import cn.cerc.jui.parts.UIComponent;

public class DateTimeField extends AbstractField {

    public DateTimeField(UIComponent owner, String name, String field) {
        super(owner, name, 10);
        this.setField(field);
    }

    public DateTimeField(UIComponent owner, String name, String field, int width) {
        super(owner, name, width);
        this.setField(field);
    }

    @Override
    public String getText(Record dataSet) {
        if (dataSet == null)
            return null;
        if (buildText != null) {
            HtmlWriter html = new HtmlWriter();
            buildText.outputText(dataSet, html);
            return html.toString();
        }
        return dataSet.getString(getField());
    }
}
