package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링 컨테이너가 생성될때(스프링 시작될때) @Controller 가 있으면 MemberController객체를 생성해서 넣어놓고 관리함.
public class MemberController {
    private final MemberService memberService;

    @Autowired // 생성자에 @Autowired가 있으면 스프링 컨테이너에 있는 memberService를 가져다 연결을 시켜준다.(DI)
    public MemberController(MemberService memberService) { // memberService를 가져오려면 memberService도 컨테이너에 있어야하기 때문에
        this.memberService = memberService;                // memberService에 @Service 를 붙여놓자.(@Repository도)
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){ // form에서 넘어온 데이터를 스프링이 setter를 동작시켜 MemberForm form에 저장
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList.html";
    }
}
