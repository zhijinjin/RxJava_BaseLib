package com.zjj.baselibrary.http;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.alibaba.fastjson.JSONObject;
import com.zjj.baselibrary.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by zhijinjin on 2018/3/28.
 */

public class ParameterMap implements Iterable<Map.Entry<String, Object>> {

    private Map<String, Object> map = new HashMap<>();

    public Object get(String key){
        return map.get(key);
    }

    public String getString(String key){
        return containsKey(key) ?
                map.get(key).toString() :
                null;
    }

    public ParameterMap put(String key, Object value){
        map.put(key, value);
        return this;
    }

    public ParameterMap putAll(Map<String, Object> map){
        this.map.putAll(map);
        return this;
    }

    public boolean containsKey(String key){
        return map.containsKey(key);
    }

    public Map<String, Object> getMap(){
        Map<String, Object> result = new HashMap<>();
        result.putAll(this.map);

        return result;
    }

    public String toJSONString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        return jsonObject.toString();
    }

    public String toQueryString(){
        List<String> parameterList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parameterList.add(entry.getKey() + "=" + (entry.getValue() == null ? "" : entry.getValue().toString()));
        }
        return  Util.join("&", parameterList);
    }

    @NonNull
    @Override
    public Iterator<Map.Entry<String, Object>> iterator() {
        return  map.entrySet().iterator();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void forEach(Consumer<? super Map.Entry<String, Object>> action) {
        if (action == null)
            return;

        for (Map.Entry<String, Object> e : map.entrySet()) {
            action.accept(e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Spliterator<Map.Entry<String, Object>> spliterator() {
        return map.entrySet().spliterator();
    }
}
