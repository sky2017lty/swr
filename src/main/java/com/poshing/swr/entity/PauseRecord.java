package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (PauseRecord)表实体类
 *
 * @author LiTianyi
 * @since 2021-03-08 19:18:25
 */
public class PauseRecord extends Model<PauseRecord> {

    @TableId
    private String uuid;

    private String workingshiftdate;

    private String workingshift;

    private String process;

    private String pauselevel;

    private String detail;


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

    public String getPauselevel() {
        return pauselevel;
    }

    public void setPauselevel(String pauselevel) {
        this.pauselevel = pauselevel;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.uuid;
    }
}
