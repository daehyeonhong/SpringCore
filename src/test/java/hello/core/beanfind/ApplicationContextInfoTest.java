package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

class ApplicationContextInfoTest {
    private final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName(value = "모든 빈 출력하기")
    void findAllBeans() {
        Arrays.stream(this.annotationConfigApplicationContext.getBeanDefinitionNames())
                .map(beanName -> String.format("beanDefinitionName = %s object = %s", beanName, annotationConfigApplicationContext.getBean(beanName)))
                .forEach(System.out::println);
    }
    @Test
    @DisplayName(value = "애플리케이션 빈 출력하기")
    void findApplicationBeans() {
        Arrays.stream(this.annotationConfigApplicationContext.getBeanDefinitionNames())
                // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
                // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
                // 스프링이 내부에서 사용하는 빈은 내가 등록한 빈이 아니라는 것을 알 수 있다.
                .filter(beanDefinitionName -> BeanDefinition.ROLE_APPLICATION == this.annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName).getRole())
                .map(beanName -> String.format("beanDefinitionName = %s object = %s", beanName, annotationConfigApplicationContext.getBean(beanName)))
                .forEach(System.out::println);
    }
}
