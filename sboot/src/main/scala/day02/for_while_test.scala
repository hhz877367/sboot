package day02

object for_while_test {
  def main(args: Array[String]): Unit = {
    var a = 0;
    // for 循环
/*    for( a <- 1 to 10){
      println( "Value of a: " + a );
    }*/
    val numList= List(1, 2, 3, 4, 5, 6)
    for( a <- numList ){
      println( "Value of a: " + a );
    }

    //判断引用
    var s1 = "hello"
    var s2 = new String("hello")
    println(s1 == s2 )
    println(s1.equals(s2))
    println(s1.eq(s2))

    //

  }
}
