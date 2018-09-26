package com.lr.tl_android.dao;

import com.lr.tl_android.pojo.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDownloadDao extends JpaRepository<Report, Integer> {
}
