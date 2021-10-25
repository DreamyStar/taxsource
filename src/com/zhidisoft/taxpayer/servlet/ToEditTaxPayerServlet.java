package com.zhidisoft.taxpayer.servlet;

import com.zhidisoft.manage.dao.IndustryDao;
import com.zhidisoft.manage.dao.TaxOrganDao;
import com.zhidisoft.manage.dao.TaxPayerDao;
import com.zhidisoft.manage.entity.TaxPayer;
import com.zhidisoft.system.dao.TaxerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/toEditTaxPayerServlet.do")
public class ToEditTaxPayerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        TaxPayer payer = TaxPayerDao.getPayer(id);
        req.setAttribute("payer",payer);
        //获取纳税人对象对应的信息
        req.setAttribute("industry", IndustryDao.getIndustry(payer.getIndustryId()));
        req.setAttribute("organ", TaxOrganDao.getOrgan(payer.getTaxOrganId()));
        req.setAttribute("user", TaxerDao.getTaxerById(payer.getUserId()));
        req.setAttribute("organs",TaxOrganDao.getOrgans());
        req.setAttribute("industrys",IndustryDao.getIndustrys());
//        req.setAttribute("taxers",TaxerDao.getTaxers());//所有人
        req.getRequestDispatcher("/manage/jsp/editTaxPayer.jsp").forward(req,resp);





    }
}
