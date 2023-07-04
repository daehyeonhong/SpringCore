package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired private DiscountPolicy rateDiscountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(final Long memberId, final String itemName, final int itemPrice) {
        final Member member = this.memberRepository.findById(memberId).get();
        final int discountPrice = this.discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Test 용도
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}
