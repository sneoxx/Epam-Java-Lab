package com.zaraev.epam.javacourses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Profile("!local")
@PropertySource("classpath:impl.properties")
@Configuration
public class DefaultConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("WER");
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.zaraev.epam.javacourses");
//        em.setPersistenceUnitName("WER");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());
//        return em;
//    }
//
////    @Bean(name="dwEntityManager")
////    public LocalContainerEntityManagerFactoryBean dwEntityManagerFactory() {
////        try {
////            LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
////            localContainerEntityManagerFactoryBean.setDataSource(lcDwDataSource);
////            localContainerEntityManagerFactoryBean.setPackagesToScan(DWAccount.class.getPackage().getName());
////            localContainerEntityManagerFactoryBean.setPersistenceUnitName("dw");
////            HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
////
////            jpaVendorAdapter.setGenerateDdl(false);
////            jpaVendorAdapter.setShowSql(true);
////            jpaVendorAdapter.setDatabasePlatform(environment.getProperty("dataSource.dialect"));
//
////            localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
////            return localContainerEntityManagerFactoryBean;
////
////        } catch (Exception e) {
////            logger.error("JpaConfigurationImpl.entityManagerFactory(): " + e.getMessage());
////        }
////        return new LocalContainerEntityManagerFactoryBean();
////    }
//
//
//    protected Map<String, Object> jpaProperties1() {
//        Map<String, Object> props = new HashMap<>();
//        props.put("hibernate.physical_naming_strategy", com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy.class);
//        props.put("hibernate.implicit_naming_strategy", com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy.class);
//        return props;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/epamcourses");
//        dataSource.setUsername("usr");
//        dataSource.setPassword("pass");
//        dataSource.setSchema("zaraev");
//        return dataSource;
//    }
//
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                entityManagerFactoryBean().getObject());
//
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    Properties additionalProperties() {
//        return new Properties() {
//            {  // Hibernate Specific:
//                //    setProperty("hibernate.hbm2ddl.auto", "create-drop");
//
//                setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
//            }
//        };
//    }

}