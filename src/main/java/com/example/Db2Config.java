/*
package main.java.com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.storage-service.repository",
        entityManagerFactoryRef = "entityManagerFactoryModule2",
        transactionManagerRef = "transactionManagerModule2"
)
public class Db2Config {

    @Bean(name = "dataSourceModule2")
    @ConfigurationProperties(prefix = "spring.datasource.module2")
    public DataSource dataSourceModule2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryModule2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryModule2(
            @Qualifier("dataSourceModule2") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.example.module2.model"); // Пакет с моделями для этой БД
        factoryBean.setPersistenceUnitName("module2");
        return factoryBean;
    }

    @Bean(name = "transactionManagerModule2")
    public PlatformTransactionManager transactionManagerModule2(
            @Qualifier("entityManagerFactoryModule2") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
*/
