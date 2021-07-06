package hello.core.beanFind;

import hello.core.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
class ApplicationContextInfoTests {

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 Bean 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            final Object bean = annotationConfigApplicationContext.getBean(beanDefinitionName);
            log.info("name : {}, object : {}", beanDefinitionName, bean);
        }
    }

    @Test
    @DisplayName("Application Bean 출력하기")
    void findApplicationAllBean() {
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();

        /**
         * Role ROLE_APPLICATION: 직접 등록한 Application Bean
         * Role ROLE_INFRASTRUCTURE: Spring 내부에서 사용하는 Bean
         */
        for (String beanDefinitionName : beanDefinitionNames) {
            final BeanDefinition beanDefinition = annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                final Object bean = annotationConfigApplicationContext.getBean(beanDefinitionName);
                log.info("name : {}, object : {}", beanDefinitionName, bean);
            }
        }
    }

}