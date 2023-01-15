package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 컨트롤러와 서비스를 연결시킨다.
    // 이것이 DI ( 컴포넌트 스캔과 자동 의존관계 설정 방식 )
    // 인스턴스를 여러개 생성하지 않고 스프링 컨테이너에 저장한 하나(싱글톤)를
    // 가져다 쓴다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
