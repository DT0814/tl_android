package com.lr.tl_android.conrtoller;

import com.lr.tl_android.pojo.result.ReportResult;
import com.lr.tl_android.service.ReportService;
import com.lr.tl_android.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private ReportService service;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ReportResult uploadImage(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "id") Integer uid) throws IOException {
        if (file.isEmpty()) {
            return ReportResult.getInstance(ResultCode.IMAGE_EMPTY_FAIL);
        }
        return service.saveImage(file, uid);
    }
}
