package com.poshing.swr.services.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poshing.swr.dao.ToolDao;
import com.poshing.swr.entity.Equipment;
import com.poshing.swr.entity.Tool;
import com.poshing.swr.services.ToolService;
import com.poshing.swr.utils.JsonUtils;
import com.poshing.swr.utils.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 17:58
 * @Description
 */
@Service
public class ToolServiceImpl implements ToolService {

    @Resource
    private ToolDao toolDao;

    @Override
    public String getAllTool() {
        List<Tool> toolList = toolDao.selectList(new QueryWrapper<Tool>());
        return JsonUtils.getInstance().formatLayerJson(0, "success", toolList.size(), JSON.toJSONString(toolList));
    }

    @Override
    public String addTool(HttpServletRequest request) {
        String name = request.getParameter("tool_name");
        String process = request.getParameter("tool_process");
        String detail = request.getParameter("tool_detail");
        String number = request.getParameter("tool_number");
        Tool one = toolDao.selectOne(new QueryWrapper<Tool>()
                .eq("name", name)
                .eq("process", process)
                .eq("detail", detail));
        if (one != null) {
            return JsonUtils.getInstance().formatLayerJson(300, "设备已存在", null);
        }
        Tool tool = new Tool();
        tool.setUuid(UuidUtil.getUuid());
        tool.setName(name);
        tool.setDetail(detail);
        tool.setProcess(process);
        tool.setNum(number);
        tool.setStatus("0");
        int flag = toolDao.insert(tool);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String editTool(HttpServletRequest request) {
        String uuid = request.getParameter("tool_uuid");
        String name = request.getParameter("tool_name");
        String process = request.getParameter("tool_process");
        String detail = request.getParameter("tool_detail");
        String number = request.getParameter("tool_number");
        String status = request.getParameter("tool_status");
        String lastchecktime = request.getParameter("tool_lastchecktime");
        String checkrecord = request.getParameter("tool_checkrecord");
        Tool one = toolDao.selectOne(new QueryWrapper<Tool>()
                .eq("uuid", uuid));
        if (one == null) {
            return JsonUtils.getInstance().formatLayerJson(300, "设备不存在", null);
        }
        Tool tool = new Tool();
        tool.setUuid(uuid);
        tool.setName(name);
        tool.setDetail(detail);
        tool.setProcess(process);
        tool.setNum(number);
        tool.setStatus(status);
        tool.setLastchecktime(lastchecktime);
        tool.setCheckrecord(checkrecord);
        int flag = toolDao.updateById(tool);
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String delTool(HttpServletRequest request) {
        String uuid = request.getParameter("tool_uuid");
        int flag = toolDao.delete(new QueryWrapper<Tool>().eq("uuid", uuid));
        if (1 == flag) {
            return JsonUtils.getInstance().formatLayerJson(0, "success", null);
        } else {
            return JsonUtils.getInstance().formatLayerJson(200, "failed", null);
        }
    }

    @Override
    public String toolSearch(HttpServletRequest request) {
        String name = request.getParameter("tool_name");
        List<Tool> toolList = toolDao.selectList(new QueryWrapper<Tool>().like("name", name));
        return JsonUtils.getInstance().formatLayerJson(0, "success", toolList.size(), JSON.toJSONString(toolList));
    }

    @Override
    public String getOneTool(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        Tool one = toolDao.selectOne(new QueryWrapper<Tool>()
                .eq("uuid", uuid));
        return JSON.toJSONString(one);
    }
}
