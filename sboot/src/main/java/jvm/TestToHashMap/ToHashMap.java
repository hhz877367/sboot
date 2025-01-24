package jvm.TestToHashMap;

import com.google.gson.GsonBuilder;

import java.util.*;

public class ToHashMap {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "/etc/hasts",
                "/etc/kubernetes/ssl/certs",
                "/etc/kubernetes/ssl/certs/lianxi",
                "/root"
        );
        Map<String, Map> result = toHashMap(list);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(result));

    }

    public  static Map<String, Map> toHashMap(List<String> list){
        Map<String, Map> result= new HashMap<>();
        //递归构建map链
        for (int i=0;i<list.size();i++) {
            String[] split = list.get(i).split("/");
            toMap(result,null,split,1);
        }
        return result;
    }

    public static void toMap( Map<String, Map> result,Map<String, Map> parrentMap, String[]  split,int index){
        if(index>=split.length){
            return;
        }else {
            if(result.get(split[index])==null){
                Map<String, Map> map=new HashMap<>();
                if(parrentMap==null){
                    result.put(split[index],map);
                }else {
                    parrentMap.put(split[index],map);
                }
                index++;
                toMap(result,map,split,index);
            }else {
                //如果etc存在，则什么也不做，把etc传下去
                Map map = result.get(split[index]);
                index++;
                toMap(map,null,split,index);
            }
        }
    }






}
