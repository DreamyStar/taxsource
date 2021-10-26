package com.zhidisoft.test;

import com.zhidisoft.manage.dao.TaxPayerDao;
import com.zhidisoft.manage.entity.TaxPayer;
import com.zhidisoft.system.dao.TaxerDao;
import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.Taxer;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.EncryptUtil;

import java.util.List;
import java.util.Map;


public class Test {
    public static void main(String[] args) throws Exception{
//        UserDao userDao = new UserDao();
//        User zg = userDao.selectName("zhangsan","aaaaa");
//        System.out.println(zg);
//        EncryptUtil en = new EncryptUtil();
//        String x = en.encryptMD5("ccccc");
//        System.out.println(x);
        //594f803b380a41396ed63dca39503542
//        TaxPayerDao to = new TaxPayerDao();
//        List<TaxPayer> taxPayers = to.selectPayers(1, 6);
//        System.out.println(taxPayers);
//        int count = to.count();
//        System.out.println(count);
//        System.out.println(to.getSearchRows("1004","小米科技"));
//        System.out.println(to.getSearchResult(1,4,"1004","小米科技"));
//        System.out.println(to.getTotalRows());
//        Taxer id = TaxerDao.getTaxerByUserId(3);
//        System.out.println(id);
//        TaxPayer payer = TaxPayerDao.getPayer("101");
//        System.out.println(payer);
//        Taxer taxerById = TaxerDao.getTaxerById(2);
//        System.out.println(taxerById);
//        boolean b = TaxerDao.deleteUser(3);
//        System.out.println(b);
//        System.out.println("aaaaaaaaaaaaaaa");
        User user = UserDao.selectName("zhangsan", "df483402b9bfeb234717a32c6e86280e");
        System.out.println(user);


    }
}
