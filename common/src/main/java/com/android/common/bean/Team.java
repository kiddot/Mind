package com.android.common.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kiddo on 17-9-10.
 */
@Entity
public class Team {
    private int imageId;
    private String teamName;
    private String description;
    private String teamGroup;
    public String getTeamGroup() {
        return this.teamGroup;
    }
    public void setTeamGroup(String teamGroup) {
        this.teamGroup = teamGroup;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTeamName() {
        return this.teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public int getImageId() {
        return this.imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    @Generated(hash = 1294380726)
    public Team(int imageId, String teamName, String description, String teamGroup) {
        this.imageId = imageId;
        this.teamName = teamName;
        this.description = description;
        this.teamGroup = teamGroup;
    }
    @Generated(hash = 882286361)
    public Team() {
    }

}
