package com.lr.tl_android.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.util.UUID;

public class test {
    private final static String uploadPath = "./images/";

    public static void main(String[] args) throws IOException {
        File file = new File("./images/1.jpg");


        String fileName = file.getName();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        String filePath = uploadPath + fileName;

        File saveFile = new File(filePath);
        if(!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        OutputStream outputStream = new FileOutputStream(saveFile);
        IOUtils.copy(new FileInputStream(file), outputStream);

        System.out.println(fileName);
    }

}
