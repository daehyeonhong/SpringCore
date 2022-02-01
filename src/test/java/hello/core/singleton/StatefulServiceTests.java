package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTests {
    @Test
    @DisplayName(value = "상태 유지 테스트")
    void statefulServiceSingleton() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulServiceF = applicationContext.getBean(StatefulService.class);
        StatefulService statefulServiceS = applicationContext.getBean(StatefulService.class);

        //ThreadA: A 사용자 10,000원 주문
        statefulServiceF.order("userA", 10000);
        //ThreadB: B 사용자 10,000원 주문
        statefulServiceS.order("userB", 20000);

        //ThreadA: A 사용자 주문 금액 조회
        int price = statefulServiceF.getPrice(20000);
        System.out.println("price = " + price);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
