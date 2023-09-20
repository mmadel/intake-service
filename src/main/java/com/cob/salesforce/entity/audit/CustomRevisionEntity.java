package com.cob.salesforce.entity.audit;

import com.cob.salesforce.audit.CustomRevisionListener;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@RevisionEntity(CustomRevisionListener.class)
@Proxy(lazy = false)
@Table(name = "revinfo")
public class CustomRevisionEntity extends DefaultRevisionEntity {
    private String uuid;

}
