package hello.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {
    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void statefulServiceSingleton() {
        final StatefulService statefulService1 = this.applicationContext.getBean(StatefulService.class);
        final StatefulService statefulService2 = this.applicationContext.getBean(StatefulService.class);

        // ThreadA: A 사용자 \10,000 주문
        final int userAPrice = statefulService1.order("userA", 10_000);

        // ThreadB: B 사용자 \20,000 주문
        final int userBPrice = statefulService2.order("userB", 20_000);

        // ThreadA: 사용자 A 주문 금액 조회
        System.out.println("userAPrice = " + userAPrice);
        assertThat(userBPrice).isEqualTo(20_000);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
