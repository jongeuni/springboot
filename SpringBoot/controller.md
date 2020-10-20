### 내용

백엔드 은행 서비스 만들기 

번호표 뽑기 -> 숫자 1씩 늘어나게 

잔액 조회 (학번) -> 남은 돈 출금         

(학번, 돈) -> 남은 돈 입금          

(학번, 돈) -> 남은돈



### BankController

```java
package com.DSM.Bank.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Bank")
public class BankController {
    private int ticet=1;

    //번호표 출력
    @RequestMapping("/numberTicet")
    public String printNum(){
        return Integer.toString(ticet++);
    }

    User userone = new User();
    public void Definition(){
        userone.setMoney(10000);
        userone.setName("이종은");
        userone.setNumber(1212);
    }
        //입금
    @RequestMapping("/MoneySum")
    public int Money_Sum(@RequestParam("number") int number, @RequestParam("money") int money){
        if(number==1212){
            Definition(); //함수실행...
            int M_oney=MoneySum(money);
            return M_oney;
        }else{
            return 404;
        }
    }

    //정보
    @RequestMapping("/UserInfo")
    public User UserInfo(@RequestParam("number") int number){
        if(number==1212){
            return userone;
        }else{
            return new User(); //빈 유저
        }
    }

    //출금
    @RequestMapping("/MoneySub")
    public int MoneySub(@RequestParam("number") int number, @RequestParam("money") int money){
        if(number==1212){
            int Money = Money_Sub(money);
            return Money;
        }else{
            return 404;
        }
    }

    //잔액체크
    @RequestMapping("/checkMoney")
    public int check(@RequestParam("number") int number){
        if(number==1212){
            return userone.getMoney();
        }else{
            return 404;
        }
    }

    // sum
    public int MoneySum(int money){
        int Money = userone.getMoney()+money;
        userone.setMoney(Money);
        return userone.getMoney();
    }
    // sub
    public int Money_Sub(int money){
        int Money = userone.getMoney()-money;
        userone.setMoney(Money);
        return userone.getMoney();
    }
}
```



### User

```java
package com.DSM.Bank.Controller;

public class User {
    private int money;
    private String name;
    private int number;

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
```