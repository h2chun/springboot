package springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //JSON 반환하는 컨트롤러로 만듬.
public class HelloController {

    @GetMapping("/hello")   //Http Method Get 요청을 받을 수 있는 API 만들어줌.
    public String hello(){

        return "hello";
    }
}
