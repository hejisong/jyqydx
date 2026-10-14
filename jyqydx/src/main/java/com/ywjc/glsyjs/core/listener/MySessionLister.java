package com.ywjc.glsyjs.core.listener;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.util.Constantval;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MySessionLister implements  HttpSessionListener {

   /* @Autowired
    private UserService userService;*/

    @Override
    public void sessionCreated(HttpSessionEvent event){

        System.out.println("session创建");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 登录账户
        String username = request.getParameter("username");

        String hisUserName = session.getAttribute("userName").toString();

        List onlineUserList = (List) application.getAttribute("onlineUserList");

        if ((StringUtils.isNotBlank(hisUserName) && !hisUserName.equals(username))
                || CollectionUtils.isEmpty(onlineUserList)) {
            // 把登录账户名保存至session
            session.setAttribute("username", username);
            // 把登录账户名放入在线列表

            if (null == onlineUserList) {
                onlineUserList = new ArrayList();
                application.setAttribute("onlineUserList", onlineUserList);
            }
            onlineUserList.add(username);
        }

        System.out.println("当前在线人数：" + onlineUserList.size());

    }



    @Override
    public void sessionDestroyed(HttpSessionEvent event){//session失效
        System.out.println("session销毁");
       /* HttpSession session=se.getSession();
        Principal principal = (Principal) session.getAttribute("userinfo");
        User userinfo=principal.getUser();
        userinfo.setIsonline(Constantval.LOGIN_OFF);
        ApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        UserService userService=(UserService) context.getBean("userService");
        userService.update(userinfo);*/
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        // 登录账户
        String username = (String) session.getAttribute("username");
        // 从在线列表中删除登录账户名
        List onlineUserList = (List) application.getAttribute("onlineUserList");
        onlineUserList.remove(username);
        System.out.println(username + "退出系统");
        System.out.println("当前在线人数：" + onlineUserList.size());

    }

}
