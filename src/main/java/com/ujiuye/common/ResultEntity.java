package com.ujiuye.common;

import java.util.HashMap;
import java.util.Map;

public class ResultEntity {

    public Map<String,Object> map = new HashMap<>();
    public ResultEntity put(String key,Object object){
        this.map.put(key, object);
        return this;
    }
    public static ResultEntity success(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.map.put("statusCode",200);
        resultEntity.map.put("msg","响应成功");
        return  resultEntity;
    }
    public static ResultEntity error(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.map.put("statusCode",500);
        resultEntity.map.put("msg","服务端异常");
        return  resultEntity;
    }
}
