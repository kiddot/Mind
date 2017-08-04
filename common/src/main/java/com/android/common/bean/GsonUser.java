package com.android.common.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kiddo on 17-8-4.
 */

public class GsonUser {
    /**
     * code : 200
     * message : 获取用户信息成功
     * data : {"username":"kiddo","password":"123345","sex":"nana","email":"askldj","phone":"sdjhl"}
     */

    private int code;
    private String message;
    @SerializedName("data")
    @Expose
    private User user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
