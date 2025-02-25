package fr.groupbees.apigeesdk.utils;

import org.springframework.beans.factory.annotation.Value;

public class ApigeeClientBuilder {


    @Value("${apigee.apim.org}")
    private String org;
    @Value("${apigee.apim.host}")
    private String host;
    @Value("${apigee.apim.auth.token}")
    private String token;


}
