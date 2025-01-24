package com.baizhi.entity.excelEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 场地表
 *
 * @author wyy
 * @since 2020-07-29
 */
@Data
@Accessors(chain = true)
public class GaTrainSourceVo {

    /** 身高 */
    private Integer height;

    /** 身高 */
    private Double DoubleHeight;

    /** 体重 */
    private Double weight;

    /** bmi */
    private String bmi;

    /** pbf */
    private String pbf;

    /** bmi状态 */
    private String bmiStandard;

    /** pbf状态 */
    private String pbfStandard;

    /** 形体状态 */
    private String shapeStatus;

    /** 俯卧撑/单杠引体向上/曲臂悬垂个数 */
    private String upNum;

    /** 俯卧撑/单杠引体向上/曲臂悬垂url */
    private String upUrl;

    /** 俯卧撑/单杠引体向上/曲臂悬垂 分数 */
    private Integer upScore;

    /** 俯卧撑/单杠引体向上/曲臂悬垂 状态 */
    private String upStatus;

    /** 3000m个数 */
    private String threekmTime;

    /** 3000murl */
    private String threekmUrl;

    /** 3000m分数 */
    private Integer threekmScore;

    /** 3000m状态 */
    private String threekmpStatus;

    /** 仰卧起坐个数 */
    private Integer sitUpNum;

    /** 仰卧起坐url */
    private String sitUpUrl;

    /** 仰卧起坐分数 */
    private Integer sitUpScore;

    /** 仰卧起坐状态 */
    private String sitUpStatus;

    /** 蛇形跑个数 */
    private String snakeTime;

    /** 蛇形跑url */
    private String snakeUrl;

    /** 蛇形跑分数 */
    private Integer snakeScore;

    /** 蛇形跑状态 */
    private String snakeStatus;
}
