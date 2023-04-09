package com.cob.salesforce.repositories.patient.admin.user;

import com.cob.salesforce.entity.admin.user.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
}
