package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 통합 테스트 방식 (spring도 띄우고 DB도 점검)
 * 통합도 필요하나 단위 테스트를 주로 연습할 것을 권장하심.
 */
@SpringBootTest
@Transactional // test후에 rollback을 해줌으로써 db에 남지 않아 같은 테스트를 똑같이 하는게 가능하다(ex. 회원가입)
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    // Test는 직관적일 수 있도록
    // 과감하게! 한글로 메서드 이름을 작성한다.
    @Test
    void 회원가입() {
        // 영한님이 추천하는 테스트 문법(?)
        // 상황에 따라 안 맞을 수도 있으나, 일단은 이렇게 학습하자

        //  given ( 무언가 주어짐 )
        Member member = new Member();
        member.setName("spring1");

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
        // 하지만 더 좋은 문법을 따로 제공한다.!
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
    }

}