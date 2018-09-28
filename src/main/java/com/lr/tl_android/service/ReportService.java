package com.lr.tl_android.service;

import com.lr.tl_android.dao.ImageUploadDao;
import com.lr.tl_android.pojo.Report;
import com.lr.tl_android.pojo.result.ReportResult;
import com.lr.tl_android.utils.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ReportService {

    @Value("${image.dir}")
    private String uploadDir;

    private ImageUploadDao dao;

    public ReportResult saveImage(MultipartFile file, Integer uid) throws IOException{

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String full_address = file + suffixName;
        String filePath = uploadDir;
        File dest = new File(filePath + fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            Report report = new Report(uid, full_address, false);
            dao.saveAndFlush(report);
            return ReportResult.getInstance(ResultCode.SUCCESS);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ReportResult.getInstance(ResultCode.IMAGE_UPLOAD_FAIL);
    }
}
