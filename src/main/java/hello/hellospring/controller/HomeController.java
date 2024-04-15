package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home"; //teplates에서 home.html을 찾음, Contrller가 static 파일보다 우선순위가 높아
    }                  //                             static의 index.html이 아닌 home.html이 메인화면이 된다.
}
