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
import com.poshing.swr.utils.DateUtils;
import com.poshing.swr.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiTianyi
 */
@Service
public class ShiftWorkServiceImpl implements ShiftWorkService {

    private static final Logger logger = LoggerFactory.getLogger(ShiftWorkServiceImpl.class);

    @Resource
    private RecordDao recordDao;

    @Resource
    private MonitorRecordDao monitorRecordDao;

    @Override
    public String getShiftWorkPeople(HttpServletRequest request) {
        String startDate = request.getParameter("start");
        String endDate = request.getParameter("end");
        if (startDate == null || "".equals(startDate)) {
            startDate = DateUtils.getInstance().getThirtyDateAgo();
        }
        if (endDate == null || "".equals(endDate)) {
            endDate = DateUtils.getInstance().getDate();
        }
        List<Record> records = recordDao.selectList(new QueryWrapper<Record>()
                .ge("workingshiftdate", startDate)
                .le("workingshiftdate", endDate));
        List<MonitorRecord> monitorRecords = monitorRecordDao.selectList(new QueryWrapper<MonitorRecord>()
                .ge("workingshiftdate", startDate)
                .le("workingshiftdate", endDate)
                .orderByDesc("workingshiftdate", "workingshift"));
        JSONArray jsonArray = new JSONArray();
        for (MonitorRecord monitorRecord : monitorRecords) {
            JSONObject json = new JSONObject();
            //??????json
            json.put("workingshiftdate", monitorRecord.getWorkingshiftdate());
            json.put("workingshift", "1".equals(monitorRecord.getWorkingshift()) ? "??????" : "??????");
            json.put("Monitor", monitorRecord.getEndshift());
            for (Record record : records) {
                //?????????????????????
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
