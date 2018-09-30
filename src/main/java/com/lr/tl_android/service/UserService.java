package com.lr.tl_android.service;

import com.lr.tl_android.dao.UserDao;
import com.lr.tl_android.pojo.User;
import com.lr.tl_android.pojo.result.SimpleResult;
import com.lr.tl_android.pojo.result.UserResult;
import com.lr.tl_android.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserDao dao;

    @Transactional
    public SimpleResult update(Integer uid, String uinfo) {
        if (!dao.existsById(uid)) {
            return SimpleResult.getInstance(ResultCode.USER_NOT_FOUND);
        }
        User one = dao.getOne(uid);
        one.setUinfo(uinfo);

        dao.saveAndFlush(one);
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    @Transactional
    public SimpleResult init(Integer uid, String uinfo) {
        if (dao.existsById(uid)) {
            return SimpleResult.getInstance(ResultCode.USER_EXISTS);
        }
        User user = new User(uid, uinfo);
        user.setValue(0);
        System.out.println(user);
        dao.saveAndFlush(user);
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    public UserResult getById(Integer uid) {
        User one = dao.getOne(uid);
        return UserResult.getInstance(ResultCode.SUCCESS, one);
    }

}
