package com.contracttest.pact.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class CustomerProfile {

    private String firstName;
    private String lastName;
    private String ssn;
    private String phoneNumber;
    private String emailAddress;
    private String customerNumber;
    private String customerCompanyNumber;
    private String originatingAppId;
}
