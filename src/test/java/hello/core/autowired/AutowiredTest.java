package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

class AutowiredTest {
    @Test
    void AutowiredOption() {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);
        applicationContext.getBean(TestBean.class);
    }

    static class TestBean {
        // Member는 스프링 빈이 아니다.
        // required = false로 설정하면 의존관계가 없으면 메서드 자체가 호출 안됨
        @Autowired(required = false)
        public void setNoBean1(final Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // @Nullable로 설정하면 의존관계가 없으면 null이 입력된다.
        @Autowired
        public void setNoBean2(@Nullable final Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional로 설정하면 의존관계가 없으면 Optional.empty가 입력된다.
        @Autowired
        public void setNoBean3(final Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
