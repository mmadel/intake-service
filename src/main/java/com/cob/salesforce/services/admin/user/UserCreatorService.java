package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.admin.user.UserModel;

public interface UserCreatorService {
    UserModel create(UserModel model);
    public UserModel update(UserModel model) throws UserException;
    public void delete(long id) throws UserException;
}
