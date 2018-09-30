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

    @RequestMapping(value = "/userReportHistory")
    public ReportHistoryResult userReoprtHistory(@RequestParam(value = "id") Integer uid, Integer pageNum, Integer pageSize) {
        if (null == uid || uid < 0) {
            return ReportHistoryResult.getInstance(ResultCode.PARAMETER_ERROR,null);
        }

        return service.userReportHistory(uid, pageNum, pageSize);
    }

}
