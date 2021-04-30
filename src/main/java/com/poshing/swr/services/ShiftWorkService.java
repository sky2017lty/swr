package com.poshing.swr.services;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiTianyi
 */
public interface ShiftWorkService {
    /**
     * 交接班人员
     * @return
     */
    String getShiftWorkPeople(HttpServletRequest request);
}
