package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.user.UserEntity;
import com.cob.salesforce.models.admin.security.SecurityUser;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = repository.getByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
        SecurityUser securityUser = new SecurityUser();
        securityUser.user = entity;
        return securityUser;
    }
}
