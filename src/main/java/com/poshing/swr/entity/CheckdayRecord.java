package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (CheckdayRecord)实体类
 *
 * @author makejava
 * @since 2021-09-11 14:37:20
 */
public class CheckdayRecord extends Model<CheckdayRecord> {

    @TableId
    private String uuid;
    
    private String checkdayUuid;

    private String checkdata;

    private String workshiftdate;
    
    private String workingshift;
    
    private String process;
    
    private String ischeck;
    
    private String checkperson;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCheckdayUuid() {
        return checkdayUuid;
    }

    public void setCheckdayUuid(String checkdayUuid) {
        this.checkdayUuid = checkdayUuid;
    }

    public String getWorkshiftdate() {
        return workshiftdate;
    }

    public void setWorkshiftdate(String workingshiftdate) {
        this.workshiftdate = workingshiftdate;
    }

    public String getWorkingshift() {
        return workingshift;
    }

    public void setWorkingshift(String workingshift) {
        this.workingshift = workingshift;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCheckperson() {
        return checkperson;
    }

    public void setCheckperson(String checkperson) {
        this.checkperson = checkperson;
    }

    public String getCheckdata() {
        return checkdata;
    }

    public void setCheckdata(String checkdata) {
        this.checkdata = checkdata;
    }
}