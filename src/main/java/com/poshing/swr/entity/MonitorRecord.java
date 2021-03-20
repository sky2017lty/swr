package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (MonitorRecord)表实体类
 *
 * @author LiTianyi
 * @since 2021-03-08 19:18:25
 */
public class MonitorRecord extends Model<MonitorRecord> {

    @TableId
    private String uuid;

    private String workingshiftdate;

    private String workingshift;

    private String endshift;

    private String startshift;

    private String fileupdate;

    private String importantmatter;

    private String importantmatterMj;

    private String importantmatterHd;

    private String importantmatterDzl;

    private String importantmatterPzd;

    private String importantmatterKl;

    private String importantmatterFs;

    private String importantmatterSrpspv;

    private String importantmatterBj;

    private String safe;


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

    public String getImportantmatterMj() {
        return importantmatterMj;
    }

    public void setImportantmatterMj(String importantmatterMj) {
        this.importantmatterMj = importantmatterMj;
    }

    public String getImportantmatterHd() {
        return importantmatterHd;
    }

    public void setImportantmatterHd(String importantmatterHd) {
        this.importantmatterHd = importantmatterHd;
    }

    public String getImportantmatterDzl() {
        return importantmatterDzl;
    }

    public void setImportantmatterDzl(String importantmatterDzl) {
        this.importantmatterDzl = importantmatterDzl;
    }

    public String getImportantmatterPzd() {
        return importantmatterPzd;
    }

    public void setImportantmatterPzd(String importantmatterPzd) {
        this.importantmatterPzd = importantmatterPzd;
    }

    public String getImportantmatterKl() {
        return importantmatterKl;
    }

    public void setImportantmatterKl(String importantmatterKl) {
        this.importantmatterKl = importantmatterKl;
    }

    public String getImportantmatterFs() {
        return importantmatterFs;
    }

    public void setImportantmatterFs(String importantmatterFs) {
        this.importantmatterFs = importantmatterFs;
    }

    public String getImportantmatterSrpspv() {
        return importantmatterSrpspv;
    }

    public void setImportantmatterSrpspv(String importantmatterSrpspv) {
        this.importantmatterSrpspv = importantmatterSrpspv;
    }

    public String getImportantmatterBj() {
        return importantmatterBj;
    }

    public void setImportantmatterBj(String importantmatterBj) {
        this.importantmatterBj = importantmatterBj;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
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
