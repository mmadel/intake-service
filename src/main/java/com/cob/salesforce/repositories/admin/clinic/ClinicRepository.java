package com.cob.salesforce.repositories.admin.clinic;

import com.cob.salesforce.entity.admin.ClinicEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClinicRepository extends PagingAndSortingRepository<ClinicEntity, Long> , RevisionRepository<ClinicEntity , Long,Long> {
    @Query("Select c.id  from ClinicEntity c")
    List<Long> getIds();
    List<ClinicEntity> findByStatus(Boolean status);

    Optional<ClinicEntity> findByName(String name);
}
