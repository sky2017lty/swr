package com.poshing.swr.controller;

import com.poshing.swr.entity.Equipment;
import com.poshing.swr.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/2/21 0021 11:59
 * @Description
 */
@Controller
public class IndexController {

    @Resource
    private IndexService indexService;

    @GetMapping("/getAllProcess")
    @ResponseBody
    public String getAllProcess() {
        return indexService.getAllProcess();
    }

    @GetMapping("/getEquipment")
    @ResponseBody
    public String getEquipment(HttpServletRequest request) {
        return indexService.getEquipment(request);
    }

    @GetMapping("/getTool")
    @ResponseBody
    public String getTool(HttpServletRequest request) {
        return indexService.getTool(request);
    }


    @GetMapping("/getImportantMatterLong")
    @ResponseBody
    public String addTool(HttpServletRequest request) {
        return indexService.getImportantMatterLong(request);
    }

    @GetMapping("/getRecord")
    @ResponseBody
    public String getRecord(HttpServletRequest request) {
        return indexService.getRecord(request);
    }

    @GetMapping("/getEquipmentRecord")
    @ResponseBody
    public String getEquipmentRecord(HttpServletRequest request) {
        return indexService.getEquipmentRecord(request);
    }

    @GetMapping("/getToolRecord")
    @ResponseBody
    public String getToolRecord(HttpServletRequest request) {
        return indexService.getToolRecord(request);
    }

    @GetMapping("/deleteRecord")
    @ResponseBody
    public String deleteRecord(HttpServletRequest request) {
        return indexService.deleteRecord(request);
    }

    @GetMapping("/checkRecord")
    @ResponseBody
    public String checkRecord(HttpServletRequest request) {
        return indexService.checkRecord(request);
    }

}
