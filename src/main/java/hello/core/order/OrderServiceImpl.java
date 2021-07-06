package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int ItemPrice) {
        final Member member = memberRepository.findById(memberId);

        final int discountPrice = discountPolicy.discount(member, ItemPrice);

        return new Order(memberId, itemName, ItemPrice, discountPrice);
    }

    // Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}