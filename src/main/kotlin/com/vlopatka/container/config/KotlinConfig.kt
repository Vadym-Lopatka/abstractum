package com.vlopatka.container.config

import org.reflections.Reflections

@Suppress("UNCHECKED_CAST")
class KotlinConfig(
    packageToScan: String,
    private val ifcToImpl: Map<Class<*>, Class<*>>

) : Config {

    private val scanner: Reflections = Reflections(packageToScan)

    override fun <T> getImplClass(ifc: Class<T>): Class<T> {
        return ifcToImpl.getOrElse(ifc, defaultValue = {

            return scanner.getSubTypesOf(ifc).takeIf { it.size == 1 }
                ?.let { it.first() as Class<T> }
                ?: run { throw RuntimeException("$ifc has 0 or more than 1 implementations") }

        }) as Class<T>
    }

    override fun getScanner() = scanner
}
