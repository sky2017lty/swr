package com.poshing.swr.services;

import com.poshing.swr.entity.Equipment;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/2/28 0028 16:34
 * @Description
 */
public interface IndexService {

    String getAllProcess();

    String getEquipment(HttpServletRequest request);

    String getImportantMatterLong(HttpServletRequest request);

    String getTool(HttpServletRequest request);

    String getRecord(HttpServletRequest request);

    String getEquipmentRecord(HttpServletRequest request);

    String getToolRecord(HttpServletRequest request);

    String deleteRecord(HttpServletRequest request);

    String checkRecord(HttpServletRequest request);

//    String addProcess();
}
