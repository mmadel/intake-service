package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.Agreement;
import org.springframework.data.repository.CrudRepository;

public interface AgreementRepository  extends CrudRepository<Agreement, Long> {
}
