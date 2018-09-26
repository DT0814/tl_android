package com.lr.tl_android.service;

import com.lr.tl_android.dao.UserDao;
import com.lr.tl_android.pojo.User;
import com.lr.tl_android.pojo.result.UserResult;
import com.lr.tl_android.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao dao;

    public UserResult getById(Integer uid) {
        User one = dao.getOne(uid);
        return UserResult.getInstance(ResultCode.SUCCESS, one);
    }
}
