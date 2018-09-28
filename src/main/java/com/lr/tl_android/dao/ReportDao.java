package com.lr.tl_android.dao;

import com.lr.tl_android.pojo.Report;
import com.lr.tl_android.pojo.result.ReportResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReportDao extends JpaRepository<Report, Integer> {

}
