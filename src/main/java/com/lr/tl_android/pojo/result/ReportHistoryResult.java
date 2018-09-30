package com.lr.tl_android.pojo.result;

import com.lr.tl_android.pojo.Report;

import java.util.Date;
import java.util.List;

public class ReportHistoryResult {

    private int retCode;
    private Long total;
    private List<Report> reports;

    public ReportHistoryResult() {
    }

    private ReportHistoryResult(int retCode, List<Report> reports, Long total) {
        this.reports = reports;
        this.retCode = retCode;
        this.total = total;
    }

    public static ReportHistoryResult getInstance(int retCode, List<Report> reports, Long pageTotal) {
        return new ReportHistoryResult(retCode, reports, pageTotal);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
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
