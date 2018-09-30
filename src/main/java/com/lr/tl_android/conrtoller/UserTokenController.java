package com.lr.tl_android.conrtoller;


import com.lr.tl_android.pojo.result.SimpleResult;
import com.lr.tl_android.pojo.result.TokenQueryHistoryResult;
import com.lr.tl_android.pojo.result.TokenQueryValueResult;
import com.lr.tl_android.pojo.result.UseTokenResult;
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

    @RequestMapping("/init")
    public SimpleResult init(@RequestParam(name = "id") Integer uid, Integer value, String reason) {
        if (null == uid || uid <= 0 || null == value || null == reason || reason.trim().equals("")) {
            return SimpleResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        service.init(uid, value, reason);
        return SimpleResult.getInstance(ResultCode.SUCCESS);
    }

    @RequestMapping("/tran")
    public SimpleResult tran(Integer from, Integer to, Integer value, String note) {
        if (null == from || from <= 0 || null == to || to <= 0 || null == value || null == note || note.trim().equals("")) {
            return SimpleResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        return service.tran(from, to, value, note);
    }

    @RequestMapping("/update")
    public SimpleResult update(@RequestParam(name = "id") Integer uid, Integer value, String reason, String url) {
        if (null == uid || uid <= 0 || null == value || null == reason || reason.trim().equals("") || null == url || url.trim().equals("")) {
            return SimpleResult.getInstance(ResultCode.PARAMETER_ERROR);
        }
        return service.update(uid, value, reason, url);
    }

    @RequestMapping("/queryValue")
    public TokenQueryValueResult queryValue(@RequestParam(name = "id") Integer uid) {
        if (null == uid || uid <= 0) {
            return TokenQueryValueResult.getInstance(ResultCode.PARAMETER_ERROR, 0);
        }
        return service.queryValue(uid);
    }

    @RequestMapping("/queryHistory")
    public TokenQueryHistoryResult queryHistory(@RequestParam(name = "id") Integer uid, Integer pageNum, Integer pageSize) {
        if (null == pageNum) {
            pageNum = 0;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        if (null == uid || uid <= 0 || pageNum < 1 || pageSize < 1) {
            return TokenQueryHistoryResult.getInstance(ResultCode.PARAMETER_ERROR, null, 0);
        }

        return service.queryHistory(uid, pageNum - 1, pageSize);
    }

    @RequestMapping("/useToken")
    public UseTokenResult useToken(Integer orderId, Integer amount, Integer uid) {
        if (null == uid || uid <= 0 || orderId < 1 || amount < 1) {
            return UseTokenResult.getInstance(ResultCode.PARAMETER_ERROR, 0);
        }

        return service.useToken(uid, orderId, amount);
    }

}
