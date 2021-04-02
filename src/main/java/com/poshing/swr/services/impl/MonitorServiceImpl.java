package com.poshing.swr.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poshing.swr.dao.LongRecordDao;
import com.poshing.swr.dao.MonitorRecordDao;
import com.poshing.swr.dao.PauseRecordDao;
import com.poshing.swr.entity.LongRecord;
import com.poshing.swr.entity.MonitorRecord;
import com.poshing.swr.entity.PauseRecord;
import com.poshing.swr.services.MonitorService;
import com.poshing.swr.utils.DateUtils;
import com.poshing.swr.utils.JsonUtils;
import com.poshing.swr.utils.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: LiTianyi
 * @Date: 2021/3/5 0005 23:04
 * @Description
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Resource
    private PauseRecordDao pauseRecordDao;

    @Resource
    private MonitorRecordDao monitorRecordDao;

    @Resource
    private LongRecordDao longRecordDao;

    @Override
    public String insertPauseRecord(HttpServletRequest request) {
        String date = request.getParameter("date");
        String workingShift = request.getParameter("workingShift");
        PauseRecord pauseRecord = new PauseRecord();
        pauseRecord.setUuid(UuidUtil.getUuid());
        pauseRecord.setWorkingshiftdate(date);
        pauseRecord.setWorkingshift(workingShift);
        pauseRecord.setPauselevel("0");
        pauseRecord.setProcess("Monitor");
        int flag = pauseRecordDao.insert(pauseRecord);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(pauseRecord));
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String updatePauseRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String status = request.getParameter("status");
        String date = request.getParameter("workShiftDate");
        String workingShift = request.getParameter("workingShift");
        String detail = request.getParameter("detail");
        PauseRecord one = pauseRecordDao.selectOne(new QueryWrapper<PauseRecord>()
                .eq("uuid", uuid));
        if (one == null) {
            return JsonUtils.getInstance().formatLayerJson(300, "找不到记录", null);
        }
        one.setPauselevel(status);
        one.setDetail(detail);
        int flag = pauseRecordDao.updateById(one);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(one));
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String deletePauseRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        int flag = pauseRecordDao.delete(new QueryWrapper<PauseRecord>()
                .eq("uuid", uuid));
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String updateMonitorRecord(HttpServletRequest request) {
        String date = request.getParameter("workShiftDate");
        String workingshift = request.getParameter("workingShift");
        String endshift = request.getParameter("endShift");
        String startshift = request.getParameter("startShift");
        String fileupdate = request.getParameter("fileupdate");
        String importantmatter = request.getParameter("importantmatter");
        String importantmatterMj = request.getParameter("importantmatterMj");
        String importantmatterHd = request.getParameter("importantmatterHd");
        String importantmatterDzl = request.getParameter("importantmatterDzl");
        String importantmatterPzd = request.getParameter("importantmatterPzd");
        String importantmatterKl = request.getParameter("importantmatterKl");
        String importantmatterFs = request.getParameter("importantmatterFs");
        String importantmatterSrpspv = request.getParameter("importantmatterSrpspv");
        String importantmatterBj = request.getParameter("importantmatterBj");
        String safe = request.getParameter("safe");
        String importantMatterLong = request.getParameter("importantMatter_Long");
        MonitorRecord one = monitorRecordDao.selectOne(new QueryWrapper<MonitorRecord>()
                .eq("workingshiftdate", date)
                .eq("workingshift", workingshift));
        LongRecord longRecord = longRecordDao.selectOne(new QueryWrapper<LongRecord>()
                .eq("process", "Monitor"));
        if (one != null) {
            one.setWorkingshiftdate(date);
            one.setWorkingshift(workingshift);
            one.setEndshift(endshift);
            one.setStartshift(startshift);
            one.setFileupdate(fileupdate);
            one.setImportantmatter(importantmatter);
            one.setImportantmatterMj(importantmatterMj);
            one.setImportantmatterHd(importantmatterHd);
            one.setImportantmatterDzl(importantmatterDzl);
            one.setImportantmatterPzd(importantmatterPzd);
            one.setImportantmatterKl(importantmatterKl);
            one.setImportantmatterFs(importantmatterFs);
            one.setImportantmatterSrpspv(importantmatterSrpspv);
            one.setImportantmatterBj(importantmatterBj);
            one.setSafe(safe);
            longRecord.setDetails(importantMatterLong);
            int flag = monitorRecordDao.updateById(one);
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
            longRecord.setProcess("Monitor");
            longRecord.setDetails(importantMatterLong);
            longRecordDao.insert(longRecord);
        }
        one = new MonitorRecord();
        one.setUuid(UuidUtil.getUuid());
        one.setWorkingshiftdate(date);
        one.setWorkingshift(workingshift);
        one.setEndshift(endshift);
        one.setStartshift(startshift);
        one.setFileupdate(fileupdate);
        one.setImportantmatter(importantmatter);
        one.setImportantmatterMj(importantmatterMj);
        one.setImportantmatterHd(importantmatterHd);
        one.setImportantmatterDzl(importantmatterDzl);
        one.setImportantmatterPzd(importantmatterPzd);
        one.setImportantmatterKl(importantmatterKl);
        one.setImportantmatterFs(importantmatterFs);
        one.setImportantmatterSrpspv(importantmatterSrpspv);
        one.setImportantmatterBj(importantmatterBj);
        one.setSafe(safe);
        longRecord.setDetails(importantMatterLong);
        int flag = monitorRecordDao.insert(one);
        int flag1 = longRecordDao.updateById(longRecord);
        if (1 == flag && 1 == flag1) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String getMonitorRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        MonitorRecord one = monitorRecordDao.selectOne(new QueryWrapper<MonitorRecord>()
                .eq("uuid", uuid));
        return JSON.toJSONString(one);

    }

    @Override
    public String deleteMonitorRecord(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String date = request.getParameter("date");
        String workingshift = request.getParameter("workingshift");
        int monitorRecordFlag = monitorRecordDao.delete(new QueryWrapper<MonitorRecord>()
                .eq("uuid", uuid));
        int pauseRecordFlag = pauseRecordDao.delete(new QueryWrapper<PauseRecord>()
                .eq("workingshiftdate", date)
                .eq("workingshift", workingshift));
        return JsonUtils.getInstance().formatLayerJson(0, "success", null);
    }

    @Override
    public String getAllMonitorRecord(HttpServletRequest request) {
        List<MonitorRecord> selectList = monitorRecordDao.selectList(new QueryWrapper<MonitorRecord>().orderByDesc("workingshiftdate"));
        return JsonUtils.getInstance().formatLayerJson(0, "success", selectList.size(), JSON.toJSONString(selectList));
    }

    @Override
    public String getPauseRecord(HttpServletRequest request) {
        String date = request.getParameter("workShiftDate");
        String workingshift = request.getParameter("workingShift");
        List<PauseRecord> selectList = pauseRecordDao.selectList(new QueryWrapper<PauseRecord>()
                .eq("workingshiftdate", date)
                .eq("workingshift", workingshift));
        return JsonUtils.getInstance().formatLayerJson(0, "success", selectList.size(), JSON.toJSONString(selectList));
    }

    @Override
    public String searchMonitorRecordByDate(HttpServletRequest request) {
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        List<MonitorRecord> recordList = monitorRecordDao.selectList(new QueryWrapper<MonitorRecord>()
                .ge("workingshiftdate", start)
                .le("workingshiftdate", end));
        return JsonUtils.getInstance().formatLayerJson(0, "success", recordList.size(), JSON.toJSONString(recordList));
    }

    @Override
    public String getMonitorRecordNow(HttpServletRequest request) {
        String date = request.getParameter("workShiftDate");
        String workingshift = request.getParameter("workingShift");
        MonitorRecord one = monitorRecordDao.selectOne(new QueryWrapper<MonitorRecord>()
                .eq("workingshiftdate", date)
                .eq("workingshift", workingshift));
        if (one == null) {
            return JsonUtils.getInstance().formatLayerJson(200, "记录不存在", null);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", 1, JSON.toJSONString(one));
    }

    @Override
    public String getPauseRecordNow(HttpServletRequest request) {
        String date = request.getParameter("workShiftDate");
        String workingshift = request.getParameter("workingShift");
        List<PauseRecord> selectList = pauseRecordDao.selectList(new QueryWrapper<PauseRecord>()
                .eq("workingshiftdate", date)
                .eq("workingshift", workingshift));
        JSONArray jsonArray = new JSONArray();
        for (PauseRecord pauseRecord : selectList) {
            JSONObject json = new JSONObject();
            json.put("uuid", pauseRecord.getUuid());
            json.put("pauselevel", pauseRecord.getPauselevel());
            json.put("detail", pauseRecord.getDetail());
            jsonArray.add(json);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", jsonArray);
    }
}
