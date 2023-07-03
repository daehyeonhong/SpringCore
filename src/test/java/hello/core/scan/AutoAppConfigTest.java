package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AutoAppConfigTest {
    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void basicScan() {
        final MemberService memberService = this.applicationContext.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
