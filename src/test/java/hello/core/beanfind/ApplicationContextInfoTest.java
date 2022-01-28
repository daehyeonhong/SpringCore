package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName(value = "모든 빈 출력하기")
    void findAll() {
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = annotationConfigApplicationContext.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " bean = " + bean);
        }
    }

    @Test
    @DisplayName(value = "애플리케이션 빈 출력하기")
    void findApplicationBeans() {
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName);
//            BeanDefinition.ROLE_APPLICATION: 직접 등록한 Application Bean
//            BeanDefinition.ROLE_INFRASTRUCTURE: 스프링이 내부에서 등록한 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = annotationConfigApplicationContext.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " bean = " + bean);
            }
        }
    }

}
