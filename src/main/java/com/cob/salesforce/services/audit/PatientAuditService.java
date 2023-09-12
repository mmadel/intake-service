package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientAuditService {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    public List<AuditModel> getByEntityAndUUID(Class entity, String UUID) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        AuditQuery auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(entity, false, false)
                .addOrder(AuditEntity.revisionProperty("timestamp").asc());
        if (UUID != null)
            auditQuery.add(AuditEntity.revisionProperty("uuid").eq(UUID));
        List patientQueryResult = auditQuery.getResultList();
        List<AuditModel> result = new ArrayList<>();
        for (Object item : patientQueryResult) {
            Patient entityClass = (Patient) ((Object[]) item)[0];
            CustomRevisionEntity CustomRevisionEntity = (com.cob.salesforce.entity.audit.CustomRevisionEntity) ((Object[]) item)[1];
            Integer versionId = CustomRevisionEntity.getId();

            Patient clinicVersion = (Patient) auditReader.find(entity, entityClass.getId(), versionId);
            RevisionType revisionType = (RevisionType) ((Object[]) item)[2];
            result.add(AuditModel.builder()
                    .revisionDate(CustomRevisionEntity.getTimestamp())
                    .uuid(CustomRevisionEntity.getUuid())
                    .revisionType(revisionType)
                    .entity(clinicVersion)
                    .entityName(((Object[]) item)[0].getClass().getSimpleName())
                    .build());
        }
        return result;
    }
    public List<AuditModel> getByEntity(Class entity) {
        return getByEntityAndUUID(entity, null);
    }
}
