package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GtCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 训练id
     */
    private Integer trainId;

    /**
     * 数据时间
     */
    private Date btutcTime;

    /**
     * 手环imei
     */
    private String imei;

    /**
     * 数据标识（4:计步数据、6:心率数据 、30:电池电量数据、19:SOS报警数据、8:GPS数据）
     */
    private Integer type;

    /**
     * 步数
     */
    private Integer steps;

    /**
     * 心率
     */
    private Integer heartbeat;

    /**
     * 电池电量的百分比
     */
    private Integer battery;

    /**
     * 信号值
     */
    @TableField("`signal`")
    private Integer signal;

    /**
     * 血氧
     */
    private Integer bloodoxygen;

    /**
     * 高压
     */
    private Integer hbp;

    /**
     * 低压
     */
    private Integer sbp;

    /**
     * 经度
     */
    private String lon;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 距离
     */
    private Integer distance;

    /**
     * 能耗
     */
    private Integer calorie;

    /**
     * 心电
     */
    private String electrocardio;

    /**
     * 心电排序
     */
    private Integer ecgSort;

    /**
     * 轨迹
     */
    private String contrail;

    /**
     * 心电组id
     */
    private String electrocardioId;

    /**
     * 速度，单位为公里/时
     */
    private Double speed;

    /**
     * 方向
     */
    private Integer direction;

    /**
     * 状状态(1:GPS定位成功、0:GPS未定位)
     */
    @TableField("`status_gps`")
    private String statusGPS;

    /**
     * GPS开始时间
     */
    private Date startTime;

}