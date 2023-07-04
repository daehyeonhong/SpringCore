package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTests {
    @Test
    @DisplayName(value = "Spring 없는 순수한 DI Container")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        MemberService memberServiceF = appConfig.memberService();
        MemberService memberServiceS = appConfig.memberService();
        System.out.println("memberServiceF = " + memberServiceF);
        System.out.println("memberServiceS = " + memberServiceS);

        //memberServiceF != memberServiceS
        assertThat(memberServiceF).isNotSameAs(memberServiceS);
    }

    @Test
    @DisplayName(value = "Singleton Pattern을 적용한 Object 사용")
    void singletonServiceTest() {
        SingletonService instanceF = SingletonService.getInstance();
        SingletonService instanceS = SingletonService.getInstance();
        System.out.println("instanceF = " + instanceF);
        System.out.println("instanceS = " + instanceS);
        assertThat(instanceF).isSameAs(instanceS);
        //same ==
        //equal equals
    }
}
