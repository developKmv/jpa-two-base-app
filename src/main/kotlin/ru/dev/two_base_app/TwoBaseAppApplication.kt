package ru.dev.two_base_app

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.dev.two_base_app.service.RegistryService

@SpringBootApplication
class TwoBaseAppApplication
val logger = LoggerFactory.getLogger(TwoBaseAppApplication::class.java)

fun main(args: Array<String>) {
	runApplication<TwoBaseAppApplication>(*args)
	val ctx = ApplicationContextProvider.getApplicationCtx()
	val registryService = ctx.getBean("registryService",RegistryService::class.java)
	registryService.findAllRegsFirst().forEach {
		logger.info(it.toString())
	}

	registryService.findAllRegsSecond().forEach {
		logger.info(it.toString())
	}
}
