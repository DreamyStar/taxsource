package com.zhidisoft.taxpayer.servlet;

import com.zhidisoft.manage.dao.TaxPayerDao;
import com.zhidisoft.manage.entity.TaxPayer;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.FormatDate;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
@WebServlet("/editTaxPayerServlet.do")
public class EditTaxPayerServlet extends HttpServlet {
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
        HttpSession session = req.getSession();
        TaxPayer payer = new TaxPayer();
        String id = req.getParameter("id");
        payer.setId(Integer.parseInt(id));
        String payerCode = req.getParameter("payerCode");
        payer.setPayerCode(payerCode);
        String payerName = req.getParameter("payerName");
        payer.setPayerName(payerName);
        String bizAddress = req.getParameter("bizAddress");
        payer.setBizAddress(bizAddress);
        String taxOrganId = req.getParameter("taxOrganId");
        if ("-1".equals(taxOrganId)){
            payer.setTaxOrganId(null);
        }else {
            payer.setTaxOrganId(Integer.parseInt(taxOrganId));
        }
        String industryId = req.getParameter("industryId");
        if ("-1".equals(industryId)){
            payer.setIndustryId(null);
        }else {
            payer.setIndustryId(Integer.parseInt(industryId));
        }
        String bizScope = req.getParameter("bizScope");
        payer.setBizScope(bizScope);
        String invoiceType = req.getParameter("invoiceType");
        payer.setInvoiceType(invoiceType);
        String legalPerson = req.getParameter("legalPerson");
        payer.setLegalPerson(legalPerson);
        String legalIdCard = req.getParameter("legalIdCard");
        payer.setLegalIdCard(legalIdCard);
        String finaceName = req.getParameter("finaceName");
        payer.setFinaceName(finaceName);
        String finaceIdCard = req.getParameter("finaceIdCard");
        payer.setFinaceIdCard(finaceIdCard);
        String taxerName = req.getParameter("taxerName");
        payer.setTaxerName(taxerName);
        String bizAddressPhone = req.getParameter("bizAddressPhone");
        payer.setBizAddressPhone(bizAddressPhone);
        Date date = new Date();
        payer.setRecordDate(FormatDate.format(date));
        User user = (User) session.getAttribute("user");
        payer.setUserId(user.getId());

        boolean b = TaxPayerDao.updatePayer(payer, Integer.parseInt(id));
        if (b){
            json.put("success",true);
            json.put("msg","保存成功");
        }else {
            json.put("success",true);
            json.put("msg","保存失败");
        }
        out.write(json.toString());
        out.flush();
        out.close();


    }
}
