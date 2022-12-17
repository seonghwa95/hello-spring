package hello.hellospring.repository;

import domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    // 실무에서는 이런식으로 공유되는 변수를 저장할 때
    // 동시성 문제가 있을 수 있기 때문에 concurrentHashMap을 사용해야 한다.
    private static Map<Long, Member> store = new HashMap<>();
    // long에서도 동시성 문제가 있을 수 있다.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 과거의 방식
//        return store.get(id);  결과가 없으면 null을 반환한다
        // 요즘의 방식 -> null이 나올 가능성이 있다면
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다 사용
        return store.values().stream()  // loop를 계속 돌린다.
                .filter(member -> member.getName().equals(name))
                .findAny();
                // member.getname 이 parameter name과 같은지 확인
                // 같으면 필터링
                // 그 중에서 찾으면 그냥 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
