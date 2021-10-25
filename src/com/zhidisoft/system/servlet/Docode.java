package com.zhidisoft.system.servlet;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/doc.do")
public class Docode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(120, 50);
        String code = circleCaptcha.getCode();
        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("sum",0);
        servletContext.setAttribute("ycode",code);
        ServletOutputStream outputStream = resp.getOutputStream();
        circleCaptcha.write(outputStream);
        outputStream.close();
    }
}
