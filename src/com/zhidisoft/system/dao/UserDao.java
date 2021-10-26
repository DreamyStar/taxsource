package com.zhidisoft.system.dao;

import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.DBUtil;
import com.zhidisoft.util.EncryptUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
//    private static Connection con = new  DBUtil().getConnection();

    public static User selectName(String username,String password){
        String sql = "select * from tb_user where username=? and password=?";
        List<Map<String, String>> list = DBUtil.query(sql, username, password);
        User user = null;
        for (Map<String, String> map: list){
            user = new User(Integer.parseInt(map.get("id")),map.get("username"),map.get("password"),
                    Integer.parseInt(map.get("taxerId")),map.get("salt"),Integer.parseInt(map.get("permissionId")),
                    Integer.parseInt(map.get("state")),map.get("email"));
        }
        return user;

    }

    /**
     * 根据username获取用户对象
     * @param username 指定username
     * @return 用户对象
     */
    public static User getUser(String username){
        Map<String, String> map = new HashMap<String, String>();

        List<Map<String, String>> list = DBUtil.query("select * from tb_user where username = ?", username);
        if(list!=null&&list.size()==1){
            map = list.get(0);
        }
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }
    /**
     *
     * @param id
     * @return
     */
    public static User getUserById(Integer id){
        Map<String, String> result = DBUtil.queryRow("select * from tb_user where id = ?", id);
        User user = new User();
        try {
            if(!result.isEmpty()&&result.size()>0){
                BeanUtils.populate(user, result);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 更新密码
     * @param username 更改密码username
     * @param oldPassword 旧的密码
     * @param newPassword 新的密码
     * @return 是否修改成功
     */
    public static boolean update(String username,String oldPassword,String newPassword){
        String uuid =null;
        String value = null;
        //通过username 拿到user对象属性
        List< Map<String, String>> result= DBUtil.query("select * from tb_user where username = ?", username);
        if(result!=null&&result.size()>0){
            //如果查询到结果 ，获取salt，password
            uuid = result.get(0).get("salt");
            value = result.get(0).get("password");
        }
        //密码uuid必须不能都是数字，加密
        String value1 = EncryptUtil.encryptMD5(oldPassword);
        //判断用户密码是否正确
        if(!value1.equals(value)){
            return false;
        }
        //对新密码加密
        String password = EncryptUtil.encryptMD5(newPassword);
        boolean row = DBUtil.update("update tb_user set password=? where username =?", password,username);
        return row;
    }


}
