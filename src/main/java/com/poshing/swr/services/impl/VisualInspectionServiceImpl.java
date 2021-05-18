package com.poshing.swr.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poshing.swr.dao.*;
import com.poshing.swr.entity.*;
import com.poshing.swr.services.VisualInspectionService;
import com.poshing.swr.utils.DateUtils;
import com.poshing.swr.utils.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 21:29
 * @Description
 */
@Service
public class VisualInspectionServiceImpl implements VisualInspectionService {

    @Resource
    private RecordDao recordDao;

    @Resource
    private EquipmentDao equipmentDao;

    @Resource
    private EquipmentRecordDao equipmentRecordDao;

    @Resource
    private ToolDao toolDao;

    @Resource
    private ToolRecordDao toolRecordDao;

    @Override
    public String getAllRecord(HttpServletRequest request) {
        String process = request.getParameter("process");
        List<Record> recordList = recordDao.selectList(new QueryWrapper<Record>()
                .eq("process", process)
                .ge("workingshiftdate", DateUtils.getInstance().getThirtyDateAgo())
                .le("workingshiftdate", DateUtils.getInstance().getTodayDate())
                .orderByDesc("workingshiftdate", "workingshift"));
        return JsonUtils.getInstance().formatLayerJson(0, "success", recordList.size(), JSON.toJSONString(recordList));
    }

    @Override
    public String searchByDate(HttpServletRequest request) {
        String process = request.getParameter("process");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        List<Record> recordList = recordDao.selectList(new QueryWrapper<Record>()
                .eq("process", process)
                .ge("workingshiftdate", start)
                .le("workingshiftdate", end)
                .orderByDesc("workingshiftdate", "workingshift"));
        return JsonUtils.getInstance().formatLayerJson(0, "success", recordList.size(), JSON.toJSONString(recordList));
    }

    @Override
    public String getRecordNow(HttpServletRequest request) {
        String process = request.getParameter("process");
        String workingshiftdate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        Record record = recordDao.selectOne(new QueryWrapper<Record>()
                .eq("process", process)
                .eq("workingshiftdate", workingshiftdate)
                .eq("workingShift", workingShift));
        if (record == null) {
            return JsonUtils.getInstance().formatLayerJson(200, "记录不存在", null);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(record));
    }

    @Override
    public String getEquipmentRecordNow(HttpServletRequest request) {
        String process = request.getParameter("process");
        String workingshiftdate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        List<Equipment> equipmentList = equipmentDao.selectList(new QueryWrapper<Equipment>()
                .eq("process", process));
        List<EquipmentRecord> equipmentRecords = equipmentRecordDao.selectList(new QueryWrapper<EquipmentRecord>()
                .eq("process", process)
                .eq("workingshiftdate", workingshiftdate)
                .eq("workingShift", workingShift));
        if (equipmentRecords == null) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", equipmentList.size(), JSON.toJSONString(equipmentList));
        }
        JSONArray jsonList = new JSONArray();
        for (Equipment equipment : equipmentList) {
            JSONObject json = new JSONObject();
            boolean flag = false;
            for (EquipmentRecord equipmentRecord : equipmentRecords) {
                if (equipment.getUuid().equals(equipmentRecord.getEquipmentUuid())) {
                    json.put("uuid", equipment.getUuid());
                    json.put("name", equipment.getName());
                    json.put("status", equipmentRecord.getStatus());
                    json.put("faultStartTime", equipmentRecord.getFaultstarttime());
                    json.put("faultEndTime", equipmentRecord.getFaultendtime());
                    json.put("expression", equipmentRecord.getExpression());
                    json.put("step", equipmentRecord.getStep());
                    json.put("maintainer", equipmentRecord.getMaintainer());
                    flag = true;
                    jsonList.add(json);
                }
            }
            // 设备正常
            if (!flag) {
                json.put("uuid", equipment.getUuid());
                json.put("name", equipment.getName());
                json.put("status", "0");
                json.put("faultStartTime", "");
                json.put("faultEndTime", "");
                json.put("expression", "");
                json.put("step", "");
                json.put("maintainer", "");
                jsonList.add(json);
            }
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", jsonList);
    }

    @Override
    public String getToolRecordNow(HttpServletRequest request) {
        String process = request.getParameter("process");
        String workingshiftdate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        List<Tool> toolList = toolDao.selectList(new QueryWrapper<Tool>()
                .eq("process", process));
        List<ToolRecord> toolRecords = toolRecordDao.selectList(new QueryWrapper<ToolRecord>()
                .eq("process", process)
                .eq("workingshiftdate", workingshiftdate)
                .eq("workingShift", workingShift));
        if (toolRecords == null) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", toolList.size(), JSON.toJSONString(toolList));
        }
        JSONArray jsonList = new JSONArray();
        for (Tool tool : toolList) {
            JSONObject json = new JSONObject();
            boolean flag = false;
            for (ToolRecord toolRecord : toolRecords) {
                if (tool.getUuid().equals(toolRecord.getToolUuid())) {
                    json.put("uuid", tool.getUuid());
                    json.put("name", tool.getName());
                    json.put("detail", tool.getDetail());
                    json.put("num", tool.getNum());
                    json.put("record_detail", toolRecord.getDetails());
                    flag = true;
                    jsonList.add(json);
                }
            }
            if (!flag) {
                json.put("uuid", tool.getUuid());
                json.put("name", tool.getName());
                json.put("detail", tool.getDetail());
                json.put("num", tool.getNum());
                json.put("record_detail", "");
                jsonList.add(json);
            }
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", jsonList);
    }

}
