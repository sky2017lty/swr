package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (Checkday)实体类
 *
 * @author makejava
 * @since 2021-09-11 14:37:20
 */
public class Checkday extends Model<Checkday> {

    @TableId
    private String uuid;
    /**
     * 工序
     */
    private String process;
    /**
     * 检查项目
     */
    private String checkdata;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getCheckdata() {
        return checkdata;
    }

    public void setCheckdata(String checkdata) {
        this.checkdata = checkdata;
    }

}