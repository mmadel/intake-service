package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.admin.InsuranceCompanyEntity;
import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceCompanyAuditService extends AuditService {

    @Override
    public List<AuditModel> getByEntity(Class entity) throws ClassNotFoundException {
        return getByEntityAndUUID(entity, null);
    }

    @Override
    public List<AuditModel> getByEntityAndUUID(Class entity, String UUID) {
        List<AuditModel> result = new ArrayList<>();
        List insuranceCompanyQueryResult = queryEntityAuditTable(entity, UUID);
        for (Object item : insuranceCompanyQueryResult) {
            InsuranceCompanyEntity entityClass = (InsuranceCompanyEntity) ((Object[]) item)[0];
            CustomRevisionEntity CustomRevisionEntity = (com.cob.salesforce.entity.audit.CustomRevisionEntity) ((Object[]) item)[1];
            Integer versionId = CustomRevisionEntity.getId();
            InsuranceCompanyEntity clinicVersion = (InsuranceCompanyEntity) getEntityVersion(entity, entityClass.getId(), versionId);
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
