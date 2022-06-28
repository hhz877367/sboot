package com.baizhi.entity.excelEntity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lbb
 * @since 2020-07-29
 */
@Data
@Accessors(chain = true)
public class GaSourceListVo {

  /** 任务Id */
  private Long trainId;

  /** 任务名称 */
  private String trianName;

  /** 开始时间 */
  private String startTime;

  /** 结束时间 */
  private String endTime;

  /** userid */
  private Long userId;

  /** 人员姓名 */
  private String userName;

  /** 性别(1: 男; 0: 女；2: 未知) */
  private Integer gender;

  private Integer userType;
  private String birthday;

  /** 年龄 */
  private Integer age;

  /** 部门名称 */
  private String deptName;

  private GaTrainSourceVo gaTrainSourceVo;

  /** 总分数 */
  private Integer score = 0;

  /** 总评价 */
  private String source = "--";
  /** 身份证号 */
  private String idCard;
  /**
   * 项目搜索ID
   */
  private Integer projectId;
}
