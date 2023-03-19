package com.contracttest.pact;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CustomerProfileConsumerTest {

    @Rule
    // It spins up the provider in localhost and a random port
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("customerProfileProvider", this);

    @Pact(provider="customerProfileProvider", consumer="customerProfileConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws JsonProcessingException {

        return builder
                .uponReceiving("A contact request for customer Profile")
                .path("/getCustomerProfile")
                .method("GET")
                .willRespondWith()
                .status(200)
                .matchHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                .body(new ObjectMapper().writeValueAsString(getCustomerProfilesFromStore().get("684088275")))
                .toPact();
    }

    @Test
    @PactVerification("customerProfileProvider")
    public void getCustomerProfile() throws Exception {
        CustomerProfileConsumer customerProfileConsumer = new CustomerProfileConsumer(mockProvider.getUrl());
        String result = customerProfileConsumer.getCustomerProfile("getCustomerProfile");
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerProfile customerProfile = objectMapper.readValue(result, CustomerProfile.class);
        assertEquals("684088275", customerProfile.getCustomerNumber());
    }

    private Map<String, CustomerProfile> getCustomerProfilesFromStore() {
        final Map<String, CustomerProfile> customerProfileStore;
        customerProfileStore = new HashMap<>();
        customerProfileStore.put("684088275", CustomerProfile.builder()
                .customerNumber("684088275")
                .emailAddress("xyz.abc@gmail.com")
                .phoneNumber("8907865478")
                .firstName("Punith")
                .lastName("Rajkumar")
                .originatingAppId("BookMyShow")
                .ssn("1234578")
                .customerCompanyNumber("111")
                .build());
        customerProfileStore.put("184088276", CustomerProfile.builder()
                .customerNumber("184088276")
                .emailAddress("fgh.abc@gmail.com")
                .phoneNumber("9907865478")
                .firstName("surya")
                .lastName("s")
                .originatingAppId("BookMyShow")
                .ssn("5234578")
                .customerCompanyNumber("111")
                .build());
        return customerProfileStore;
    }
}