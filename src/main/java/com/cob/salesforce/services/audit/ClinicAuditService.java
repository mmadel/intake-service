package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import org.hibernate.envers.RevisionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class ClinicAuditService extends AuditService {


    @Override
    public List<AuditModel> getByEntity(Class entity) {
        return getByEntityAndUUID(entity, null);
    }

    @Override
    public List<AuditModel> getByEntityAndUUID(Class entity, String UUID) {
        List clinicQueryResult = queryEntityAuditTable(entity, UUID);
        for (Object item : clinicQueryResult) {
            ClinicEntity entityClass = (ClinicEntity) ((Object[]) item)[0];
            CustomRevisionEntity CustomRevisionEntity = (com.cob.salesforce.entity.audit.CustomRevisionEntity) ((Object[]) item)[1];
            Integer versionId = CustomRevisionEntity.getId();
            ClinicEntity clinicVersion = (ClinicEntity) getEntityVersion(entity, entityClass.getId(), versionId);
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
}
