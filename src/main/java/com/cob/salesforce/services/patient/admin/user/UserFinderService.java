package com.cob.salesforce.services.patient.admin.user;

import com.cob.salesforce.models.admin.user.UserModel;

import java.util.List;

public interface UserFinderService {
    List<UserModel> getAll();
    UserModel getById(Long Id);

}
