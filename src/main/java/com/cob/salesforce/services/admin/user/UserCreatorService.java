package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.models.admin.user.UserModel;

public interface UserCreatorService {
    UserModel create(UserModel model);
}
