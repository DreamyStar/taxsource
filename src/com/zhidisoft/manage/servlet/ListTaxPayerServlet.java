package com.zhidisoft.manage.servlet;

import com.zhidisoft.manage.dao.TaxPayerDao;
import com.zhidisoft.manage.entity.TaxPayer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/listTaxPayerServlet")
public class ListTaxPayerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int pageNum = Integer.parseInt(req.getParameter("pageNum"));
//        int  size = Integer.parseInt(req.getParameter("size"));
//        PrintWriter out = resp.getWriter();
//
//        TaxPayerDao taxPayerDao = new TaxPayerDao();
//        List<TaxPayer> list = taxPayerDao.selectPayers(pageNum, size);
//        Map<Object, Object> map = new HashMap<>();
//        req.getSession().setAttribute("list",list);
//        map.put("rows",list);
//        map.put("tatal",taxPayerDao.count());
//        out.write(JSONObject.fromObject(map).toString());
//        out.flush();
//        out.close();
        // 设置响应字符编码，类型，创建json对象
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();
        // 获取参数 pageNum, pageSize,totalRows
        /**
         * easyUI传递过来的参数page，size 请求传输过去的数据total ，rows
         */
        int pageNum = 1;
        if (req.getParameter("page") != null
                && !req.getParameter("page").isEmpty()) {
            pageNum = Integer.parseInt(req.getParameter("page"));
        }
        int pageSize = 10;
        if (req.getParameter("rows") != null
                && !req.getParameter("rows").isEmpty()) {
            pageSize = Integer.parseInt(req.getParameter("rows"));
        }

        String payerName = req.getParameter("payerName");
        String payerCode = req.getParameter("payerCode");
        // 传递参数
        TaxPayerDao tpd = new TaxPayerDao();
        List<Map<String, String>> result = tpd.getSearchResult(pageNum,
                pageSize, payerCode, payerName);
        JSONArray jsonArray = new JSONArray();
        for (Map<String, String> map : result) {
            jsonArray.add(map);
        }
        int totalRows = tpd.getSearchRows(payerCode, payerName);
        json.put("total", totalRows);
        json.put("rows", jsonArray);
        out.print(json);
        out.flush();
        out.close();

    }
}
