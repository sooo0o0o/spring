package kr.co.ch03.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"kr.co.ch03"})
@EnableAspectJAutoProxy //AOP 활성화

public class AppConfig {


}
