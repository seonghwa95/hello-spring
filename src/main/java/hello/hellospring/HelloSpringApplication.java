package hello.hellospring;
// 기본적인 컴포넌트 스캔 대상
// 이 패키지의 하위만 스캔 대상이다.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
