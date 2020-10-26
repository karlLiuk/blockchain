package com.lk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

/**
 * @Description 数据库DataSource配置类.
 * @Date 2020/6/30
 * @Author lk
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 数据库名称.
     **/
    @Value("${sqlite.dbName}")
    private String dbName;

    /**
     * 配置SQLite数据库的DataSource
     * @return SQLite数据库的DataSource
     **/
    @Bean(destroyMethod = "", name = "EmbeddedDataSource")
    public DataSource dataSource() {
        final DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:" + dbName);
        dataSourceBuilder.type(SQLiteDataSource.class);
        return dataSourceBuilder.build();
    }
}
