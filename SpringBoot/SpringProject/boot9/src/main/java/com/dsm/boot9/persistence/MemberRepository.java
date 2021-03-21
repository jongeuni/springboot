package com.dsm.boot9.persistence;

import com.dsm.boot9.domain.Member;
import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<Member, String> {
}
