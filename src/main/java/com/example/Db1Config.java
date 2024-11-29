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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.shop-service.repository",
        entityManagerFactoryRef = "entityManagerFactoryModule1",
        transactionManagerRef = "transactionManagerModule1"
)
public class Db1Config {

    @Bean(name = "dataSourceModule1")
    @ConfigurationProperties(prefix = "spring.datasource.module1")
    public DataSource dataSourceModule1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryModule1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryModule1(
            @Qualifier("dataSourceModule1") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.example.module1.model");  // Пакет с моделями для этой БД
        factoryBean.setPersistenceUnitName("module1");
        return factoryBean;
    }

    @Bean(name = "transactionManagerModule1")
    public PlatformTransactionManager transactionManagerModule1(
            @Qualifier("entityManagerFactoryModule1") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}

*/
