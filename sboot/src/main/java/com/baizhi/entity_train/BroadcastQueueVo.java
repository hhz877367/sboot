package com.baizhi.entity_train;

public class BroadcastQueueVo extends GaTrainQueue {


    /** 用户姓名 */
    private String userName;

    /** 用户id */
    private Long userId;

    /** 场地id */
    private Long positionId;

    /** 项目id */
    private Long projectId;
    /** 用户编号 */
    private String serialNo;

    /** 训练id */
    private Long trainId;

    /** 标记状态， 立即考核第二次为true 为true时开启60秒线程等待考生考核 60后如果未考核放入过号队列 */
    private Boolean flag;

    /** 广播消息 */
    private String msg;
}
