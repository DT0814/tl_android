package com.lr.tl_android.service;

import com.lr.tl_android.dao.ReportDao;
import com.lr.tl_android.pojo.Report;
import com.lr.tl_android.pojo.result.ReportResult;
import com.lr.tl_android.utils.ResultCode;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class ReportService {

    private final static String uploadPath = "../images/";
    @Resource
    private ReportDao dao;


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

        Report report = new Report(uid, filePath, false, 1);
        //Log.debug();
        System.out.println(uid + "*******************************" + filePath);
        dao.saveAndFlush(report);


        return ReportResult.getInstance(ResultCode.SUCCESS);

    }



}
