package com.zhidisoft.Taxer.servlet;

import com.zhidisoft.manage.dao.TaxPayerDao;
import com.zhidisoft.system.dao.TaxerDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
@WebServlet("/listTaxerServlet.do")
public class ListTaxerServlet extends HttpServlet {
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

        int pageNum = 1;
        if (req.getParameter("page") != null && !req.getParameter("page").isEmpty()){
            pageNum = Integer.parseInt(req.getParameter("page"));
        }
        int pageSize = 10;
        if (req.getParameter("rows")!=null && !req.getParameter("rows").isEmpty()){
            pageSize = Integer.parseInt(req.getParameter("rows"));
        }
        String taxerName = req.getParameter("taxerName");
        List<Map<String, String>> result = TaxerDao.getSearchResult(pageNum, pageSize, taxerName);
        JSONArray jsonArray = new JSONArray();
        for(Map<String,String> map : result){
            jsonArray.add(map);
        }
        int totalRows = TaxerDao.getSearchRows(taxerName);
        json.put("total",totalRows);
        json.put("rows",jsonArray);
        out.write(json.toString());
        out.flush();
        out.close();

    }
}
