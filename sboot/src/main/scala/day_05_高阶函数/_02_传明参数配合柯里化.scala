package day_05_高阶函数


object _02_传明参数配合柯里化 {
  def main(args: Array[String]): Unit = {
    var n =10

      while (n >= 1){
        println(n)
        n -= 1
      }

    //柯里化配合传名参数 实现while 关键字功能
      def myWhile3(condition: => Boolean)(opt: =>Unit):Unit = {
        if(condition){
          opt
          myWhile3(condition)(opt)
        }
      }
    println("================柯里化实现自定义while循环")
    n = 10
    myWhile3( n >= 1){
      println(n)
      n  -= 1
    }

  }
}
