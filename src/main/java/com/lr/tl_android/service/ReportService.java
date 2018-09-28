package com.lr.tl_android.service;

import com.lr.tl_android.dao.ReportDao;
import com.lr.tl_android.dao.UserDao;
import com.lr.tl_android.pojo.Report;
import com.lr.tl_android.pojo.User;
import com.lr.tl_android.pojo.result.ReportResult;
import com.lr.tl_android.utils.ResultCode;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
public class ReportService {

    private final static String uploadPath = "images/";
    @Resource
    private ReportDao dao;
    @Resource
    private UserDao userDao;

    public ReportResult userReport(MultipartFile photo, Integer uid, String reason) throws IOException {
        if (!userDao.existsById(uid)) {
            return ReportResult.getInstance(ResultCode.REPORT_USER_NOT_FOUND);
        }
        String fileName = photo.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffixName;
        Date date = new Date();
        String filePath = uploadPath + new SimpleDateFormat("yyyyMMdd").format(date) + "/" + fileName;

        String aStatic = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        File file = new File(aStatic + "/" + filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        System.out.println(file.getAbsolutePath());
        OutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = photo.getInputStream();
        IOUtils.copy(inputStream, outputStream);

        Report report = new Report(uid, filePath, (byte) 1, reason, date);
        dao.saveAndFlush(report);
        outputStream.close();
        inputStream.close();
        return ReportResult.getInstance(ResultCode.SUCCESS);

    }


}
