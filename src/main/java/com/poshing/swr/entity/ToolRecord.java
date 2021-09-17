package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (ToolRecord)表实体类
 *
 * @author LiTianyi
 * @since 2021-03-08 19:18:26
 */
public class ToolRecord extends Model<ToolRecord> {

    @TableId
    private String uuid;

    private String toolUuid;

    private String workingshiftdate;

    private String workingshift;

    private String details;

    private String status;

    private String process;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToolUuid() {
        return toolUuid;
    }

    public void setToolUuid(String toolUuid) {
        this.toolUuid = toolUuid;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
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
