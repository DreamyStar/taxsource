package com.zhidisoft.Taxer.servlet;

import com.zhidisoft.manage.dao.TaxOrganDao;
import com.zhidisoft.system.dao.TaxerDao;
import com.zhidisoft.system.entity.Taxer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/toEditTaxerServlet.do")
public class ToEditTaxerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Taxer taxer = TaxerDao.getTaxerById(Integer.parseInt(id));
        req.setAttribute("taxer",taxer);
        req.setAttribute("organs", TaxOrganDao.getOrgans());
        req.getRequestDispatcher("/manage/jsp/editTaxer.jsp").forward(req,resp);
    }
}
