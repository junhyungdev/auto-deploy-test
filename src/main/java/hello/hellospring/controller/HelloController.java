package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data","hello auto deploy!!!!????123");
        return "hello"; // resources/templates 하위의 hello를 찾아 model을 hello.html에 넘겨서 랜더링 한다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model)
    {                       // 웹 url에서 쿼리 파리미터로 name이라는 키값을 가진 데이터가 name에 저장된다.
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http response의 body에 해당 메소드의 리턴값을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name)
    {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
