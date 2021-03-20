package com.poshing.swr.services;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/5 0005 23:04
 * @Description
 */
public interface MonitorService {
    String insertPauseRecord(HttpServletRequest request);

    String updatePauseRecord(HttpServletRequest request);

    String deletePauseRecord(HttpServletRequest request);

    String updateMonitorRecord(HttpServletRequest request);

    String getMonitorRecord(HttpServletRequest request);

    String deleteMonitorRecord(HttpServletRequest request);

    String getAllMonitorRecord(HttpServletRequest request);

    String getPauseRecord(HttpServletRequest request);

    String searchMonitorRecordByDate(HttpServletRequest request);
}
