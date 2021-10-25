package com.zhidisoft.system.servlet;

import com.google.code.kaptcha.Constants;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkCaptchaServlet")
public class CheckCaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        JSONObject json = new JSONObject();
        PrintWriter out = resp.getWriter();
        String code = req.getParameter("captcha");
        String captcha = req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        if (code.equals(captcha)){
            json.put("success",true);
            json.put("msg","");
        }else{
            json.put("success",false);
            json.put("msg","请输入正确的验证码");
        }
        out.write(json.toString());
        out.flush();
        out.close();
    }
}
