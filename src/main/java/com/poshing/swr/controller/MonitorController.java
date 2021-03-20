package com.poshing.swr.controller;

import com.poshing.swr.services.MonitorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/5 0005 23:04
 * @Description
 */
@Controller
public class MonitorController {

    @Resource
    private MonitorService monitorService;

    @GetMapping("/getPauseRecord")
    @ResponseBody
    public String getPauseRecord(HttpServletRequest request) {
        return monitorService.getPauseRecord(request);
    }

    @GetMapping("/insertPauseRecord")
    @ResponseBody
    public String insertPauseRecord(HttpServletRequest request) {
        return monitorService.insertPauseRecord(request);
    }

    @GetMapping("/updatePauseRecord")
    @ResponseBody
    public String updatePauseRecord(HttpServletRequest request) {
        return monitorService.updatePauseRecord(request);
    }

    @GetMapping("/deletePauseRecord")
    @ResponseBody
    public String deletePauseRecord(HttpServletRequest request) {
        return monitorService.deletePauseRecord(request);
    }

    @GetMapping("/updateMonitorRecord")
    @ResponseBody
    public String updateMonitorRecord(HttpServletRequest request) {
        return monitorService.updateMonitorRecord(request);
    }

    @GetMapping("/getMonitorRecord")
    @ResponseBody
    public String getMonitorRecord(HttpServletRequest request) {
        return monitorService.getMonitorRecord(request);
    }

    @GetMapping("/getAllMonitorRecord")
    @ResponseBody
    public String getAllMonitorRecord(HttpServletRequest request) {
        return monitorService.getAllMonitorRecord(request);
    }

    @GetMapping("/deleteMonitorRecord")
    @ResponseBody
    public String deleteMonitorRecord(HttpServletRequest request) {
        return monitorService.deleteMonitorRecord(request);
    }

    @PostMapping("/searchMonitorRecordByDate")
    @ResponseBody
    public String searchMonitorRecordByDate(HttpServletRequest request) {
        return monitorService.searchMonitorRecordByDate(request);
    }

}
