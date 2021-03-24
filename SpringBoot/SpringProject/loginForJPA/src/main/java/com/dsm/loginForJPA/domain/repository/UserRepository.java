package com.dsm.loginForJPA.domain.repository;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
