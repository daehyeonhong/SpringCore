package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class ApplicationContextBasicFindTests {

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Bean 이름으로 조회")
    void findBeanByName() {
        final MemberService memberService = annotationConfigApplicationContext.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 Type 으로 조회")
    void findBeanByType() {
        final MemberService memberService = annotationConfigApplicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현체 Type으로 조회")
    void findBeanByName2() {
        final MemberService memberService = annotationConfigApplicationContext.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름을 조회 X")
    void findBeanByNameX() {
//        annotationConfigApplicationContext.getBean("xxxx", MemberService.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> annotationConfigApplicationContext.getBean("xxxx", MemberService.class));
    }

}