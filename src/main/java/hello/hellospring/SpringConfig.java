package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔 방식(@Service, @Repository @Autowired 방식)을 사용한다.
// 정형화 되지 않거나 상황에 따라 구현 클래스를 변경해야 할 경우에는
// 아래와 같은 설정을 통해 스프링 빈에 등록해서 사용하는 경우 변경에 용이하다.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
