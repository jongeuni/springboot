package com.dsm.boot04;

import com.dsm.boot04.domain.Member;
import com.dsm.boot04.domain.Profile;
import com.dsm.boot04.persistence.MemberRepository;
import com.dsm.boot04.persistence.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.List;
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

    /*
    @Test
    public void testInsertProfile(){
        Member member = new Member();
        member.setUid("user1");

        for(int i=1; i<5; i++){
            Profile profile01 = new Profile();
            profile01.setFname("face"+i+".jpg");

            if(i==1){
                profile01.setCurrent(true);
            }

            profile01.setMember(member);

            profileRepository.save(profile01);
        }
    }
    */

    @Test
    public void testFetchJoin1(){
        List<Object[]> result = memberRepository.getMemberWithProfileCount("user1");
        result.forEach(arr-> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void testFetchJoin2(){
        List<Object[]> result = memberRepository.getMemberWithProfile("user1");

        result.forEach(arr-> System.out.println(Arrays.toString(arr)));
    }



}
