package com.cob.salesforce.repositories.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.UserClinicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserClinicEntity, Long> {

    Optional<UserClinicEntity> findByClinicId(Long clinicId);
    Optional<List<UserClinicEntity>> findByUserId(String userId);
}
