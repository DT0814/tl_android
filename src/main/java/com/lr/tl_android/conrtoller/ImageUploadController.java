package com.lr.tl_android.conrtoller;

import com.lr.tl_android.pojo.result.UploadResult;
import com.lr.tl_android.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUploadController {
    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
    @Value("${uploadDir}")
    private String uploadDir;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public  UploadResult uploadImage(@RequestParam(value = "file") MultipartFile file) throws RuntimeException {
        if (file.isEmpty()) {
            return UploadResult.getInstance(ResultCode.EMPTY_FILE);
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = uploadDir;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return UploadResult.getInstance(ResultCode.SUCCESS);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UploadResult.getInstance(ResultCode.UPLOAD_FAIL);
    }
}
