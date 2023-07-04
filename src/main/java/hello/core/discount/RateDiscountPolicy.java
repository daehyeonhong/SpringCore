package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Service;

@Service
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_PERCENT = 10;

    @Override
    public int discount(Member member, int price) {
        return member.getGrade() == Grade.VIP ? price * DISCOUNT_PERCENT / 100 : 0;
    }
}
