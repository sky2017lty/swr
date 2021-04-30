package com.poshing.swr.controller;

import com.poshing.swr.services.ShiftWorkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/4/30 0030 19:46
 * @Description
 */

@Controller
public class ShiftWorkController {

    @Resource
    private ShiftWorkService shiftWorkService;

    @GetMapping("/getShiftWorkPeople")
    @ResponseBody
    public String getShiftWorkPeople(HttpServletRequest request) {
        return shiftWorkService.getShiftWorkPeople(request);
    }
}
