package com.cob.salesforce.models.admin.audit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionType;

import java.util.Date;

@Setter
@Getter
@Builder
public class AuditModel {
    private Date revisionDate;
    private String uuid;
    private RevisionType revisionType;
    private String entity;

}
