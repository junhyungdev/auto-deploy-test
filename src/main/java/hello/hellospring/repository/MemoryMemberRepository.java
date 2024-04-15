package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));// Optional.ofNullable 을 통해 null이어도 감쌀 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store를 순회하면서 파라미터로 넘어온 member의 이름과 같은지 확인
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나라도 찾으면 반환, 없으면 optional에 null을 넣어 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 values() 즉 member들을 반환
    }

    public void clearStore(){ // 테스트를 위하여 데이터를 비워주는 메소드
        store.clear();
    }
}
