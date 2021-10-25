package com.zhidisoft.payer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.IndustryDao;
import com.zhidisoft.manage.dao.TaxOrganDao;

@WebServlet("/toAddTaxerServlet.do")
public class ToAddTaxerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("organs", TaxOrganDao.getOrgans());
		req.setAttribute("industrys", IndustryDao.getIndustrys());
		req.getRequestDispatcher("/manage/jsp/addTaxer.jsp").forward(req,
				resp);
	}
}
