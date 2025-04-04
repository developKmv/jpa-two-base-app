package ru.dev.two_base_app.service

import org.springframework.stereotype.Service
import ru.dev.two_base_app.entity_first.Registry
import ru.dev.two_base_app.entity_second.Registry2
import ru.dev.two_base_app.repository_first.RegistryDAO
import ru.dev.two_base_app.repository_second.RegistryDAOsecond
import java.util.*

@Service
class RegistryService(val registryRepository: RegistryDAO,val registryRepositorySecond: RegistryDAOsecond) {
    fun findAllRegsFirst(): MutableList<Registry> {
        registryRepository.save(Registry(null,Random().nextLong(),"test"))
        return registryRepository.findAll()
    }

    fun findAllRegsSecond(): MutableList<Registry2> {
        registryRepositorySecond.save(Registry2(null,Random().nextLong(),"test2"))
        return registryRepositorySecond.findAll()
    }
}