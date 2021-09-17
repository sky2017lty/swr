package com.poshing.swr.controller;

import com.poshing.swr.services.NormalRecordAddService;
import com.poshing.swr.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/3 0003 12:05
 * @Description
 */
@Controller
public class NormalRecordAddController {

    @Resource
    private NormalRecordAddService normalRecordAddService;

    /**
     * 增加设备记录
     * @param request
     * @return
     */
    @GetMapping("/insertEquipmentRecord")
    @ResponseBody
    public String insertEquipmentRecord(HttpServletRequest request) {
        return normalRecordAddService.insertEquipmentRecord(request);
    }

    /**
     * 修改设备记录
     * @param request
     * @return
     */
    @GetMapping("/deleteEquipmentRecord")
    @ResponseBody
    public String deleteEquipmentRecord(HttpServletRequest request) {
        return normalRecordAddService.deleteEquipmentRecord(request);
    }

    /**
     * 修改设备记录
     * @param request
     * @return
     */
    @GetMapping("/updateEquipmentRecord")
    @ResponseBody
    public String updateEquipmentRecord(HttpServletRequest request) {
        return normalRecordAddService.updateEquipmentRecord(request);
    }

    /**
     * 修改工具记录
     * @param request
     * @return
     */
    @GetMapping("/updateToolRecord")
    @ResponseBody
    public String updateToolRecord(HttpServletRequest request) {
        return normalRecordAddService.updateToolRecord(request);
    }

    /**
     * 修改记录
     * @param request
     * @return
     */
    @PostMapping("/updateRecord")
    @ResponseBody
    public String updateRecord(HttpServletRequest request) {
        return normalRecordAddService.updateRecord(request);
    }

    /**
     * 修改不合格记录
     * @param request
     * @return
     */
    @GetMapping("/getUnQualifiedRecordNow")
    @ResponseBody
    public String getUnQualifiedRecordNow(HttpServletRequest request) {
        return normalRecordAddService.getUnQualifiedRecordNow(request);
    }

    @GetMapping("/insertUnQualifiedRecord")
    @ResponseBody
    public String insertUnQualifiedRecord(HttpServletRequest request) {
        return normalRecordAddService.insertUnQualifiedRecord(request);
    }

    @GetMapping("/deleteUnQualifiedRecord")
    @ResponseBody
    public String deleteUnQualifiedRecord(HttpServletRequest request) {
        return normalRecordAddService.deleteUnQualifiedRecord(request);
    }

    @GetMapping("/updateUnQualifiedRecord")
    @ResponseBody
    public String updateUnQualifiedRecord(HttpServletRequest request) {
        return normalRecordAddService.updateUnQualifiedRecord(request);
    }

    @GetMapping("/insertCheckDayRecord")
    @ResponseBody
    public String insertCheckDayRecord(HttpServletRequest request) {
        return normalRecordAddService.insertCheckDayRecord(request);
    }

    @GetMapping("/createCheckDayRecord")
    @ResponseBody
    public String createCheckDayRecord(HttpServletRequest request) {
        return normalRecordAddService.createCheckDayRecord(request);
    }

    @GetMapping("/getCheckDayRecord")
    @ResponseBody
    public String getCheckDayRecord(HttpServletRequest request) {
        return normalRecordAddService.getCheckDayRecord(request);
    }

    @GetMapping("/delCheckDayRecord")
    @ResponseBody
    public String delCheckDayRecord(HttpServletRequest request) {
        return normalRecordAddService.delCheckDayRecord(request);
    }

    @GetMapping("/updateCheckDayRecord")
    @ResponseBody
    public String updateCheckDayRecord(HttpServletRequest request) {
        return normalRecordAddService.updateCheckDayRecord(request);
    }
}
