package cn.cerc.jui.page;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cn.cerc.jbean.core.Application;
import cn.cerc.jbean.core.CustomHandle;
import cn.cerc.jbean.form.IForm;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.AbstractJspPage;
import cn.cerc.jmis.page.IMenuBar;
import cn.cerc.jpage.core.Component;
import cn.cerc.jpage.core.UrlRecord;
import cn.cerc.jui.parts.RightMenus;

public class UIPageView extends AbstractJspPage {

    public UIPageView(IForm form) {
        super(form);
        initCssFile();
        initJsFile();
    }

    @Override
    public void execute() throws ServletException, IOException {
        HttpServletRequest request = getRequest();
        IForm form = this.getForm();
        CustomHandle sess = (CustomHandle) form.getHandle().getProperty(null);
        if (sess.logon()) {
            List<UrlRecord> rightMenus = getHeader().getRightMenus();
            RightMenus menus = Application.getBean("RightMenus", RightMenus.class);
            menus.setHandle(form.getHandle());
            for (IMenuBar item : menus.getItems())
                item.enrollMenu(form, rightMenus);
        } else {
            getHeader().getHomePage().setSite(Application.getAppConfig().getFormWelcome());
        }

        // 系统通知消息
        Component content = this.getContent();
        if (form instanceof AbstractForm) {
            this.getHeader().initHeader();
            request.setAttribute(content.getId(), content);
            for (Component component : content.getComponents()) {
                request.setAttribute(component.getId(), component);
            }
        }

        // 开始输出
        PrintWriter out = getResponse().getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.printf("<title>%s</title>\n", this.getForm().getTitle());
        out.printf("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n");
        out.printf(
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0\"/>\n");
        out.print(this.getCssHtml());
        out.print(getScriptHtml());
        out.println("<script>");
        out.println("var Application = new TApplication();");
        out.printf("Application.device = '%s';\n", form.getClient().getDevice());
        out.printf("Application.bottom = '%s';\n", getFooter().getId());
        String msg = form.getParam("message", "");
        msg = msg == null ? "" : msg.replaceAll("\r\n", "<br/>");
        out.printf("Application.message = '%s';\n", msg);
        out.println("$(document).ready(function() {");
        out.println("Application.init();");
        out.println("});");
        out.println("</script>");
        out.println("</head>");
        outBody(out);
        out.println("</html>");
    }
}
