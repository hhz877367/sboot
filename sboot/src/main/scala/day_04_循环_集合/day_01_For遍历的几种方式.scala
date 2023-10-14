package day_04_循环_集合


import scala.collection.immutable.Range
import scala.util.control.Breaks

object day_01_For遍历的几种方式 {
  def main(args: Array[String]): Unit = {
    //scala 中跳}出循环
    Breaks.breakable(
      for (i <- 1 to 10) {
        if (i == 3) {
          Breaks.break()
        }
        println(i)
      })






    /*  val arrayList =   for(i <- (1 to 10 )) yield i
      println(arrayList)*/
    /*    for(i <- 1 to(10)){
          println(i +"hello world")
        }

        /*循环守卫*/
        for(i <- 1 to(10) if i!= 5){
          println(i +"hello world")
        }*/

    /*循环步长*/
    for (i <- (1 to 10).by(5)) {
      println(i + "hello world")
    }

    for (i <- 1 to 10 by 2) {
      println(i + "hello world")
    }
    /*循环半步*/

    println("-----------------")
    for (i <- 1 until (10)) {
      println(i + "hello world")
    }

    println("-----------------")
    for (i <- Range(1, 10)) {
      println(i + "hello world")
    }

    var a = new Array[String](100)

    /*遍历集合*/
    for (i <- Array(12, 3, 4)) {
      println("我是" + i)
    }

    for (i <- a) {
      println("你是" + i)
    }


  }


}
