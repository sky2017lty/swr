package com.poshing.swr.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poshing.swr.dao.*;
import com.poshing.swr.entity.*;
import com.poshing.swr.entity.Process;
import com.poshing.swr.services.IndexService;
import com.poshing.swr.utils.JsonUtils;
import com.poshing.swr.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LiTianyi
 * @Date: 2021/2/28 0028 16:34
 * @Description
 */
@Service
public class IndexServiceImpl implements IndexService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    @Resource
    private ProcessDao processDao;

    @Resource
    private EquipmentDao equipmentDao;

    @Resource
    private EquipmentRecordDao equipmentRecordDao;

    @Resource
    private ToolDao toolDao;

    @Resource
    private ToolRecordDao toolRecordDao;

    @Resource
    private LongRecordDao longRecordDao;

    @Resource
    private RecordDao recordDao;

    @Resource
    private MonitorRecordDao monitorRecordDao;

    @Resource
    private NowRecordDao nowRecordDao;

    @Resource
    private UnqualifiedDao unqualifiedDao;

    @Override
    public String getAllProcess() {
        List<Process> processList = processDao.selectList(new QueryWrapper<Process>());
        return JsonUtils.getInstance().formatLayerJson(0, "success", processList.size(), JSON.toJSONString(processList));
    }

    @Override
    public String getEquipment(HttpServletRequest request) {
        String process = request.getParameter("process");
        List<Equipment> equipmentList = equipmentDao.selectList(new QueryWrapper<Equipment>()
                .eq("process", process)
                .eq("status", "0"));
        return JsonUtils.getInstance().formatLayerJson(0, "success", equipmentList.size(), JSON.toJSONString(equipmentList));
    }

    @Override
    public String getImportantMatterLong(HttpServletRequest request) {
        String process = request.getParameter("process");
        LongRecord one = longRecordDao.selectOne(new QueryWrapper<LongRecord>()
                .eq("process", process));
        if (one == null) {
            one = new LongRecord();
            one.setUuid(UuidUtil.getUuid());
            one.setProcess(process);
            int flag = longRecordDao.insert(one);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(one));
    }

    @Override
    public String getTool(HttpServletRequest request) {
        String process = request.getParameter("process");
        List<Tool> toolList = toolDao.selectList(new QueryWrapper<Tool>()
                .eq("process", process)
                .eq("status", "0"));
        return JsonUtils.getInstance().formatLayerJson(0, "success", toolList.size(), JSON.toJSONString(toolList));
    }

    @Override
    public String getRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        Record one = recordDao.selectOne(new QueryWrapper<Record>()
                .eq("uuid", uuid));
        if (one == null) {
            return JsonUtils.getInstance().formatLayerJson(200, "找不到记录", null);
        }
        return JSON.toJSONString(one);
    }

    @Override
    public String getEquipmentRecord(HttpServletRequest request) {
        String process = request.getParameter("process");
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        List<EquipmentRecord> equipmentRecordList = equipmentRecordDao.selectList(new QueryWrapper<EquipmentRecord>()
                .eq("process", process)
                .eq("workingshiftdate", workShiftDate)
                .eq("workingShift", workingShift)
                .eq("status", "1"));
        JSONArray json = new JSONArray();
        for (EquipmentRecord equipmentRecord : equipmentRecordList) {
            Equipment one = equipmentDao.selectOne(new QueryWrapper<Equipment>()
                    .eq("uuid", equipmentRecord.getEquipmentUuid()));
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(equipmentRecord);
            jsonObject.put("name", one.getName());
            json.add(jsonObject);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", json);
    }

    @Override
    public String getToolRecord(HttpServletRequest request) {
        String process = request.getParameter("process");
        String workShiftDate = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        List<ToolRecord> toolRecordList = toolRecordDao.selectList(new QueryWrapper<ToolRecord>()
                .eq("process", process)
                .eq("workingshiftdate", workShiftDate)
                .eq("workingShift", workingShift));
        JSONArray json = new JSONArray();
        for (ToolRecord toolRecord : toolRecordList) {
            Tool one = toolDao.selectOne(new QueryWrapper<Tool>()
                    .eq("uuid", toolRecord.getToolUuid()));
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(toolRecord);
            jsonObject.put("name", one.getName());
            jsonObject.put("detail", one.getDetail());
            jsonObject.put("number", one.getNum());
            json.add(jsonObject);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", json);
    }

    @Override
    public String deleteRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String process = request.getParameter("process");
        String date = request.getParameter("workingshiftdate");
        String workingshift = request.getParameter("workingshift");
        Map<String, Object> map = new HashMap<>(0);
        map.put("process", process);
        map.put("workingshiftdate", date);
        map.put("workingshift", workingshift);
        int equipmentRecordFlag = equipmentRecordDao.deleteByMap(map);
        int toolRecordFlag = toolRecordDao.deleteByMap(map);
        int unqualifiedFlag = unqualifiedDao.deleteByMap(map);
        int recordFlag = recordDao.delete(new QueryWrapper<Record>()
                .eq("uuid", uuid));
        return JsonUtils.getInstance().formatLayerJson(0, "success", null);
    }

    @Override
    public String checkRecord(HttpServletRequest request) {
        String date = request.getParameter("date");
        String workingshift = request.getParameter("workingshift");
        List<Record> recordList = recordDao.selectList(new QueryWrapper<Record>().eq("workingshiftdate", date).eq("workingshift", workingshift));
        MonitorRecord monitorRecord = monitorRecordDao.selectOne(new QueryWrapper<MonitorRecord>().eq("workingshiftdate", date).eq("workingshift", workingshift));
        Map<String, Object> recordMap = new HashMap<>(0);
        for (Record record : recordList) {
            recordMap.put(record.getProcess(), record);
        }
        if (monitorRecord != null) {
            recordMap.put("Monitor", monitorRecord);
        }
        return JSON.toJSONString(recordMap);
    }

    @Override
    public String getImportantMatterNow(HttpServletRequest request) {
        String process = request.getParameter("process");
        //查找当前
        NowRecord one = nowRecordDao.selectOne(new QueryWrapper<NowRecord>()
                .eq("process", process));
        if (one == null) {
            one = new NowRecord();
            one.setUuid(UuidUtil.getUuid());
            one.setProcess(process);
            int flag = nowRecordDao.insert(one);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(one));
    }

    @Override
    public String editNowRecord(HttpServletRequest request) {
        String uuid = request.getParameter("nowRecord_uuid");
        String process = request.getParameter("nowRecord_process");
        String details = request.getParameter("nowRecord_name");
        NowRecord one = nowRecordDao.selectOne(new QueryWrapper<NowRecord>().eq("uuid", uuid));
        if (one == null) {
            one = new NowRecord();
            one.setUuid(UuidUtil.getUuid());
            one.setProcess(process);
            one.setDetails(details);
            int flag = nowRecordDao.insert(one);
        }
        //添加内容
        one.setDetails(details);
        int flag = nowRecordDao.updateById(one);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(one));
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }    }

    @Override
    public String getAllIM(HttpServletRequest request) {
        List<NowRecord> selectList = nowRecordDao.selectList(new QueryWrapper<NowRecord>());
        return JsonUtils.getInstance().formatLayerJson(0, "success", selectList.size(), JSON.toJSONString(selectList));
    }
}
