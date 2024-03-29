package springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)    //스프링부트 테스트와 JUnit 사이에 연결자. 여기서는 SpringRunner 라는 스프링 실행자를 사용.
@WebMvcTest(controllers = HelloController.class)    //컨트롤러 사용가능. (서비스, 컴포넌트, 레파시토리 어노테이션은 사용 못함.)
public class HelloControllerTest {

    @Autowired  //스프링이 관리하는 빈을 주입 받음.
    private MockMvc mvc;    //이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))          //MockMvc 통해 /hello 주소로 HTTP GET 요청을 함.
                .andExpect(status().isOk())           //mvc.perform 의 결과를 HTTP Header Status 검증(200, 404, 500 등 상태)
                .andExpect(content().string(hello));  //mvc.perform 의 결과를 응답 본문의 내용으로 검증. ("hello"가 맞는지 검증)
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;
        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", is(name)))
                        .andExpect(jsonPath("$.amount", is(amount)));
        /*
        * param
        *   - API 테스트 시 요청 파라미터 설정
        *   - String 값만 허용
        * 
        * jsonPath
        *   - JSON 응답값을 필드별로 검증할 수 있는 메소드
        *   - $ 기준으로 필드명 명시
        * */
    }
}
