package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트 실행 전 동작함
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository(); // a
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 각 메소드가 끝나고 실행되는 메소드로 하나의 테스트가 끝나면 데이터를 비워주기 위한 역할
    public void afterEach(){
        memberRepository.clearStore();
    }
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
//        assertThat(member.getName()).isEqualTo("hello");
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
//        try{
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        } try catch를 사용하지 않고 주로 아래와 같이 구현한다.

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 이 메소드를 실행할때
                                                                                    // IllegalStateException이 발생해야한다는 의미
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}