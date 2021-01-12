package com.fante.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Map通用处理方法
 *
 * @author fante
 */
public class MapDataUtil {

    /**
     * 请求参数转为Map
     * @param request
     * @return
     */
    public static Map<String, Object> convertDataMap(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<?> entries = properties.entrySet().iterator();
        Map.Entry<?, ?> entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry<?, ?>) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 参数Map转换为请求字符串<br/>
     * 非空参数值的参数按照参数名ASCII码从小到大排序（字典序）<br/>
     * 即key1=value1&key2=value2…
     * @param paramMap
     * @return
     */
    public static String convertMapToReqStr(Map<String, String> paramMap){
        TreeMap<String, String> sortMap = new TreeMap<> (paramMap);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : sortMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                builder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        String paramStr = builder.toString();
        if (StringUtils.isNotBlank(paramStr)) {
            paramStr = paramStr.substring(0, paramStr.length() - 1);
        }
        return paramStr;
    }
}
