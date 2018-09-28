package com.lr.tl_android.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lr.tl_android.utils.CustomDateSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tl_report")
@JsonIgnoreProperties({"hibernateLazyInitializ,er", "handler"})
public class Report {
    @Id
    @GeneratedValue
    private Integer rid;
    private Integer uid;
    private String imagePath;
    private String reason;
    private Date date;
    //举报处理状态1未处理2处理完成
    private byte state;

    public Report() {
    }

    public Report(Integer uid, String imagePath, byte state, String reason, Date date) {
        this.uid = uid;
        this.imagePath = imagePath;
        this.state = state;
        this.reason = reason;
        this.date = date;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
