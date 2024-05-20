package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;

import java.util.List;

public interface UserFinderService {
    List<UserModel> getAll();

    UserModel getById(String Id);

    List<ClinicModel> findByUserId(String userId);

}
