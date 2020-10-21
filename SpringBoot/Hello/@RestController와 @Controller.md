### @RestController와 @Controller

return "hello"를 하면 그냥 hello가 보내질 줄 알았다. 그런데 hello.html이 보내졌다. 다른 코드에서도 이런 실수를 했는데, 난 문자열 hello를 찍고 싶어서 return "hello"를 했는데 서버를 키니 오류가 났다. 그 이유는 @Controller 어노테이션을 썼기 때문이었다. 

결론적으로 말하면 **문자열을 보내고 싶다면 @RestController**을 쓰던가, @Controller을 쓰고 메소드 앞에 **@ResponseBody 어노테이션**을 붙여줘야 한다. 

@RestController = @Controller + @ResponseBody로 보면 편하다.  (@RestController는 이를 제외하고 대부분의 과정이 @Controller와 비슷하게 처리된다. RestController는 리턴값에 자동으로 @ResponseBody가 붙게 된다.)



### @ResponseBody와 @RequestBody

body는 본문이라는 뜻이다.

**@ResponseBody**

- ResponseBody를 사용한 메소드에서 리턴되는 값은 View를 통해서 출력되지 않고 HTTP Body에(본문에) 직접 쓰여지게 된다. 

- 자바 객체를 HTTP 요청의 본문 내용으로 매핑한다.

  > 객체를 리턴하게되면 객체가 자동으로 json형태로 바뀐다.

- 사용방법

  그냥 메소드 앞에 붙이면 된다.

**`View??`**

- View는 클라이언트 측 기술인 html, css, js들을 모아둔 컨테이너이다.

**@RequestBody**

- HTTP 요청데이터(HTTP 요청의 본문 내용)를 자바 객체로 매핑하는 역할을 한다.

- 사용방법

  @RequestParam과 똑같이 사용한다.

**`@RequestParam과의 차이는?`**

@RequestParam은 파라미터를 받는다. (쿼리 형식으로 - ?id=10&pw=22)

@RequestBody는 json을 받는다. (클라이언트가 json형태로 요청을 보내는 경우가 있다고 한다.)



HomeController

```java
package com.dsm.springtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "hello";
    }
}
```

JongController

```java
package com.dsm.springtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class JongController {
    @RequestMapping("/jong")
    //@ResponseBody               // 값을 보냄
    public String jong(@RequestParam("id") String id, @RequestParam("pw") String pw) {
        return "id : " + id + " pw : " + pw;
    }
}
```

hello.html

```html
<!DOCTYPE html>
<html>
<body>

<form action = "/jong" method="get">
    id : <input type="text" name="id"/><br/>
    pw : <input type="text" name="pw"/><br/>
    <input type="submit"/>
</form>

</body>
</html>
```
