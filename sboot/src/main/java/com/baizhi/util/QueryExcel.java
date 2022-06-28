package com.baizhi.util;


import com.baizhi.constant.StringUtils;
import com.baizhi.entity.excelEntity.SourceOutPdfVo;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryExcel {

  /**
   * 无伤病的excel模版解析
   *
   * @param is 输入流
   * @return List<SourceOutPdfVo>
   */
  public static List<SourceOutPdfVo> getListByIs(InputStream is, Class clazz)
      throws Exception {
    //返回的数据结果

    List<SourceOutPdfVo> list = new ArrayList<>();
    Sheet sheetOne=null;
    Field[] allFields=null;
    try {
       allFields = clazz.getDeclaredFields();
      Workbook sheets = WorkbookFactory.create(is);
      sheetOne = sheets.getSheetAt(0);
      if (sheetOne == null) {
        throw new IOException("文件sheet不存在");
      }
    }catch (Exception e){
      System.out.println("创建文件发生错误");
    }


    //得到第一行模版字符串
    int rows = sheetOne.getPhysicalNumberOfRows();
    if (rows > 0) {
      for (int i = 1; i < rows; i++) {
        // 从表头下一行开始取数据,默认第一行是表头
        Row row = sheetOne.getRow(i);
        SourceOutPdfVo model = new SourceOutPdfVo();
        for (int j = 1; j <= allFields.length; j++) {
          String value = (String) getCellValue(row, j);
          if (StringUtils.isNotEmpty(value)) {
            //设置姓名
            value = value.trim();
            if ("未完成".equals(value)) {
              continue;
            }
            //姓名之中不能出现数字
            if (j == 1) {
              if (StringUtils.isEmpty(value)) {
                System.out.println("第" + (i + 1) + "行(姓名)数据有误");
                return new ArrayList<>();
              } else {
                model.setUserName(value);
              }
            }
            //判断性别是否为男女
            if (j == 2) {
              model.setSex(value);
              if (!value.equals("男") && !value.equals("女")) {
                System.out.println("第" + (i + 1) + "行(性别)数据有误");
                return new ArrayList<>();
              }
            }
            //判断年龄必须为数字
            if (j == 3) {
              model.setAge(value);
              if (StringUtils.isNotEmpty(value)) {
                for (int k = 0; k < value.length(); k++) {
                  if (!Character.isDigit(value.charAt(k))) {
                    model.setAge("60");
                  }
                }
                int valueInt = Integer.parseInt(value);
                if (valueInt > 200 || valueInt < 0) {
                  model.setAge(value.substring(0,2));
                }
              } else {
                 model.setAge("60");
              }
            }
            if (j == 4) {
              model.setUnitName(value);
            }
            //去掉身高体重的.符号，判断是否是数字
            if (j == 5) {
              model.setHeight(value);
              if(value.length()>=4){
                model.setHeight(value.substring(0,3));
              }
              String valueModel = value.replaceAll("\\.", "");
              for (int k = 0; k < valueModel.length(); k++) {
                if (!Character.isDigit(valueModel.charAt(k))) {
                  System.out.println("第" + (i + 1) + "行(身高)数据有误");
                  return new ArrayList<>();
                }
              }
            }
            if (j == 6) {
              model.setWeight(value);
              String valueModel = value.replaceAll("\\.", "");
              for (int k = 0; k < valueModel.length(); k++) {
                if (!Character.isDigit(valueModel.charAt(k))) {
                  System.out.println("第" + (i + 1) + "行(体重)数据有误");
                  return new ArrayList<>();
                }
              }
              if(value.length()>=6){
                if(Double.parseDouble(value)>=100){
                  model.setWeight(value.substring(0,3));
                }
              }
            }
            //判断俯卧撑是否为数字
            if (j == 7) {
              model.setPushUpConsts(value);
              if(value.length()>=4){
                model.setPushUpConsts(value.substring(0,2));
              }
              for (int k = 0; k < value.length(); k++) {
                if (!Character.isDigit(value.charAt(k))) {
                  System.out.println("第" + (i + 1) + "行(俯卧撑)数据有误");
                  return new ArrayList<>();
                }
              }

            }
            //判断仰卧起坐是否为数字
            if (j == 8) {
              model.setSitUpsConsts(value);
              for (int k = 0; k < value.length(); k++) {
                if (!Character.isDigit(value.charAt(k))) {
                  System.out.println("第" + (i + 1) + "行(仰卧起坐)数据有误");
                  return new ArrayList<>();
                }
              }
              if(value.length()>=4){
                model.setSitUpsConsts(value.substring(0,2));
              }
            }
            //判断蛇形跑 01位，34位，67位否为数字，并且长度是否为8
            if (j == 9) {
              value = value.substring(0, 2) + value.substring(3, 5) + value.substring(6, 8);
              model.setGoBackRun(value);
            }
            //判断3000米 01位，34位，67位否为数字，并且长度是否为8
            if (j == 10) {
              value = value.substring(0, 2) + value.substring(3, 5) + value.substring(6, 8);
              model.setRacesConsts(value);
            }
            //判断引体向上是否为数字
            if (j == 11) {
              model.setPullUp(value);
              if (value.length() >= 4) {
                model.setPullUp(value.substring(0,3));
              }
              for (int k = 0; k < value.length(); k++) {
                if (!Character.isDigit(value.charAt(k))) {
                  System.out.println("第" + (i + 1) + "行(引体向上)数据有误");
                  return new ArrayList<>();
                }
              }
            }
            //判断屈臂悬垂 01位，34位，67位否为数字，并且长度是否为8
            if (j == 12) {
              if(value.length()!=8){
                 value = value.substring(0, 8);
              }
              model.setCantileVeredArmConsts(value);
            }
            //判断人员类别是否为一类 二类 三类
            if (j == 13) {
              model.setPeopleType(value);
              if (!value.equals("一类") && !value.equals("二类") && !value.equals("三类")) {
                System.out.println("第" + (i + 1) + "行(人员类别)数据有误");
                return new ArrayList<>();
              }
            }
            //判断驻地海拔是否为数字
            if (j == 14) {
              model.setSeaLeave(value);
              value = value.replaceAll("-", "");
              for (int k = 0; k < value.length(); k++) {
                if (!Character.isDigit(value.charAt(k))) {
                  System.out.println("第" + (i + 1) + "行(驻地海拔)数据有误");
                  return new ArrayList<>();
                }
              }
            }
          }
        }
        list.add(model);
      }
    }
    return list;
  }

  public static Object getCellValue(Row row, int column) {
    if (row == null) {
      return null;
    }
    Object val = "";
    try {
      Cell cell = row.getCell(column);
      if (StringUtils.isNotNull(cell)) {
        if (cell.getCellType() == CellType.NUMERIC ||
                cell.getCellType() == CellType.FORMULA) {
          val = cell.getNumericCellValue();
          if (DateUtil.isCellDateFormatted(cell)) {
            // POI Excel 日期格式转换
            val = DateUtil.getJavaDate((Double) val);
          } else {
            if (((Double) val % 1) > 0) {
              val = new DecimalFormat("0.00").format(val);
            } else {
              val = new DecimalFormat("0").format(val);
            }
          }
        } else if (cell.getCellType() == CellType.STRING) {
          val = cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.BOOLEAN) {
          val = cell.getBooleanCellValue();
        } else if (cell.getCellType() == CellType.ERROR) {
          val = cell.getErrorCellValue();
        }
      }
    } catch (Exception e) {
      return val;
    }
    return val;
  }



}