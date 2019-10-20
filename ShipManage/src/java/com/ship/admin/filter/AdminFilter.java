/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.admin.filter;

import com.ship.model.*;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 管理员目录过滤器 当用户没有登录时跳转到登录页面
 *
 * @author wy105
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*", "/Admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        /*
         * 包含初始化Filter时需要执行的代码，该代码执行一次
         * */
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;//将request强制转换为HttpServletRequest类型
        HttpServletResponse res = (HttpServletResponse) response;//将response强制转换为HttpServletResponse类型

        HttpSession session = req.getSession();//获得一个Session对象，用于存放一些提示信息返回到前台或者获取Session对象中的信息
        //从session中取出user对象（该对象在登录成功后放入session）
        Manager manager = (Manager) session.getAttribute("manager");
        //如果用户登录成功并且登录用户为管理员类型，将继续执行用户请求操作，否则返回越权操作或者未登录提示信息并跳转到系统首页或者登录页面
        if (manager != null) {
            chain.doFilter(req, res);//继续执行用户请求的操作		
        } else {
            //未登录成功将返回用户没有登录提示信息并跳转到系统登录页面
            session.setAttribute("message", "对不起，只有登录后才能访问管理员系统！");
            res.sendRedirect(req.getContextPath() + "/Login.jsp");
        }
    }

    @Override
    public void destroy() {
        /*
         * 包含资源释放的代码，通常对init()中的初始化的资源执行收尾工作；
         * */
    }

}
