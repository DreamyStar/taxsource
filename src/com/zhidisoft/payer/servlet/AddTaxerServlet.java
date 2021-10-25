package com.zhidisoft.payer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.zhidisoft.system.dao.TaxerDao;
import com.zhidisoft.system.entity.Taxer;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.FormatDate;

import net.sf.json.JSONObject;

@WebServlet("/addTaxerServlet.do")
public class AddTaxerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();
		HttpSession session = req.getSession();

		Taxer taxer = new Taxer();
		String taxerCode = req.getParameter("taxerCode");
		taxer.setTaxerCode(taxerCode);
		String taxerName = req.getParameter("taxerName");
		taxer.setTaxerName(taxerName);
		String mobile = req.getParameter("mobile");
		taxer.setMobile(mobile);
		String address = req.getParameter("address");
		taxer.setAddress(address);
		String sex = req.getParameter("sex");
		taxer.setSex(sex);
		String birthday = req.getParameter("birthday");
		if(StringUtils.isNotEmpty(birthday))
			taxer.setBirthday(birthday);
		String email = req.getParameter("email");
		taxer.setEmail(email);
		String organId = req.getParameter("organId");
		if ("-1".equals(organId)) {
			taxer.setOrganId(null);
		} else {
			taxer.setOrganId(Integer.parseInt(organId));
		}

		Date date = new Date();
		taxer.setRecordDate(FormatDate.format(date));
		User user = (User) session.getAttribute("user");
		taxer.setRecordUserId(user.getId());

		boolean b = TaxerDao.addTaxer(taxer);
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
