package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.services.security.KeyCloakUsersRemoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserRemoverService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    KeyCloakUsersRemoverService keyCloakUsersRemoverService;

    public void remove(String userId) throws UserException {
        keyCloakUsersRemoverService.removeKCUser(userId);
        userRepository.deleteUser(userId);

    }
}
