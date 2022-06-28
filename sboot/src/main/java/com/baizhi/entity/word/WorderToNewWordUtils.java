package com.baizhi.entity.word;

import lombok.SneakyThrows;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/** @author jack.zhang 20210624 */
public class WorderToNewWordUtils {
  /**
   * 根据模板生成word文档
   *
   * @param inputUrl 模板路径
   * @param textMap 需要替换的文本内容
   * @param mapList 需要动态生成的内容
   * @return
   */
  public static CustomXWPFDocument changWord(
          String inputUrl, Map<String, Object> textMap, List<String[]> mapList) {
    CustomXWPFDocument document = null;
    try {
      // 获取docx解析对象
      document = new CustomXWPFDocument(POIXMLDocument.openPackage(inputUrl));
      // 解析替换文本段落对象
      WorderToNewWordUtils.changeText(document, textMap);
      // 解析替换表格对象
      WorderToNewWordUtils.changeTable(document, textMap, mapList); // 替换
    } catch (IOException e) {
      e.printStackTrace();
    }
    return document;
  }

  /**
   * 替换段落文本
   *
   * @param document docx解析对象
   * @param textMap 需要替换的信息集合
   */
  public static void changeText(CustomXWPFDocument document, Map<String, Object> textMap) {
    // 获取段落集合
    List<XWPFParagraph> paragraphs = document.getParagraphs();

    for (XWPFParagraph paragraph : paragraphs) {
      // 判断此段落时候需要进行替换
      String text = paragraph.getText();
      if (checkText(text)) {
        List<XWPFRun> runs = paragraph.getRuns();
        for (XWPFRun run : runs) {
          // 替换模板原来位置
          Object ob = changeValue(run.toString(), textMap);
          System.out.println("段落：" + run.toString());
          if (ob instanceof String) {
            run.setText((String) ob, 0);
          }
        }
      }
    }
  }

  /**
   * 替换表格对象方法
   *
   * @param document docx解析对象
   * @param textMap 需要替换的信息集合
   * @param mapList 需要动态生成的内容
   */
  public static void changeTable(
          CustomXWPFDocument document, Map<String, Object> textMap, List<String[]> mapList) {
    // 获取表格对象集合
    List<XWPFTable> tables = document.getTables();
    // 循环所有需要进行替换的文本，进行替换
    for (int i = 0; i < tables.size(); i++) {
      XWPFTable table = tables.get(i);
      if (checkText(table.getText())) {
        List<XWPFTableRow> rows = table.getRows();
        // 遍历表格,并替换模板
        eachTable(document, rows, textMap);
      }
    }
  }

