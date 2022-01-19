package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     * @param member
     * @param price  : 할인 대상 금액
     * @return
     */
    int discount(Member member, int price);
}
