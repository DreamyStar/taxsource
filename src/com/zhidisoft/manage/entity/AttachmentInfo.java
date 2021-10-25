package com.zhidisoft.manage.entity;

import java.util.Date;

public class AttachmentInfo {
    private Integer id;
    private Integer taskId;         //任务编号
    private String illustrate;   //说明
    private String url;
    private Date recordDate;        //记录日期
    private Integer  recordUserId;

    public AttachmentInfo() {
    }

    public AttachmentInfo(Integer id, Integer taskId, String illustrate, String url, Date recordDate, Integer recordUserId) {
        this.id = id;
        this.taskId = taskId;
        this.illustrate = illustrate;
        this.url = url;
        this.recordDate = recordDate;
        this.recordUserId = recordUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getRecordUserId() {
        return recordUserId;
    }

    public void setRecordUserId(Integer recordUserId) {
        this.recordUserId = recordUserId;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", illustrate='" + illustrate + '\'' +
                ", url='" + url + '\'' +
                ", recordDate=" + recordDate +
                ", recordUserId=" + recordUserId +
                '}';
    }
}
