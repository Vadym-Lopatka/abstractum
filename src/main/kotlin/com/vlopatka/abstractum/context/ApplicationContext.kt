package com.vlopatka.abstractum.context

import com.vlopatka.abstractum.annotation.Singleton
import com.vlopatka.abstractum.config.KotlinConfig
import com.vlopatka.abstractum.factory.ObjectFactory
import java.util.concurrent.ConcurrentHashMap

@Suppress("UNCHECKED_CAST")
class ApplicationContext(val config: KotlinConfig) {
    private val cache: ConcurrentHashMap<Class<*>, Any> = ConcurrentHashMap()
    var factory: ObjectFactory? = null

    fun <T> getObject(type: Class<T>): T {

        if (cache.containsKey(type)) {
            return cache[type] as T
        }

        val implClass = type.takeIf { it.isInterface }?.let { config.getImplClass(type) } ?: type
        val obj = factory?.createObject(implClass) ?: throw IllegalStateException("factory is not initialized")

        if (implClass.isAnnotationPresent(Singleton::class.java)) {
            cache[type] = obj
        }

        return obj
    }

}