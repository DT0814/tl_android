package com.lr.tl_android.dao;

import com.lr.tl_android.pojo.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenDao extends JpaRepository<UserToken, Integer> {
}
