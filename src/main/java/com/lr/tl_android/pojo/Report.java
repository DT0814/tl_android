package com.lr.tl_android.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tl_report")
@JsonIgnoreProperties({"hibernateLazyInitializ,er", "handler"})
public class Report {
    @Id
    @GeneratedValue
    private Integer rid;
    private Integer uid;
    private String imagePath;
    //举报处理状态false未处理true处理完成
    private boolean state;

    public Report() {
    }

    public Report(Integer uid, String imagePath, boolean state, Integer rid) {
        this.uid = uid;
        this.imagePath = imagePath;
        this.state = state;
        this.rid = rid;
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

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
