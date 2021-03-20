package com.poshing.swr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (EquipmentRecord)表实体类
 *
 * @author LiTianyi
 * @since 2021-03-08 19:18:19
 */
public class EquipmentRecord extends Model<EquipmentRecord> {

    @TableId
    private String uuid;

    private String equipmentUuid;

    private String workingshiftdate;

    private String workingshift;

    private String process;

    private String status;

    private String faultstarttime;

    private String faultendtime;

    private String expression;

    private String step;

    private String maintainer;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEquipmentUuid() {
        return equipmentUuid;
    }

    public void setEquipmentUuid(String equipmentUuid) {
        this.equipmentUuid = equipmentUuid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFaultstarttime() {
        return faultstarttime;
    }

    public void setFaultstarttime(String faultstarttime) {
        this.faultstarttime = faultstarttime;
    }

    public String getFaultendtime() {
        return faultendtime;
    }

    public void setFaultendtime(String faultendtime) {
        this.faultendtime = faultendtime;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
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
