package com.cob.salesforce.repositories.admin.user;

import com.cob.salesforce.entity.admin.user.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    Optional<UserEntity> getByName(String name);
}
