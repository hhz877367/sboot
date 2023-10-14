package day03_变量_数据类型_运算符

object _04函数基础_多参_带名参数 {
  def main(args: Array[String]): Unit = {
    /*1 可变参数*/
/*    def f1(s:String*): Unit ={
      println(s)
    }
    f1("aaa","bbb","ccc")
    f1("aaa")*/
    /*如果存在多个可变参数，可变参数一般放到后面*/
    def f2(s:String,s2:String*): Unit ={
      println(s)
      println(s2)
    }
    f2("ssss","bbbb","ccccc","ddddd")
    /*带名参数*/
    f2(s = "bbbb")
  }
}
