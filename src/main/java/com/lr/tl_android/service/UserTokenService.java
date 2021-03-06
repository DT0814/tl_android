package com.lr.tl_android.service;


import com.lr.tl_android.dao.UserDao;
import com.lr.tl_android.dao.UserTokenDao;
import com.lr.tl_android.pojo.User;
import com.lr.tl_android.pojo.UserToken;
import com.lr.tl_android.pojo.result.SimpleResult;
import com.lr.tl_android.pojo.result.TokenQueryHistoryResult;
import com.lr.tl_android.pojo.result.TokenQueryValueResult;
import com.lr.tl_android.pojo.result.UseTokenResult;
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
    public SimpleResult init(Integer uid, Integer value, String reason) {
        if (!userDao.existsById(uid)) {
            return SimpleResult.getInstance(ResultCode.TOKEN_FROM_USER_NOT_FOUND);
        }
        User one = userDao.getOne(uid);
        one.setValue(value);
        userDao.saveAndFlush(one);
        UserToken userToken = new UserToken(value, uid, reason, "", new Date());
        userTokenDao.saveAndFlush(userToken);
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    @Transactional
    public SimpleResult tran(Integer from, Integer to, Integer value, String note) {

        if (!userDao.existsById(from)) {
            return SimpleResult.getInstance(ResultCode.TOKEN_FROM_USER_NOT_FOUND);
        }
        if (!userDao.existsById(to)) {
            return SimpleResult.getInstance(ResultCode.TOKEN_TO_USER_NOT_FOUND);
        }

        User fromUser = userDao.getOne(from);
        if (fromUser.getValue() < value) {
            return SimpleResult.getInstance(ResultCode.TOKEN_USER_BALANCE_NOT_ENOUGH);
        }
        User toUser = userDao.getOne(to);

        fromUser.setValue(fromUser.getValue() - value);
        userDao.saveAndFlush(fromUser);
        toUser.setValue(toUser.getValue() + value);
        userDao.saveAndFlush(toUser);
        Date date = new Date();
        UserToken fromUserToken = new UserToken(-value, fromUser.getUid(), note, "", date);
        userTokenDao.saveAndFlush(fromUserToken);
        UserToken toUserToken = new UserToken(value, toUser.getUid(), note, "", date);
        userTokenDao.saveAndFlush(toUserToken);
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    @Transactional
    public SimpleResult update(Integer uid, Integer value, String reason, String url) {
        if (!userDao.existsById(uid)) {
            return SimpleResult.getInstance(ResultCode.TOKEN_USER_NOT_FOUND);
        }
        User one = userDao.getOne(uid);
        one.setValue(one.getValue() + value);
        userDao.saveAndFlush(one);
        userTokenDao.saveAndFlush(new UserToken(value, uid, reason, url, new Date()));
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    public TokenQueryValueResult queryValue(Integer uid) {
        if (!userDao.existsById(uid)) {
            return TokenQueryValueResult.getInstance(ResultCode.TOKEN_USER_NOT_FOUND, 0);
        }
        User one = userDao.getOne(uid);
        Integer value = one.getValue();
        return TokenQueryValueResult.getInstance(ResultCode.SUCCESS, value);
    }

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
                .withIgnorePaths("reason").withIgnorePaths("url");
        Example<UserToken> example = Example.of(userToken, matcher);
        Page<UserToken> all = userTokenDao.findAll(example, pageable);
        return TokenQueryHistoryResult.getInstance(ResultCode.SUCCESS, all.getContent(), (int) all.getTotalElements());
    }

    @Transactional
    public UseTokenResult useToken(Integer uid, Integer orderId, Integer amount) {
        if (!userDao.existsById(uid)) {
            return UseTokenResult.getInstance(ResultCode.TOKEN_USER_NOT_FOUND, 0);
        }
        User user = userDao.getOne(uid);
        if (user.getValue() < amount) {
            //余额不足扣扣费失败
            return UseTokenResult.getInstance(ResultCode.SUCCESS, -1);
        }
        user.setValue(user.getValue() - amount);
        userDao.saveAndFlush(user);
        UserToken userToken = new UserToken(-amount, uid, "消费订单ID：" + orderId, "", new Date());
        userTokenDao.saveAndFlush(userToken);
        return UseTokenResult.getInstance(ResultCode.SUCCESS, user.getValue());
    }
}
