package com.vlopatka.abstractum.context

import com.vlopatka.abstractum.config.KotlinConfig
import com.vlopatka.abstractum.factory.ObjectFactory

object Application {

    fun run(packageToScan: String, ifcToImpl: Map<Class<*>, Class<*>>): ApplicationContext {
        val config = KotlinConfig(packageToScan, ifcToImpl)
        val context = ApplicationContext(config)

        val factory = ObjectFactory(context)
        context.factory = factory

        return context
    }
}