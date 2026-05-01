package com.billing.workshop.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;   // ← Correct import
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.billing.workshop.repository",
    entityManagerFactoryRef = "workshopEntityManager",
    transactionManagerRef = "workshopTransactionManager"
)
public class WorkshopDataSourceConfig {

    @Bean(name = "workshopDataSource")
    public DataSource workshopDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/myworkshop")
                .username("root")
                .password("root")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "workshopEntityManager")
    public LocalContainerEntityManagerFactoryBean workshopEntityManagerFactory(
            @Qualifier("workshopDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.billing.workshop.entity");
        em.setPersistenceUnitName("workshop");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Bean(name = "workshopTransactionManager")
    public PlatformTransactionManager workshopTransactionManager(
            @Qualifier("workshopEntityManager") EntityManagerFactory emf) {   // ← jakarta.persistence
        return new JpaTransactionManager(emf);
    }
}