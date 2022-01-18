package hello.core.member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> findById(Long memberId);
}
