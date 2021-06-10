package com.example.filestorageback.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceProvider implements TransactionManagementConfigurer {

    @Value("${spring.datasource.username}")
    private String USER_NAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
    @Value("${spring.datasource.url}")
    private String DATASOURCE_URL;
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER_CLASS_NAME;

    @Override
    @NonNull
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(USER_NAME);
        config.setPassword(PASSWORD);
        config.setJdbcUrl(DATASOURCE_URL);
        config.setDriverClassName(DRIVER_CLASS_NAME);

        return new HikariDataSource(config);
    }

    @Bean
    public Sql2o sql2o() {
        return new Sql2o(dataSource());
    }
}
