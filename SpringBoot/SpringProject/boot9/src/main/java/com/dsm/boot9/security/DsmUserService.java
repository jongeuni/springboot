package com.dsm.boot9.security;

import com.dsm.boot9.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log
public class DsmUserService implements UserDetailsService{
    @Autowired
    MemberRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        repo.findById(username).ifPresent(member->log.info(""+member));

        return repo.findById(username).filter(m->m!=null).map(m->new DsmSecurityUser(m)).get();
    }
}
