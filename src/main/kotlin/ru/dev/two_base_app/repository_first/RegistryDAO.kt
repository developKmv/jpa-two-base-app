package ru.dev.two_base_app.repository_first

import org.springframework.data.jpa.repository.JpaRepository
import ru.dev.two_base_app.entity_first.Registry

interface RegistryDAO: JpaRepository<Registry,Long> {
}