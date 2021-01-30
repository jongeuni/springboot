package com.dsm.boot04.persistence;

import com.dsm.boot04.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
