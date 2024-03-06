package com.vlopatka.abstractum.factory

import com.vlopatka.abstractum.context.ApplicationContext
import com.vlopatka.abstractum.objectConfigurator.ObjectConfigurator

@Suppress("UNCHECKED_CAST")
class ObjectFactory(
    private val context: ApplicationContext
) {
    private val configurators: List<ObjectConfigurator> = initConfigurators()

    fun <T> createObject(implClass: Class<T>): T {
        val obj = implClass.declaredConstructors.first().newInstance()

        /**
         * Here I used a chain of responsibility pattern.
         *
         * Every DI component that would be created through @Injection
         * will be processed once(on startup) by each implementation of the ObjectConfigurator.
         *
         * @see com.vlopatka.abstractum.annotation.Injection
         * @see com.vlopatka.abstractum.objectConfigurator.ObjectConfigurator
         */
        configurators.forEach { it.configure(obj, context) }

        return obj as T
    }

    private fun initConfigurators(): List<ObjectConfigurator> {
        val objConfiguratorImplementations = context.config.getScanner().getSubTypesOf(ObjectConfigurator::class.java)

        return objConfiguratorImplementations
            .map { it.declaredConstructors.first().newInstance() as ObjectConfigurator }
            .toList()
    }
}
