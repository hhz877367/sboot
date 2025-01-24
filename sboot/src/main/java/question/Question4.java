package question;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* <p>背景：程序的编译过程需要构建抽象语法树（AST），在此之前需要先进行词法分析，词法分析是把输入的源代码字符串根据语言规范，
*        分解为一串连续的符号（Token），符号包括关键字、运算符、标识符和数字等。
* <p>逻辑表达式是编程语言的重要组成部分，分为逻辑与（&&）、逻辑或（||）和逻辑非（!）表达式，其运算符优先级为
*  !>&&>||，在复杂情况下可通过小括号改变优先级，如A&&(B||C)中先进行先进行||运算，然后进行&&运算
*  <p>要求：编写一个函数，把逻辑表达式转换为token列表。逻辑表达式字符串可包含true、false、小括号、逻辑运算符和空格，
*        其中空格在解析token时被忽略，不考虑true，false，&&等拼写错误的情况，另外解析token时无需检查表达式正确性，如
*    <li>"true   &&!    false" 被解析为{"true","&&", "!", "false"}
*    <li>"&& true ! || false"表达式错误，但仍可被解析为{"&&", "true", "!", "||", "false" }

 */
public class Question4 {
    enum State {
        TF, LOGIC_AND, LOGIC_OR, LOGIC_NOT, LPAREN, RPAREN
    }

    public static List<String> tokenize(String logicExpr) {
        List<String> tokens = new ArrayList<String>();
        // TODO 
        return tokens;
    }

    public static void main(String[] args) {

        String expr = "true&&!    false";
        System.out.println(tokenize(expr));

        expr = "true&&(false||true)";
        System.out.println(tokenize(expr));

        expr = "  !    !!   || true";
        System.out.println(tokenize(expr));

        expr = "&& true ! || false";
        System.out.println(tokenize(expr));
        
        expr = "(false  ||(true  &&false))  && ( (!  !false||true)";
        System.out.println(tokenize(expr));

        expr = "(false||(true  &&(false   || true)))&&(          false||true &&       !(false||true))";
        System.out.println(tokenize(expr));

        expr = "(false||(true  &&(false   || true)))&&(          false||true &&       !(false||true))";
        System.out.println(tokenize(expr));
    }
}
