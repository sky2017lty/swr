package com.poshing.swr.controller;

import com.poshing.swr.services.NormalRecordAddService;
import com.poshing.swr.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/updateRecord")
    @ResponseBody
    public String updateRecord(HttpServletRequest request) {
        return normalRecordAddService.updateRecord(request);
    }

}
