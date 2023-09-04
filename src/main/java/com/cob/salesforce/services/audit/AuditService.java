package com.cob.salesforce.services.audit;

import com.cob.salesforce.models.admin.audit.AuditModel;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public abstract class AuditService {

    private EntityManagerFactory factory;
    AuditReader auditReader = null;

    abstract List<AuditModel> getByEntity(Class entity) throws ClassNotFoundException;

    abstract List<AuditModel> getByEntityAndUUID(Class entity, String UUID) throws ClassNotFoundException;

    @Autowired
    public final void setEntityManagerFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public List queryEntityAuditTable(Class entity, String UUID) {
        AuditReader reader = getAuditReader(factory.createEntityManager());
        AuditQuery auditQuery = reader.createQuery()
                .forRevisionsOfEntity(entity, false, false)
                .addOrder(AuditEntity.revisionProperty("timestamp").asc());
        if (UUID != null)
            auditQuery.add(AuditEntity.revisionProperty("uuid").eq(UUID));
        List result = auditQuery.getResultList();
        factory.createEntityManager().close();
        reader = null;
        auditQuery = null;
        return result;
    }

    public Object getEntityVersion(Class cls, Long entityId, Integer versionId) {

        AuditReader reader = getAuditReader(factory.createEntityManager());
        Object obj = reader.find(cls, entityId, versionId);
        reader = null;
        factory.createEntityManager().close();
        return obj;
    }

    private AuditReader getAuditReader(EntityManager entityManager) {
        if (auditReader != null) {
            return auditReader;
        }
        return auditReader = AuditReaderFactory.get(entityManager);
    }
}
