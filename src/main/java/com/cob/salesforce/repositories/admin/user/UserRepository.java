package com.cob.salesforce.repositories.admin.user;


import com.cob.salesforce.entity.admin.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(String userId);
    @Modifying
    @Query("delete from UserEntity uc where uc.userId =:userId")
    void deleteUser(@Param("userId") String userId);

    @Query("select uc from UserEntity uc where uc.userId in :ids")
    List<UserEntity> findByUsers(@Param("ids") List<String> ids);
}
