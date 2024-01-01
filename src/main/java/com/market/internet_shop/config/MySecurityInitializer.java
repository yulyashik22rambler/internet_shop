package com.market.internet_shop.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class MySecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public MySecurityInitializer() {
        super(SecurityConfig.class);
    }
}
