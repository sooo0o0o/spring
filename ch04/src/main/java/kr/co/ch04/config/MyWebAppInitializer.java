package kr.co.ch04.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //스프링 웹 애플리케이션 컨텍스트 생성
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        //스프링 웹 애플리케이션 구성 클래스 등록
        context.register(AppConfig.class);

        //DispatcherServlet 설정
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic serviceRegistration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        serviceRegistration.setLoadOnStartup(1);
        serviceRegistration.addMapping("/");
    }
}
