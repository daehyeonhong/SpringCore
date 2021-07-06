package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SingletonTests {

    @Test
    @DisplayName("Spring 없는 순수한 DI Container")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        /*
         *1. 조회: 호출할 때 마다 객체를 생성
         */
        MemberService memberService1 = appConfig.memberService();
        /*
         * 2. 조회: 호출할 때 마다 객체를 생성
         */
        MemberService memberService2 = appConfig.memberService();
        /*
         * 3. 조회: 호출할 때 마다 객체를 생성
         */
        MemberService memberService3 = appConfig.memberService();

        log.info("memberService1 : {}", memberService1);
        log.info("memberService2 : {}", memberService2);
        log.info("memberService3 : {}", memberService3);

        /*
         * memberService1 != memberService2
         */
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingleTonService singleTonService1 = SingleTonService.getInstance();
        SingleTonService singleTonService2 = SingleTonService.getInstance();
        assertThat(singleTonService1).isSameAs(singleTonService2);
        /*
         * same ==
         * equals
         */
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        /*
         *1. 조회: 호출할 때 마다 객체를 생성
         */
        MemberService memberService1 = applicationContext.getBean("memberService", MemberService.class);

        MemberService memberService2 = applicationContext.getBean("memberService", MemberService.class);

        MemberService memberService3 = applicationContext.getBean("memberService", MemberService.class);

        log.info("memberService1 : {}", memberService1);
        log.info("memberService2 : {}", memberService2);
        log.info("memberService3 : {}", memberService3);

        assertThat(memberService1).isSameAs(memberService2).isSameAs(memberService3);
    }

}