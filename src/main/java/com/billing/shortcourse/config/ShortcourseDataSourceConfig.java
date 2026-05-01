package com.billing.shortcourse.config;   // ← Change package according to your project

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

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.billing.shortcourse.repository",
    entityManagerFactoryRef = "shortcourseEntityManager",
    transactionManagerRef = "shortcourseTransactionManager"
)
public class ShortcourseDataSourceConfig {

    @Bean(name = "shortcourseDataSource")
    public DataSource shortcourseDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/shortcourse_db")
                .username("root")
                .password("root")                    // ← Change if your password is different
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "shortcourseEntityManager")
    public LocalContainerEntityManagerFactoryBean shortcourseEntityManagerFactory(
            @Qualifier("shortcourseDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.billing.shortcourse.entity");
        em.setPersistenceUnitName("shortcourse");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Bean(name = "shortcourseTransactionManager")
    public PlatformTransactionManager shortcourseTransactionManager(
            @Qualifier("shortcourseEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}