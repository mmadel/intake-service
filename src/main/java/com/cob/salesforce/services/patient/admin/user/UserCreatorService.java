package com.cob.salesforce.services.patient.admin.user;

import com.cob.salesforce.models.admin.user.UserModel;

public interface UserCreatorService {
    UserModel create(UserModel model);
}
