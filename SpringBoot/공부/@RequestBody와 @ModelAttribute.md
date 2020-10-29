## @RequestBody

- POST 형식에서만 사용해야 한다.

  GET/POST 등은 다른 방식으로 데이터를 서버로 전송한다. RequestBody를 쓸시 GET방식은 Request 패킷에 Body가 없어서 데이터를 가져올 수 없다.

- JSON이나 XML같은 데이터를 읽을 때 사용한다.

```java
@PostMapping("/")
public String main(@RequestBody UserRepository user){
    user.getId();
    return "main";
}
```

```java
@Repository
public Class UserRepository {
	private Stirng id;
	privqte String pw;
	//getter와 setter 존재
}
```



## @ModelAttribute

도메인이나 오브젝트로 파라미터를 받을 경우에 사용할 수 있다.

파라미터에 쓸 경우 데이터의 이름을 지정해 해당 데이터만 가져온다. {name:'이종은', comment:'안녕하세요'}라고 프론트에 있고 컨트롤러에 @ModelAttribute('comment') String 

```java
@PostMapping("/main.do") public String main(@ModelAttribute("comment") String comment) { 		
    System.out.prinln(comment); 
    return "main"; 
}
```
