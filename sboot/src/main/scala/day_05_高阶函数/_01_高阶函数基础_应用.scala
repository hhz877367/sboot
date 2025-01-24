package day_05_高阶函数

object _01_高阶函数基础_应用 {
  def main(args: Array[String]): Unit = {
    val arr: Array[Int] = Array(123, 3, 5432, 42, 423, 4432, 432)
    var newArray: Array[Int] = arrayOption(arr, e => e * 2)
    println(newArray.mkString(","))
    // 练习，定义一个匿名函数，有三个参数，当为 0 "" 0 时返回false,否则返回true

    var opt = (i1: Int, s1: String, c1: Char) =>{
      if(i1 == 0 && s1 == "" && c1 == 0) false else true
    }

    println(opt(1,"",2))
    println(opt(1321,"",2321))
    println(opt(1312,"",2312))
    println(opt(0,"",0))
    //函数柯里化
    def func2(i1:Int)(s1:String)(c1:Char):Boolean = {
      if(i1 == 0 && s1 == "" && c1 == 0) false else true
    }

    println(func2(0)("")(21))
    println(func2(1)("")(321))
    println(func2(2)("")(321))
    println(func2(0)("")(0))

    println("===========================函数柯里化")
    def addCurrying(i:Int)(j:Int): Int ={
      i+j
    }
    println(addCurrying(1)(2))

    println("===========================闭包的应用")
    def addByforOne1(a:Int):Int=>Int = {
      def addB(b: Int) : Int = {
        a +b
      }
      addB
    }
    var add1 = addByforOne1(1)(23)
    println(add1)

    var add2 =addByforOne1(1)
    println(add2(23))


  }

  def arrayOption(arr: Array[Int], op: Int => Int): Array[Int] = {
    for (e <- arr) yield op(e)
  }

  def addOne(e: Int): Int = {
    e + 1
  }
}
