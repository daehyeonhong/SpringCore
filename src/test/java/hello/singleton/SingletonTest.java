package hello.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonTest {
    @Test
    @DisplayName("`Spring`이 없는 순수한 DI Continer")
    void pureContainer() {
        final AppConfig appConfig = new AppConfig();
        final MemberService memberService1 = appConfig.memberService();
        final MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1)
                .isNotSameAs(memberService2)
                .isNotEqualTo(memberService2)
                .isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("`Singleton` 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        final SingletonService singletonService1 = SingletonService.getInstance();
        final SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }
}
