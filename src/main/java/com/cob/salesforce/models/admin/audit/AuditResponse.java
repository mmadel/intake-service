package com.cob.salesforce.models.admin.audit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class AuditResponse {
    Long count;
    List<AuditModel> auditModels;
}
