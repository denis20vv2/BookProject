package main.java.com.example.shop.config;

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
        basePackages = "com.example.shop.repository",
        entityManagerFactoryRef = "shopEntityManagerFactory",
        transactionManagerRef = "shopTransactionManager"
)
public class ShopConfig {



        @Bean(name = "shopDataSource")
        @ConfigurationProperties(prefix = "shop.datasource")
        public DataSource shopDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "shopEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean shopEntityManagerFactory(
                @Qualifier("shopDataSource") DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan("com.example.shop.model");
            return em;
        }

        @Bean(name = "shopTransactionManager")
        public PlatformTransactionManager shopTransactionManager(
                @Qualifier("shopEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory.getObject());
        }
    }
