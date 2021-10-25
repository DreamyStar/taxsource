package com.zhidisoft.taxpayer.servlet;

import com.zhidisoft.manage.dao.TaxSourceDao;
import com.zhidisoft.manage.entity.TaxSource;
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

@WebServlet("/addTaskServlet.do")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应参数，获取
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        JSONObject json = new JSONObject();
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        // 获取参数，创建任务对象，未选的下拉框判断值是否为-1
        TaxSource task = new TaxSource();
        String payerId = req.getParameter("payerId");
        if (payerId == null || payerId.length() == 0) {
            task.setPayerId(null);
        } else {
            task.setPayerId(Integer.parseInt(payerId));
        }

        String taskName = req.getParameter("taskName");
        task.setTaskName(taskName);
        int subOrganId = Integer.parseInt(req.getParameter("subOrganId"));
        if ("-1".equals(subOrganId)) {
            task.setSubOrganId(null);
        } else {
            task.setSubOrganId(subOrganId);
        }

        String approverId = req.getParameter("approverId");
        if ("-1".equals(approverId)) {
            task.setApproverId(null);
        } else {
            task.setApproverId(Integer.parseInt(approverId));
        }
        String executeId = req.getParameter("executeId");
        if ("-1".equals(executeId)) {
            task.setExecuteId(null);
        } else {
            task.setExecuteId(Integer.parseInt(executeId));
        }
        String executeTime = req.getParameter("executeTime");
        task.setExecuteTime(executeTime);
        String taskState = req.getParameter("taskState");
        task.setTaskState(taskState);

        User user = (User) session.getAttribute("user");
        Integer recordUserId = user.getId();
        task.setRecordUserId(recordUserId);
        Date date = new Date();
        task.setRecordTaskDate(FormatDate.format(date));
        // 获取插入的任务id，如果返回-1，则插入失败
        int id = TaxSourceDao.getId(task);
        if (id != -1) {
            json.put("success", true);
            json.put("id", id);
            json.put("msg", "添加任务成功");
        } else {
            json.put("success", false);
            json.put("msg", "添加失败");
        }
        out.print(json);
        out.flush();
        out.close();
    }
}
