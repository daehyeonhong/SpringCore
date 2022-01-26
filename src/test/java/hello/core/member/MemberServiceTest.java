package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberService = new AppConfig().memberService();
    }

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
