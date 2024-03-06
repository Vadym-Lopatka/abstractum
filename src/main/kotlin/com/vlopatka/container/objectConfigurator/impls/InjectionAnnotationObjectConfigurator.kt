package com.vlopatka.container.objectConfigurator.impls

import com.vlopatka.container.annotation.Injection
import com.vlopatka.container.context.ApplicationContext
import com.vlopatka.container.helper.FieldHelper.setValueToObject
import com.vlopatka.container.objectConfigurator.ObjectConfigurator
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.javaType

class InjectionAnnotationObjectConfigurator : ObjectConfigurator {

    @ExperimentalStdlibApi
    override fun configure(obj: Any, context: ApplicationContext) {
        obj::class.memberProperties
            .filter { it.hasAnnotation<Injection>() }
            .forEach { setValueToObject(obj, it, context.getObject(it.returnType.javaType as Class<*>)) }
    }
}