package com.poshing.swr.services.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poshing.swr.dao.EquipmentDao;
import com.poshing.swr.dao.RecordDao;
import com.poshing.swr.entity.Equipment;
import com.poshing.swr.entity.Record;
import com.poshing.swr.services.VisualInspectionService;
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

    @Override
    public String getAllRecord(HttpServletRequest request) {
        String process = request.getParameter("process");
        List<Record> recordList = recordDao.selectList(new QueryWrapper<Record>().eq("process", process).orderByDesc("workingshiftdate"));
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
                .orderByDesc("workingshiftdate"));
        return JsonUtils.getInstance().formatLayerJson(0, "success", recordList.size(), JSON.toJSONString(recordList));
    }

}
