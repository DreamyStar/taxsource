package com.zhidisoft.taxpayer.servlet;

import com.zhidisoft.manage.dao.IndustryDao;
import com.zhidisoft.manage.dao.TaxOrganDao;
import com.zhidisoft.manage.dao.TaxPayerDao;
import com.zhidisoft.manage.entity.TaxPayer;
import com.zhidisoft.system.dao.TaxerDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getTaxPayerInfoServlet.do")
public class GetTaxPayerInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String id = req.getParameter("id");
        TaxPayer payer = TaxPayerDao.getPayer(id);
        req.setAttribute("payer",payer);
        req.setAttribute("user", TaxerDao.getTaxerById(payer.getUserId()));
        req.setAttribute("organ", TaxOrganDao.getOrgan(payer.getTaxOrganId()));
        req.setAttribute("industry", IndustryDao.getIndustry(payer.getIndustryId()));
        req.getRequestDispatcher("/manage/jsp/payerInfo.jsp").forward(req,resp);

    }
}
