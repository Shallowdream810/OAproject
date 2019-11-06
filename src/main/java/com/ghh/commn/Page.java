package com.ghh.commn;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/*
* 此工具类，需要你前端的条件查询的name关键字以search_开头，且模糊查询的name关键字以search_like
*
* */
public class Page {
    //通过方法获取对应的map类型，将区分模糊查询和精确查询，对模糊查询的条件添加like
    public static Map<String, String> getMapString(Map<String, Object> search) {
        Map<String,String> map = new HashMap<>();
//        遍历获取的search的map集合
        Set<Map.Entry<String, Object>> entries = search.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            //获取前端传来的数据进行解析转换
            String key = entry.getKey();
            String value =(String) entry.getValue();
            if (key.contains("like")){
                value = "%"+value+"%";
            }
            map.put(key,value);
        }
        return map;
    }
    //由于我们传过去模糊查询的数据之后，在点击首页，下一页等信息是发送的请求中，并未携带上次请求的
    //的keyword和status等信息，所以我们需要在后台将这些信息在返回到前台页面
    public static String addSearch(Map<String, Object> search) {
        Set<Map.Entry<String, Object>> entries = search.entrySet();
        String requestInfo = "";
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            requestInfo =  requestInfo+"&"+"search_" + key + "=" + value;
        }
        return requestInfo;
    }
}
