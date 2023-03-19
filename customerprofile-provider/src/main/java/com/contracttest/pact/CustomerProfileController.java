package com.contracttest.pact;

import com.contracttest.pact.model.CustomerProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class CustomerProfileController {

    private final Map<String, CustomerProfile> customerProfileStore;

    public CustomerProfileController() {
        customerProfileStore = getCustomerProfilesFromStore();
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

    @RequestMapping("/getCustomerProfile/{customerNumber}")
    public ResponseEntity<CustomerProfile> getCustomerProfile(@PathVariable String name) {
        return new ResponseEntity<CustomerProfile>(customerProfileStore.get(name), HttpStatus.OK);
    }
}
