package com.poshing.swr.services;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 21:28
 * @Description
 */
public interface VisualInspectionService {

    String getAllRecord(HttpServletRequest request);

    String searchByDate(HttpServletRequest request);
}
