## Redirect

: 현재 페이지를 다른 페이지로 보내는 것이다. 결과적으로 새로운 URL 요청이 다시 일어나도록 한다.

> Forward?
>
> > 객체를 재사용하거나 공유해야 한다면 Redirect 대신 Forward를 사용하는 게 좋다.

예시로 게시물 등록 후 게시물이 등록된 페이지로 redirect 되는 경우가 있다. redirect가 발생하면 원래 요청은 끊어지고 새로운 GET 요청이 시작된다. redirect 실행 전에 수행된 모델 데이터는 소멸함으로,  리다이렉트 시 redirect 되는 경로에 데이터를 보내기 위해선 GET 방식으로 데이터를 붙여서 전송할 수 있다.

```java
return "redirect:/http?param01=one&param02=two"
```

그러나 데이터 전송시 정보를 숨길 수 없다는 문제가 있다.

-> RedirectAttributes



### RedirectAttributes

```java
public interface RedirectAttributes extends Model{
    ...
}
```

▲ Model의 기능을 그대로 확장했다.

RedirectAttributes는 redirect가 발생하기 전에 모든 플래시 속성을 세션에 저장한다. (**`addFlashAttribute()`**)redirect 이후에는 플래시 속성을 모델로 이동시킨다. header가 아닌 세션을 통해 전달하므로 url이 깔끔해진다.

### addAttribute vs addFlashAttribute

addAttribute()

- 전달 값이 url 뒤에 붙는다. 
- 또 requestparameters로 값을 전달하므로 request.getParameter로 값에 접근할 수 있다. 

addFlashAttribute()

* url에 data가 노출되지 않는다.

* header가 아닌 session을 통해 전달한다.

  (redirect 전 data를 session에 저장하고 redirect 후 즉시 제거한다.)

* FlashAttribute로 넘긴 데이터는 model에 담겨있기 때문에 Model만 선언해두어 model에서 꺼내어 사용할 수 있다. (@ModelAttribute나 @RequestParam을 사용하지 않아도 된다.)
