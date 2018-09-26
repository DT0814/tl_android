package com.lr.tl_android.dao;

import com.lr.tl_android.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {
}
