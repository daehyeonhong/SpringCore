package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import static org.assertj.core.api.Assertions.assertThat;

class SingletonTest {
    @Test
    @DisplayName(value = "")
    void singletonBeanFind() {
        //given
        final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SingletonBean.class);
        //when
        final SingletonBean singletonBean1 = annotationConfigApplicationContext.getBean(SingletonBean.class);
        final SingletonBean singletonBean2 = annotationConfigApplicationContext.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        //then
        assertThat(singletonBean1).isSameAs(singletonBean2);
        annotationConfigApplicationContext.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
