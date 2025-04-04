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
    entityManagerFactoryRef = "secondEntityManagerFactory",
    transactionManagerRef = "secondTransactionManager",
    basePackages = ["ru.dev.two_base_app.repository_second"]
)
class SecondDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.second")
    fun secondDataSourceProperties(): DataSourceProperties =
        DataSourceProperties()

    @Bean("secondDataSource")
    fun secondDataSource() = secondDataSourceProperties().initializeDataSourceBuilder().build()

    @Bean(name = ["secondEntityManagerFactory"])
    fun secondEntityManagerFactoryBean(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("secondDataSource") secondDataSource: DataSource?
    ): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(secondDataSource)
            .packages("ru.dev.two_base_app.entity_second")
            .persistenceUnit("second").build()
    }

    @Bean(name = ["secondTransactionManager"])
    @ConfigurationProperties("spring.jpa")
    fun transactionManager(
        @Qualifier("secondEntityManagerFactory") entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}