package com.vlopatka.container.objectConfigurator

import com.vlopatka.container.context.ApplicationContext

interface ObjectConfigurator {
    fun configure(obj: Any, context: ApplicationContext)
}