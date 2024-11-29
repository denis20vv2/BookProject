package main.java.com.example.storage.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.storage.repository",
        entityManagerFactoryRef = "storageEntityManagerFactory",
        transactionManagerRef = "storageTransactionManager"
)
public class StorageConfig {

    @Bean(name = "storageDataSource")
    @ConfigurationProperties(prefix = "storage.datasource")
    public DataSource storageDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "storageEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean storageEntityManagerFactory(
            @Qualifier("storageDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.storage.model");
        return em;
    }

    @Bean(name = "storageTransactionManager")
    public PlatformTransactionManager storageTransactionManager(
            @Qualifier("storageEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
