package hello.core.beanDefinition;

import hello.core.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@Slf4j
public class BeanDefinitionTests {

    //    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        final String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            final BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);
            log.info("beanDefinitionName : {}, beanDefinition : {}", beanDefinitionName, beanDefinition);
        }
    }

}