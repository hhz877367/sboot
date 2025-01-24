package day03_变量_数据类型_运算符

//Scala 中使用 val 语句可以定义函数，def 语句定义方法。
object _01TestFunciton {
  def main(args: Array[String]): Unit = {
/*    printf("a+b=" + addInt(1, 2))
    var a: String = "aaa"*/
    var result :Int  = m2(2)
    println(result)
  }

  def addInt(a: Int, b: Int): Int = {
    var sum = a + b
    return sum
  }

  def m2(n: Int): Int = {
    if (n == 0) {
      throw new NullPointerException
    } else {
      return 0
    }
  }

}
