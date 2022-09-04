package day01
// var 代表可以修饰的name 和string 能够被外部访问,val只能访问没，不能赋值
class Student(var name: String,var age: Int) {
  def prientInfo(): Unit = {
    print(name, "" + age + Student.school)
  }
}

object Student {
  var school: String = "atgutiu";

  def main(args: Array[String]): Unit = {
    var alcie = new Student("alci", 20);
    alcie.age=1;
    var bob = new Student("alci2", 30);
    alcie.prientInfo();
    bob.prientInfo();
    /*
      定义常量和变量
     */
    var a:Int=1;
    val b:Int=2;

    var a1=1;
    val b2=2;
    println(a+"----"+b)
    /*
      s"" 模版字符串
      f"" 格式化模版字符串
      raw"" 原始输出,只解析${}字符串
     */
    println(s"${a1}我是中${b}国人")
    var num:Double=2.3423;
    println(f"${a1}我是中${num}%2.2f国人")
    println(raw"${a1}我是中${num}%2.2f国人")
  }
}
