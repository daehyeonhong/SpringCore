package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationContextSameBeanFindTest {

    private final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName(value = "타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        assertThatThrownBy(() -> this.annotationConfigApplicationContext.getBean(MemberRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName(value = "타입이 같은 빈이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName() {
        final MemberRepository memberRepository = this.annotationConfigApplicationContext.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName(value = "특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        final var beansOfType = this.annotationConfigApplicationContext.getBeansOfType(MemberRepository.class);
        beansOfType.forEach((key, value) -> System.out.printf("beanName = \"%s\", bean = \"%s\"%n", key, value));
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType).hasSize(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
