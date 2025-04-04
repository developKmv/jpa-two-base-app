package ru.dev.two_base_app.entity_first

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "REGISTRY")
data class Registry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "REGISTRY_ID")
    var registryId: Long? = null,
    @Column(name = "ORG_INN")
    var orgInn: String? = null
) {}

