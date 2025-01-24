package question;

import lombok.SneakyThrows;

import java.io.*;

/**
 * <p>背景: C/C++程序构建过程需要经过预处理、编译、汇编和链接，最终生成目标码。其中预处理文件用于展开头文件和宏替换，
 * 预处理文件中使用预处理标记表示该行代码与原始代码的对应关系，以#开头，后面为原始文件的行号和原始文件路径等信息。如下所示：
 * # 221 "e:\\mingw\\lib\\gcc\\mingw32\\6.3.0\\include\\c++\\mingw32\\bits\\c++config.h" 3
 * <p>功能：实现一个函数，输入Question1_input.i文件,过滤掉以#开头的预处理行(用空行替代)，把结果输出到Question1_output.i文件中

 */
public class Question1 {
	@SneakyThrows
	public void answer() {
	    //TODO
		File readFile = new File("E:\\mingw\\lib\\gcc\\mingw32\\6.3.0\\include\\c++\\mingw32\\bits\\Question1_output.i");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(readFile));
		FileWriter fileOutputStream = new FileWriter("E:\\mingw\\lib\\gcc\\mingw32\\6.3.0\\include\\c++\\mingw32\\bits\\Question1_output_o.i");
		String temp=null;
		while ((temp=bufferedReader.readLine())!=null){
			 temp = temp.replace("#", "\r\n");
			fileOutputStream.append(temp);
		}
		fileOutputStream.close();
	}
	public static void main(String[] args) {

		new Question1().answer();
    }
}
 