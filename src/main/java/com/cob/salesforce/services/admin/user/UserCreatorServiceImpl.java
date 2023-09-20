package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.UserClinicEntity;
import com.cob.salesforce.exception.business.UserKeyCloakException;
import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.models.security.KeyCloakUser;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.services.security.KeyCloakUsersCreatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserCreatorServiceImpl implements UserCreatorService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KeyCloakUsersCreatorService keyCloakUsersCreatorService;

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public UserModel create(UserModel userModel) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UserException, UserKeyCloakException {
        log.info("create user " , userModel.getName());
        KeyCloakUser keyCloakUser = KeyCloakUser.builder()
                .username(userModel.getName())
                .firstName("firstName" + generateRandom())
                .lastName("lastName" + generateRandom())
                .email(userModel.getName() + "@mail.com")
                .password(userModel.getPassword())
                .address(userModel.getAddress())
                .roles(Arrays.asList(userModel.getUserRole()))
                .build();
        String createdUserId = keyCloakUsersCreatorService.create(keyCloakUser).getId();
        assignUserToClinics(createdUserId, userModel.getClinics().stream().map(ClinicModel::getId).collect(Collectors.toList()));
        userModel.setUuid(createdUserId);
        return userModel;
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public UserModel update(UserModel userModel) throws UserException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("update user " , userModel.getName());
        KeyCloakUser keyCloakUser = KeyCloakUser.builder()
                .userId(userModel.getUuid())
                .address(userModel.getAddress())
                .roles(Arrays.asList(userModel.getUserRole()))
                .build();
        if (userModel.getPassword() != null) {
            keyCloakUser.setPassword(userModel.getPassword());
        }
        keyCloakUsersCreatorService.update(keyCloakUser);
        userRepository.deleteUser(userModel.getUuid());
        assignUserToClinics(userModel.getUuid(), userModel.getClinics().stream().map(ClinicModel::getId).collect(Collectors.toList()));
        return null;
    }

    private void assignUserToClinics(String createdUserId, List<Long> clinics) {
        List<UserClinicEntity> userToClinics = new ArrayList<>();
        clinics.forEach(clinicId -> {
            UserClinicEntity entity = new UserClinicEntity();
            entity.setClinicId(clinicId);
            entity.setUserId(createdUserId);
            userToClinics.add(entity);
        });
        userRepository.saveAll(userToClinics);
    }

    private String generateRandom() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
