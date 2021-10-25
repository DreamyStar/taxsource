package com.zhidisoft.manage.dao;

import com.zhidisoft.manage.entity.AttachmentInfo;
import com.zhidisoft.util.DBUtil;

public class AttachmentInfoDao {
    private static AttachmentInfoDao instance;
    private AttachmentInfoDao(){}
    public static AttachmentInfoDao getInstance(){
        if (instance == null){
            instance = new AttachmentInfoDao();
        }
        return instance;
    }
    public static boolean add(AttachmentInfo attachment){
//        DBUtil.add()
        return true;
    }
}
