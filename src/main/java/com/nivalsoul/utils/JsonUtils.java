package com.nivalsoul.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtils {

    // private static final Logger LOG =
    // LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T jsonToBean(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    public static String beanToJson(Object o) {
    	return JSON.toJSONString(o);
    }

}
