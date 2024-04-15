package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트 케이스에 어노테이션이 붙은 경우
class MemberServiceIntegrationTest { //테스트 시작전 트랜잭션을 걸고 테스트가 끝나면(테스트 메소드마다) rollback을 해준다. 테스트는 반복할 수 있어야하기 때문에

    @Autowired
    MemberService memberService; // 테스트는 필드기반 주입으로 하는것이 편하다. 다른곳에서 사용할것이 아니기 때문에
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() { //테스트 코드는 한글로 작성해도 괜찮다.
    //테스트 코드의 3단계 구조
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 이 메소드를 실행할때
                                                                                    // IllegalStateException이 발생해야한다는 의미
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }

}