package com.zhidisoft.Taxer.servlet;

import com.zhidisoft.manage.dao.TaxPayerDao;
import com.zhidisoft.manage.dao.TaxSourceDao;
import com.zhidisoft.system.dao.TaxerDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/deleteTaxerServlet.do")
public class DeleteTaxerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();
        String id = req.getParameter("id");
        boolean b = TaxerDao.deleteTaxer(id);
        if (b) {
            json.put("success", true);
            json.put("msg", "删除成功");
        } else {
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        out.write(json.toString());
        out.flush();
        out.close();

    }
}
