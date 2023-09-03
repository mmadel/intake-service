package com.cob.salesforce.models.admin.audit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionType;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Date;

@Setter
@Getter
@Builder
public class AuditModel {
    private Long revisionDate;
    private String uuid;
    private RevisionType revisionType;

    private Object entity;
    private String entityName;

}
