package com.baizhi.entity_train;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class GaTrainQueue {
    private Long id;
    /**
     * 训练id
     */
    private Long trainId;
    /**
     * 用户id
     */
    private Long userId;

    // 场地id
    private Long placeId;
    /**
     * 考位id
     */
    private Long positionId;

    /**
     * 项目id
     */
    private Long projectId;
}
