package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //찾지 못하면 null이 반환되는데 이때 null을 Optional에 감싸서 반환? (java8 기능)
    Optional<Member> findByName(String name);

    List<Member> findAll();


}
