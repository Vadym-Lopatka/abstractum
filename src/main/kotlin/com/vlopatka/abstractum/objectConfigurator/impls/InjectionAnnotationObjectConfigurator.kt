package com.vlopatka.abstractum.objectConfigurator.impls

import com.vlopatka.abstractum.annotation.Injection
import com.vlopatka.abstractum.context.ApplicationContext
import com.vlopatka.abstractum.helper.FieldHelper.setValueToObject
import com.vlopatka.abstractum.objectConfigurator.ObjectConfigurator
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