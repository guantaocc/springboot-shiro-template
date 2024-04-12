package com.rain;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@MapperScan(basePackages = "com.rain.shiro.project.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableEnvironment environment =
                SpringApplication.run(Application.class, args).getEnvironment();

        logger.info("\n-----------------------------------------------------------------------------\n\t" +
                        "Application '{}' is running! \n\t" +
                        "Access URLs:\n\t" +
                        "Local: \thttp://localhost:{}\n\t" +
                        "Druid: \thttp://{}:{}/druid/index.html\n\t" +
                        "D o c: \thttp://{}:{}/doc.html\n" +
                        "\n-----------------------------------------------------------------------------\n\t",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"));
    }
}
