- 일반적으로 패키지명은 웹사이트의 역순으로 한다. admin.jojoldu.com이라는 사이트면 com.jojoldu.admin으로 한다.
- 테스트 코드 작성을 도와주는 프레임워크들이 있다. 자바용인 JUnit을 사용한다.
- 테스트 클래스는 테스트할 대상 클래스 이름에 Test를 붙인다.



TDD

테스트가 주도하는 개발, 테스트 코드를 먼저 작성하는 것부터 시작한다.

단위 테스트

TDD 첫 번째 단계인 기능 단위 테스트 코드를 작성하는 것이다. TDD와 달리 테스트 코드를 먼저 작성해야하는 것도 아니고, 리팩토링도 포함되지 않는다.

[^리팩토링]: 외부 동작을 바꾸지 않으면서 내부 구조를 개선하는 방법, 코드가 작성된 후에 디자인을 개선하는 작업이다. (소프트웨어를 보다 이해하기 쉽고 수정하기 쉽도록 만드는 것이다.)



테스트 코드 왜 쓰는가?

- 단위 테스트가 아니라면 sout을 통해 결과를 눈으로 검증해야 한다. 테스트 코드를 작성하면 자동검증이 가능하다. 

- 개발자가 만든 기능을 보호해준다. 새로운 기능이 추가될 때 기존 기능이 잘 작동되는 것을 보장해준다. 



> apache와 tomcat

> apache 서버는 http웹서버를 말한다. 클라이언트가 GET, HOST...등의 메소드를 이용해 요청을하면 프로그램이 어떤 결과를 돌려주는 기능을 한다. 아파치는 웹서버이다.
>
> Tomcat은 WebApplicationServer라고 한다. 웹서버+웹컨테이너로 다양한 기능을 컨테이너에 구현하여 다양한 역할을 수행할 수 있는 서버를 말한다. 웹 컨테이너? 클라이언트의 요청이 있을 때 내부의 프로그램을 통해 결과를 만들어내고 다시 클라이언트에 전달해주는 역할을 한다.
>
> 웹 서버는 정적인 데이터를 처리하는 서버이다. 이미지나 단순 html파일을 제공하는 서버는 웹서버를 통하면 WAS를 이용하는 것보다 빠르고 안정적이다. WAS는 동적인 데이터를 처리하는 서버이다. DB와 연결되어 데이터를 주고받거나 프로그램으로 데이터 조작이 필요한 경우 WAS를 쓴다.



```JAVA
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
```

앞으로 만들 프로젝트의 메인 클래스가 된다. **@SpringBootApplication**으로 스프링 Bean 일기와 생성이 모두 자동으로 설정된다. 이 어노테이션이 있는 위치부터 설정을 읽어간다. **Application.run**으로 인해 내장 WAS를 실행한다.

[^내장 WAS]: 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것이다. 서버에 톰캣을 설치할 필요가 없다.

내장 WAS를 사용하는 것을 스프링 부트에서 권장하는데, 그 이유는 언제 어디에서나 같은 환경에서 스프링부트를 배포할 수 있기 때문이다. 



```java
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello ="hello";
        mvc.perform(get("/hello")) 
            // MockMvc를 통해 주소로 GET 요청. 체이닝 지원.
                .andExpect(status().isOk()) 
            // mvc.perform 결과 검증. http header의 상태 검증. (200, 404 등 상태 검증.) 여기서는 ok, 200인지 아닌지를 검증.
                .andExpect(content().string(hello));
        // mvc.perform의 결과 검증. 응답 본문의 내용 검증. Controller에서 hello 리턴, 이 값이 맞는지 검증한다.
    }
}
```

@RunWith (SpringRunner.class)

테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자를 (여기에서는 SpringRunner라는 실행자 사용.) 실행시킴. 실행자는 스프링 부트 테스트와 JUnit 사이 연결자 역할.

@WebMvcTest

선언 시 @Controller, @ControllerAdvice 사용 가능. @Service, @Repository 등 사용 불가. 여기서는 컨트롤러만 사용하기 때문에 선언.

@Test

테스트를 수행하는 메소드가 됨. JUnit은 테스트가 독립적으로 실행됨을 원칙으로 해서 @Test마다 객체를 생성한다.

private MockMvc mvc

웹 API를 테스트 할 때 사용. 스프링 MVC 테스트의 시작점. 이 클래스로 GET, POST에 대한 API 테스트 가능.

