package com.vlopatka.abstractum.helper

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

object FieldHelper {
    fun setValueToObject(thObject: Any, field: KProperty1<out Any, *>, value: Any?) {
        if (field is KMutableProperty<*>) {
            field.isAccessible = true
            field.setter.call(thObject, value)
        }
    }
}
