package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;

@Configuration
public class springConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public springConfig(MemberRepository memberRepository) { // spring jpa가 구현체를 만들어 놓은것을 주입 받기만 하면 된다.
        this.memberRepository = memberRepository;
    }

//    private EntityManager em;
//
//    @Autowired
//    public springConfig(EntityManager em) {
//        this.em = em;
//    }
    //    private DataSource dataSource;
//
//    @Autowired
//    public springConfig(DataSource dataSource) { // spring boot가 dataSource 주입 해줌.
//        this.dataSource = dataSource;
//    }

    //일반적으로 Controller는 @Bean 으로 직접 등록하지 않고 @Controller와 @Autowired를 사용하여 등록하고 의존성을 주입받는다.
    @Bean // 스프링 빈을 등록할거라는 의미. 스프링이 실행될때 스프링 컨테이너에 아래 메소드를 실행시켜 인스턴스 즉 빈을 등록해준다.
    public MemberService memberService(){             //memberService는 memberRepository의존성 주입이 필요(MemberService 생성자에는 memberRepository가 필요)
        return new MemberService(memberRepository); //스프링 빈에 등록되어 있는 memberRepository를 memberService를 넣어준다.
    }

    @Bean
    public MemberRepository memberRepository(){

        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
