package com.dsm.boot04;

import com.dsm.boot04.domain.Member;
import com.dsm.boot04.persistence.MemberRepository;
import com.dsm.boot04.persistence.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.stream.IntStream;

@SpringBootTest
@Commit

public class ProfileTests {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Test
    public void testInsertMmebers(){
        IntStream.range(1,101).forEach(i->{
            Member member = new Member();
            member.setUid("user"+i);
            member.setUpw("pw"+i);
            member.setUname("사용자"+i);

            memberRepository.save(member);
        });
    }
}
