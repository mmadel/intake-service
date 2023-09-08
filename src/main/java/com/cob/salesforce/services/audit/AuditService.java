package com.cob.salesforce.services.audit;

import com.cob.salesforce.models.admin.audit.AuditModel;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.util.List;


public abstract class AuditService {

    @Autowired
    AuditReader reader;


    abstract List<AuditModel> getByEntity(Class entity) throws ClassNotFoundException;

    abstract List<AuditModel> getByEntityAndUUID(Class entity, String UUID) throws ClassNotFoundException;


    public List queryEntityAuditTable(Class entity, String UUID) {
        AuditQuery auditQuery = reader.createQuery()
                .forRevisionsOfEntity(entity, false, false)
                .addOrder(AuditEntity.revisionProperty("timestamp").asc());
        if (UUID != null)
            auditQuery.add(AuditEntity.revisionProperty("uuid").eq(UUID));
        List result = auditQuery.getResultList();
        return result;
    }

    public Object getEntityVersion(Class cls, Long entityId, Integer versionId) {

        Object obj = reader.find(cls, entityId, versionId);
        return obj;
    }

}
