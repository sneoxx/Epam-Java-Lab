package com.zaraev.epam.javacourses.config;


import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
public class EntityManagerFactoriesConfiguration {

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan( new String[]{"com.zaraev.epam.javacourses"});
        emf.setPersistenceUnitName("WER");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(additionalProperties());
        return emf;
    }




    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(emf().getObject());
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/epamcourses");
        dataSource.setUsername( "usr" );
        dataSource.setPassword( "pass" );
        dataSource.setSchema("zaraev");
        return dataSource;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put("hibernate.show_sql", "true");
       properties.put("hibernate.physical_naming_strategy", "com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy");
       // properties.put("hibernate.implicit_naming_strategy", "com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy");
        return properties;


    }

    protected Map<String, Object> jpaProperties1() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy",com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy.class);
        props.put("hibernate.implicit_naming_strategy", com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy.class);
        return props;
    }

}




