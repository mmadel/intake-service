package com.cob.salesforce.models.integration.nppes;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Result {
    private String created_epoch;
    private String enumeration_type;
    private String last_updated_epoch;
    private String number;
    private ArrayList<Address> addresses;
    private ArrayList<Object> practiceLocations;
    private Basic basic;
    private ArrayList<Taxonomy> taxonomies;
    private ArrayList<Object> identifiers;
    private ArrayList<Object> endpoints;
    private ArrayList<Object> other_names;
}
