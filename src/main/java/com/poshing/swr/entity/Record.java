package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (Record)表实体类
 *
 * @author LiTianyi
 * @since 2021-03-08 19:18:25
 */
public class Record extends Model<Record> {

    @TableId
    private String uuid;

    private String workingshiftdate;

    private String workingshift;

    private String process;

    private String endshift;

    private String startshift;

    private String fileupdate;

    private String importantmatter;

    private String importantmatterNow;

    private String importantmatterLong;

    private String unqualified;


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

    public String getEndshift() {
        return endshift;
    }

    public void setEndshift(String endshift) {
        this.endshift = endshift;
    }

    public String getStartshift() {
        return startshift;
    }

    public void setStartshift(String startshift) {
        this.startshift = startshift;
    }

    public String getFileupdate() {
        return fileupdate;
    }

    public void setFileupdate(String fileupdate) {
        this.fileupdate = fileupdate;
    }

    public String getImportantmatter() {
        return importantmatter;
    }

    public void setImportantmatter(String importantmatter) {
        this.importantmatter = importantmatter;
    }

    public String getImportantmatterNow() {
        return importantmatterNow;
    }

    public void setImportantmatterNow(String importantmatterNow) {
        this.importantmatterNow = importantmatterNow;
    }

    public String getImportantmatterLong() {
        return importantmatterLong;
    }

    public void setImportantmatterLong(String importantmatterLong) {
        this.importantmatterLong = importantmatterLong;
    }

    public String getUnqualified() {
        return unqualified;
    }

    public void setUnqualified(String unqualified) {
        this.unqualified = unqualified;
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
