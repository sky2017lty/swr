package com.poshing.swr.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poshing.swr.dao.MonitorRecordDao;
import com.poshing.swr.dao.RecordDao;
import com.poshing.swr.entity.MonitorRecord;
import com.poshing.swr.entity.Record;
import com.poshing.swr.services.ShiftWorkService;
import com.poshing.swr.utils.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiTianyi
 */
@Service
public class ShiftWorkServiceImpl implements ShiftWorkService {

    @Resource
    private RecordDao recordDao;

    @Resource
    private MonitorRecordDao monitorRecordDao;

    @Override
    public String getShiftWorkPeople(HttpServletRequest request) {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        if (startDate == null) {
            startDate = "1453-05-29";
        }
        if (endDate == null) {
            endDate = "2077-01-01";
        }
        List<Record> records = recordDao.selectList(new QueryWrapper<Record>()
                .ge("workingshiftdate", startDate)
                .le("workingshiftdate", endDate));
        List<MonitorRecord> monitorRecords = monitorRecordDao.selectList(new QueryWrapper<MonitorRecord>()
                .ge("workingshiftdate", startDate)
                .le("workingshiftdate", endDate)
                .orderByDesc("workingshiftdate"));
        JSONArray jsonArray = new JSONArray();
        for (MonitorRecord monitorRecord : monitorRecords) {
            JSONObject json = new JSONObject();
            //构建json
            json.put("workingshiftdate", monitorRecord.getWorkingshiftdate());
            json.put("workingshift", "1".equals(monitorRecord.getWorkingshift()) ? "夜班" : "白班");
            json.put("Monitor", monitorRecord.getEndshift());
            for (Record record : records) {
                //确认是同一班次
                if (monitorRecord.getWorkingshiftdate().equals(record.getWorkingshiftdate())
                        && monitorRecord.getWorkingshift().equals(record.getWorkingshift())) {
                    switch (record.getProcess()) {
                        case "VisualInspection":
                            json.put("VisualInspection", record.getEndshift());
                            break;
                        case "Thickness":
                            json.put("Thickness", record.getEndshift());
                            break;
                        case "Resistivity":
                            json.put("Resistivity", record.getEndshift());
                            break;
                        case "Particles":
                            json.put("Particles", record.getEndshift());
                            break;
                        case "PutOnShelf":
                            json.put("PutOnShelf", record.getEndshift());
                            break;
                        case "FinalInspection":
                            json.put("FinalInspection", record.getEndshift());
                            break;
                        case "Corrosion":
                            json.put("Corrosion", record.getEndshift());
                            break;
                        case "Package":
                            json.put("Package", record.getEndshift());
                            break;
                        default:
                            break;
                    }
                }
            }
            jsonArray.add(json);
        }
        return JsonUtils.getInstance().formatLayerJson(0, "success", jsonArray);
    }
}
