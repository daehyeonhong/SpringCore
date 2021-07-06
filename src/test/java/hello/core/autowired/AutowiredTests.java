package hello.core.autowired;

import hello.core.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import java.util.Optional;

@Slf4j
public class AutowiredTests {

    @Test
    void AutowiredOption() {
        final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false)
        public void setNoBeanA(Member beanA) {
            log.info("noBeanA : {}", beanA);
        }

        @Autowired
        public void setNoBeanB(@Nullable Member beanB) {
            log.info("noBeanB : {}", beanB);
        }

        @Autowired
        public void setNoBeanC(Optional<Member> beanC) {
            log.info("noBeanC : {}", beanC);
        }
    }

}