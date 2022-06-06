package com.baizhi.util;

import com.baizhi.controller.WebSocketController;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WebSocketMapUtil {

    public static ConcurrentMap<String, WebSocketController> webSocketMap = new ConcurrentHashMap<>();

    public static void put(String key, WebSocketController webSocketController){
        webSocketMap.put(key, webSocketController);
    }

    public static WebSocketController get(String key){
        return webSocketMap.get(key);
    }

    public static void remove(String key){
        webSocketMap.remove(key);
    }

    public static Collection<WebSocketController> getValues(){
        return webSocketMap.values();
    }
}
