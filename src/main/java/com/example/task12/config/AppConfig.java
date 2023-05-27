package com.example.task12.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig
{

    public HibernateConfig hibernateConfig()
    {
        return new HibernateConfig();
    }

}
