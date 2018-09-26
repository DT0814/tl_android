package com.lr.tl_android.pojo.result;

public class UserTokenResult {
    private int retCode;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public UserTokenResult(int retCode) {
        this.retCode = retCode;
    }

    public static UserTokenResult getInstance(int retCode) {
        return new UserTokenResult(retCode);
    }

}
