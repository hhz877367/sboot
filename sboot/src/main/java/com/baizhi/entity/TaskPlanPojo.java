package com.baizhi.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Crazy.X
 * @DATE 2020/2/15
 */
@Data
@Accessors(chain = true)
public class TaskPlanPojo {

    /**
     * 训练任务ID
     */
    private Long trainId;

    /**
     * 教练Id
     */
    private Long userId;

    /**
     * 部门Id
     */
    private Long deptId;

    /**
     * 任务类别
     */
    private String taskCategory;

    /**
     * 任务状态
     */
    private String taskStatus;

    /**
     * 训练开始时间
     */
    private String startTime;

    /**
     * 训练结束时间
     */
    private String endTime;

    /**
     * 部门
     */
    private String deptName;

    /**
     * 应出勤人数
     */
    private Integer attendance;
    /**
     * 教官
     */
    private String instructor;
}
