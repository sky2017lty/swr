package com.poshing.swr.services;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 17:58
 * @Description
 */
public interface ToolService {
    String getAllTool();

    String addTool(HttpServletRequest request);

    String editTool(HttpServletRequest request);

    String delTool(HttpServletRequest request);

    String toolSearch(HttpServletRequest request);

    String getOneTool(HttpServletRequest request);
}
