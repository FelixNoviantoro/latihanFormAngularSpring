package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    public DataSource dataSource;

    @Bean(name = "sql2o")
    public Sql2o getSql2o(){
        return new Sql2o(dataSource);
    }

}
