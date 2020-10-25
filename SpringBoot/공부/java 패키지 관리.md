@Component

스프링에서 관리되는 객체임을 표시하기 위해 사용하는 기본적인 어노테이션이다.

@Componect의 구체화된 형태로  @Repository, @Service, @Controller가 있다. 이것들은 클래스를 빈으로 만들기 위한 방법들이다. 



여기에 정리할 건 어노테이션이 아니라 java 패키지 관리 할 때 어떻게 해야 하는지이다. 서버를 짤 때 controller, service, repository등의 패키지를 만든다. 이것은 **패키지를 계층별로 분류**하는 것이다. (tier, 또는 Layer라고 부르기도 한다.) <<중요하다고 한다.>> 레이어는 사용자가 어떤 디자인 패턴을 사용하냐에 따라 갈린다. 디자인 패턴에 대한 공부는 나중에 시간을 내서 따로 하자. 한달 내로 정리하겠다.



어쨌든 지금은 controller, service, dto, dao,  이정도 계층으로 볼 수 있을 것 같다. dto와 dao 부분은 repository에 포함된다.



컨트롤러는 요청을 받고 응답을 보낸다. 스프링에서는 서비스를 만들어 비즈니스 로직을 처리한다. 

[^비즈니스 로직]: 데이터를 생성, 표시, 저장, 변경하는 부분을 말한다. 사용자에게 보여지지 않는 부분에서 데이터를 처리하는 코드이다.

컨트롤러가 Request를 받으면 적절한 Service에 전달하고 Service는 비즈니스 로직을 처리한다. 그 비즈니스 로직에서 데이터 베이스에 접근해야 할 시 Repository를 사용한다. dto랑 비슷한 vo도 있는 거 같은데 이것도 나중에 더 공부하자. (지금 레이어를 공부하는 것보다는 개발을 해보는 게 중요하다고 느꼈다.)

어쨌든 이게 저것들의 역할이다. 잘 사용해서 서버 개발을 해보자.



**dao와 dto**

dao는 data access object의 약자이다. db의 data에 접근을 위한 객체이다. 

- 하는일

  dao 클래스는 db와 연결할 connection을 가져오고 어던 db를 사용할 것인지, 어떤 드라이브와 로그인 정보보를 사용할 것인지를 알려준다. 또 작업이 끝나면 사용한 리소스를 close해준다.

dto는 data transfer object로 로직을 갖고 있지 않은 객체이다. 속성과 그 속성에 접근하기 위한 setter, getter 메소드만 가진 클래스를 말한다.

**예제**

dao 클래스의 예제

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDao {

    public void add(TestDto dto) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(id,name,password) value(?,?,?)");


        preparedStatement.setString(1, dto.getName());
        preparedStatement.setString(3, dto.getData());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        
        connection.close();

    }
}
```

dto 클래스의 예제

```java
public class TestDto {
    private String name;
    private String data;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
```