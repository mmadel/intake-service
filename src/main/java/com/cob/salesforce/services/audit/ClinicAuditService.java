package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import com.google.gson.Gson;
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
public class ClinicAuditService implements AuditService {
    @Autowired
    EntityManagerFactory factory;

    @Override
    public List<AuditModel> getByEntity(Class entity) throws ClassNotFoundException {
        return getByEntityAndUUID(entity, null);
    }

    @Override
    public List<AuditModel> getByEntityAndUUID(Class entity, String UUID) {
        AuditReader reader = AuditReaderFactory.get(factory.createEntityManager());
        AuditQuery auditQuery = reader.createQuery()
                .forRevisionsOfEntity(entity, false, false);
        List<AuditModel> result = new ArrayList<>();
        if (UUID != null)
            auditQuery.add(AuditEntity.revisionProperty("uuid").eq(UUID));
        List queryResult = auditQuery.getResultList();
        for (Object item : queryResult) {
            ClinicEntity entityClass = (ClinicEntity) ((Object[]) item)[0];
            CustomRevisionEntity CustomRevisionEntity = (com.cob.salesforce.entity.audit.CustomRevisionEntity) ((Object[]) item)[1];
            Integer versionId = CustomRevisionEntity.getId();
            ClinicEntity clinicVersion = reader.find(ClinicEntity.class, entityClass.getId(), versionId);
            RevisionType revisionType = (RevisionType) ((Object[]) item)[2];
            result.add(AuditModel.builder()
                    .revisionDate(CustomRevisionEntity.getRevisionDate())
                    .uuid(CustomRevisionEntity.getUuid())
                    .revisionType(revisionType)
                    .entity(clinicVersion)
                    .entityName(((Object[]) item)[0].getClass().getSimpleName())
                    .build());
        }
        return result;
    }
}
