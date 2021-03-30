package com.poshing.swr.controller;

import com.poshing.swr.services.VisualInspectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 19:46
 * @Description
 */
@Controller
public class VisualInspectionController {

    @Resource
    private VisualInspectionService visualInspectionService;

    /**
     * 获取所有非班长记录
     * @param request
     * @return
     */
    @GetMapping("/getAllRecord")
    @ResponseBody
    public String getAllRecord(HttpServletRequest request) {
        return visualInspectionService.getAllRecord(request);
    }

    /**
     * 通过日期查找记录
     * @param request
     * @return
     */
    @PostMapping("/searchByDate")
    @ResponseBody
    public String searchByDate(HttpServletRequest request) {
        return visualInspectionService.searchByDate(request);
    }

    /**
     * 查找当班记录
     * @param request
     * @return
     */
    @PostMapping("/getNormalRecordNow")
    @ResponseBody
    public String getRecordNow(HttpServletRequest request) {
        return visualInspectionService.getRecordNow(request);
    }

    /**
     * 查找当班设备记录
     * @param request
     * @return
     */
    @GetMapping("/getEquipmentRecordNow")
    @ResponseBody
    public String getEquipmentRecordNow(HttpServletRequest request) {
        return visualInspectionService.getEquipmentRecordNow(request);
    }

    /**
     * 查找当班工具记录
     * @param request
     * @return
     */
    @GetMapping("/getToolRecordNow")
    @ResponseBody
    public String getToolRecordNow(HttpServletRequest request) {
        return visualInspectionService.getToolRecordNow(request);
    }
}
