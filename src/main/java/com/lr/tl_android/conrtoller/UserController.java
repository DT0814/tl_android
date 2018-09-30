package com.lr.tl_android.conrtoller;

import com.lr.tl_android.pojo.result.SimpleResult;
import com.lr.tl_android.pojo.result.UserResult;
import com.lr.tl_android.service.UserService;
import com.lr.tl_android.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService service;


    @GetMapping("init")
    public SimpleResult init(Integer uid, String uinfo) {
        if (null == uid || uid <= 0 || null == uinfo || uinfo.trim().equals("")) {
            return SimpleResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        return service.init(uid, uinfo);
    }

    @GetMapping("update")
    public SimpleResult update(Integer uid, String uinfo) {
        if (null == uid || uid <= 0 || null == uinfo || uinfo.trim().equals("")) {
            return SimpleResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        return service.update(uid, uinfo);
    }

    @GetMapping("/findById/{id}")
    public UserResult get(@PathVariable(name = "id") Integer uid) {
        UserResult result = service.getById(uid);
        return result;
    }


}
