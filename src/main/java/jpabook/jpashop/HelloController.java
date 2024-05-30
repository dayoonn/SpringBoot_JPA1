package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!!"); //data라는 key값에 hello!!!를 넘김
        return "hello"; //resources/templates/hello.html으로 리턴

    }
}
