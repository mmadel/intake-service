package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Slf4j
public class AuditService {
    @Autowired
    EntityManagerFactory factory;

    public void test(String entity) throws ClassNotFoundException {
        Class cls = Class.forName("com.cob.salesforce.entity.admin." + entity);
        AuditReader reader = AuditReaderFactory.get(factory.createEntityManager());
        AuditQuery queryHistoryOfUserWithRev = reader.createQuery()
                .forRevisionsOfEntity(cls, false, false);
        List lstHistoryOfUserWithRev = queryHistoryOfUserWithRev.getResultList();
        for (Object item : lstHistoryOfUserWithRev) {
            // ClinicEntity user = (ClinicEntity)((Object[]) item)[0];
            CustomRevisionEntity CustomRevisionEntity = (com.cob.salesforce.entity.audit.CustomRevisionEntity) ((Object[]) item)[1];
            RevisionType revisionType = (RevisionType) ((Object[]) item)[2];

            log.info("revinfo - modifier:{}, date:{}", CustomRevisionEntity.getUuid(), CustomRevisionEntity.getRevisionDate());
            log.info("revType: {}", revisionType);
        }
    }
}
