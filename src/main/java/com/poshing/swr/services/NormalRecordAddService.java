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
}
