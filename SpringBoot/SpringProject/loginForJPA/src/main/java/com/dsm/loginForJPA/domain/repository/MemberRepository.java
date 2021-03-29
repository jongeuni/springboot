package com.dsm.loginForJPA.domain.repository;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface MemberRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByNumber(int num);
    UserEntity findByPw(String pw);

    @Transactional
    @Modifying // select 문이 아님을 나타낸다
    @Query(value = "UPDATE user_info u set u.u_pw = :pw where u.u_num = :number", nativeQuery = true)
    void changePw(@Param("pw")String pw, @Param("number")int number) throws Exception;
}
