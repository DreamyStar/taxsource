package com.zhidisoft.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.zhidisoft.exception.DataAccessException;

/**
 * 数据库操作通用工具类
 *
 * @author 卢健良
 */
public class DBUtil {
    private static String driverClass;
    private static String user;
    private static String password;
    private static String url;

    static {
        Properties prop = new Properties();
        //将jdbc配置信息加载内存并且设置到properites对象中
        try {
            prop.load(DBUtil.class.getResourceAsStream("/jdbc.properties"));
            driverClass = prop.getProperty("jdbc.mysql.driverClass");
            user = prop.getProperty("jdbc.mysql.user");
            password = prop.getProperty("jdbc.mysql.password");
            url = prop.getProperty("jdbc.mysql.url");
            Class.forName(driverClass);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接的方法
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("无法获取数据库连接", e);
        }

    }

    /**
     * 关闭所有数据库资源
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 统一的查询语句
     * sql执行的sql预编译语句
     * args可变参数
     * @return 将结果返回到list集合
     */
    public static List<Map<String, String>> query(String sql, Object... args) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            //执行结果
            rs = ps.executeQuery();
            //获取到结果集元数据,封装本次查询，查询多少列，这些列名是什么
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取查询的总列树
            int count = rsmd.getColumnCount();

            while (rs.next()) {
                //创建一个Map集合，将当前行的所有的列放置到map集合中
                Map<String, String> rowsData = new HashMap<String, String>();
                for (int i = 0; i < count; i++) {
                    //根据列的索引获取列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //根据列名从结果集中获取某一列的值
                    String columnValue = rs.getString(columnLabel);

                    rowsData.put(columnLabel, columnValue);
                }
                //将当前行数据放置到list集合中
                list.add(rowsData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("执行查询语句- " + sql + " -是出现异常", e);
        } finally {
            close(rs, ps, conn);
        }

        return list;

    }

    /**
     * 根据id查询指定记录
     * @param sql 执行的sql语句
     * @param row 指定id
     * @return 返回记录结果
     */
    public static Map<String,String> queryRow(String sql,int row){
        List<Map<String, String>> result = query(sql, row);
        if(result.size()==1){
            return result.get(0);
        }
        return null;
    }


    /**
     * 根据唯一字段查询某一行是否存在，查询username是否存在
     * @param sql 需要执行的sql语句
     * @param row 指定唯一字段
     * @return 返回是否包含该字段
     */
    public static boolean queryExist(String sql,String row){
        List<Map<String, String>> result = query(sql, row);
        if(result.size()==1){
            return true;
        }
        return false;
    }




    /**
     * 查询通用方法
     *
     * @param sql -执行类似于select * from emp
     * @return
     */
    public static List<Map<String, String>> query(String sql) {
        return query(sql, new Object[]{});
    }

    /**
     * 增删改通用方法
     *
     * @param sql
     * @param args
     * @return
     */
    public static boolean update(String sql, Object... args) {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            int rows = ps.executeUpdate();
            if (rows >= 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new DataAccessException("执行更新语句- " + sql + " -出现错误", e);
        } finally {
            close(null, ps, conn);
        }

        return false;
    }


    /**
     * 增删改通用方法
     *
     * @param sql
     * @return
     */
    public static boolean update(String sql) {
        return update(sql, new Object[]{});
    }

    /**
     * 执行插入操作,插入成功返回该记录的id
     *
     * @param sql
     * @param args
     * @return
     */
    public static Integer insert(String sql, Object... args) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //创建预处理语句对象，同时指定该语句执行完毕返回数据库生成的主键值
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            //执行sql语句 返回受影响的行数
            int rows = ps.executeUpdate();
            //如果插入成功
            if (rows > 0) {
                //获取生成的主键,生成的主键存放到结果集中
                rs = ps.getGeneratedKeys();
                //从结果集中获取生成的主键
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }

        return null;
    }

    /**
     * 添加操作 根据对象添加
     *
     * @param obj   需要添加到数据库对应的对象
     * @param table 表名
     * @return 返回受影响的行数
     */
    public static int add(Object obj, String table) {
        Connection conn = getConnection();
        Class<?> cls = obj.getClass();
        Field[] fies = cls.getDeclaredFields();
        Statement st = null;
        StringBuilder sql = new StringBuilder("insert into " + table + "(");
        int rows = 0;
        try {
            st = conn.createStatement();
            for (Field f : fies) {
                f.setAccessible(true);
                sql.append(f.getName() + ",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(") values (");
            for (Field f : fies) {
                f.setAccessible(true);
                try {
                    if (f.getType() == String.class) {
                        if (f.get(obj) != null) {
                            sql.append("'" + f.get(obj).toString() + "',");
                        } else {
                            sql.append("null,");
                        }

                    } else {
                        if (f.get(obj) != null) {
                            sql.append(f.get(obj).toString() + ",");
                        } else {
                            sql.append("null,");
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
            rows = st.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, st, conn);
        }
        return rows;
    }

    /**
     * 获取添加记录的id
     * @param obj 添加的对象
     * @param table 表名
     * @return 返回添加记录的id，如果添加失败返回-1
     */
    public static int getAddId(Object obj,String table) {
        Connection conn = getConnection();
        Class<?> cls = obj.getClass();
        Field [] fies = cls.getDeclaredFields();
        Statement st = null;
        StringBuilder sql = new StringBuilder("insert into "+table+"(");
        int rows = 0;
        int id = -1;
        try {
            st = conn.createStatement();
            for(Field f:fies){
                f.setAccessible(true);
                sql.append(f.getName()+",");
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(") values (");
            for(Field f:fies){
                f.setAccessible(true);
                try {
                    if(f.getType()==String.class){
                        if(f.get(obj)!=null){
                            sql.append("'"+f.get(obj).toString()+"',");
                        }else{
                            sql.append("null,");
                        }

                    }else{
                        if(f.get(obj)!=null){
                            sql.append(f.get(obj).toString()+",");
                        }else{
                            sql.append("null,");
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(")");
            rows = st.executeUpdate(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            if(rows>0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(null,st,conn);
        }
        return id;
    }

    /**
     * 更新
     * @param obj 需要添加的对象
     * @param table 表名
     * @return 受影响的行数
     */
    public static int edit(Object obj,String table,Integer id){
        Connection conn = getConnection();
        Class cls = obj.getClass();
        Field [] fies = cls.getDeclaredFields();
        Statement st = null;
        StringBuilder sql = new StringBuilder("update  "+table+" set ");
        int row = 0;
        try {
            st = conn.createStatement();
            for(Field f:fies){
                f.setAccessible(true);
                try {
                    if(f.getType()==String.class){
                        if(f.get(obj)!=null){
                            sql.append(f.getName()+"='"+f.get(obj)+"',");
                        }else{
                            sql.append(f.getName()+"=null"+",");
                        }
                    }else{
                        if(f.get(obj)!=null){
                            sql.append(f.getName()+"="+f.get(obj)+",");
                        }else{
                            sql.append(f.getName()+"=null"+",");
                        }
                    }

                } catch (IllegalArgumentException | IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(" where id = "+id);
            row = st.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(null,st,conn);
        }
        return row;
    }

}
