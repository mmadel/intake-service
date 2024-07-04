package com.cob.salesforce.models.integration.nppes;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class NPPESResponse {
    public int result_count;
    public ArrayList<Result> results;
}
