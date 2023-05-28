package com.cob.salesforce.entity.validation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "basic_r_info")
@Setter
@Getter
public class BasicInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name_field_v")
    Boolean firstName;
    @Column(name = "middle_name_field_v")
    Boolean middleName;
    @Column(name = "last_name_field_v")
    Boolean lastName;

    @Column(name = "birth_date_field_v")
    Boolean birthDate;
    @Column(name = "gender_field_v")
    Boolean gender;
    @Column(name = "marital_status_field_v")
    Boolean maritalStatus;
    @Column(name = "phone_field_v")
    Boolean Phone;
    @Column(name = "email_field_v")
    Boolean email;
    @Column(name = "patient_id_field_v")
    Boolean patientId;
    @Column(name = "emergency_contact_field_v")
    Boolean emergencyContact;
}
