package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.*;

@Slf4j
public class AutoAppConfigTest {

    @Test
    void basicScan() {
        final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        final MemberService memberService = annotationConfigApplicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        final OrderServiceImpl bean = annotationConfigApplicationContext.getBean(OrderServiceImpl.class);
        log.info("bean : {}", bean);
    }

}