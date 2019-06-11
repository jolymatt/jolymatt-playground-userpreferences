package com.github.jolymatt.playground.userpref.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;


@Configuration
@ComponentScan
/**
 * The H2 DB configuration
 * <p> Testing purpose only</p>
 * @author Joly Mathew
 */
public class H2Config {

    @Bean(initMethod="start",destroyMethod="stop")
    public org.h2.tools.Server h2WebConsonleServer () throws SQLException {
        return org.h2.tools.Server.createWebServer("-web","-webAllowOthers","-webDaemon","-webPort", "8082");
    }
}
