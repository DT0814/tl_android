package com.lr.tl_android.conrtoller;

import com.lr.tl_android.pojo.result.UploadResult;
import com.lr.tl_android.service.ImageUploadService;
import com.lr.tl_android.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ImageUploadController {
    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
    private ImageUploadService service;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public  UploadResult uploadImage(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "id") Integer uid) throws IOException {
        if (file.isEmpty()) {
            return UploadResult.getInstance(ResultCode.EMPTY_FILE);
        }

        return service.saveImage(file, uid);
    }
}
