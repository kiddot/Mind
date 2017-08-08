package com.android.common.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kiddo on 17-8-8.
 */
@Entity
public class Task {
    private String startDate;
    private String content;
    private String endDate;
    private String sender;
    private String receiver;
    public String getReceiver() {
        return this.receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getSender() {
        return this.sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getStartDate() {
        return this.startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    @Generated(hash = 1445057552)
    public Task(String startDate, String content, String endDate, String sender,
            String receiver) {
        this.startDate = startDate;
        this.content = content;
        this.endDate = endDate;
        this.sender = sender;
        this.receiver = receiver;
    }
    @Generated(hash = 733837707)
    public Task() {
    }
}
