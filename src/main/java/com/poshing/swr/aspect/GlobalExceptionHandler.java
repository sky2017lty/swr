package com.poshing.swr.aspect;

import com.alibaba.fastjson.JSONObject;
import com.poshing.swr.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiTianyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        LOGGER.error(String.valueOf(e.getCause()));
        return JsonUtils.getInstance().formatLayerJson(500, e.getCause().getMessage(), null);
    }
}
