package com.dd.glsc.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GlscThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlscThirdPartyApplication.class, args);
    }

}
