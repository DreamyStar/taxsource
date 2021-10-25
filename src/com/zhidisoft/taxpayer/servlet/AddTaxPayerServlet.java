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
@WebServlet("/addTaxPayerServlet.do")
public class AddTaxPayerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置响应字符编码，类型，创建json对象
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();
        HttpSession session = req.getSession();
        // 建立对象，获取和设置参数，对于和外键相连的字段判断是否有选择
        TaxPayer payer = new TaxPayer();
        String payerCode = req.getParameter("payerCode");
        payer.setPayerCode(payerCode);
        String payerName = req.getParameter("payerName");
        payer.setPayerName(payerName);
        String bizAddress = req.getParameter("bizAddress");
        payer.setBizAddress(bizAddress);
        String taxOrganId = req.getParameter("taxOrganId");
        if ("-1".equals(taxOrganId)) {
            payer.setTaxOrganId(null);
        } else {
            payer.setTaxOrganId(Integer.parseInt(taxOrganId));
        }

        String industryId = req.getParameter("industryId");
        if ("-1".equals(industryId)) {
            payer.setIndustryId(null);
        } else {
            payer.setIndustryId(Integer.parseInt(industryId));
        }
        //办税专员添加
//		String industryId = req.getParameter("industryId");
//		if ("-1".equals(industryId)) {
//			payer.setIndustryId(null);
//		} else {
//			payer.setIndustryId(Integer.parseInt(industryId));
//		}
        String bizScope = req.getParameter("bizScope");
        payer.setBizScope(bizScope);
        String invoiceType = req.getParameter("invoiceType");
        payer.setInvoiceType(invoiceType);
        String legalPerson = req.getParameter("legalPerson");
        payer.setLegalPerson(legalPerson);
        String legalIdCard = req.getParameter("legalIdCard");
        payer.setLegalIdCard(legalIdCard);
        String legalIdCardImageURL = req.getParameter("legalIdCardImageURL");
        payer.setLegalIdCardImageURL(legalIdCardImageURL);
        String finaceName = req.getParameter("finaceName");
        payer.setFinaceName(finaceName);
        String finaceIdCard = req.getParameter("finaceIdCard");
        payer.setFinaceIdCard(finaceIdCard);
        String finaceIdCardImageURL = req.getParameter("finaceIdCardImageURL");
        payer.setFinaceIdCardImageURL(finaceIdCardImageURL);
        String taxerName = req.getParameter("taxerName");
        payer.setTaxerName(taxerName);
        String bizAddressPhone = req.getParameter("bizAddressPhone");
        payer.setBizAddressPhone(bizAddressPhone);
        Date date = new Date();
        payer.setRecordDate(FormatDate.format(date));
        User user = (User) session.getAttribute("user");
        payer.setUserId(user.getId());
        // 保存
        TaxPayerDao tpd = new TaxPayerDao();
        boolean b = tpd.addPayer(payer);
        if (b) {
            json.put("success", true);
            json.put("msg", "添加成功");
        } else {
            json.put("success", false);
            json.put("msg", "添加失败");
        }
        out.print(json);
        out.flush();
        out.close();
    }

}
