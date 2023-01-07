package hello.hellospring.service;

import domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 독립적인 환경에서 테스트를 진행하기 위해서
    // 각각의 메서드 전에 실행되는 메서드
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 각각의 메서드 후에 실행되는 메서드
    @AfterEach
    public void afterEach() {memberRepository.clearStore();}
    // 메모리 클리어 역할


    // Test는 직관적일 수 있도록
    // 과감하게! 한글로 메서드 이름을 작성한다.
    @Test
    void 회원가입() {
        // 영한님이 추천하는 테스트 문법(?)
        // 상황에 따라 안 맞을 수도 있으나, 일단은 이렇게 학습하자

        //  given ( 무언가 주어짐 )
        Member member = new Member();
        member.setName("spring");

        // when ( 이것을 실행했을 때 )
        Long saveId = memberService.join(member);

        // then ( 이렇게 결과가 나와야 해 )
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 예외처리가 잘 돌아가는지 확인은 가능
        // 하지만 더 좋은 문법을 따로 제공한다.
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}