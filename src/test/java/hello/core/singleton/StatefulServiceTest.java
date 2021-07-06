package hello.core.singleton;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class StatefulServiceTest {

    @Test
    void statefulServiceSingleTone() {
        final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = annotationConfigApplicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = annotationConfigApplicationContext.getBean(StatefulService.class);

        /*ThreadA: A 사용자 10,000원 주문*/
        final int userA = statefulService1.order("userA", 10000);
        /*ThreadB: B 사용자 20,000원 주문*/
        final int userB = statefulService2.order("userB", 20000);

        /*ThreadA: 사용자 A 주문 금액 조회*/
//        int price = statefulService1.getPrice();
        log.info("{}", userA);

//        Assertions.assertThat(userA).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}