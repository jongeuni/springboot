## 비밀번호 암호화 방법

단방향 암호화: 복호화할 수 없는 암호화 방법이다. 



**왜 사용하나?** 

홈페이지 비밀번호같은 경우는 복호화 할 이유가 없다. 운영자들이 사용자들의 비밀번호를 알아야 할 이유가 없기 때문이다.



**SHA-256 알고리즘을 사용한 비밀번호 암호화**

```JAVA
 // 비밀번호 암호화 해주는 메소드
public String pwEncrypt(String pw)  {
    String encryptedPasswor="";
    try{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pw.getBytes());
        StringBuilder builder = new StringBuilder();
        for(byte b:md.digest()){
            builder.append(String.format("%02x",b)); 
        }
        encryptedPasswor = builder.toString();
    }catch (NoSuchAlgorithmException e){
        e.printStackTrace();
        System.out.println("비밀번호 암호화 실패");
    }
    return encryptedPasswor;
}
```

java.security.MessageDigest << Java에서 해시 알고리즘을 사용할 수 있게 제공하는 라이브러리이다. MD4, SHA-256, SHA-1을 사용한다. (SHA-1은 권장되지 않음.)

> 왜 SHA-1은 권장되지 않을까?
>
> 해쉬 충돌이 일어났다. (안전하지 못하다.) 해쉬 충돌이란 다른 데이터에서 동일한 해쉬값이 출력되는 것이다. 그래서 이보다 더 안전한 SHA-256을 사용해야 한다.



**MessageDiges.getInstance("Algorithm")**를 사용해 SHA-256를 사용하는 메세지 다이제스트 객체를 생성한다.  

> 메세지 다이제스트란?
>
> 데이터 무결성을 위해 메세지를 특정 고정 크기의 블럭으로 만드는 일으로 (암호화 과정을 통해)만들어진 메세지이다. ex) 해시 함수는 메세지 다이제스트의 충돌이 일어날 가능성이 낮다. (해시 함수는 메세지 다이제스트라고 하는 고정 길이의 값을 출력하는 함수라고 볼 수 있다.)



MessageDigest 객체의 **update()**메소드를 사용해 지정된 바이트 메세지 (데이터)로 다이제스트를 갱신한다. **digest()** 메소드를 호출하면 그 값을 가져올 수 있다. return 값이 byte[]라 String으로 변환이 필요하다. digest() 밑에는 String으로 변환하는 과정이다.

for문을 돌릴 때 md.digets()가 아니라 byte[] bytes = md.digest()해서 값을 담은 후 for문을 돌려도 된다.  어쨌든 digest한 값을 가져와 b에 저장한다. 

**StringBuilder**은 String 클래스와 유사하다. 그러나 String 클래스는 한 번 생성되면 메모리 내부에서 변경이 불가능한데, StringBuilder은 가능하다.  (문자열을 더할 때 String 클래스는 메모리 내에서 더해진 새로운 문자열을 생성하는데 StringBuilder은 기존 문자열에 추가된다.)

format()메소드는 문자열을 만들어낸다. **format("%02cx",b)**를 사용해 b의 바이너리 코드를 헥사로 바꾼다.  %02x, %x, %X 다 다르다. %x는 소문자로 바꿔지고 %X는 대문자로 바꿔진다. %02x는 2자리 헥사는 대문자로, 1자리 헥사는 앞에 0을 붙인다. (헥사? 16진수)

SpringBuilder의 **append()**메소드는 문자열을 더해준다. 

StringBuilder의 **toString()**으로 만들어진 문자열을 가져온다.