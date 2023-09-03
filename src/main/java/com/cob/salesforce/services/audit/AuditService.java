package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuditService {

    private static final String PACKAGE_NAME = "com.cob.salesforce.entity.admin.";
    @Autowired
    EntityManagerFactory factory;

    public List<AuditModel> getByEntity(String entity) throws ClassNotFoundException {
        return getByEntityAndUUID(entity, null);
    }

    public List<AuditModel> getByEntityAndUUID(String entity, String UUID) throws ClassNotFoundException {
        List<AuditModel> result = new ArrayList<>();
        Class cls = Class.forName(PACKAGE_NAME + entity);
        AuditReader reader = AuditReaderFactory.get(factory.createEntityManager());
        AuditQuery queryHistoryOfUserWithRev = reader.createQuery()
                .forRevisionsOfEntity(cls, false, false);
//                .add(AuditEntity.and(
//                        AuditEntity.or(AuditEntity.property(fieldsNames.get(1)).hasChanged(), AuditEntity.property(fieldsNames.get(2)).hasChanged()),
//                        AuditEntity.revisionProperty("uuid").eq(UUID)));
        if (UUID != null)
            queryHistoryOfUserWithRev.add(AuditEntity.revisionProperty("uuid").eq(UUID));

        List lstHistoryOfUserWithRev = queryHistoryOfUserWithRev.getResultList();
        for (Object item : lstHistoryOfUserWithRev) {
            //ClinicEntity user = (ClinicEntity) ((Object[]) item)[0];
            CustomRevisionEntity CustomRevisionEntity = (com.cob.salesforce.entity.audit.CustomRevisionEntity) ((Object[]) item)[1];
            RevisionType revisionType = (RevisionType) ((Object[]) item)[2];
            result.add(AuditModel.builder()
                    .revisionDate(CustomRevisionEntity.getRevisionDate())
                    .uuid(CustomRevisionEntity.getUuid())
                    .revisionType(revisionType)
                    .entity(((Object[]) item)[0].getClass().getSimpleName())
                    .build());
            log.debug("revinfo - modifier:{}, date:{}", CustomRevisionEntity.getUuid(), CustomRevisionEntity.getRevisionDate());
            log.debug("revType: {}", revisionType);
        }
        return result;
    }
}
