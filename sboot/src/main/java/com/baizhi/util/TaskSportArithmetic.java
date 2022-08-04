package com.baizhi.util;

import com.baizhi.entity.GtCollect;
import com.github.pagehelper.util.StringUtil;

import java.util.*;

public class TaskSportArithmetic {





    public static ArrayList<GtCollect> checkList(List<GtCollect> list) {
        ArrayList<GtCollect> listCollect = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GtCollect c = list.get(i);
            if (c.getHeartbeat() != null && c.getBtutcTime() != null) {
                listCollect.add(c);
            }
        }
        return listCollect;
    }

    /**
     * 计算 运动时长  heartbeatList的size
     * 计算 运动量(运动冲量) heartbeatList的累计值
     * 计算 运动时长内平均心率值   heartbeatList的累计值/heartbeatList的size
     * 滑动窗口算法
     */
    public static Map<String, Object> getSportTimesByAge(List<GtCollect> list, Date startTime, Integer age) {
        Map<String, Object> map = new HashMap<>();
        Date dateN1 = new Date();
        Integer taskSprotHeartbeatStatus = (220 - age) / 2;
        //排除异常数据
        ArrayList<GtCollect> listCollect = checkList(list);
        // heartbeatList 用于计算运动量
        List<Integer> heartbeatList = new ArrayList<>();
        if (StringUtils.isNotEmpty(listCollect)) {
            // 取第一个List中的buttime
            Date firstBtutcTime = listCollect.get(0).getBtutcTime();
            //定义时间差大小为1分钟
            Long diffTime = 1000 * 60L;
            if (firstBtutcTime != null) {
                Long firstTime = firstBtutcTime.getTime();
                //求得N分钟运动时间内的每分钟平均运动心率的累加值，放入到 heartbeatList 中
                Integer sumheard = 0;
                Integer sumheardIndex = 0;
                for (GtCollect gc : listCollect) {
                    if (gc.getBtutcTime() != null && gc.getHeartbeat() != null) {
                        if (gc.getBtutcTime().getTime() - firstTime >= diffTime) {
                            firstTime = gc.getBtutcTime().getTime();
                            if (sumheardIndex != 0) {
                                int avgHeard = Utils.getIntByObject(new Double(sumheard) / sumheardIndex);
                                if (avgHeard >= taskSprotHeartbeatStatus) {
                                    heartbeatList.add(avgHeard);
                                }
                                sumheard = 0;
                                sumheardIndex = 0;
                                sumheard = sumheard + gc.getHeartbeat();
                                sumheardIndex++;
                            }
                        } else {
                            sumheard = sumheard + gc.getHeartbeat();
                            sumheardIndex++;
                        }
                    }
                }
            }
        }
        //运动时间
        map.put("time", heartbeatList.size());
        map.put("sportStarttimes", startTime);
        //运动量(冲量)
        map.put("heartbeats", heartbeatList);
        //运动时长内平均心率值
        if(heartbeatList.size() != 0 ){
            Integer sumHeart=0;
            for(Integer heart:heartbeatList){
                sumHeart=sumHeart+heart;
            }
            map.put("avgTimeHeart", Utils.getIntByObject(new Double(sumHeart)/heartbeatList.size()));
        }
        Date dateN2 = new Date();

        //计算消耗时间
        long l = dateN2.getTime() - dateN1.getTime();
        return map;
    }
}
