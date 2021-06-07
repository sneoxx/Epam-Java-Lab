

package com.zaraev.epam.javacourses.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


//@Profile("local")
//@PropertySource("classpath:impl.properties")
//@Configuration
//  @ComponentScan("com.zaraev.epam.javacourses")
//  @EnableTransactionManagement
public class PersistenceJPAConfig {
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        return Persistence.createEntityManagerFactory("WER");
//    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource =
                new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setBasename("classpath:messages/message");
        return reloadableResourceBundleMessageSource;
    }

    @Bean
    public Locale locale() {
        return new Locale("ru", "RU");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.zaraev.epam.javacourses");
        em.setPersistenceUnitName("WER");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

//    @Bean(name="dwEntityManager")
//    public LocalContainerEntityManagerFactoryBean dwEntityManagerFactory() {
//        try {
//            LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//            localContainerEntityManagerFactoryBean.setDataSource(lcDwDataSource);
//            localContainerEntityManagerFactoryBean.setPackagesToScan(DWAccount.class.getPackage().getName());
//            localContainerEntityManagerFactoryBean.setPersistenceUnitName("dw");
//            HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//
//            jpaVendorAdapter.setGenerateDdl(false);
//            jpaVendorAdapter.setShowSql(true);
//            jpaVendorAdapter.setDatabasePlatform(environment.getProperty("dataSource.dialect"));
//            localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//            return localContainerEntityManagerFactoryBean;
//
//        } catch (Exception e) {
//            logger.error("JpaConfigurationImpl.entityManagerFactory(): " + e.getMessage());
//        }
//        return new LocalContainerEntityManagerFactoryBean();
//    }

//       @Bean
//       public EntityManagerFactory entityManagerFactory() {
//           return Persistence.createEntityManagerFactory("WER");
//       }

    protected Map<String, Object> jpaProperties1() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy.class);
        props.put("hibernate.implicit_naming_strategy", com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy.class);
        return props;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/epamcourses");
        dataSource.setUsername("usr");
        dataSource.setPassword("pass");
        dataSource.setSchema("zaraev");
        return dataSource;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactoryBean().getObject());

        return transactionManager;
    }


    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        return new Properties() {
            {  // Hibernate Specific:
                //    setProperty("hibernate.hbm2ddl.auto", "create-drop");

                setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
            }
        };
    }
}