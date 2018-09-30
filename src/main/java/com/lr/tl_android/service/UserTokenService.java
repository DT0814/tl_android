package com.lr.tl_android.service;


import com.lr.tl_android.dao.UserDao;
import com.lr.tl_android.dao.UserTokenDao;
import com.lr.tl_android.pojo.User;
import com.lr.tl_android.pojo.UserToken;
import com.lr.tl_android.pojo.result.TokenQueryHistoryResult;
import com.lr.tl_android.pojo.result.UserTokenResult;
import com.lr.tl_android.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @Transactional(readOnly = true)
    public TokenQueryHistoryResult queryHistory(Integer uid, Integer pageNum, Integer pageSize) {
        if (!userDao.existsById(uid)) {
            return TokenQueryHistoryResult.getInstance(ResultCode.TOKEN_USER_NOT_FOUND, null, 0);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "time");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        UserToken userToken = new UserToken();
        userToken.setUid(uid);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("value")
                .withIgnorePaths("reason")
                .withIgnorePaths("url");

        Example<UserToken> example = Example.of(userToken, matcher);
        Page<UserToken> all = userTokenDao.findAll(example, pageable);
        return TokenQueryHistoryResult.getInstance(ResultCode.SUCCESS, all.getContent(), (int) all.getTotalElements());
    }
}
