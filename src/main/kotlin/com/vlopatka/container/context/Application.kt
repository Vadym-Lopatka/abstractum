package com.vlopatka.container.context

import com.vlopatka.container.config.KotlinConfig
import com.vlopatka.container.factory.ObjectFactory

object Application {

    fun run(packageToScan: String, ifcToImpl: Map<Class<*>, Class<*>>): ApplicationContext {
        val config = KotlinConfig(packageToScan, ifcToImpl)
        val context = ApplicationContext(config)

        val factory = ObjectFactory(context)
        context.factory = factory

        return context
    }
}