매핑이란? 해당 값이 다른 값을 가리키도록 하는 것

하나의 값을 다른 값으로 대응시키는 것

url path = url 경로

### @RequestMapping

 URL을 컨트롤러의 클래스나 메소드로 연결시키는 (매핑할 때 사용하는) 어노테이션이다. 클래스나 메소드 선언부에 URL을 명시하여 사용한다. 

클라이언트는 URL로 요청을 전송하고, 요청된 **URL을 어떤 메소드가 처리할지 여부를 결정하는 것**이 리퀘스트 매핑이다. 

@RequestMapping(value={"", "/info", "info*","view/"})

method = RequestMethod.GET는 메소드 설정이다.

이런식으로 사용할 수도 있다. 

```java
// 아래소스를
@RequestMapping(value ="/getBoardList", method = { RequestMethod.GET})
 
// 이렇게 줄일수 있다.
@GetMapping("/getBoardList")
```

@GetMapping 조회

@PostMapping  생성

@PutMapping  수정

@DeleteMapping  삭제



- @RequestParam

  메소드에서 파라미터를 받아오는 게 필수가 아니라 선택적으로 사용할때는 required=false 로 설정해야한다.  (파라미터를 넘기지 않으면 에러 발생)

  파라미터를 String 형식으로 변환해서 넣어준다. 변수에.

  ```java
  @RequestMapping(value = "/board/i")
  public String requestParma(@RequestParam(value="Id", required = false) String Id, @RequestParam ("page") String page){
      return "hello";
  }
  ```

  defaultValue="1"하면 값이 안 들어왔을 때 1을 설정해준다.

  http://127.0.0.1?id=1&page=2같이 전달될 때 사용한다. (파라미터의 값과 이름을 함께 전달)

  

- @PathVarialble

  ID를 그냥 URL에 포함시켜버리는거... RequestParam 에서 /acc?id=100으로 할걸 /acc/100으로 쓴다.

  리퀘스트 매핑 값으로 {매개변수}를 쓰고, 패치 밸리블을 이용해서 {매개변수}와 동일한 이름을 갖는 파라미터를 추가하면 된다.

  ```java
  @RequestMapping(value = "user/email/{email:.+}", method = RequestMethod.GET)
  public ModelAndView getUserByEmail(@PathVariable("email") String email) {
      return getUserByEmail
  }
  ```

  들어오는 값을 변수로 사용하겠다는 의미이다. 

