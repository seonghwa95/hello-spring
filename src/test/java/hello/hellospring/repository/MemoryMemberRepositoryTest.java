package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

// Test 실행
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    @AfterEach  // 각각의 메서드들이 끝난후에 실행되는 메서드
    public void afterEach() {
        repository.clearStore();
    }
    // 테스트 코드는 서로 순서의 상관이 없고
    // 의존 관게가 없도록 설계가 되어야 한다. (저장소 혹은 데이터가 테스트가 끝날 때마다 날아가도록 설정하자)
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional 방식의 값을 얻기 위해 get -> 좋은 방식은 아니라고 하심
        Member result = repository.findById(member.getId()).get();
        // 이렇게 바로 출력 해 볼수 있다. soutv 자동완성
        // 그러나 그렇게 계속 텍스트로 확인할 수는 없다!
        System.out.println("result = " + (result == member) );

        // 둘이 같다면 초록불       기대      실제
//        Assertions.assertEquals(member, result);
        // 둘이 다르기 때문에 빨간 불
//        Assertions.assertEquals(member, null);

        // 요즘 많이 쓰는 방식
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // Ctrl + Alt + v
        Member result = repository.findByName("spring1").get();
        // spring1 초록불   |  spring2 빨간불
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
