package ru.dev.two_base_app.repository_second

import org.springframework.data.jpa.repository.JpaRepository
import ru.dev.two_base_app.entity_second.Registry2

interface RegistryDAOsecond: JpaRepository<Registry2,Long> {
}