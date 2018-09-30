package com.lr.tl_android.pojo.result;

public class ReportResult {
    private int retCode;

    public ReportResult(){}

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    private ReportResult(int retCode) {
        this.retCode = retCode;
    }

    public static ReportResult getInstance(int retCode) {
        return new ReportResult(retCode);
    }
}
