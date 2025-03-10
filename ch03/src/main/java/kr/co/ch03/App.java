package kr.co.ch03;

import kr.co.ch03.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 날짜: 2025.03.10
 * 이름: 곽혜수
 * 내용: ch03 Spring AOP 실습
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MyService myService = (MyService) context.getBean("myService");

        myService.insert();
        myService.select(null);
        myService.delete();
        myService.update();
    }
}
