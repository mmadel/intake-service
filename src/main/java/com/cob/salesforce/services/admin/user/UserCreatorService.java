package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.exception.business.UserKeyCloakException;
import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.admin.user.UserModel;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface UserCreatorService {

    UserModel create(UserModel userModel) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UserException, UserKeyCloakException;
    UserModel update(UserModel userModel) throws UserException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
}
