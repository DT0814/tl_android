package com.lr.tl_android.pojo.result;

import com.lr.tl_android.pojo.Report;

import java.util.Date;
import java.util.List;

public class ReportHistoryResult {

    private int retCode;
    private List<Report> reports;

    public ReportHistoryResult() {
    }

    private ReportHistoryResult(int retCode, List<Report> reports) {
        this.reports = reports;
        this.retCode = retCode;
    }

    public static ReportHistoryResult getInstance(int retCode, List<Report> reports) {
        return new ReportHistoryResult(retCode, reports);
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
