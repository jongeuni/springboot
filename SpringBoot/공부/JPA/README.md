# JPA

: Java Persistence API

* java의 ORM 기술 표준이다. 실제로 동작하는 것이 아니다. 그래서 이를 실제로 구현한 구현체가 있어야 하는데 그 중 하나가 Hibernate이다.

* 개발자가 JPA를 사용하면 JPA 내부에서 JDBC API를 사용하여 SQL을 호출해 DB와 통신한다. (직접 JDBC를 쓰지 않음.)



### ORM

: Object Relational Mapping

ORM은 **객체 지향에서 말하는 객체와 데이터 베이스의 개체가 유사하다**는 입장에서 시작한다.  ORM은 DB 테이블을 자바 객체로 매핑하며 객체 관계를 바탕으로 SQL을 자동으로 생성한다. 그러므로 SQL 쿼리가 아니라 메서드로 데이터를 조작할 수 있다.

ORM은 객체지향과 관계형 데이터베이스를 매핑시킨다는 추상화된 개념이고, JPA는 Java에서 ORM을 구현하기 위한 스펙이다.

```java
@Entity
@Table(name='tbl_member')
@Data
public class Member{
    @id
    private String mid;
    private String mpw;
}
```

```table
tbl_member
mid VARCHAR(225)
mpw VARCHAR(225)
```





### 장점

1. 객체 중심 개발

2. 간단한 CRUD

3. 쉬운 유지보수

   SQL은 JPA가 처리하기 때문에 필드만 추가하면 된다.

4. DB와 독립적인 관계

   JPA는 특정 회사의 DB에 종속적이지 않아서 DB 제품이 변경되거나 버전이 변경되는 것과 같은 일에서 자유로워진다.



###  