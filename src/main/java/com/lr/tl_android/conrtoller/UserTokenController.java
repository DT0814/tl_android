package com.lr.tl_android.conrtoller;


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

    @RequestMapping("/update")
    public int update(@RequestParam(name = "id") Integer uid, Integer value) {
        return ResultCode.SUCCESS;
    }

}
