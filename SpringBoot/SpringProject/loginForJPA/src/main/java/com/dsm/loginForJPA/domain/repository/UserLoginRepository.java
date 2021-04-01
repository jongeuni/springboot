package com.dsm.loginForJPA.domain.repository;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserLoginRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByIdAndPw(String id, String pw);
}
