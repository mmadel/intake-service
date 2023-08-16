package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.UserClinicEntity;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.models.security.KeyCloakUser;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.services.security.KeyCloakUsersCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@Transactional
public class UserCreatorServiceImpl implements UserCreatorService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KeyCloakUsersCreatorService keyCloakUsersCreatorService;

    @Override
    public String create(UserModel userModel) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String createdUserId = createKeyCloakUser(userModel);
        assignUserToClinics(createdUserId, userModel.getClinics());
        return createdUserId;
    }

    private String createKeyCloakUser(UserModel userModel) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        KeyCloakUser keyCloakUser = KeyCloakUser.builder()
                .username(userModel.getName())
                .firstName("firstName" + generateRandom())
                .lastName("lastName" + generateRandom())
                .email(userModel.getName() + "@mail.com")
                .password(userModel.getPassword())
                .roles(Arrays.asList(userModel.getUserRole()))
                .build();
        return keyCloakUsersCreatorService.create(keyCloakUser).getId();
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