  /**
   * 定向复制插入表格并写入内容
   *
   * @param filePath
   * @param mapList
   */
  public static CustomXWPFDocument createTable(
      String filePath,
      Map<String, Object> textMap,
      List<String[]> mapList,
      List<String[]> mapList1,
      List<String[]> mapList2,
      List<String[]> mapList3,
      List<String[]> mapList4) {
    CustomXWPFDocument document = null;
    // 获取docx解析对象
    try {
      document = new CustomXWPFDocument(POIXMLDocument.openPackage(filePath));
      // 获取表格对象集合
      List<XWPFTable> tables = document.getTables();
      // 获取第一个表格   根据实际模板情况 决定去第几个word中的表格
      XWPFTable table = tables.get(0);
      // 解析替换文本段落对象
      WorderToNewWordUtils.changeText(document, textMap);
      // 向特定位置 行插入数据
      insertTableCell(table, mapList, 5, 1); // 基础
      insertTable(table, mapList2, 13, 14); // 速度
      if (mapList2.size() == 0) {
        insertTable(table, mapList3, 15, 16);
      } else {
        insertTable(table, mapList3, 14 + mapList2.size(), 14 + mapList2.size() + 1);
      }
      if (mapList2.size() == 0 && mapList3.size() == 0) {
        insertTable(table, mapList4, 17, 18);
      } else if (mapList2.size() == 0) {
        insertTable(table, mapList4, 16 + mapList3.size(), 17 + mapList3.size());
      } else if (mapList3.size() == 0) {
        insertTable(table, mapList4, 16 + mapList2.size(), 17 + mapList2.size());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return document;
  }

  /**
   * 新增行
   *
   * @param document
   * @param mapList
   * @param copyrowIndex
   * @param newrowIndex
   */
  public static void insertAddCell(
          CustomXWPFDocument document, List<String[]> mapList, int copyrowIndex, int newrowIndex) {
    // 获取表格对象集合
    List<XWPFTable> tables = document.getTables();
    // 获取第一个表格   根据实际模板情况 决定去第几个word中的表格
    XWPFTable table = tables.get(0);
    insertTable(table, mapList, copyrowIndex, newrowIndex);
  }
  /**
   * 插入固定行列数据
   *
   * @param table
   * @param tableList
   * @param rowIndex 行
   * @param cellIndex 列
   */
  public static void insertTableCell(
          XWPFTable table, List<String[]> tableList, int rowIndex, int cellIndex) {
    // 遍历表格插入数据
    List<XWPFTableRow> rows = table.getRows();
    XWPFTableCell targetCell = null; // 列对象
    for (int i = rowIndex; i < tableList.size() + rowIndex; i++) {
      XWPFTableRow newRow = table.getRow(i);
      List<XWPFTableCell> cells = newRow.getTableCells();
      for (int j = cellIndex; j < cells.size(); j++) {
        XWPFTableCell cell = cells.get(j);
        cell.setText(tableList.get(i - rowIndex)[j - 1]);
      }
    }
  }
  /**
   * 为表格插入数据，行数不够添加新行
   *
   * @param table 需要插入数据的表格
   * @param tableList 插入数据集合
   * @param copyrowIndex 复制的行号
   * @param newrowIndex 开始插入的行号
   */
  public static void insertTable(
          XWPFTable table, List<String[]> tableList, int copyrowIndex, int newrowIndex) {
    // 创建行,根据需要插入的数据添加新行，不处理表头
    // 判断需要插入的数量
    if (tableList.size() > 0) {
      for (int i = 0; i < tableList.size() - 1; i++) {
        insertRow(table, copyrowIndex, newrowIndex);
      }
    }
    // 遍历表格插入数据
    List<XWPFTableRow> rows = table.getRows();
    XWPFTableCell targetCell = null; // 列对象
    for (int i = copyrowIndex; i < tableList.size() + copyrowIndex; i++) {
      XWPFTableRow newRow = table.getRow(i);
      List<XWPFTableCell> cells = newRow.getTableCells();
      for (int j = 0; j < cells.size(); j++) {
        XWPFTableCell cell = cells.get(j);
        cell.setText(tableList.get(i - copyrowIndex)[j]);
      }
    }
  }
  /**
   * insertRow 在word表格中指定位置插入一行，并将某一行的样式复制到新增行
   *
   * @param copyrowIndex 需要复制的行位置
   * @param newrowIndex 需要新增一行的位置
   */
  public static void insertRow(XWPFTable table, int copyrowIndex, int newrowIndex) {
    // 在表格中指定的位置新增一行
    XWPFTableRow targetRow = table.insertNewTableRow(newrowIndex);
    // 获取需要复制行对象
    XWPFTableRow copyRow = table.getRow(copyrowIndex);
    // 复制行对象
    targetRow.getCtRow().setTrPr(copyRow.getCtRow().getTrPr());
    // 或许需要复制的行的列
    List<XWPFTableCell> copyCells = copyRow.getTableCells();
    // 复制列对象
    XWPFTableCell targetCell = null;
    for (int i = 0; i < copyCells.size(); i++) {
      XWPFTableCell copyCell = copyCells.get(i);
      targetCell = targetRow.addNewTableCell();
      targetCell.getCTTc().setTcPr(copyCell.getCTTc().getTcPr());
      if (copyCell.getParagraphs() != null && copyCell.getParagraphs().size() > 0) {
        targetCell
            .getParagraphs()
            .get(0)
            .getCTP()
            .setPPr(copyCell.getParagraphs().get(0).getCTP().getPPr());
        if (copyCell.getParagraphs().get(0).getRuns() != null
            && copyCell.getParagraphs().get(0).getRuns().size() > 0) {
          XWPFRun cellR = targetCell.getParagraphs().get(0).createRun();
          cellR.setBold(copyCell.getParagraphs().get(0).getRuns().get(0).isBold());
        }
      }
    }
  }

  /**
   * 遍历表格
   *
   * @param rows 表格行对象
   * @param textMap 需要替换的信息集合
   */
  public static void eachTable(
          CustomXWPFDocument document, List<XWPFTableRow> rows, Map<String, Object> textMap) {
    for (XWPFTableRow row : rows) {




      List<XWPFTableCell> cells = row.getTableCells();
      for (XWPFTableCell cell : cells) {
        // 判断单元格是否需要替换
        if (checkText(cell.getText())) {
          List<XWPFParagraph> paragraphs = cell.getParagraphs();
          for (XWPFParagraph paragraph : paragraphs) {
            List<XWPFRun> runs = paragraph.getRuns();
            for (XWPFRun run : runs) {
              Object ob = changeValue(run.toString(), textMap);
              if (ob instanceof String) {
                run.setText((String) ob, 0);
              } else if (ob instanceof Map) {
                run.setText("", 0);
                Map pic = (Map) ob;
                int width = Integer.parseInt(pic.get("width").toString());
                int height = Integer.parseInt(pic.get("height").toString());
                int picType = getPictureType(pic.get("type").toString());
                byte[] byteArray = (byte[]) pic.get("content");
                ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);
                try {
                  document.addPictureData(byteInputStream, XWPFDocument.PICTURE_TYPE_PNG);
                  document.createPicture(
                      document.getAllPictures().size() - 1, width, height, paragraph);
                  break;
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * 为表格插入数据，行数不够添加新行
   *
   * @param table 需要插入数据的表格
   * @param tableList 第四个表格的插入数据
   * @param daList 第二个表格的插入数据
   * @param type 表格类型：1-第一个表格 2-第二个表格 3-第三个表格 4-第四个表格
   */
  public static void insertTable(
          XWPFTable table, List<String> tableList, List<String[]> daList, Integer type) {
    if (2 == type) {
      // 创建行和创建需要的列
      for (int i = 1; i < daList.size(); i++) {
        // 添加一个新行
        XWPFTableRow row = table.insertNewTableRow(1);
        for (int k = 0; k < daList.get(0).length; k++) {
          row.createCell(); // 根据String数组第一条数据的长度动态创建列
        }
      }

      // 创建行,根据需要插入的数据添加新行，不处理表头
      for (int i = 0; i < daList.size(); i++) {
        List<XWPFTableCell> cells = table.getRow(i + 1).getTableCells();
        for (int j = 0; j < cells.size(); j++) {
          XWPFTableCell cell02 = cells.get(j);
          cell02.setText(daList.get(i)[j]);
        }
      }
    } else if (4 == type) {
      // 插入表头下面第一行的数据
      for (int i = 0; i < tableList.size(); i++) {
        XWPFTableRow row = table.createRow();
        List<XWPFTableCell> cells = row.getTableCells();
        cells.get(0).setText(tableList.get(i));
      }
    }
  }

  /**
   * 判断文本中时候包含$
   *
   * @param text 文本
   * @return 包含返回true,不包含返回false
   */
  public static boolean checkText(String text) {
    boolean check = false;
    if (text.indexOf("$") != -1) {
      check = true;
    }
    return check;
  }

  /**
   * 匹配传入信息集合与模板
   *
   * @param value 模板需要替换的区域
   * @param textMap 传入信息集合
   * @return 模板需要替换区域信息集合对应值
   */
  public static Object changeValue(String value, Map<String, Object> textMap) {
    Set<Map.Entry<String, Object>> textSets = textMap.entrySet();
    Object valu = "";
    for (Map.Entry<String, Object> textSet : textSets) {
      // 匹配模板与替换值 格式${key}
      String key = textSet.getKey();
      if (value.indexOf(key) != -1) {
        valu = textSet.getValue();
      }
    }
    return valu;
  }

  /**
   * 将输入流中的数据写入字节数组
   *
   * @param in
   * @return
   */
  public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
    byte[] byteArray = null;
    try {
      int total = in.available();
      byteArray = new byte[total];
      in.read(byteArray);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (isClose) {
        try {
          in.close();
        } catch (Exception e2) {
          System.out.println("关闭流失败");
        }
      }
    }
    return byteArray;
  }


  /**
   * 解决使用 inputStream2ByteArray 方法 导致图片部分出不来问题
   *
   */
  @SneakyThrows
  public static byte[] imgArray(InputStream inputStream) {
   //引入 ImageIO 流
   BufferedImage bi = ImageIO.read(inputStream);
   //创建字节流缓冲区
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //把img图片转成 byte[]  数组
    ImageIO.write(bi,"jpg",baos);
     return baos.toByteArray();
  }

  /**
   * 根据图片类型，取得对应的图片类型代码
   *
   * @param picType
   * @return int
   */
  private static int getPictureType(String picType) {
    int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
    if (picType != null) {
      if (picType.equalsIgnoreCase("png")) {
        res = CustomXWPFDocument.PICTURE_TYPE_PNG;
      } else if (picType.equalsIgnoreCase("dib")) {
        res = CustomXWPFDocument.PICTURE_TYPE_DIB;
      } else if (picType.equalsIgnoreCase("emf")) {
        res = CustomXWPFDocument.PICTURE_TYPE_EMF;
      } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
        res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
      } else if (picType.equalsIgnoreCase("wmf")) {
        res = CustomXWPFDocument.PICTURE_TYPE_WMF;
      }
    }
    return res;
  }

  /**
   * 合并行
   *
   * @param table
   * @param col 需要合并的列
   * @param fromRow 开始行
   * @param toRow 结束行
   */
  public static void mergeCellVertically(XWPFTable table, int col, int fromRow, int toRow) {
    for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
      CTVMerge vmerge = CTVMerge.Factory.newInstance();
      if (rowIndex == fromRow) {
        vmerge.setVal(STMerge.RESTART);
      } else {
        vmerge.setVal(STMerge.CONTINUE);
      }
      XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
      CTTcPr tcPr = cell.getCTTc().getTcPr();
      if (tcPr != null) {
        tcPr.setVMerge(vmerge);
      } else {
        tcPr = CTTcPr.Factory.newInstance();
        tcPr.setVMerge(vmerge);
        cell.getCTTc().setTcPr(tcPr);
      }
    }
  }
  /**
   * 获取需要合并单元格的下标
   *
   * @return
   */
  public static List<Integer[]> startEnd(List<String[]> daList) {
    List<Integer[]> indexList = new ArrayList<Integer[]>();

    List<String> list = new ArrayList<String>();
    for (int i = 0; i < daList.size(); i++) {
      list.add(daList.get(i)[0]);
    }
    Map<Object, Integer> tm = new HashMap<Object, Integer>();
    for (int i = 0; i < daList.size(); i++) {
      if (!tm.containsKey(daList.get(i)[0])) {
        tm.put(daList.get(i)[0], 1);
      } else {
        int count = tm.get(daList.get(i)[0]) + 1;
        tm.put(daList.get(i)[0], count);
      }
    }
    for (Map.Entry<Object, Integer> entry : tm.entrySet()) {
      String key = entry.getKey().toString();
      String value = entry.getValue().toString();
      if (list.indexOf(key) != (-1)) {
        Integer[] index = new Integer[2];
        index[0] = list.indexOf(key);
        index[1] = list.lastIndexOf(key);
        indexList.add(index);
      }
    }
    return indexList;
  }
}
