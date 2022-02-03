package hello.core.singleton;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTests {
    @Test
    void configurationDeep() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = applicationContext.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    }
}
