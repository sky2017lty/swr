package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * (Unqualified)实体类
 *
 * @author makejava
 * @since 2021-05-02 13:40:24
 */
public class Unqualified extends Model<Unqualified> {

    @TableId
    private String uuid;
    
    private String workingshiftdate;
    
    private String workingshift;
    
    private String process;
    
    private String furnace;
    
    private String exception;
    
    private String subsequent;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWorkingshiftdate() {
        return workingshiftdate;
    }

    public void setWorkingshiftdate(String workingshiftdate) {
        this.workingshiftdate = workingshiftdate;
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

    public String getFurnace() {
        return furnace;
    }

    public void setFurnace(String furnace) {
        this.furnace = furnace;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getSubsequent() {
        return subsequent;
    }

    public void setSubsequent(String subsequent) {
        this.subsequent = subsequent;
    }

}