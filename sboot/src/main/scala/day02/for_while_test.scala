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
  }
}
