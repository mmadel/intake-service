package com.cob.salesforce.services.audit;

import com.cob.salesforce.models.admin.audit.AuditModel;

import java.util.List;


public interface AuditService {

     List<AuditModel> getByEntity(Class entity) throws ClassNotFoundException;
     List<AuditModel> getByEntityAndUUID(Class entity, String UUID) throws ClassNotFoundException;

}
