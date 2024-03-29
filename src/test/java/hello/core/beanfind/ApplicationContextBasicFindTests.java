package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTests {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName(value = "빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = annotationConfigApplicationContext.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName(value = "이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = annotationConfigApplicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName(value = "구체 타입으로만 조회")
    void findBeanByImplementType() {
        MemberService memberService = annotationConfigApplicationContext.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName(value = "빈 이름으로 조회 X")
    void findBeanByNameX() {
//        annotationConfigApplicationContext.getBean("xxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, () -> annotationConfigApplicationContext.getBean("xxxx", MemberService.class));
    }
}
