package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationContextBasicFindTest {
    private final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName(value = "빈 이름으로 조회")
    void findByBeanName() {
        final MemberService memberService = this.annotationConfigApplicationContext.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName(value = "이름 없이 타입[인터페이스]으로 조회")
    void findByInterface() {
        final MemberService memberService = this.annotationConfigApplicationContext.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName(value = "이름 없이 타입[구현체]으로 조회")
    void findByImplementation() {
        final MemberService memberService = this.annotationConfigApplicationContext.getBean(MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName(value = "빈 이름으로 조회 불가")
    void findBeanByNameX() {
        assertThatThrownBy(() -> this.annotationConfigApplicationContext.getBean("xxxxx", MemberService.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
