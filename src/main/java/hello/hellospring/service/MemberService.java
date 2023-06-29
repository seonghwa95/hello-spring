package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// DATA를 저장하거나 변경할때는 항상 transaction이 필요함 (JPA)
@Transactional
public class MemberService {
    // Ctrl + Shift + t
    // test 자동 생성
    private final MemberRepository memberRepository;

    // 새로운 인스턴스를 계속 만들지 않도록
    // 외부에서 주입받는 느낌이다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 서비스는 비즈니스 로직을 다루기 때문에
    // 작명(?)을 할 때도 비즈니스에 맞게 하는 것이 좋다.
    // repository에서는 개발적인 이름으로 직관적으로 짓는다.
    // TDD에 대해서 좀더 공부하고 습관화할 수 있도록 하자
    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 불가
        // Optional -> null일 가능성이 있는 것들은 웬만하면 감싸서
        // 이용하는 요즘 추세!

        // Ctrl + Alt + Shift + t 로 메서드로 뽑아낸다. ( 요런건 메서드로 뽑아서 많이 쓴다!! )
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 같은 이름이 있는 중복 회원 X 메서드
     */
    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        // isPresent -> result에 값이 있으면 밑에 로직 실행
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단일 회원 조회
     */
    public Optional<Member> findOne(long memberId) {
        return memberRepository.findById(memberId);
    }
}
