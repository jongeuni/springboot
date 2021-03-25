package com.dsm.loginForJPA.domain.repository;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByPw(String pw);
    public UserEntity findByNickname(String nickname);
    UserEntity findByEmail(String email);
}
