package com.lr.tl_android.conrtoller;


import com.lr.tl_android.pojo.UserToken;
import com.lr.tl_android.pojo.result.UserTokenResult;
import com.lr.tl_android.service.UserTokenService;
import com.lr.tl_android.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTokenController {
    @Autowired
    private UserTokenService service;

    @RequestMapping("/report_update")
    public UserTokenResult update(@RequestParam(name = "id") Integer uid, Integer value, String reason, String url) {
        if (null == uid || uid <= 0 || null == reason || null == value || null == url) {
            return UserTokenResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        return service.update(uid, value, reason, url);
    }
}
