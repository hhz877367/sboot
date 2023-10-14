package day03_变量_数据类型_运算符

object _03匿名函数{
  def main(args: Array[String]): Unit = {
/*    var result1 = fun1(1,2)
    println(result1)
    val value1 = (a : Int) => {
      println(a)
    }
    value1(100)
    //偏应用函数
    var fn = fun1(2,_:Int)
    println(fn(2))
    println(fn(4))
    println(fn(100))
    println(fn(200))*/
     //参数为函数的情况
    //scala 的高阶函数 参数列表是函数，且参数列表的函数的参数为单个

    /*    def hightFun(f : (Int,Int) =>Int, a:Int) : Int = {
          f(a,100)
        }

        def hightFun2(f : (Int,Int) =>Int, a:Int,b:Int) : Int = {
          f(a,b)
        }
        def f(v1 :Int,v2: Int):Int  = {
          v1+v2
        }

        println(hightFun(f, 1))
        println(hightFun2(f, 1,200))
    4*/
    //函数的返回是函数
    //1，2,3,4相加
/*    def hightFun2(a : Int,b:Int) : (Int,Int)=>Int = {
      def f2 (v1: Int,v2:Int) :Int = {
        v1+v2+a+b
      }
      f2
    }
    println(hightFun2(1,2)(3,4))*/
    //函数的返回值是函数，函数的参数是函数

  }
  def fun1(num:Int,num2:Int): Int ={
    num+num2
  }

}
