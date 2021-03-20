package com.poshing.swr.controller;

import com.poshing.swr.services.EquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 12:32
 * @Description
 */
@Controller
public class EquipmentController {

    @Resource
    private EquipmentService equipmentService;

    @GetMapping("/getAllEquipment")
    @ResponseBody
    public String getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/getOneEquipment")
    @ResponseBody
    public String getOneEquipment(HttpServletRequest request) {
        return equipmentService.getOneEquipment(request);
    }

    @GetMapping("/addEquipment")
    @ResponseBody
    public String addEquipment(HttpServletRequest request) {
        return equipmentService.addEquipment(request);
    }

    @GetMapping("/delEquipment")
    @ResponseBody
    public String delEquipment(HttpServletRequest request) {
        return equipmentService.delEquipment(request);
    }

    @GetMapping("/editEquipment")
    @ResponseBody
    public String editEquipment(HttpServletRequest request) {
        return equipmentService.editEquipment(request);
    }

    @PostMapping("/equipmentSearch")
    @ResponseBody
    public String equipmentSearch(HttpServletRequest request) {
        return equipmentService.equipmentSearch(request);
    }
}
