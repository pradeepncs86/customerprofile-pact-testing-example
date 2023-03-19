package com.contracttest.pact;


import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CustomerProfileConsumer {
    private String url;

    public CustomerProfileConsumer(String url) {
        this.url = url;
    }

    public String getCustomerProfile(String customerNumber) throws IOException {
        Request request = Request.Get(url + "/" + customerNumber);
        Response executeRequest = request.execute();
        HttpResponse response = executeRequest.returnResponse();
        System.out.println("intermediate response : " + response);
        String responseToString = EntityUtils.toString(response.getEntity());
        System.out.println("Final client response is : " + responseToString);
        return responseToString;
    }
}
