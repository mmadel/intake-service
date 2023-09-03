package com.cob.salesforce.services.audit;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


public abstract class AuditService {

    private EntityManagerFactory factory;
    private AuditReader reader;
    List<AuditModel> result = new ArrayList<>();

    abstract List<AuditModel> getByEntity(Class entity) throws ClassNotFoundException;

    abstract List<AuditModel> getByEntityAndUUID(Class entity, String UUID) throws ClassNotFoundException;

    @Autowired
    public final void setEntityManagerFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public List queryEntityAuditTable(Class entity, String UUID) {
        reader = AuditReaderFactory.get(factory.createEntityManager());
        AuditQuery auditQuery = reader.createQuery()
                .forRevisionsOfEntity(entity, false, false);
        if (UUID != null)
            auditQuery.add(AuditEntity.revisionProperty("uuid").eq(UUID));
        return auditQuery.getResultList();
    }

    public Object getEntityVersion(Class cls, Long entityId, Integer versionId) {
        return reader.find(cls, entityId, versionId);
    }
}
