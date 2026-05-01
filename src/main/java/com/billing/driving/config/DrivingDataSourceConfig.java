package com.billing.driving.config;

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
    basePackages = "com.billing.driving.repository",
    entityManagerFactoryRef = "drivingEntityManager",
    transactionManagerRef = "drivingTransactionManager"
)
public class DrivingDataSourceConfig {

    @Bean(name = "drivingDataSource")
    public DataSource drivingDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/driving_db")
                .username("root")
                .password("root")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "drivingEntityManager")
    public LocalContainerEntityManagerFactoryBean drivingEntityManagerFactory(
            @Qualifier("drivingDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.billing.driving.entity");
        em.setPersistenceUnitName("driving");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Bean(name = "drivingTransactionManager")
    public PlatformTransactionManager drivingTransactionManager(
            @Qualifier("drivingEntityManager") EntityManagerFactory emf) {   // ← jakarta.persistence
        return new JpaTransactionManager(emf);
    }
}