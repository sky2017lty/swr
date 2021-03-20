package com.poshing.swr.services.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poshing.swr.dao.EquipmentDao;
import com.poshing.swr.entity.Equipment;
import com.poshing.swr.services.EquipmentService;
import com.poshing.swr.utils.JsonUtils;
import com.poshing.swr.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 12:33
 * @Description
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    @Resource
    private EquipmentDao equipmentDao;

    @Override
    public String addEquipment(HttpServletRequest request) {
        String name = request.getParameter("equipment_name");
        String process = request.getParameter("equipment_process");
        Equipment one = equipmentDao.selectOne(new QueryWrapper<Equipment>().eq("name", name).eq("process", process));
        if (one != null) {
            return JsonUtils.getInstance().formatLayerJson(300, "设备已存在", null);
        }
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setProcess(process);
        equipment.setUuid(UuidUtil.getUuid());
        equipment.setStatus("0");
        int flag = equipmentDao.insert(equipment);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String getAllEquipment() {
        List<Equipment> equipmentList = equipmentDao.selectList(new QueryWrapper<Equipment>());
        return JsonUtils.getInstance().formatLayerJson(0, "success", equipmentList.size(), JSON.toJSONString(equipmentList));
    }

    @Override
    public String equipmentSearch(HttpServletRequest request) {
        String name = request.getParameter("equipment_name");
        List<Equipment> equipmentList = equipmentDao.selectList(new QueryWrapper<Equipment>().like("name", name));
        return JsonUtils.getInstance().formatLayerJson(0, "success", equipmentList.size(), JSON.toJSONString(equipmentList));
    }

    @Override
    public String getOneEquipment(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        Equipment one = equipmentDao.selectOne(new QueryWrapper<Equipment>().eq("uuid", uuid));
        return JSON.toJSONString(one);
    }

    @Override
    public String editEquipment(HttpServletRequest request) {
        String name = request.getParameter("equipment_name");
        String process = request.getParameter("equipment_process");
        String uuid = request.getParameter("equipment_uuid");
        String status = request.getParameter("equipment_status");
        Equipment one = equipmentDao.selectOne(new QueryWrapper<Equipment>().eq("uuid", uuid));
        if (one == null) {
            return JsonUtils.getInstance().formatLayerJson(300, "设备不存在", null);
        }
        Equipment equipment = new Equipment();
        equipment.setUuid(uuid);
        equipment.setName(name);
        equipment.setProcess(process);
        equipment.setStatus(status);
        int flag = equipmentDao.updateById(equipment);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String delEquipment(HttpServletRequest request) {
        String uuid = request.getParameter("equipment_uuid");
        int flag = equipmentDao.delete(new QueryWrapper<Equipment>().eq("uuid", uuid));
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

}
