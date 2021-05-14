package com.example.homework.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
@Configuration
@EnableTransactionManagement
public class HibernateConfig {
//    @Bean
//    public DataSource getDataSource(){
//        DriverManagerDataSource dataSource= new DriverManagerDataSource();
//
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/newPostgres");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("qwe");
//        return dataSource;
//    }
@Bean(name = "datasource1")
@Primary // первый кандидат на внедрение бина
public DataSource getDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    //dataSourceBuilder.driverClassName("org.h2.Driver");
    dataSourceBuilder.driverClassName("org.postgresql.Driver");
    dataSourceBuilder.url("jdbc:postgresql://localhost/newPostgres");
    dataSourceBuilder.username("postgres");
    dataSourceBuilder.password("qwe");
    return dataSourceBuilder.build();
}
    @Bean(name = "datasource2")
    public DataSource getDataSource1() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        //dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost/newPostgres");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("qwe");
        return dataSourceBuilder.build();
    }
    @Bean

    public JdbcTemplate jdbcTemplate( @Autowired
                                          @Qualifier(value = "datasource2")DataSource dataSource){
        JdbcTemplate jdbcTemplate= new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;

    }
    @Bean
    public Properties additionalPropreties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        return properties;
    }
    @Bean
    public SessionFactory getSessionFactory() throws IOException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan("com.example.homework.Model");
        factoryBean.setHibernateProperties(additionalPropreties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
    @Autowired
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;

    }
}
