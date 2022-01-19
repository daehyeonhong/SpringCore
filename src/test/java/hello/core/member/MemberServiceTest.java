package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private final MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "member", Grade.BASIC);
        //when
        this.memberService.join(member);
        Member findMember = this.memberService.findMember(1L).get();
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
