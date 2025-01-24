package question;

/**
 * 
* <p>背景: 逻辑表达式是编程语言的重要组成部分，分为逻辑与（&&）、逻辑或（||）和逻辑非（!）表达式，其运算符优先级为
*  !>&&>||，在复杂情况下可通过小括号改变优先级，如A&&(B||C)中先进行先进行||运算，然后进行&&运算。
*  <p>要求：输入一个逻辑表达式字符串，编写程序计算该逻辑表达式的结果，如输入true&&(false||true)的结果为true。
*  不能使用已有的库或引擎。

 */
public class Question6 {
    public boolean answer(String logicExpr) {
        // 推荐思路1：可根据Question4生成token列表建立二叉树，通过二叉树的递归遍历方式计算
        // 推荐思路2：直接转换为后缀表达式进行计算
        return false;
    }

    public static void main(String[] args) {
        Question6 instance = new Question6();

        String expr = "true&&!false";
        System.out.println(expr + " result = " + instance.answer(expr));

        expr = "true&&(false||true)";
        System.out.println(expr + " result = " + instance.answer(expr));
        
        expr = "     (false||(true &&   false)    )&&(       !!false|| true       )";
        System.out.println(expr + " result = " + instance.answer(expr));
        
        expr = "(false   ||(       true&&(false||      true)))&&(false||    true&&    !(false||true))   ";
        System.out.println(expr + " result = " + instance.answer(expr));
    }
}
