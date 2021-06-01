package com.zaraev.epam.javacourses.config;

import com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

//    @Configuration
 //   @ComponentScan(basePackages = " testgroup.filmography")
//    @EnableTransactionManagement
//    @PropertySource(value = "classpath:db.properties")
    public class HibernateConfig {
        private Environment environment;

        @Autowired
        public void setEnvironment(Environment environment) {
            this.environment = environment;
        }

        private Properties hibernateProperties() {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
            properties.put("hibernate.show_sql", "true");
            return properties;
        }


//
//        protected Map<String, Object> jpaProperties() {
//            Map<String, Object> props = new HashMap<>();
//            props.put("hibernate.physical_naming_strategy",com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy.class);
//            props.put("hibernate.implicit_naming_strategy", com.zaraev.epam.javacourses.domain.entity.CamelCaseToSnakeCaseNamingStrategy.class);
//            return props;
//        }

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


        @Bean
        public LocalSessionFactoryBean sessionFactory() {
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource());
            sessionFactory.setPackagesToScan("com.zaraev.epam.javacourses");
            sessionFactory.setHibernateProperties(hibernateProperties());
            sessionFactory.setPhysicalNamingStrategy(new CamelCaseToSnakeCaseNamingStrategy());
            return sessionFactory;
        }

//        @Bean
//        public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//            em.setDataSource(dataSource());
//            em.setPackagesToScan("com.zaraev.epam.javacourses");
//            em.setPersistenceUnitName("WER");
//            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//            em.setJpaVendorAdapter(vendorAdapter);
//            em.setJpaProperties(additionalProperties());
//            return em;
//        }


        @Bean
        public HibernateTransactionManager transactionManager() {
            HibernateTransactionManager transactionManager = new HibernateTransactionManager();
            transactionManager.setSessionFactory(sessionFactory().getObject());
            return transactionManager;
        }



}
