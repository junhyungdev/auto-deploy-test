package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // MemberService에도 new로 MemoryMemberRepository 객체를 생성하고
    // MemberServiceTest에도 new로 MemoryMemberRepository 객체를 생성한다.
    // 테스트를 하는데 두 객체는 다른 객체이므로 좀 애매하다. static 변수는 동일한 타입의 인스턴스가 모두 공유?
    // 같은 repository를 사용할 수 있도록 변경해주자
    private final MemberRepository memberRepository;

    // MemberService 입장에서는 memberRepository를 외부에서 넣어 준다. 이것을 DI(Dependance Injection)의존성 주입이라 한다.


    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원 가입
     */
    public Long join(Member member){ // jpa는 join 들어올때 모두 트랜잭션 안에서 실행되어야 한다?
        // 같은 이름이 있는 중복 회원x
//        Optional<Member> result = memberRepository.findByName(member.getName());// 리턴값을 쓰지 않고 ctrl+alt+v 하면 자동 작성
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); 아래처럼 간단히 작성 가능
        
//        memberRepository.findByName(member.getName())
//                .ifPresent(m->{
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                }); // 로직이 있는 경우 메소드로 뽑아 내는게 좋다.

        validateDuplicateMember(member); // 중복회원 검증(메소드로 뽑아낸 결과)
        System.out.println(member.getId());
        memberRepository.save(member);
        System.out.println(member.getId());
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
