package cn.cerc.jpage.grid;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.cerc.jpage.core.Component;
import cn.cerc.jpage.core.HtmlWriter;
import cn.cerc.jpage.core.IColumn;
import cn.cerc.jui.parts.UIComponent;

public class ObjectGrid extends UIComponent {
    private List<IColumn> columns = new ArrayList<>();
    private List<RowData> rows = new ArrayList<>();
    private RowData current;

    @Override
    public void addComponent(Component component) {
        if (component instanceof IColumn) {
            columns.add((IColumn) component);
        }
        if (component instanceof RowData) {
            rows.add((RowData) component);
        }
    }

    @Override
    public void output(HtmlWriter html) {
        html.print("<table>");
        html.print("<tr>");
        for (IColumn column : columns) {
            html.print("<th>%s</th>", column.getTitle());
        }
        html.print("<tr>");

        for (RowData row : rows) {
            html.print("<tr>");
            for (IColumn column : columns) {
                html.print("<td>");
                Object data = row.getData(column);
                html.print("%s", column.format(data));
                html.print("</td>");
            }
            html.print("<tr>");
        }

        html.print("</table>");
    }

    public void addItem() {
        current = new RowData();
        rows.add(current);
    }

    public RowData getCurrent() {
        return current;
    }

    public void setCurrent(RowData current) {
        this.current = current;
    }

    public class RowData extends Component {
        private Map<IColumn, Object> items = new LinkedHashMap<>();

        public Object getData(IColumn column) {
            return items.get(column);
        }

        public void addData(IColumn column, Object data) {
            items.put(column, data);
        }
    }

}
