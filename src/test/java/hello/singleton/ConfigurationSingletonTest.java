package hello.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        final var applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        final var memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        final var orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
        final var memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        final var memberRepositoryFromMemberService = memberService.getMemberRepository();
        final var memberRepositoryFromOrderService = orderService.getMemberRepository();

        System.out.println("memberRepositoryFromMemberService = " + memberRepositoryFromMemberService);
        System.out.println("memberRepositoryFromOrderService = " + memberRepositoryFromOrderService);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberRepository)
                .isSameAs(memberRepositoryFromMemberService)
                .isSameAs(memberRepositoryFromOrderService);
    }
}
