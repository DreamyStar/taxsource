package com.zhidisoft.manage.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.zhidisoft.manage.entity.TaxSource;
import com.zhidisoft.util.DBUtil;
import com.zhidisoft.util.Expression;
import com.zhidisoft.util.QueryCriteria;

public class TaxSourceDao {

	private static TaxSourceDao instance;
	private TaxSourceDao(){}
	public static TaxSourceDao getinstance(){
		if(instance==null){
			instance = new TaxSourceDao();
		}
		return instance;
	}
	/**
	 * 删除任务
	 * @param id 指定id
	 * @return
	 */
	public static boolean deleteTask(String id){
		boolean row = DBUtil.update("update tb_tax_source set removeState =1 where id=?",id);
		return row;
	}

	/**
	 * 获得查询后的任务集
	 * @param pageNum 页
	 * @param pageSize 页面包含的记录数
	 * @param params 查询参数
	 * @return 任务集合
	 */
	public static List<Map<String, String>> getSearchTasks(int pageNum,int pageSize,Map<String, String> params){
		QueryCriteria qc = new QueryCriteria();
		String payerCode = params.get("payerCode");
		String payerName = params.get("payerName");
		String subOrganId = params.get("subOrganId");
		String startDate = params.get("startDate");
		String endDate = params.get("endDate");
		String industryId = params.get("industryId");
		String sql="select s.*,p.* from tb_tax_source s join tb_tax_payer p join tb_tax_organ o join tb_industry i on s.payerId=p.id and s.subOrganId=o.id and p.industryId=i.id where ";
		qc.add(Expression.eq("payerCode", payerCode)).add(Expression.like("payerName", payerName)).add(Expression.eq("subOrganId", subOrganId));
		if(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate))
			qc.add(Expression.between("s.executeTime", startDate,endDate));
		qc.add(Expression.eq("industryId", industryId));
		qc.add(Expression.eq("s.removeState", "0"));
		sql = sql+qc.toString();
		sql = sql+" limit ?,?";
		List<Map<String, String>> list= DBUtil.query(sql,(pageNum-1)*pageSize,pageSize);
		return list;
	}
	/**
	 * 添加任务
	 * @param task 任务对象
	 * @return 是否添加成功
	 */
	public static boolean addTask(TaxSource task){
		int row = DBUtil.add(task, "tb_tax_source");
		return row == 1;
	}
	/**
	 * 更新任务
	 * @param task 任务对象
	 * @param id 执行更新id
	 * @return 是否更新成功
	 */
	public static boolean update(TaxSource task,Integer id){
		int row = DBUtil.edit(task, "tb_tax_source", id);
		return row == 1;
	}
	/**
	 * 获取所有记录数
	 * @return 记录总数
	 */
	public static int getTotalRows(){
		int totalRows = DBUtil.query("select * from tb_tax_source s join tb_tax_payer p join tb_tax_organ o join tb_industry i on s.payerId=p.id and p.taxOrganId=o.id and p.industryId=i.id").size();
		return totalRows;
	}
	/**
	 * 获取查询行
	 * @param params 查询参数
	 * @return 查询总记录数
	 */
	public static int getSearchRows(Map<String, String> params){
		QueryCriteria qc = new QueryCriteria();
		String payerCode = params.get("payerCode");
		String payerName = params.get("payerName");
		String subOrganId = params.get("subOrganId");
		String startDate = params.get("startDate");
		String endDate = params.get("endDate");
		String industryId = params.get("industryId");
		String sql="select * from tb_tax_source s join tb_tax_payer p join tb_tax_organ o join tb_industry i on s.payerId=p.id and s.subOrganId=o.id and p.industryId=i.id where ";
		qc.add(Expression.eq("payerCode", payerCode)).add(Expression.like("payerName", payerName)).add(Expression.eq("subOrganId", subOrganId));
		qc.add(Expression.between("recordTaskDate", startDate, endDate)).add(Expression.eq("industryId", industryId)).add(Expression.eq("s.removeState", "0"));
		sql = sql+qc.toString();
		List<Map<String, String>> list= DBUtil.query(sql);
		return list.size();
	}
	/**
	 * 根据id获取任务对象
	 * @param id 任务对象id
	 * @return 任务对象
	 */
	public static TaxSource getTask(int id) {
		Map<String ,String> row = DBUtil.queryRow("select * from tb_tax_source where id = ?", id);
		TaxSource task = new TaxSource();
		try {
			BeanUtils.populate(task, row);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return task;
	}
	/**
	 * 根据id修改 任务对象
	 * @param task 表单中数据修改后的创建的任务对象
	 * @param id 指定id
	 * @return 修改是否成功
	 */
	public static boolean edit(TaxSource task,Integer id) {
		int rows = DBUtil.edit(task, "tb_tax_source", id);
		return rows==1;
	}
	/**
	 * 获得添加任务的id
	 * @param task 表单中的数据创建的任务对象
	 * @return 添加后的任务对象的id
	 */
	public static int getId(TaxSource task) {
		return DBUtil.getAddId(task, "tb_tax_source");
	}


	/**
	 * 通过纳税人识别号获取纳税人对象
	 * @param payerId 纳税人识别号
	 * @return 纳税人对象
	 */
	public static boolean getSourceByPlayer(String payerId){
		List<Map<String, String>> list = DBUtil.query("select * from tb_tax_source where payerId = ?", payerId);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
}
