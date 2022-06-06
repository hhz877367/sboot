package com.baizhi.security;

import java.util.List;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl/* implements UserDetailsService*/ {

/*  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    List<GrantedAuthority> auths = AuthorityUtils
        .commaSeparatedStringToAuthorityList("role");
    User user = new User("lucy",new BCryptPasswordEncoder().encode("123"),auths);
    return user;
  }*/
}
