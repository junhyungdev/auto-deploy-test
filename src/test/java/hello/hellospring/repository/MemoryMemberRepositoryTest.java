package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; //Assertions 를 static import

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 메소드가 끝나고 실행되는 메소드로 하나의 테스트가 끝나면 데이터를 비워주기 위한 역할
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring"); // ctrl + shift +enter하면 바로아래 줄로 내려감

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // findById의 리턴값은 Optional이르로 get으로 꺼낼 수 있음
        //Assertions.assertEquals(member,result);// result와 member가 동일한지 확인 (기대값,결과값)
        assertThat(member).isEqualTo(result); //요즘 많이 사용하는 assertj의 assertThat
        // Assertions에서 alt+enter로 static import 하면 앞에 Assertions를 붙이지 않아도 된다.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // rename 단축키 shift f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // rename 단축키 shift f6
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
