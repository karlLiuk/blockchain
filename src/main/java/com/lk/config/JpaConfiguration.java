package com.lk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description JPA配置类.
 * @Date 2020/6/30
 * @Author lk
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.lk",
        transactionManagerRef = "jpaTransactionManager",
        entityManagerFactoryRef = "")
@EnableTransactionManagement
public class JpaConfiguration {

    /**
     * Jpa属性.
     **/
    @Resource
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    /**
     * 配置JPA事务管理器.
     * @return JPA事务管理器
     **/
    @Autowired
    @Bean
    public JpaTransactionManager jpaTransactionManager(
            @Qualifier(value = "EmbeddedDataSource") final DataSource dataSource,
            final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource);

        return jpaTransactionManager;
    }

    /**
     * 本地容器管理工厂.
     * @param dataSource 数据源
     * @param builder
     * @return org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
     **/
    @Autowired
    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            @Qualifier(value = "EmbeddedDataSource") final DataSource dataSource,
            final EntityManagerFactoryBuilder builder) {
        final Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(dataSource)
                .packages("com.lk")
                .properties(properties)
                .build();
    }

    /**
     * 返回对应实现方式的属性（Hibernate）.
     * @param dataSource 数据源
     * @return java.util.Map<java.lang.String,?>
     **/
//    private Map<String,String> getVendorProperties(final DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
}
