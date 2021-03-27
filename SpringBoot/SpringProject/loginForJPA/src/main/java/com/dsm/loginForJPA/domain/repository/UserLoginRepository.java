package com.dsm.loginForJPA.domain.repository;

import com.dsm.loginForJPA.domain.dto.UserLoginDto;
import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.vo.LoginVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface UserLoginRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByIdAndPw(String id, String pw);
}
