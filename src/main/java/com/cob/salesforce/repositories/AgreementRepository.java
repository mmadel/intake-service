package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.Agreement;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AgreementRepository  extends CrudRepository<Agreement, Long> {
    Optional<Agreement> findByAgreementName(String name);
}
