package com.poshing.swr.controller;

import com.poshing.swr.services.ToolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 17:57
 * @Description
 */
@Controller
public class ToolController {

    @Resource
    private ToolService toolService;

    /**
     * 获取所有工具
     * @param request
     * @return
     */
    @GetMapping("/getAllTool")
    @ResponseBody
    public String getAllTool(HttpServletRequest request) {
        return toolService.getAllTool();
    }

    /**
     * 获取所有工具
     * @param request
     * @return
     */
    @GetMapping("/getOneTool")
    @ResponseBody
    public String getOneTool(HttpServletRequest request) {
        return toolService.getOneTool(request);
    }

    /**
     * 添加
     * @param request
     * @return
     */
    @GetMapping("/addTool")
    @ResponseBody
    public String addTool(HttpServletRequest request) {
        return toolService.addTool(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @GetMapping("/editTool")
    @ResponseBody
    public String editTool(HttpServletRequest request) {
        return toolService.editTool(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @GetMapping("/delTool")
    @ResponseBody
    public String delTool(HttpServletRequest request) {
        return toolService.delTool(request);
    }

    /**
     * 查询
     * @param request
     * @return
     */
    @PostMapping("/toolSearch")
    @ResponseBody
    public String toolSearch(HttpServletRequest request) {
        return toolService.toolSearch(request);
    }
}
