package com.zjj.baselibrary.http;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by zhijinjin on 2018/3/28.
 */

public class HttpResult {

    private boolean successful = false;
    private String error = null;
    private String rawJson = null;
    private Exception exception;
    private JSONObject jsonObject;

    public boolean isSuccessful() {
        return successful;
    }

    public String getError() {
        return error;
    }

    public String getRawJson() {
        return rawJson;
    }
    public Exception getException() {
        return exception;
    }

    public boolean isLocalError(){
        return exception != null;
    }

    public Map<String, Object> parseMap(){
        return JSONObject.parseObject(rawJson);
    }

    public <T> T parseObject(Class<T>  classzz){
        return jsonObject == null ?
                null :
                jsonObject.toJavaObject(classzz);
    }

    public String toString(){
        return String.format("success:%s, error:%s, exception:%s rawJson:%s",
                this.successful,
                this.error,
                this.exception,
                this.rawJson);
    }

    public static HttpResult FromJsonString(String jsonString){
        HttpResult result = new HttpResult();
        result.rawJson = jsonString;
        result.jsonObject = JSONObject.parseObject(jsonString);
        JSONObject json = result.jsonObject;
        if (json.containsKey("success")){
            result.successful = json.getBoolean("success");
        }
        if (json.containsKey("error")){
            result.error = json.getString("error");
        }
        return result;
    }

    public static HttpResult Failed(Exception ex){
        HttpResult result = new HttpResult();
        result.successful = false;
        result.error = ex.getMessage();
        result.exception = ex;

        return result;
    }
}
