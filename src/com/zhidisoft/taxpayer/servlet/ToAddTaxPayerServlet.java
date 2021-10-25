package com.zhidisoft.taxpayer.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.IndustryDao;
import com.zhidisoft.manage.dao.TaxOrganDao;

import com.zhidisoft.system.dao.TaxerDao;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.FormatDate;

@WebServlet("/toAddTaxPayerServlet.do")
public class ToAddTaxPayerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("organs", TaxOrganDao.getOrgans());
        req.setAttribute("industrys", IndustryDao.getIndustrys());
//		req.setAttribute("taxers", TaxerDao.getTaxers());//所有人

        //当前管理员对应的税务人员
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", TaxerDao.getTaxerByUserId(user.getId()));
        req.setAttribute("curDate", FormatDate.format(new Date()));
        req.getRequestDispatcher("/manage/jsp/addTaxPayer.jsp").forward(req, resp);
    }
}
