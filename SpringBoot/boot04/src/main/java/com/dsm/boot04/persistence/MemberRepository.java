package com.dsm.boot04.persistence;

import com.dsm.boot04.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {
    // uid가 user1인 회원의 정보와 회원의 프로필 사진 숫자를 알고 싶다
    @Query("SELECT m.uid, count(p) from Member m LEFT OUTER JOIN Profile p"+" ON m.uid = p.member WHERE m.uid = ?1 GROUP BY m")
    public List<Object[]> getMemberWithProfileCount(String uid);

    //회원 정보와 현재 사용중인 프로필에 대한 정보를 알고 싶다
    @Query("select m, p from Member m LEFT OUTER JOIN Profile p"+ " On m.uid = p.member where m.uid =?1 and p.current = true")
    public List<Object[]> getMemberWithProfile(String uid);

}
