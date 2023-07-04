package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTests {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName(value = "타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> annotationConfigApplicationContext.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName(value = "Type으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository memberRepositoryF = annotationConfigApplicationContext.getBean("memberRepositoryF", MemberRepository.class);
        MemberRepository memberRepositoryS = annotationConfigApplicationContext.getBean("memberRepositoryS", MemberRepository.class);
        assertThat(memberRepositoryF).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName(value = "특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beans = annotationConfigApplicationContext.getBeansOfType(MemberRepository.class);
        for (String key : beans.keySet()) System.out.printf("key = %s, value = %s", key, beans.get(key));
        System.out.println("beans = " + beans);
        assertThat(beans.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepositoryF() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepositoryS() {
            return new MemoryMemberRepository();
        }
    }
}
