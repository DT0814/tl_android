package com.lr.tl_android.conrtoller;

import com.lr.tl_android.pojo.result.UserResult;
import com.lr.tl_android.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/findById/{id}")
    public UserResult get(@PathVariable(name = "id") Integer uid) {
        UserResult result = service.getById(uid);
        return result;
    }
}
