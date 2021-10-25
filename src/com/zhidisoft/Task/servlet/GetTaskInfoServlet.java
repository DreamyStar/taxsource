package com.zhidisoft.Task.servlet;

import com.zhidisoft.manage.dao.IndustryDao;
import com.zhidisoft.manage.dao.TaxOrganDao;
import com.zhidisoft.manage.dao.TaxPayerDao;
import com.zhidisoft.manage.dao.TaxSourceDao;
import com.zhidisoft.manage.entity.TaxPayer;
import com.zhidisoft.manage.entity.TaxSource;
import com.zhidisoft.system.dao.TaxerDao;
import com.zhidisoft.system.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/getTaskInfoServlet.do")
public class GetTaskInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        TaxSource task = TaxSourceDao.getTask(Integer.parseInt(id));
        TaxPayer payer = TaxPayerDao.getPayer(task.getPayerId().toString());
        req.setAttribute("task",task);
        req.setAttribute("payer",payer);
        req.setAttribute("organ", TaxOrganDao.getOrgan(payer.getTaxOrganId()));
        req.setAttribute("industry", IndustryDao.getIndustry(payer.getIndustryId()));
        req.setAttribute("subOrgan",TaxOrganDao.getOrgan(task.getSubOrganId()));
        req.setAttribute("user", UserDao.getUserById(payer.getUserId()));
        req.setAttribute("executeTaxer", TaxerDao.getTaxerById(task.getExecuteId()));
        req.setAttribute("approverTaxer",TaxerDao.getTaxerById(task.getApproverId()));
        req.setAttribute("taskUser",UserDao.getUserById(task.getRecordUserId()));
        req.getRequestDispatcher("/manage/jsp/taskInfo.jsp").forward(req,resp);
    }
}
