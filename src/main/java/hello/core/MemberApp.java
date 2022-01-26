package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import java.util.Optional;

public class MemberApp {
    public static void main(String[] args) {
        final MemberService memberService = new AppConfig().memberService();
        Member member = new Member(1L, "member", Grade.VIP);
        memberService.join(member);

        Optional<Member> findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.get().getName());
    }
}
