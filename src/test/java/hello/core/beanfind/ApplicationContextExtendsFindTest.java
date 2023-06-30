package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationContextExtendsFindTest {
    private final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName(value = "부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        assertThatThrownBy(() -> this.annotationConfigApplicationContext.getBean(DiscountPolicy.class)).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName(value = "부모 타입으로 조회 시, 자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName() {
        final DiscountPolicy rateDiscountPolicy = this.annotationConfigApplicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName(value = "특정 하위 타입으로 조회")
    void findBeanBySubType() {
        final DiscountPolicy rateDiscountPolicy = this.annotationConfigApplicationContext.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class)
                .isInstanceOf(RateDiscountPolicy.class)
                .isNotInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName(value = "부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        final Map<String, DiscountPolicy> beansOfType = this.annotationConfigApplicationContext.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType).hasSize(2);
        beansOfType.forEach((key, value) -> System.out.printf("beanName = \"%s\", bean = \"%s\"%n", key, value));
        System.out.println("beansOfType = " + beansOfType);
    }

    @Test
    @DisplayName(value = "부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        final Map<String, Object> beansOfType = this.annotationConfigApplicationContext.getBeansOfType(Object.class);
        beansOfType.forEach((key, value) -> System.out.printf("beanName = \"%s\", bean = \"%s\"%n", key, value));
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType).hasSizeGreaterThan(2);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
