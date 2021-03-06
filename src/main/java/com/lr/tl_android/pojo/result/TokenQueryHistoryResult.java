package com.lr.tl_android.pojo.result;

import com.lr.tl_android.pojo.UserToken;

import java.util.List;

public class TokenQueryHistoryResult {
    private int retCode;
    private List<UserToken> value;
    private Integer total;

    public static TokenQueryHistoryResult getInstance(int retCode, List<UserToken> value, int total) {
        return new TokenQueryHistoryResult(retCode, value, total);
    }

    public TokenQueryHistoryResult() {
    }

    public TokenQueryHistoryResult(int retCode, List<UserToken> value, Integer total) {
        this.retCode = retCode;
        this.value = value;
        this.total = total;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public List<UserToken> getValue() {
        return value;
    }

    public void setValue(List<UserToken> value) {
        this.value = value;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}