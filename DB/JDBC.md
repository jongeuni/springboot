## JDBC

: 자바에서 데이터베이스를 사용하기 위한 절차에 대한 규약이다. DB에 접근하기 위해서 JDBC에서 제공하는 API를 사용하면 된다.



GOOD

JDBC에서 인터페이스를 제공하기 때문에, 어떤 DB를 사용하든 개발자가 JDBC를 사용하는 방법은 변하지 않는다. 



사용 방법

1. import java.sql.*;
2. 드라이버 load

3. mysql 연결을 위해 Connection 객체 생성

4. Statement 객체를 생성해 질의 수행

5. 질의 결과가 있다면, ResultSet 객체를 생성해 결과 저장
6. close



> 2. 드라이버 load

Class.forName 으로 드라이버를 로드할 수 있다.

드라이버 인터페이스를 구현은 클래스를 로딩한다. mysql은 "com.mysql.jdbc.Driver"이다. 드라이버가 읽히기만 하면 자동 객체가 생성되고 DriverManager에 등록된다.

```java
static {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch(Exception e) {
        e.printStackTrace();
    }
}
```



> 3. mysql 연결을 위해 Connection 객체 생성

자바에서 데이터베이스에 **접속할 수** 있도록 하는 자바 API이다.

getConnection으로 접속에 필요한 정보인 DB url, username, pw를 보내야 한다.

java.sql.SQLException 예외가 발생하므로 예외처리를 해줘야 한다. 

```java
private Connection conn;
string url = "jdbc:mysql://localhost/DBname?serverTimezone=UTC";
string user = "root";
String password = "DBpassword";
```

url은 DB마다 다르고, username은 root이다 (접속 계정명). password는 DB에서 쓰는 pw를 입력하면 된다.

```java
conn = DriverManager.getConnection(url,user,password);
```



> 4. Statement 객체를 생성해 질의 수행

PreparedStatement 객체

statement를 상속받는 인터페이스로 SQL구문을 실행시키는 기능을 갖는다. 

(**Connection 객체의**) prepareStatement 메서드의 매개변수로 SQL문을 보내 준다. 반환 값은 PreparedStatement 객체이다.

```JAVA
private PreparedStatement pstmt;
String sql = "SELECT * FROM DBname";
```

```java
pstmt = conn.prepareStatement(sql);
```



> 5. ResultSet 객체를 생성해 질의 결과 저장

데이터 베이스에서 처리된 결과값을 (특히 SELECT에 의해) 처리하는 

(**Statement 객체의**) executeQuery() 메소드나 executeUpdate() 메소드를 사용해서 쿼리를 처리한다. 메소드 반환 값은  ResultSet이다. (ResultSet는 Statement 객체 또는 PreparedStatement 객체로 SELECT문을 사용하여 얻어온 ROW 값들을 **테이블의 형태로** 갖게 되는 객체이다.)

```java
 private ResultSet rs;
```

```java
 rs = pstmt.executeQuery();
 while(rs.next()){
 	int id = rs.getInt("id");
 	String content = rs.getString("content");
 	BoardDto boarddto = new BoardDto();
 	boarddto.setId(id);
 	boarddto.setContent(content);
 	boardlist.add(boarddto); //리스트에넣음
 }
```

ResultSet의 메소드

getXXX("") : 로우 값을 지정한 XXX 타입으로 가져온다.

https://hyeonstorage.tistory.com/110 (질문!)

next() : 리턴값 boolean. 다음 행으로 한 행씩 이동한다. 더이상 행이 존재하지 않을 때는 false, 존재할 때는 true return.



> 6. close

```java
finally {
    try{
        if(conn!=null){
            conn.close();
        }
        if(pstmt!=null){
            pstmt.close();
        }
        if(rs!=null){
            rs.close();
        }
    } catch(Exception e){
        e.printStackTrace();
    }
}
```