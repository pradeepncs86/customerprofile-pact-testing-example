package com.contracttest.pact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
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
