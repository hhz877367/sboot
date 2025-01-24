package day01

import java.text.SimpleDateFormat
import java.util
import java.util.{Calendar, Date}


/*object伴生对象
*
* oject的产生就是因为 scala删除了static 属性，为了引入全局对象的思想，产生了object
*
* */
import java.time.LocalDate
import java.time.temporal.ChronoUnit

object HelloWorld {
  /*
  *
  * def (参数名称: 类型): 返回值类型={方法体}
  * */


  def main(args: Array[String]): Unit = {
   /* getPreDate("2022-08-01","2022-08-10")*/
  }
/*  private def getPreDate(startDateStr: String,endDateStr:String): Array[String] = {

    val start = LocalDate.parse(startDateStr)
    val end = LocalDate.parse(endDateStr)
    var betDate:String = String.valueOf(ChronoUnit.DAYS.between(start, end)+1)
    var days:Int = Integer.valueOf(betDate)
    System.out.println(days)
    var  dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    var  startDate = dateFormat.parse(startDateStr);
    var cal1:Calendar=Calendar.getInstance()
    var cal2:Calendar=Calendar.getInstance()
    cal1.setTime(startDate)
    cal2.setTime(startDate)
    cal1.add(Calendar.DATE,-days)
    cal2.add(Calendar.DATE,-1)
    dateFormat.format(cal1.getTime())
    dateFormat.format(cal2.getTime())


  }*/
}
