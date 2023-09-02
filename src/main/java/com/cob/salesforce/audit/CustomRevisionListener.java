package com.cob.salesforce.audit;

import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.security.UUIDUtils;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        customRevisionEntity.setUuid(UUIDUtils.get());
    }
}
