package com.poshing.swr.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poshing.swr.dao.*;
import com.poshing.swr.entity.*;
import com.poshing.swr.entity.Record;
import com.poshing.swr.services.NormalRecordAddService;
import com.poshing.swr.utils.JsonUtils;
import com.poshing.swr.utils.Utils;
import com.poshing.swr.utils.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiTianyi
 * @Date: 2021/3/3 0003 12:07
 * @Description
 */
@Service
public class NormalRecordAddServiceImpl implements NormalRecordAddService {

    @Resource
    private EquipmentRecordDao equipmentRecordDao;

    @Resource
    private RecordDao recordDao;

    @Resource
    private ToolRecordDao toolRecordDao;

    @Resource
    private LongRecordDao longRecordDao;

    @Resource
    private UnqualifiedDao unqualifiedDao;

    @Resource
    private CheckdayRecordDao checkdayRecordDao;

    @Resource
    private CheckdayDao checkdayDao;

    @Override
    public String insertEquipmentRecord(HttpServletRequest request) {
        String equipmentUuid = request.getParameter("uuid");
        String status = request.getParameter("status");
        String workShiftDate = request.getParameter("workShiftDate");
        String process = request.getParameter("process");
        String workingShift = request.getParameter("workingShift");
        EquipmentRecord one = equipmentRecordDao.selectOne(new QueryWrapper<EquipmentRecord>()
                .eq("equipment_uuid", equipmentUuid)
                .eq("workingshiftdate", workShiftDate)
                .eq("workingShift", workingShift)
                .eq("process", process));
        if (one != null) {
            one.setStatus(status);
            int flag = equipmentRecordDao.updateById(one);
            return JsonUtils.getInstance().formatLayerJson(300, "记录已存在", null);
        }
        EquipmentRecord equipmentRecord = new EquipmentRecord();
        equipmentRecord.setUuid(UuidUtil.getUuid());
        equipmentRecord.setEquipmentUuid(equipmentUuid);
        equipmentRecord.setWorkingshiftdate(workShiftDate);
        equipmentRecord.setProcess(process);
        equipmentRecord.setWorkingshift(workingShift);
        equipmentRecord.setStatus(status);
        int flag = equipmentRecordDao.insert(equipmentRecord);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String updateEquipmentRecord(HttpServletRequest request) {
        String equipmentUuid = request.getParameter("uuid");
        String status = request.getParameter("status");
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String process = request.getParameter("process");
        String faultStartTime = request.getParameter("faultStartTime");
        String faultEndTime = request.getParameter("faultEndTime");
        String expression = request.getParameter("expression");
        String step = request.getParameter("step");
        String maintainer = request.getParameter("maintainer");
        EquipmentRecord one = equipmentRecordDao.selectOne(new QueryWrapper<EquipmentRecord>()
                .eq("equipment_uuid", equipmentUuid)
                .eq("workingshiftdate", workShiftDate)
                .eq("workingShift", workingShift)
                .eq("process", process));
        if (one == null) {
            return JsonUtils.getInstance().formatLayerJson(300, "记录不存在", null);
        }
        one.setStatus(status);
        one.setFaultstarttime(faultStartTime);
        one.setFaultendtime(faultEndTime);
        one.setExpression(expression);
        one.setProcess(process);
        one.setStep(step);
        one.setMaintainer(maintainer);
        int flag = equipmentRecordDao.updateById(one);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String updateRecord(HttpServletRequest request) {
        String date = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String process = request.getParameter("process");
        String endShift = request.getParameter("endShift");
        String startShift = request.getParameter("startShift");
        String fileUpdate = request.getParameter("fileUpdate");
        String importantMatter = request.getParameter("importantMatter");
        String importantMatterNow = request.getParameter("importantMatter_Now");
        String importantMatterLong = request.getParameter("importantMatter_Long");
        String unQualified = request.getParameter("unQualified");
        Record one = recordDao.selectOne(new QueryWrapper<Record>()
                .eq("workingshiftdate", date)
                .eq("workingShift", workingShift)
                .eq("process", process));
        LongRecord longRecord = longRecordDao.selectOne(new QueryWrapper<LongRecord>()
                .eq("process", process));
        List<CheckdayRecord> selectList = checkdayRecordDao.selectList(new QueryWrapper<CheckdayRecord>()
                .eq("workshiftdate", date)
                .eq("workingShift", workingShift)
                .eq("process", process));
        for (CheckdayRecord checkdayRecord : selectList) {
            if (!"1".equals(checkdayRecord.getIscheck())) {
                return JsonUtils.getInstance().formatLayerJson(200, "每日检查项未全部核对");
            }
            if (Utils.isNull(checkdayRecord.getCheckperson())) {
                return JsonUtils.getInstance().formatLayerJson(200, "核实人未全部填写");
            }
        }
        if (one != null) {
            one.setWorkingshiftdate(date);
            one.setWorkingshift(workingShift);
            one.setProcess(process);
            one.setEndshift(endShift);
            one.setStartshift(startShift);
            one.setFileupdate(fileUpdate);
            one.setImportantmatter(importantMatter);
            one.setImportantmatterNow(importantMatterNow);
            one.setUnqualified(unQualified);
            longRecord.setDetails(importantMatterLong);
            int flag = recordDao.updateById(one);
            int flag1 = longRecordDao.updateById(longRecord);
            if (1 == flag && 1 == flag1) {
                return JsonUtils.getInstance().formatLayerJson(0, "success", null);
            } else {
                return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
            }
        }
        if (longRecord == null) {
            longRecord = new LongRecord();
            longRecord.setUuid(UuidUtil.getUuid());
            longRecord.setProcess(process);
            longRecord.setDetails(importantMatterLong);
            longRecordDao.insert(longRecord);
        }
        one = new Record();
        one.setUuid(UuidUtil.getUuid());
        one.setWorkingshiftdate(date);
        one.setWorkingshift(workingShift);
        one.setProcess(process);
        one.setEndshift(endShift);
        one.setStartshift(startShift);
        one.setFileupdate(fileUpdate);
        one.setImportantmatter(importantMatter);
        one.setImportantmatterNow(importantMatterNow);
        one.setUnqualified(unQualified);
        longRecord.setDetails(importantMatterLong);
        int flag = recordDao.insert(one);
        int flag1 = longRecordDao.updateById(longRecord);
        if (1 == flag && 1 == flag1) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String updateToolRecord(HttpServletRequest request) {
        String toolUuid = request.getParameter("uuid");
        String process = request.getParameter("process");
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String details = request.getParameter("record_detail");
        ToolRecord one = toolRecordDao.selectOne(new QueryWrapper<ToolRecord>()
                .eq("workingshiftdate", workShiftDate)
                .eq("workingShift", workingShift)
                .eq("tool_uuid", toolUuid));
        if (one == null) {
            one = new ToolRecord();
            one.setUuid(UuidUtil.getUuid());
            one.setWorkingshiftdate(workShiftDate);
            one.setProcess(process);
            one.setToolUuid(toolUuid);
            one.setWorkingshift(workingShift);
            one.setDetails(details);
            int flag = toolRecordDao.insert(one);
            if (1 == flag) {
                return JsonUtils.getInstance().formatLayerJson(0, "success", null);
            } else {
                return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
            }
        }
        if (details.isEmpty()) {
            toolRecordDao.deleteById(one);
        }
        one.setWorkingshiftdate(workShiftDate);
        one.setToolUuid(toolUuid);
        one.setProcess(process);
        one.setWorkingshift(workingShift);
        one.setDetails(details);
        int flag = toolRecordDao.updateById(one);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String deleteEquipmentRecord(HttpServletRequest request) {
        String equipmentUuid = request.getParameter("uuid");
        String status = request.getParameter("status");
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String process = request.getParameter("process");
        String faultStartTime = request.getParameter("faultStartTime");
        String faultEndTime = request.getParameter("faultEndTime");
        String expression = request.getParameter("expression");
        String step = request.getParameter("step");
        String maintainer = request.getParameter("maintainer");
        int flag = equipmentRecordDao.delete(new QueryWrapper<EquipmentRecord>()
                .eq("equipment_uuid", equipmentUuid)
                .eq("workingshiftdate", workShiftDate)
                .eq("workingShift", workingShift)
                .eq("process", process));
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String getUnQualifiedRecordNow(HttpServletRequest request) {
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String process = request.getParameter("process");
        List<Unqualified> selectList;
        if (process.isEmpty()) {
            selectList = unqualifiedDao.selectList(new QueryWrapper<Unqualified>()
                    .eq("workingshiftdate", workShiftDate)
                    .eq("workingshift", workingShift)
                    .orderByDesc("process"));
        } else {
            selectList = unqualifiedDao.selectList(new QueryWrapper<Unqualified>()
                    .eq("workingshiftdate", workShiftDate)
                    .eq("workingshift", workingShift)
                    .eq("process", process)
                    .orderByDesc("process"));
        }
        JSONArray jsonArray = new JSONArray();
        for (Unqualified unqualified : selectList) {
            JSONObject json = new JSONObject();
            json.put("uuid", unqualified.getUuid());
            json.put("process", unqualified.getProcess());
            json.put("furnace", unqualified.getFurnace());
            json.put("exception", unqualified.getException());
            json.put("subsequent", unqualified.getSubsequent());
            jsonArray.add(json);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", jsonArray);
    }

    @Override
    public String insertUnQualifiedRecord(HttpServletRequest request) {
        String workShiftDate = request.getParameter("date");
        String workingShift = request.getParameter("workingShift");
        String process = request.getParameter("process");
        Unqualified unqualified = new Unqualified();
        unqualified.setUuid(UuidUtil.getUuid());
        unqualified.setWorkingshiftdate(workShiftDate);
        unqualified.setWorkingshift(workingShift);
        unqualified.setProcess(process);
        int flag = unqualifiedDao.insert(unqualified);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(unqualified));
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String deleteUnQualifiedRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        int flag = unqualifiedDao.delete(new QueryWrapper<Unqualified>().eq("uuid", uuid));
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String updateUnQualifiedRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String process = request.getParameter("process");
        String furnace = request.getParameter("furnace");
        String exception = request.getParameter("exception");
        String subsequent = request.getParameter("subsequent");
        Unqualified one = unqualifiedDao.selectOne(new QueryWrapper<Unqualified>().eq("uuid", uuid));
        if (one == null) {
            return JsonUtils.getInstance().formatLayerJson(300, "找不到记录", null);
        }
        one.setFurnace(furnace);
        one.setException(exception);
        one.setSubsequent(subsequent);
        int flag = unqualifiedDao.updateById(one);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(one));
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String insertCheckDayRecord(HttpServletRequest request) {
        String workShiftDate = request.getParameter("date");
        String workingShift = request.getParameter("workingShift");
        String checkData = request.getParameter("checkData");
        String process = request.getParameter("process");
        Checkday checkday = new Checkday();
        CheckdayRecord checkdayRecord = new CheckdayRecord();
        checkday.setUuid(UuidUtil.getUuid());
        checkday.setProcess(process);
        checkday.setCheckdata(checkData);
        checkdayRecord.setUuid(UuidUtil.getUuid());
        checkdayRecord.setWorkshiftdate(workShiftDate);
        checkdayRecord.setWorkingshift(workingShift);
        checkdayRecord.setProcess(process);
        checkdayRecord.setCheckdayUuid(checkday.getUuid());
        checkdayRecord.setCheckdata(checkday.getCheckdata());
        int flag = checkdayDao.insert(checkday);
        checkdayRecordDao.insert(checkdayRecord);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(checkday));
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String createCheckDayRecord(HttpServletRequest request) {
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String process = request.getParameter("process");
        List<Checkday> selectList = checkdayDao.selectList(new QueryWrapper<Checkday>().eq("process", process));
        for (Checkday checkday : selectList) {
            CheckdayRecord checkdayRecord = new CheckdayRecord();
            checkdayRecord.setUuid(UuidUtil.getUuid());
            checkdayRecord.setWorkshiftdate(workShiftDate);
            checkdayRecord.setWorkingshift(workingShift);
            checkdayRecord.setProcess(process);
            checkdayRecord.setCheckdayUuid(checkday.getUuid());
            checkdayRecord.setCheckdata(checkday.getCheckdata());
            checkdayRecordDao.insert(checkdayRecord);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", null);
    }

    @Override
    public String getCheckDayRecord(HttpServletRequest request) {
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String process = request.getParameter("process");
        List<CheckdayRecord> selectList = checkdayRecordDao.selectList(new QueryWrapper<CheckdayRecord>()
                .eq("process", process)
                .eq("workShiftDate", workShiftDate)
                .eq("workingShift", workingShift));
        JSONArray jsonArray = new JSONArray();
        for (CheckdayRecord checkdayRecord : selectList) {
            JSONObject json = new JSONObject();
            json.put("uuid", checkdayRecord.getUuid());
            json.put("process", checkdayRecord.getProcess());
            json.put("checkdata", checkdayRecord.getCheckdata());
            json.put("CheckDayUUID", checkdayRecord.getCheckdayUuid());
            json.put("ischeck", checkdayRecord.getIscheck());
            json.put("checkperson", checkdayRecord.getCheckperson());
            jsonArray.add(json);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", jsonArray);
    }

    @Override
    public String delCheckDayRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String checkDayUUID = request.getParameter("CheckDayUUID");
        int flag = checkdayDao.delete(new QueryWrapper<Checkday>().eq("uuid", checkDayUUID));
        int i = checkdayRecordDao.delete(new QueryWrapper<CheckdayRecord>().eq("uuid", uuid));
        if (1 == flag && 1 == i) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String updateCheckDayRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String checkdata = request.getParameter("checkdata");
        String ischeck = request.getParameter("ischeck");
        String checkperson = request.getParameter("checkperson");
        CheckdayRecord one = checkdayRecordDao.selectOne(new QueryWrapper<CheckdayRecord>().eq("uuid", uuid));
        if (one == null) {
            return JsonUtils.getInstance().formatLayerJson(300, "找不到记录", null);
        }
        one.setCheckperson(checkperson);
        one.setIscheck(ischeck);
        one.setCheckdata(checkdata);
        int flag = checkdayRecordDao.updateById(one);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(one));
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }
}
