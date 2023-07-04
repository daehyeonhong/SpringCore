package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {
    @Test
    void createOrder() {
//        final OrderServiceImpl orderService = new OrderServiceImpl();
        // orderService.createOrder(1L, "itemA", 10000);
        final MemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

        final OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
        final Order order = orderService.createOrder(1L, "itemA", 10_000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1_000);

    }
}
