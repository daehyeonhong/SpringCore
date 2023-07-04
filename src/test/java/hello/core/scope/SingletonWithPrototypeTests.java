package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonWithPrototypeTests {
    @Test
    void prototypeFind() {
        try (final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class)) {
            final PrototypeBean prototypeBean1 = annotationConfigApplicationContext.getBean(PrototypeBean.class);
            prototypeBean1.addCount();
            assertThat(prototypeBean1.getCount()).isEqualTo(1);
            final PrototypeBean prototypeBean2 = annotationConfigApplicationContext.getBean(PrototypeBean.class);
            prototypeBean2.addCount();
            assertThat(prototypeBean2.getCount()).isEqualTo(1);
        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void singletonClientUsePrototype() {
        try (final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class)) {
            final ClientBean clientBean1 = annotationConfigApplicationContext.getBean(ClientBean.class);
            assertThat(clientBean1.logic()).isEqualTo(1);
            final ClientBean clientBean2 = annotationConfigApplicationContext.getBean(ClientBean.class);
            assertThat(clientBean2.logic()).isEqualTo(1);
            final PrototypeBean prototypeBean2 = annotationConfigApplicationContext.getBean(PrototypeBean.class);
            prototypeBean2.addCount();
            assertThat(prototypeBean2.getCount()).isEqualTo(1);
        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }

    @Scope(value = "singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        //        private final ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            final PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init = " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

    @Scope(value = "prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init = " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
