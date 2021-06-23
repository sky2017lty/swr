package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (Equipment)表实体类
 *
 * @author LiTianyi
 * @since 2021-03-08 19:18:15
 */
public class Equipment extends Model<Equipment> {

    @TableId
    private String uuid;

    private String name;

    private String process;

    private String status;

    private String lastchecktime;

    private String checkrecord;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastchecktime() {
        return lastchecktime;
    }

    public void setLastchecktime(String lastchecktime) {
        this.lastchecktime = lastchecktime;
    }

    public String getCheckrecord() {
        return checkrecord;
    }

    public void setCheckrecord(String checkrecord) {
        this.checkrecord = checkrecord;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }
}
