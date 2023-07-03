package com.cob.salesforce.repositories.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.user.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    Optional<UserEntity> getByName(String name);
    @Query("Select u.clinics from UserEntity u where u.id = :userId" )
    List<ClinicEntity> findUserClinics(@Param("userId") Long userId);
}
