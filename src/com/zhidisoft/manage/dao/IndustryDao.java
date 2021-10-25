package com.zhidisoft.manage.dao;

import com.zhidisoft.manage.entity.Industry;
import com.zhidisoft.util.BeanUtil;
import com.zhidisoft.util.DBUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndustryDao {

    private static IndustryDao instance;

    private IndustryDao() {
    }

    public static IndustryDao getInstance() {
        if (instance == null) {
            instance = new IndustryDao();
        }
        return instance;
    }

    /**
     * 获取所有的行业
     *
     * @return 所有的行业对象
     */

    public static List<Industry> getIndustrys() {
        List<Industry> industrys = new ArrayList<>();
        String sql = "select * from tb_industry";
        List<Map<String, String>> result = DBUtil.query(sql);
        if (result != null && result.size() > 0) {
            for (Map<String, String> map : result) {
                Industry industry = new Industry();
                try {
                    BeanUtils.populate(industry, map);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                industrys.add(industry);
            }
        }
        return industrys;
    }

    /**
     * 根据id获取行业对象
     * industryId 行业  高效能人事的七个习惯   下一本书
     * 行业对象   我看过这本书，贼好，好吊
     */
    public static Object getIndustry(Integer industryId) {
        String sql = "select * from tb_industry where id = ?";
        Map<String, String> map = DBUtil.queryRow(sql, industryId);
        Industry industry = new Industry();
        if (map != null && map.size() > 0) {
            try {
                BeanUtils.populate(industry, map);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return industry;
    }
}
