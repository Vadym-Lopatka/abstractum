package com.vlopatka.abstractum.objectConfigurator

import com.vlopatka.abstractum.context.ApplicationContext

interface ObjectConfigurator {
    fun configure(obj: Any, context: ApplicationContext)
}