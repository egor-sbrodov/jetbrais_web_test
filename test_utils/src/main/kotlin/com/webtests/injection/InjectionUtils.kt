package com.webtests.injection

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

object InjectionUtils {

    fun createInjectorFor(clazz: KClass<*>): Injector {
        val module = clazz.primaryConstructor?.call() as Module
        return Guice.createInjector(module)
    }
}