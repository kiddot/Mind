package com.android.common.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kiddo on 17-8-8.
 */
@Entity
public class Task {
    private String title;
    private String startDate;
    private String content;
    private String endDate;
    private String sender;
    private String receiver;
    private String teamName;
    private int priority;
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
    public String getTeamName() {
        return this.teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    @Generated(hash = 1022503555)
    public Task(String title, String startDate, String content, String endDate,
            String sender, String receiver, String teamName, int priority) {
        this.title = title;
        this.startDate = startDate;
        this.content = content;
        this.endDate = endDate;
        this.sender = sender;
        this.receiver = receiver;
        this.teamName = teamName;
        this.priority = priority;
    }
    @Generated(hash = 733837707)
    public Task() {
    }
}
