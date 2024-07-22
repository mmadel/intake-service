package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.UserEntity;
import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.exception.business.UserKeyCloakException;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.models.security.KeyCloakUser;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.services.security.keycloak.user.CreateKeycloakUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
import java.util.stream.StreamSupport;

@Service
@Transactional
@Slf4j
public class UserCreatorServiceImpl implements UserCreatorService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ClinicRepository clinicRepository;

    //    @Autowired
//    KeyCloakUsersCreatorService keyCloakUsersCreatorService;
    @Autowired
    CreateKeycloakUserService createKeycloakUserService;

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public UserModel create(UserModel userModel) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UserException, UserKeyCloakException {
        log.info("create user ", userModel.getName());
        createKeycloakUserService.create(userModel);
        saveUserModel(userModel);
        return userModel;
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public UserModel update(UserModel userModel) throws UserException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("update user ", userModel.getName());
        KeyCloakUser keyCloakUser = KeyCloakUser.builder()
                .userId(userModel.getUuid())
                .roles(Arrays.asList(userModel.getUserRole()))
                .build();
        if (userModel.getPassword() != null) {
            keyCloakUser.setPassword(userModel.getPassword());
        }
        clinicRepository.deleteAllById(userModel.getClinics().stream().map(ClinicModel::getId ).collect(Collectors.toList()));
        userRepository.deleteUser(userModel.getUuid());
        saveUserModel(userModel);
        return null;
    }

    private void saveUserModel(UserModel userModel) throws UserException {
        try{
            List<Long> clinics = userModel.getClinics().stream().map(ClinicModel::getId).collect(Collectors.toList());
            List<UserEntity> userToClinics = new ArrayList<>();
            List<ClinicEntity> userClinics = StreamSupport.stream(clinicRepository.findAllById(clinics).spliterator(), false)
                    .collect(Collectors.toList());
            UserEntity entity = new UserEntity();
            entity.setClinics(userClinics);
            entity.setUserId(userModel.getUuid());
            entity.setUserName(userModel.getName());
            entity.setAddress(userModel.getAddress());
            userToClinics.add(entity);
            userRepository.saveAll(userToClinics);
        }catch(Exception exception){
            createKeycloakUserService.delete(userModel);
        }
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
