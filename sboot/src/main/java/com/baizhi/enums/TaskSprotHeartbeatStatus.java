package com.baizhi.enums;

/**
 * 用户状态
 *
 * @author gmz
 */
public enum TaskSprotHeartbeatStatus {
    SPORT_HEARTBEAT(100, "运动心率标准值"),
    ONE_MINUTE(60000, "一分钟"),
    SPORT_TIME_10(10, "分钟"),
    SPORT_TIME_20(20, "分钟"),
    SPORT_TIME_30(30, "分钟");

    private final int value;
    private final String info;

    TaskSprotHeartbeatStatus(int value, String info) {
        this.value = value;
        this.info = info;
    }

    public int getValue() {
        return value;
    }

    public String getInfo() {
        return info;
    }
}
