package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOver() {
        Long memberId = 1L;

        final Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        final Order order = orderService.createOrder(member.getId(), "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setMemberRepository(new MemoryMemberRepository());
        orderService.setDiscountPolicy(new RateDiscountPolicy());
        orderService.createOrder(1L, "itemA", 10000);
    }

}