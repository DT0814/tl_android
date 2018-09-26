package com.lr.tl_android.service;


import com.lr.tl_android.dao.UserDao;
import com.lr.tl_android.dao.UserTokenDao;
import com.lr.tl_android.pojo.User;
import com.lr.tl_android.pojo.UserToken;
import com.lr.tl_android.pojo.result.UserTokenResult;
import com.lr.tl_android.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserTokenService {
    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    public UserTokenResult update(Integer uid, Integer value, String reason, String url) {
        if (!userDao.existsById(uid)) {
            return UserTokenResult.getInstance(ResultCode.TOKEN_USER_NOT_FOUND);
        }
        User one = userDao.getOne(uid);
        one.setValue(one.getValue() + value);
        userDao.saveAndFlush(one);
        userTokenDao.saveAndFlush(new UserToken(value, uid, reason, url, new Date()));
        return UserTokenResult.getInstance(ResultCode.SUCCESS);
    }

}
