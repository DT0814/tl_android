package com.lr.tl_android.service;

import com.lr.tl_android.dao.ImageUploadDao;
import com.lr.tl_android.pojo.Report;
import com.lr.tl_android.pojo.result.ReportResult;
import com.lr.tl_android.utils.ResultCode;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class ReportService {

    private final static String uploadPath = "../images/";

    private ImageUploadDao dao;


    public ReportResult saveImage(MultipartFile file, Integer uid) throws IOException{

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        String filePath = uploadPath + fileName;

        File saveFile = new File(filePath);
        if(!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        OutputStream outputStream = new FileOutputStream(saveFile);
        IOUtils.copy(file.getInputStream(), outputStream);

        Report report = new Report(uid, filePath, false);
        dao.saveAndFlush(report);

        return ReportResult.getInstance(ResultCode.SUCCESS);

    }
}
