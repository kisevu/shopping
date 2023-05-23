package com.amedakevin.app.webParent.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.amedakevin.app.common.entity","com.amedakevin.app.webParent.admin.user"})
public class ShopBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopBackendApplication.class,args);
    }
}