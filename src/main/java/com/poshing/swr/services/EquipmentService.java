package com.poshing.swr.services;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiTianyi
 * @Date: 2021/3/1 0001 12:32
 * @Description
 */
public interface EquipmentService {

    String addEquipment(HttpServletRequest request);

    String getAllEquipment();

    String equipmentSearch(HttpServletRequest request);

    String getOneEquipment(HttpServletRequest request);

    String editEquipment(HttpServletRequest request);

    String delEquipment(HttpServletRequest request);
}
