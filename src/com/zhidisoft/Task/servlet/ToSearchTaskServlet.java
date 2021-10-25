package com.zhidisoft.Task.servlet;

import com.zhidisoft.manage.dao.IndustryDao;
import com.zhidisoft.manage.dao.TaxOrganDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/toSearchTaskServlet.do")
public class ToSearchTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("organs", TaxOrganDao.getOrgans());
        req.setAttribute("industrys", IndustryDao.getIndustrys());
        req.getRequestDispatcher("/manage/jsp/searchTask.jsp").forward(req,resp);

    }
}
