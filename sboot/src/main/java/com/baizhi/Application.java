package com.baizhi;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;*/

@MapperScan("com.baizhi.dao")
//@EnableAspectJAutoProxy  关于aop类的注解
/*@EnableGlobalMethodSecurity(securedEnabled = true)*/
@SpringBootApplication()
@EnableScheduling
@EnableTransactionManagement
public class    Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    System.out.println("(♥◠‿◠)ﾉﾞ sboot启动成功   ლ(´ڡ`ლ)ﾞ  \n" + " .-------.       ____     __  " + "   " +
        "   \n" + " |  _ _   \\      \\   \\   /  /     \n" + " | ( ' )  |       \\  _. " +
        "/ " + " '       \n" + " |(_ o _) /        _( )_ .'         \n" + " | (_,_).' " +
        "__ " + " ___(_ " + "o _)'          \n" + " |  |\\ \\  |  ||   |(_,_)'         " +
        "\n" + " | " + " | \\ `'   " + "/|   `-'  /           \n" +
        " |  |  \\    /  \\  " + "    /          " + " \n" + " ''-'   " +
        "`'-'    `-..-'              ");
  }





}
