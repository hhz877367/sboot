package com.baizhi.entity;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Test {
  public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
   //创建测试数据
    List<String>   nameList=addList();
    for(int i=0;i<nameList.size()-1;i++){
      for(int j=0;j<nameList.size()-1-i;j++){
        String temp1;
        if(comperNamebyHashCode(nameList.get(j),nameList.get(j+1))){
          temp1=nameList.get(j);
          nameList.set(j,nameList.get(j+1));
          nameList.set(j+1,temp1);
        }
      }
    }
    for(String uname:nameList){
      System.out.println(uname);
    }
  }

  public  static  String formatString(String name) throws Exception {
    HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
    //拼音小写
    format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    //不带声调
    format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    //要转换的中文，格式，转换之后的拼音的分隔符，遇到不能转换的是否保留   wo,shi,zhong,guo,ren,，hello
    String string = null;
      string = PinyinHelper.toHanYuPinyinString(name, format, "", true);
      int a=1/0;

    return string;
  }

  public static List<String> addList(){
    List<String> nameList=new ArrayList();
    nameList.add("爱贝 母婴店 莉莉15037125967");
    nameList.add("阿里巴巴");
    nameList.add("A_");
    nameList.add("A!");
    nameList.add("AAAAA");
    nameList.add("AAA七个隆咚锵AAA");
    nameList.add("AAA_招聘主管");
    nameList.add("A安居租房");
    nameList.add("Aa天领装饰 福利18439427124");
    nameList.add("AA尹尹  图灵课堂");
    nameList.add("阿彪");
    nameList.add("A抖音商家服务中心");
    nameList.add("Angel@李甜甜");
    nameList.add("安硕信息（金融）16到18  周二");
    nameList.add("傲娇的奥特曼");
    nameList.add("A王帅|知舟|抖音 | 京东天猫服务商");
    nameList.add("A_ 『小妍』");
    nameList.add("a智轩水行18739953939");
    nameList.add("。。。。");
    nameList.add(".");
    nameList.add("2王女221");
    nameList.add("58严选服务商（李楠）日结");
    nameList.add("杨斌");
    nameList.add("阳光保险");
    nameList.add("旸谷（甲方）");
    nameList.add("杨静水站13641183381");
    return nameList;
  }

  /*     数据为本地造的，不考虑名字为空的情况。
   *  一 先考虑两个字符串 的所有排序情况。   比如张三和zs1 、李四和Ls1
   *     1 先对字符串进行处理 把中文转换成拼音类型的英文，
   *     2 比较规则 hashCode小的排上面，大排下面
   *        (一) 字符串全为a-z A-Z
   *             (1) 把(A-Z)+ 32 进行比  当出现不同时，返回，相同则回到2继续比
   *        (二) 字符串为特殊字符(包含0-9) 和 a-z A-Z
   *             (1) 特殊字符直接仍最下面
   *        (三) 字符串为特殊字符  特殊字符
   *             (1) 则直接按照hashcode排序即可  当出现不同时，返回，相同则回到2继续比
   *     3 当出现不同的英文字目时，按照英文字母的Hash值进行排序
   *  二 外层调用 一，返回结果为 1  2, 1 代表位置交换 ， 2 代表不交互位置
   * */
  public static boolean comperNamebyHashCode(String firstName,String  secondName)
      throws BadHanyuPinyinOutputFormatCombination {
    String SName1="";
    String SName2="";
    try {
       SName1=formatString(firstName).trim();
       SName2=formatString(secondName).trim();
    }catch (Exception e){
      e.printStackTrace();
    }

    //取其中长度最小的用于做比较的循环下标，避免数组比较越界
    Integer mixIndex=(firstName.length()>=secondName.length())?secondName.length():firstName.length();
    for(int i=0;i<mixIndex;i++){
      String s1 = SName1.substring(i, i + 1);
      String s2 = SName2.substring(i, i + 1);
      Integer hashCode1=s1.hashCode();
      Integer hashCode2=s2.hashCode();
      //s1 s2 全为 字母情况
      if(((hashCode1>=65 && hashCode1<=90) || (hashCode1>=97&& hashCode1<=122)) &&
          ((hashCode2>=65 && hashCode2<=90) || (hashCode2>=97&& hashCode2<=122))){
        //把 A-Z 的hashCode 扩大32 ，为了避免出现 B 和 a比出现B排上面的情况。
        hashCode1=(hashCode1<=90)?hashCode1+32:hashCode1;
        hashCode2=(hashCode2<=90)?hashCode2+32:hashCode2;
        if(hashCode1>hashCode2){
          return true;
        }else if(hashCode1<hashCode2){
          return false;
        }
      }else if((hashCode1<65||hashCode1>122 ||(hashCode1>90 && hashCode1<97))&&
          (hashCode2<65||hashCode2>122 ||(hashCode2>90 && hashCode2<97))) {
        //全为特殊字符情况
        if(hashCode1>hashCode2){
          return true;
        }else if(hashCode1<hashCode2){
          return false;
        }
      }else {
        //一种为特殊字符，一种为普通a-z A-Z 情况
        if((hashCode1<65||hashCode1>122 ||(hashCode1>90 && hashCode1<97))){
          return true;
        }else {
          return false;
        }
      }
    }
    //如果比较完毕还没分出结果，只有张三111和张三 、张鹏飞和张鹏 这种出现的姓名情况，取最小的放上面
    if(firstName.length()>mixIndex){
      return true;
    }else {
      return false;
    }
  }
}
