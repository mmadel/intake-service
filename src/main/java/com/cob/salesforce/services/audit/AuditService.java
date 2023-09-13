package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import com.cob.salesforce.models.admin.audit.AuditResponse;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public abstract class AuditService {
    @Autowired
    EntityManager entityManager;

    abstract AuditResponse getByEntityAndUUID(String UUID, Integer currentPage, Integer pageSize);

    abstract public AuditResponse getByEntity(Integer currentPage, Integer pageSize);

    abstract List<AuditModel> getAuditModels(AuditReader auditReader, String uuid, Integer currentPage, Integer pageSize);

    protected AuditQuery buildQuery(Class entity, AuditReader auditReader, String uuid, Integer currentPage, Integer pageSize) {
        AuditQuery auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(entity, false, false);
        if (uuid != null)
            auditQuery.add(AuditEntity.revisionProperty("uuid").eq(uuid));
        if (currentPage != null && pageSize != null) {
            auditQuery.setFirstResult(currentPage).setMaxResults(pageSize);
        } else {
            auditQuery.addProjection(AuditEntity.id().count());
        }
        return auditQuery;
    }
}
