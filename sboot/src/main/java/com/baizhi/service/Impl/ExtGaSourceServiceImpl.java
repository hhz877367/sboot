package com.baizhi.service.Impl;

import com.baizhi.entity.excelEntity.*;
import com.baizhi.entity.word.CustomXWPFDocument;
import com.baizhi.entity.word.WorderToNewWordUtils;
import com.baizhi.service.ExtGaSourceService;
import com.baizhi.util.DateUtils;
import com.baizhi.util.Utils;

import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 考场 业务层处理
 *
 * @author wyy
 */
public class ExtGaSourceServiceImpl implements ExtGaSourceService {



  /**
   * 状态（进行中）
   */
  public static final String STATUS_1 = "1";
  /**
   * 状态（未开始）
   */
  public static final String STATUS_2 = "2";
  /**
   * 状态（已结束）
   */
  public static final String STATUS_3 = "3";

  public static final String SOURCE_1 = "待测";
  public static final String SOURCE_2 = "缺考";

  public static final String SOURCE_3 = "及格";
  public static final String SOURCE_4 = "不及格";
  public static final String SOURCE_5 = "合格";
  public static final String SOURCE_6 = "不合格";



  private static String sourcePath="D:\\temp\\";


  /**
   * 成绩评定
   *
   * @param listVo SourceOutPdfVo集合
   */
  @Override
  public Map getPdfByListSourceOutPdfVo(List<SourceOutPdfVo> listVo,String uuid,String  threadName,String index) {
    HashMap<String, Object> result = new HashMap<>();
    result.put("uuid", uuid);
    List<GaSourceListVo> listGaSourceVo = new ArrayList<>();
    Integer outPDFNameIndex = 1;
    int j=0;
    for (SourceOutPdfVo vo : listVo) {
      j++;
      if(j%25==0){
        System.out.println(threadName+"---index="+index+"执行第"+j+"个");
      }
      GaSourceListVo gaSourceListVo = new GaSourceListVo();
      GaTrainSourceVo gaTrainSourceVo = new GaTrainSourceVo();
      Integer sumScore = 0;
      String sex = "";
      String outPDFName = "";
      Integer age = null;
      Map<String, Object> params = Maps.newHashMap();
      // 渲染map数据
      if (vo.getUserName() != null) {
        params.put("${name}", vo.getUserName());
        gaSourceListVo.setUserName(vo.getUserName());
        outPDFName = "-" + vo.getUserName();
      }
      if (vo.getSex() != null) {
        params.put("${sex}", vo.getSex());
        if (vo.getSex().equals("男")) {
          gaSourceListVo.setGender(1);
        } else {
          gaSourceListVo.setGender(0);
        }
        sex = vo.getSex();
      } else {
        continue;
      }
      // 设置人员类别
      Integer peopleType = 3;
      String peopleTypeStr = "3";
      if (vo.getPeopleType().trim().equals("一类")) {
        peopleType = 1;
        peopleTypeStr = "1";
      } else if (vo.getPeopleType().trim().equals("二类")) {
        peopleType = 2;
        peopleTypeStr = "2";
      } else {
        peopleType = 3;
        peopleTypeStr = "3";
      }
      if (vo.getAge() != null) {
        params.put("${age}", vo.getAge());
        gaSourceListVo.setAge(Integer.parseInt(vo.getAge()));
        age = Integer.parseInt(vo.getAge());
      } else {
        continue;
      }
      if (vo.getUnitName() != null) {
        params.put("${deptName}", vo.getUnitName());
        gaSourceListVo.setDeptName(vo.getUnitName());
      }
      // 设置身高
      if (vo.getHeight() != null) {
        gaTrainSourceVo.setDoubleHeight(new Double(vo.getHeight()));
      }

      // 设置体重
      if (vo.getWeight() != null) {
        gaTrainSourceVo.setWeight(new Double(vo.getWeight()));
      }

      // 计算bmi 和pdf
      if (vo.getWeight() != null && vo.getHeight() != null && vo.getSex() != null) {
        float bmival = 0f;
        if (age < 24) {
          if (sex.equals("男")) { // 男
            bmival = 25.9f;
          } else { // 女
            bmival = 23.9f;
          }
        } else {
          int arrayIndex = GetIndexUtils.getIndex(BmiConsts.AGE_ARRAY, age); // 下标
          float[] bis;
          if (sex.equals("男")) {
            bis = BmiConsts.BMI_ARRAY_1;
          } else {
            bis = BmiConsts.BMI_ARRAY_0;
          }
          bmival = bis[arrayIndex];
        }
        Double weight = new Double(vo.getWeight());
        Double height = new Double(vo.getHeight());
        Double bmiDouble = weight * 10000 / (height * height);
        if (bmiDouble > bmival) {
          gaTrainSourceVo.setShapeStatus("不合格");
          params.put("${status}", "合格□    不合格☑");
        } else if (bmiDouble <= BmiConsts.BMI) {
          gaTrainSourceVo.setShapeStatus("不合格");
          params.put("${status}", "合格□    不合格☑");
        } else {
          gaTrainSourceVo.setShapeStatus("合格");
          params.put("${status}", "合格☑   不合格□");
        }
        Double bmi = Utils.getIntByObjectOne(bmiDouble);
        if (bmi != null) {
          params.put("${bmi}", "BMI:" + bmi.toString());
          gaTrainSourceVo.setBmi(bmi.toString());
        }
      }
      if (params.get("${status}") == null) {
        params.put("${status}", "合格□   不合格□");
      }
      // 计算   单杠引体向上/曲臂悬垂/俯卧撑（40岁以上）
      // 用于计算100分以上的类型
      String standStr = "";
      gaTrainSourceVo.setUpNum(vo.getPushUpConsts());
      int[] sourceFrist = new int[0];
      Integer scoreint = 0;
      if (sex.equals("男")) {
        // 单杠引体向上
        if (age <= 39) {
          // 要求excel里的所有的成绩单元格 可以为 空，可以为未完成，所以这里加了这步判断
          if (StringUtil.isNotEmpty(vo.getPullUp()) && !vo.getPullUp().equals("未完成")) {
            standStr = "单杠引体向上";
            int pullUpIndex = PullUpScoreServiceImpl.getIndex(PullUpConsts.AGE_ARRAY, age);
            sourceFrist = PullUpConsts.SOURCE_ARRAY[pullUpIndex];
            int scoreIndex =
                PullUpScoreServiceImpl.getScoreIndex(sourceFrist, Integer.parseInt(vo.getPullUp()));
            scoreint = PullUpConsts.SCORE_ARRAY[scoreIndex];
            params.put("${upNum}", vo.getPullUp() + "次");
            gaTrainSourceVo.setUpNum(vo.getPullUp());
            params.put("${pushUpNum}", "次");
          } else if (StringUtil.isNotEmpty(vo.getPullUp()) && vo.getPullUp().equals("未完成")) {
            params.put("${upNum}", "次/秒");
            params.put("${upScore}", "分数: 0");
            params.put("${pushUpNum}", "次");
            gaTrainSourceVo.setUpNum("0");
          } else {
            params.put("${upNum}", "次/秒");
            params.put("${pushUpNum}", "次");
          }
        } else {
          if (StringUtil.isNotEmpty(vo.getPushUpConsts()) && !vo.getPushUpConsts().equals("未完成")) {
            standStr = "俯卧撑";
            int pullUpIndex = PushUpScoreServiceImpl.getIndex(PushUpConsts.AGE_ARRAY, age);
            sourceFrist = PushUpConsts.SOURCE_ARRAY_1[pullUpIndex];
            int scoreIndex =
                PushUpScoreServiceImpl.getScoreIndex(
                    sourceFrist, Integer.parseInt(vo.getPushUpConsts()));
            scoreint = PushUpConsts.SCORE_ARRAY[scoreIndex];
            params.put("${upNum}", "次/秒");
            params.put("${pushUpNum}", vo.getPushUpConsts() + "次");
            gaTrainSourceVo.setUpNum(vo.getPushUpConsts());
          } else if (StringUtil.isNotEmpty(vo.getPushUpConsts())
              && vo.getPushUpConsts().equals("未完成")) {
            params.put("${upNum}", "次/秒");
            params.put("${upScore}", "分数: 0");
            params.put("${pushUpNum}", "次");
            gaTrainSourceVo.setUpNum("0");
          } else {
            params.put("${upNum}", "次/秒");
            params.put("${pushUpNum}", "次");
          }
        }
      } else {
        if (age <= 39) {
          if (StringUtil.isNotEmpty(vo.getCantileVeredArmConsts())
              && !vo.getCantileVeredArmConsts().equals("未完成")) {
            standStr = "屈臂悬垂";
            // 计算屈臂悬垂 女
            int CantileVeredArmConstsIndex =
                CantileVeredArmScoreServiceImpl.getIndex(CantileVeredArmConsts.AGE_ARRAY, age);
            sourceFrist = CantileVeredArmConsts.SOURCE_ARRAY[CantileVeredArmConstsIndex];

            if (vo.getCantileVeredArmConsts().length() == 8) {
              String time = vo.getCantileVeredArmConsts();
              String minStr = time.substring(1, 2);
              String secondStr = time.substring(3, 5);
              int timeScore =
                  Integer.parseInt(minStr) * 1000 * 60 + Integer.parseInt(secondStr) * 1000;
              gaTrainSourceVo.setUpNum(minStr + "分" + secondStr + "秒");
              int scoreIndex =
                  CantileVeredArmScoreServiceImpl.getScoreIndex(sourceFrist, timeScore);
              scoreint = CantileVeredArmConsts.SCORE_ARRAY[scoreIndex];
            }
            params.put("${upNum}", vo.getCantileVeredArmConsts().substring(0, 5));
            params.put("${pushUpNum}", "次");
          } else if (StringUtil.isNotEmpty(vo.getCantileVeredArmConsts())
              && vo.getPushUpConsts().equals("未完成")) {
            params.put("${upScore}", "分数: 0");
            params.put("${upNum}", "次/秒");
            params.put("${pushUpNum}", "次");
            gaTrainSourceVo.setUpStatus("未完成");
          } else {
            params.put("${upNum}", "次/秒");
            params.put("${pushUpNum}", "次");
          }
        } else {
          if (StringUtil.isNotEmpty(vo.getPushUpConsts()) && !vo.getPushUpConsts().equals("未完成")) {
            standStr = "俯卧撑";
            int CantileVeredArmConstsIndex =
                CantileVeredArmScoreServiceImpl.getIndex(PushUpConsts.AGE_ARRAY, age);
            sourceFrist = PushUpConsts.SOURCE_ARRAY_0[CantileVeredArmConstsIndex];
            int scoreIndex =
                CantileVeredArmScoreServiceImpl.getScoreIndex(
                    sourceFrist, Integer.parseInt(vo.getPushUpConsts()));
            scoreint = PushUpConsts.SCORE_ARRAY[scoreIndex];
            params.put("${upNum}", "次/秒");
            params.put("${pushUpNum}", vo.getPushUpConsts() + "次");
            gaTrainSourceVo.setUpNum(vo.getPushUpConsts());
          } else if (StringUtil.isNotEmpty(vo.getPushUpConsts())
              && vo.getPushUpConsts().equals("未完成")) {
            params.put("${upScore}", "分数: 0");
            params.put("${upNum}", "次/秒");
            params.put("${pushUpNum}", "次");
            gaTrainSourceVo.setUpStatus("未完成");
          } else {
            params.put("${upNum}", "次/秒");
            params.put("${pushUpNum}", "次");
          }
        }
      }
      // 等于100分的情况下,单杠引体向上1次1分，曲臂悬垂5秒1分，俯卧撑2次1分
      if (scoreint == 100) {
        // 100分的成绩
        int finnalSource = sourceFrist[sourceFrist.length - 1];
        if (standStr.equals("单杠引体向上")) {
          Integer pushUpConsts = Integer.parseInt(vo.getPullUp());
          scoreint = scoreint + (pushUpConsts - finnalSource);
        } else if (standStr.equals("俯卧撑")) {
          Integer pushUpConsts = Integer.parseInt(vo.getPushUpConsts());
          scoreint = scoreint + (pushUpConsts - finnalSource) / 2;
        } else {
          String time = vo.getCantileVeredArmConsts();
          String minStr = time.substring(1, 2);
          String secondStr = time.substring(3, 5);
          int timeScore = Integer.parseInt(minStr) * 1000 * 60 + Integer.parseInt(secondStr) * 1000;
          int diffTimes = timeScore - finnalSource;
          scoreint = scoreint + diffTimes / 5000;
        }
      }
      sumScore = sumScore + scoreint;
      params.put("${upScore}", "分数:" + scoreint);
      gaTrainSourceVo.setUpScore(scoreint);
      String projectSource = getProjectSource(scoreint, peopleTypeStr);
      gaTrainSourceVo.setUpStatus(projectSource);

      // 30*2蛇形跑
      if (vo.getGoBackRun() != null && !vo.getGoBackRun().equals("未完成")) {
        String goBackRun = vo.getGoBackRun();
        int goBack = 0;
        String minStr = goBackRun.substring(1, 2);
        String secondStr = goBackRun.substring(3, 5);
        String millSecondStr = goBackRun.substring(goBackRun.length() - 2, goBackRun.length() - 1);
        goBack =
            goBack
                + Integer.parseInt(minStr) * 1000 * 60
                + Integer.parseInt(secondStr) * 1000
                + Integer.parseInt(millSecondStr) * 100;
        gaTrainSourceVo.setSnakeTime(goBack + "");
        String voTime = "";
        if (!minStr.equals("0")) {
          voTime = minStr + "'" + secondStr + "\"" + millSecondStr;
        } else {
          voTime = secondStr + "\"" + millSecondStr;
        }

        params.put("${snake}", voTime);
        int[] sourceSecond;
        int SnakeIndex = SnakeRunScoreServiceImpl.getIndex(SnakeRunConsts.AGE_ARRAY, age);
        if (sex.equals("男")) {
          sourceSecond = SnakeRunConsts.SOURCE_ARRAY_1[SnakeIndex];
        } else {
          sourceSecond = SnakeRunConsts.SOURCE_ARRAY_0[SnakeIndex];
        }
        int scoreIndex = SnakeRunScoreServiceImpl.getRunIndex(sourceSecond, goBack);
        int score = SnakeRunConsts.SCORE_ARRAY[scoreIndex];

        // 等于100分的情况下,30*2蛇行跑 每减少一秒增加一分
        if (score == 100) {
          // 100分的成绩
          int finnalSource = sourceSecond[sourceSecond.length - 1];
          int diffTimes = finnalSource - goBack;
          score = score + diffTimes / 1000;
        }

        gaTrainSourceVo.setSnakeScore(score);
        String snaketSource = getProjectSource(scoreint, peopleTypeStr);
        gaTrainSourceVo.setSnakeStatus(snaketSource);
        params.put("${snakeScore}", "分数:" + score);
        sumScore = sumScore + score;
      } else if (vo.getGoBackRun() != null &&vo.getGoBackRun().equals("未完成")) {
        gaTrainSourceVo.setSnakeStatus("不及格");
        gaTrainSourceVo.setSnakeScore(0);
        params.put("${snake}", "分/  秒");
        params.put("${snakeScore}", "分数:0");
      } else {
        params.put("${snake}", "分/  秒");
        params.put("${snakeScore}", "分数:0");
      }
      // 仰卧起坐
      if (vo.getSitUpsConsts() != null && !vo.getSitUpsConsts().equals("未完成")) {
        int[] scoureThree;
        // 年龄下标
        int sitUparrIndex = SitUpsScoreServiceImpl.getIndex(SitUpsConsts.AGE_ARRAY, age);
        if (sex.equals("女")) {
          scoureThree = SitUpsConsts.SOURCE_ARRAY_0[sitUparrIndex];
        } else {
          scoureThree = SitUpsConsts.SOURCE_ARRAY_1[sitUparrIndex];
        }
        int scoreIndex =
            SitUpsScoreServiceImpl.getScoreIndex(
                scoureThree, Integer.parseInt(vo.getSitUpsConsts()));
        int score = SitUpsConsts.SCORE_ARRAY[scoreIndex];
        gaTrainSourceVo.setSitUpNum(Integer.parseInt(vo.getSitUpsConsts()));
        Integer finalSource = scoureThree[scoureThree.length - 1];
        if (score == 100) {
          score = score + (Integer.parseInt(vo.getSitUpsConsts()) - finalSource) / 2;
        }
        gaTrainSourceVo.setSitUpScore(score);
        String situpSource = getProjectSource(score, peopleTypeStr);
        gaTrainSourceVo.setSitUpStatus(situpSource);
        params.put("${sitUpNum}", vo.getSitUpsConsts() + "次");
        sumScore = sumScore + score;
        params.put("${sitUpScore}", "分数:" + score);
      } else if (vo.getSitUpsConsts() != null && vo.getSitUpsConsts().equals("未完成")) {
        gaTrainSourceVo.setSitUpStatus("未完成");
        gaTrainSourceVo.setSitUpScore(0);
        params.put("${sitUpNum}", "次");
        params.put("${sitUpScore}", "分数:0");
      } else {
        params.put("${sitUpNum}", "次");
        params.put("${sitUpScore}", "分数:0");
      }
      // 3000米
      if (vo.getRacesConsts() != null && !vo.getRacesConsts().equals("未完成")) {
        Integer seaLeave = 0;
        if (StringUtil.isNotEmpty(vo.getSeaLeave())) {
          seaLeave = Integer.parseInt(vo.getSeaLeave());
        }
        String racesConsts = vo.getRacesConsts();
        int time = 0;
        String minStr = racesConsts.substring(0, 2);
        String secondStr = racesConsts.substring(3, 5);
        String millSecondStr =
            racesConsts.substring(racesConsts.length() - 2, racesConsts.length());
        time =
            time
                + Integer.parseInt(minStr) * 1000 * 60
                + Integer.parseInt(secondStr) * 1000
                + Integer.parseInt(millSecondStr) * 10;
        gaTrainSourceVo.setThreekmTime(time + "");
        Integer score = SeeLeaveConsts.getSeaLeaveScore(age, sex, seaLeave, time);
        gaTrainSourceVo.setThreekmScore(score);
        String racesSource = getProjectSource(score, peopleTypeStr);
        gaTrainSourceVo.setThreekmpStatus(racesSource);
        params.put("${threeKmTime}", vo.getRacesConsts().substring(0, 5));
        params.put("${threeKmScore}", "分数:" + score);
        sumScore = sumScore + score;
      } else if (vo.getRacesConsts() != null && vo.getRacesConsts().equals("未完成")) {
        gaTrainSourceVo.setThreekmScore(0);
        gaTrainSourceVo.setThreekmpStatus("不及格");
        params.put("${threeKmTime}", "分/  秒");
        params.put("${threeKmScore}", "分数:0");
      } else {
        params.put("${threeKmTime}", "分/  秒");
        params.put("${threeKmScore}", "分数:0");
      }
      gaSourceListVo.setScore(sumScore);
      params.put("${totalScore}", "总分:" + sumScore + "分");

      if (vo.getPeopleType().equals("一类")) {
        params.put("${userType}", "一类☑\n" + "二类□\n" + "三类□\n");
        peopleType = 1;
      } else if (vo.getPeopleType().equals("二类")) {
        params.put("${userType}", "一类□\n" + "二类☑\n" + "三类□\n");
        peopleType = 2;
      } else if (vo.getPeopleType().equals("三类")) {
        params.put("${userType}", "一类□\n" + "二类□\n" + "三类☑\n");
        peopleType = 3;
      }
      // 排除每一种成绩状态为null,为不合格，为不及格，为未完成的情况
      if (StringUtil.isNotEmpty(gaTrainSourceVo.getShapeStatus())
          && !gaTrainSourceVo.getShapeStatus().equals("不合格")
          && StringUtil.isNotEmpty(gaTrainSourceVo.getUpStatus())
          && !gaTrainSourceVo.getUpStatus().equals("未完成")
          && !gaTrainSourceVo.getUpStatus().equals("不及格")
          && StringUtil.isNotEmpty(gaTrainSourceVo.getThreekmpStatus())
          && !gaTrainSourceVo.getThreekmpStatus().equals("未完成")
          && !gaTrainSourceVo.getThreekmpStatus().equals("不及格")
          && StringUtil.isNotEmpty(gaTrainSourceVo.getSitUpStatus())
          && !gaTrainSourceVo.getSitUpStatus().equals("未完成")
          && !gaTrainSourceVo.getSitUpStatus().equals("不及格")
          && StringUtil.isNotEmpty(gaTrainSourceVo.getSnakeStatus())
          && !gaTrainSourceVo.getSnakeStatus().equals("未完成")
          && !gaTrainSourceVo.getSnakeStatus().equals("不及格")) {
        String level = getSource(peopleType, sumScore);
        if (level.equals("不及格")) {
          gaSourceListVo.setSource("不及格");
          params.put(
              "${level}",
              "特1级□\n" + "特2级□\n" + "特3级□\n" + "优 秀□\n" + "良 好□\n" + "及 格□\n" + "不 及 格☑\n");
        } else if (level.equals("及格")) {
          params.put(
              "${level}",
              "特1级□\n" + "特2级□\n" + "特3级□\n" + "优 秀□\n" + "良 好□\n" + "及 格☑\n" + "不 及 格□\n");
          gaSourceListVo.setSource("及格");
        } else if (level.equals("良好")) {
          params.put(
              "${level}",
              "特1级□\n" + "特2级□\n" + "特3级□\n" + "优 秀□\n" + "良 好☑\n" + "及 格□\n" + "不 及 格□\n");
          gaSourceListVo.setSource("良好");
        } else if (level.equals("优秀")) {
          gaSourceListVo.setSource("优秀");
          params.put(
              "${level}",
              "特1级□\n" + "特2级□\n" + "特3级□\n" + "优 秀☑\n" + "良 好□\n" + "及 格□\n" + "不 及 格□\n");
        } else if (level.equals("特3级")) {
          gaSourceListVo.setSource("特3级");
          params.put(
              "${level}",
              "特1级□\n" + "特2级□\n" + "特3级☑\n" + "优 秀□\n" + "良 好□\n" + "及 格□\n" + "不 及 格□\n");
        } else if (level.equals("特2级")) {
          gaSourceListVo.setSource("特2级");
          params.put(
              "${level}",
              "特1级□\n" + "特2级☑\n" + "特3级□\n" + "优 秀□\n" + "良 好□\n" + "及 格□\n" + "不 及 格□\n");
        } else if (level.equals("特1级")) {
          params.put(
              "${level}",
              "特1级☑\n" + "特2级□\n" + "特3级□\n" + "优 秀□\n" + "良 好□\n" + "及 格□\n" + "不 及 格□\n");
          gaSourceListVo.setSource("特1级");
        }
      } else {
        gaSourceListVo.setSource("不及格");
        params.put(
            "${level}",
            "特1级□\n" + "特2级□\n" + "特3级□\n" + "优 秀□\n" + "良 好□\n" + "及 格□\n" + "不 及 格☑\n");
      }
      gaSourceListVo.setGaTrainSourceVo(gaTrainSourceVo);
      listGaSourceVo.add(gaSourceListVo);
      String outPath =
          sourcePath
              + "querysourcedownload/"
              + uuid
              + "/"
              + outPDFNameIndex
              + outPDFName
              + "-"
              + DateUtils.dateTimeNowYYYYMMDD()
              + "-个人军事体育训练考核";
      String outPathModel =
          sourcePath
              + "querysourcedownload/"+uuid+"/"+threadName+"----"
              + UUID.randomUUID()
              + outPDFName+outPDFNameIndex;
      String outPathDoc = outPathModel + ".docx";
      outPDFNameIndex++;

      CustomXWPFDocument doc =
          WorderToNewWordUtils.changWord(
              sourcePath + "querysourcedownload/军事体育训练考核成绩登记表"+index+".docx", params, null);
      FileOutputStream fopts = null;
      try {
        fopts = new FileOutputStream(outPathDoc);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      try {
        doc.write(fopts);
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        fopts.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    result.put("listGaSourceVo", listGaSourceVo);
    return result;
  }

  private int getIndex(Integer score) {
    int index = 0;
    if (score > 55 && score < 61) {
      index = 1;

    } else if (score > 60 && score < 66) {
      index = 2;

    } else if (score > 65 && score < 71) {
      index = 3;
    } else if (score > 70 && score < 76) {
      index = 4;
    } else if (score > 75 && score < 81) {
      index = 5;
    } else if (score > 80 && score < 86) {
      index = 6;
    } else if (score > 85 && score < 91) {
      index = 7;
    } else if (score > 90 && score < 96) {
      index = 8;
    } else if (score > 95 && score < 101) {
      index = 9;
    } else if (score > 100) {
      index = 10;
    }
    return index;
  }

  private String getSource(Integer userType, Integer score) {
    int[] scoreArray = new int[0];
    if (userType == 1) {
      scoreArray = ScoreConsts.SCORE_ARRAY_1;
    } else if (userType == 2) {
      scoreArray = ScoreConsts.SCORE_ARRAY_2;
    } else if (userType == 3) {
      scoreArray = ScoreConsts.SCORE_ARRAY_3;
    }
    int index = getIndex(scoreArray, score);
    return ScoreConsts.SOURCE_ARRAY[index];
  }

  /**
   * 获取成绩评价的下标
   */
  public static int getIndex(int[] array, int number) {
    int index = -1;
    for (int i = 0; i < array.length; i++) {
      if (i == 0) {
        if (number <= array[i]) {
          index = i;
        }
      } else {
        if (number > array[i - 1] && number <= array[i]) {
          index = i;
        }
      }
    }
    if (index == -1) {
      index = array.length;
    }
    return index;
  }

  public String getProjectSource(int score, String userType) {
    int index = Integer.valueOf(userType);
    if (score < ScoreConsts.SCORE_ARRAY[index]) {
      return SOURCE_4;
    }
    return SOURCE_3;
  }


  public String getTime(String time) {
    if (StringUtil.isNotEmpty(time) && Long.parseLong(time) > 0) {
      long minutes = Long.parseLong(time) / 60000;
      long seconds = (Long.parseLong(time) % 60000) / 1000;
      time = minutes + "分";
      time = time + seconds + "秒";
    }
    return time;
  }
  public String getTimeSecond(String time) {
    if (StringUtil.isNotEmpty(time) && Long.parseLong(time) > 0) {
      long minutes = Long.parseLong(time) / 60000;
      long seconds = (Long.parseLong(time) % 60000) / 1000;
      long kilosecond  = (Long.parseLong(time) % 1000);
      time = minutes + "′";
      time = time+seconds + "″";
      time = time + kilosecond + "";
    }
    return time;
  }

  private int sumSource(String source) {
    int n = 0;
      if (StringUtil.isEmpty(source)||source.equals("不及格")) {
      n += 1;
    } else if (source.equals("不合格")) {
      n += 1;
    }
    return n;
  }

}
