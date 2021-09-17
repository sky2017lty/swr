package com.poshing.swr.services;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/3 0003 12:06
 * @Description
 */
public interface NormalRecordAddService {
    String insertEquipmentRecord(HttpServletRequest request);

    String updateEquipmentRecord(HttpServletRequest request);

    String updateRecord(HttpServletRequest request);

    String updateToolRecord(HttpServletRequest request);

    String deleteEquipmentRecord(HttpServletRequest request);

    String getUnQualifiedRecordNow(HttpServletRequest request);

    String insertUnQualifiedRecord(HttpServletRequest request);

    String deleteUnQualifiedRecord(HttpServletRequest request);

    String updateUnQualifiedRecord(HttpServletRequest request);

    String insertCheckDayRecord(HttpServletRequest request);

    String createCheckDayRecord(HttpServletRequest request);

    String getCheckDayRecord(HttpServletRequest request);

    String delCheckDayRecord(HttpServletRequest request);

    String updateCheckDayRecord(HttpServletRequest request);
}
