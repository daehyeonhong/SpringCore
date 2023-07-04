package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AllBeanTest {
    @Test
    void findAllBean() {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        final DiscountService discountService = applicationContext.getBean(DiscountService.class);

        final Member member = new Member(1L, "userA", Grade.VIP);

        final int discountPrice = discountService.discount(member, 10_000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1_000);

        final int rateDiscountPrice = discountService.discount(member, 20_000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2_000);

    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        DiscountService(final Map<String, DiscountPolicy> policyMap, final List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(final Member member, final int i, final String fixDiscountPolicy) {
            final DiscountPolicy discountPolicy = this.policyMap.get(fixDiscountPolicy);
            return discountPolicy.discount(member, i);
        }
    }
}
