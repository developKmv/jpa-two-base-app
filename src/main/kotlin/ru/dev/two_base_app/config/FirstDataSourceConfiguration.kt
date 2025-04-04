package ru.dev.two_base_app.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "firstEntityManagerFactory",
    transactionManagerRef = "firstTransactionManager",
    basePackages = ["ru.dev.two_base_app.repository_first"]
)
class FirstDataSourceConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    fun firstDataSourceProperties(): DataSourceProperties =
        DataSourceProperties()

    @Primary
    @Bean("firstDataSource")
    fun firstDataSource() = firstDataSourceProperties().initializeDataSourceBuilder().build()

    @Primary
    @Bean(name = ["firstEntityManagerFactory"])
    fun firstEntityManagerFactoryBean(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("firstDataSource") firstDataSource: DataSource?
    ): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(firstDataSource)
            .packages("ru.dev.two_base_app.entity_first")
            .persistenceUnit("first").build()
    }

    @Primary
    @Bean(name = ["firstTransactionManager"])
    @ConfigurationProperties("spring.jpa")
    fun transactionManager(
        @Qualifier("firstEntityManagerFactory") entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}