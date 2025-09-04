package com.applaudo.project.configuration;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * Example connection: jdbc:h2:tcp://localhost:11000/mem:JavChallenge
 */
@Configuration(proxyBeanMethods = false)
public class H2MemoryTcpDBConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "11000");
    }
}
