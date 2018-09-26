package com.lr.tl_android.pojo.result;

public class UploadResult {
    private int retCode;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public UploadResult(int retCode) {
        this.retCode = retCode;
    }

    public static UploadResult getInstance(int retCode) {
        return new UploadResult(retCode);
    }
}
