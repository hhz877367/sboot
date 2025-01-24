package day03_变量_数据类型_运算符

object _02类型转换 {
  def main(args: Array[String]): Unit = {
    var a1 : Byte = 10
    var b1 : Long = 1000

    //把by 和Long 转换成Int

    var result1 = a1 + b1
    var result2 : Int = (a1 + b1 ).toInt
    println(result1)
    println(result2)

    // int 转字符串 + ""
    // String 转换成数值
    // 12.3 转换成int
    var f1: Int  = "123.4".toDouble.toInt
    println(f1)

    var int3 :Int = (a1.+(b1)).toInt
    println(int3)


  }
}
