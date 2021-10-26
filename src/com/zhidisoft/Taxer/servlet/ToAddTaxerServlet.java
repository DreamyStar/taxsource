package com.zhidisoft.Taxer.servlet;

import com.zhidisoft.manage.dao.TaxOrganDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/toAddTaxerServlet.do")
public class ToAddTaxerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("organs", TaxOrganDao.getOrgans());
        req.getRequestDispatcher("/manage/jsp/addTaxer.jsp").forward(req,resp);

    }
}
