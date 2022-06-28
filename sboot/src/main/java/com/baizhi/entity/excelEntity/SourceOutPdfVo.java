package com.baizhi.entity.excelEntity;

import com.baizhi.enums.Excel;
import lombok.Data;

@Data
public class SourceOutPdfVo {

  @Excel(name = "编号")
  private String id;

  @Excel(name = "姓名")
  private String userName;

  @Excel(name = "性别")
  private String sex;

  @Excel(name = "年龄")
  private String age;

  @Excel(name = "单位名称")
  private String unitName;

  @Excel(name = "身高")
  private String height;

  @Excel(name = "体重")
  private String weight;

  //俯卧撑，仰卧起坐，曲臂悬垂
  @Excel(name = "俯卧撑")
  private String pushUpConsts;

  @Excel(name = "仰卧起坐")
  private String sitUpsConsts;

  @Excel(name = "往返跑")
  private String goBackRun;

  @Excel(name = "3000米跑")
  private String racesConsts;

  @Excel(name = "引体向上")
  private String pullUp;

  @Excel(name = "屈臂悬垂")
  private String cantileVeredArmConsts;

  @Excel(name = "人员类别")
  private String peopleType;

  @Excel(name = "驻地海拔")
  private String seaLeave;
}
