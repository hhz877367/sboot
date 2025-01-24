package day_05_高阶函数

object _02控制抽象_传名参数 {
  def main(args: Array[String]): Unit = {
      def f2(a: => Int):Unit = {
        println("a="+a)
        println("a="+a)
      }

    def f3(a:Int):Unit = {
        println("a="+a)
        println("a="+a)
      }
    def f1(): Int ={
      println("f1调用")
      12
    }
    /*传值函数*/
    println(f3(f1()))

    println("测试传名函数====================")
    /*传名函数*/
    println(f2(f1()))

    /*柯里化配合传名参数*/

  }
}
