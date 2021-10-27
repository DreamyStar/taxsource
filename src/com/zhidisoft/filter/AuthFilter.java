package com.zhidisoft.filter;

import com.zhidisoft.system.entity.User;

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

@WebFilter(urlPatterns = "/manage/jsp/*")
public class AuthFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("login.jsp").forward(request, response);
            System.out.println("从新登录");
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

}
