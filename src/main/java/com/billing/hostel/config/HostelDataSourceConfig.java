package com.billing.hostel.config;

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
    basePackages = "com.billing.hostel.repository",
    entityManagerFactoryRef = "hostelEntityManager",
    transactionManagerRef = "hostelTransactionManager"
)
public class HostelDataSourceConfig {

    @Bean(name = "hostelDataSource")
    public DataSource hostelDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/myhostel")
                .username("root")
                .password("root")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "hostelEntityManager")
    public LocalContainerEntityManagerFactoryBean hostelEntityManagerFactory(
            @Qualifier("hostelDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.billing.hostel.entity");
        em.setPersistenceUnitName("hostel");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Bean(name = "hostelTransactionManager")
    public PlatformTransactionManager hostelTransactionManager(
            @Qualifier("hostelEntityManager") EntityManagerFactory emf) {   // ← jakarta.persistence
        return new JpaTransactionManager(emf);
    }
}