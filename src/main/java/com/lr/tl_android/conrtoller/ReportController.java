package com.lr.tl_android.conrtoller;

import com.lr.tl_android.pojo.result.ReportHistoryResult;
import com.lr.tl_android.pojo.result.ReportResult;
import com.lr.tl_android.service.ReportService;
import com.lr.tl_android.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("report")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    @Resource
    private ReportService service;

    @PostMapping(value = "/userReport")
    public ReportResult userReport(@RequestParam(value = "reason") String reason, @RequestParam(value = "photo") MultipartFile photo, @RequestParam(value = "id") Integer uid) throws IOException {
        if (null == uid || uid < 0) {
            return ReportResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        if (photo.isEmpty()) {
            return ReportResult.getInstance(ResultCode.REPORT_IMAGE_EMPTY_FAIL);
        }
        return service.userReport(photo, uid, reason);
    }

    @RequestMapping(value = "/getUserReportHistory")
    public ReportHistoryResult getUserReportHistory(Byte state, @RequestParam(value = "id") Integer uid, Integer pageNum, Integer pageSize) {
        if (null == uid || uid < 0) {
            return ReportHistoryResult.getInstance(ResultCode.PARAMETER_ERROR, null, 0L);
        }
        if (null == pageNum || pageNum < 1) {
            pageNum = 0;
        } else {
            //从0开始
            pageNum--;
        }
        if (null == pageSize || pageSize < 0) {
            pageSize = 10;
        }
        System.out.println(pageSize + "  " + pageNum + "  " + state);
        return service.getUserReportHistory(state, uid, pageNum, pageSize);
    }

    @RequestMapping(value = "/getReportHistory")
    public ReportHistoryResult getReportHistory(Byte state, Integer pageNum, Integer pageSize) {
        if (null == pageNum || pageNum < 1) {
            pageNum = 0;
        } else {
            //从0开始
            pageNum--;
        }
        if (null == pageSize || pageSize < 0) {
            pageSize = 10;
        }
        return service.getReportHistory(state, pageNum, pageSize);
    }
}
