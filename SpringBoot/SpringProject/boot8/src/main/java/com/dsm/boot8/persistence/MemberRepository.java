package com.dsm.boot8.persistence;

import com.dsm.boot8.domain.Member;
import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<Member, String> {
}
