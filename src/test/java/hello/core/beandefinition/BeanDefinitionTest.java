package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BeanDefinitionTest {
    private final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    private final GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBeanJava() {
        final String[] beanDefinitionNames = this.annotationConfigApplicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).filter(
                beanDefinitionName -> this.annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName).getRole() == BeanDefinition.ROLE_APPLICATION
        ).forEach(
                beanDefinitionName -> System.out.println("beanDefinitionName = " + beanDefinitionName + ", beanDefinition = " + this.annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName))
        );

        assertThat(beanDefinitionNames).contains("appConfig", "memberService", "orderService", "memberRepository", "discountPolicy");
    }

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBeanXml() {
        final String[] beanDefinitionNames = this.genericXmlApplicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).filter(
                beanDefinitionName -> this.genericXmlApplicationContext.getBeanDefinition(beanDefinitionName).getRole() == BeanDefinition.ROLE_APPLICATION
        ).forEach(
                beanDefinitionName -> System.out.println("beanDefinitionName = " + beanDefinitionName + ", beanDefinition = " + this.genericXmlApplicationContext.getBeanDefinition(beanDefinitionName))
        );

        assertThat(beanDefinitionNames).contains("memberService", "orderService", "memberRepository", "discountPolicy");
    }

}
