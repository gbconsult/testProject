package cn.cerc.jmis.page;

import java.util.List;

import cn.cerc.jbean.form.IForm;
import cn.cerc.jpage.core.UrlRecord;

public interface IMenuBar {
    // 登记菜单栏菜单项
    public int enrollMenu(IForm form, List<UrlRecord> menus);
}
