package com.lr.tl_android.pojo.result;

import com.lr.tl_android.pojo.User;

public class UserResult {
    private Integer retCode;
    private User user;

    public UserResult() {
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserResult(Integer retCode, User user) {
        this.retCode = retCode;
        this.user = user;
    }

    public static UserResult getInstance(int retCode, User user) {
        return new UserResult(retCode, user);
    }
}
