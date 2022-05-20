package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import static org.assertj.core.api.Assertions.assertThat;

class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        final PrototypeBean prototypeBean1 = annotationConfigApplicationContext.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        final PrototypeBean prototypeBean2 = annotationConfigApplicationContext.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        annotationConfigApplicationContext.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
