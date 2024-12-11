/*package com.example.storage.config;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.example.storage.book.rep", "com.example.storage.author.rep"}, // Указываем два пакета, // Пакет с репозиториями модуля storage
        entityManagerFactoryRef = "storageEntityManagerFactory",
        transactionManagerRef = "storageTransactionManager"
)
public class StorageJpaConfig {

    @Bean(name = "storageDataSource")
    @ConfigurationProperties(prefix = "storage.datasource")
    public DataSource storageDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "storageEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean storageEntityManagerFactory(
            @Qualifier("storageDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.storage.entity") // Пакет с Entity-классами модуля storage
                .persistenceUnit("storagePU")
                .build();
    }

    @Bean(name = "storageTransactionManager")
    public PlatformTransactionManager storageTransactionManager(
            @Qualifier("storageEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
*/
