스프링 mvc controller은 java Beans 규칙에 맞는 객체는 자동으로 클라이언트에 전달 해 준다.

> java Beans 규칙
>
> * getter/setter을 가져야 함.
> * 생성자에 파라미터가 있으면 안됨.
>
> [^java Beans]: jsp 파일 내에서 사용 가능한 객체





## Model

개발자는 model을 생성할 필요가 없다. **파라미터**로 선언만 해주면 된다.

![Model](https://user-images.githubusercontent.com/66874658/111318625-922d4480-86a8-11eb-9799-0932477214f5.JPG)

키벨류 값으로 넣어주면 클라이언트에 전달된다.



그런데 기본자료형(int, double)일 경우 파라미터로 선언되었어도 화면에 전달되지 않는다.

```java
@GetMapping("/dd") //name=aa&page=99를 전달하면 page는 뜨지 않음
public String ex04(DTO dto, int page){
    return "Ex04"
}
```

이럴 때는 

방법 1 Model 객체를 사용

방법 2 @ModleAttribute 사용

![@ModleAttribute 사용](https://user-images.githubusercontent.com/66874658/111318685-a07b6080-86a8-11eb-825f-9685ef7ffd25.JPG)