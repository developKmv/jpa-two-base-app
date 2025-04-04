package ru.dev.two_base_app

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

class ApplicationContextProvider {
    @Component
    companion object : ApplicationContextAware {

        lateinit var ctx: ApplicationContext

        override fun setApplicationContext(applicationContext: ApplicationContext) {
            ctx = applicationContext
        }

        fun getApplicationCtx(): ApplicationContext{
            return ctx;
        }
    }
}