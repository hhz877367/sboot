package day_04_集合

object TestList {
  def main(args: Array[String]): Unit = {
    val site1 = "Runoob" :: ("Google" :: ("Baidu" :: Nil))
    val site2 = "Facebook" :: ("Taobao" :: Nil)

    // 使用 ::: 运算符
    var fruit = site1 ::: site2
    println( "site1 ::: site2 : " + fruit )

    // 使用 List.:::() 方法
    fruit = site1.:::(site2)
    println( "site1.:::(site2) : " + fruit )

    // 使用 concat 方法
    fruit = List.concat(site1, site2)
    println( "List.concat(site1, site2) : " + fruit  )
  }
}
