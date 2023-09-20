package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.admin.InsuranceCompanyEntity;
import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import com.cob.salesforce.models.admin.audit.AuditResponse;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceCompanyAuditService extends AuditService {

    @Transactional
    @Override
    public AuditResponse getByEntityAndUUID(String UUID, Integer currentPage, Integer pageSize) {
        Integer offset = ((currentPage - 1) * pageSize + 1) -1;
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        return AuditResponse.builder()
                .count((Long)(buildQuery(InsuranceCompanyEntity.class,auditReader, UUID,null,null).getSingleResult()))
                .auditModels(getAuditModels(auditReader, UUID,offset,pageSize))
                .build();
    }
    @Override
    public AuditResponse getByEntity(Integer currentPage, Integer pageSize) {
        return getByEntityAndUUID(null, currentPage, pageSize);
    }
    List<AuditModel> getAuditModels( AuditReader auditReader, String uuid, Integer currentPage, Integer pageSize) {
        List<AuditModel> result = new ArrayList<>();
        List clinicQueryResult = buildQuery(InsuranceCompanyEntity.class, auditReader, uuid, currentPage, pageSize).getResultList();
        for (Object item : clinicQueryResult) {
            InsuranceCompanyEntity entityClass = (InsuranceCompanyEntity) ((Object[]) item)[0];
            CustomRevisionEntity CustomRevisionEntity = (com.cob.salesforce.entity.audit.CustomRevisionEntity) ((Object[]) item)[1];
            Integer versionId = CustomRevisionEntity.getId();

            InsuranceCompanyEntity clinicVersion = auditReader.find(InsuranceCompanyEntity.class, entityClass.getId(), versionId);
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
