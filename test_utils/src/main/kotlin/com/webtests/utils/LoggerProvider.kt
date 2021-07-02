package com.webtests.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private class LoggerDelegate<T : Any> : ReadOnlyProperty<T, Logger> {
    private lateinit var logger: Logger

    override fun getValue(thisRef: T, property: KProperty<*>): Logger {
        if (! ::logger.isInitialized) {
            logger = LoggerFactory.getLogger(thisRef.javaClass)
        }
        return logger
    }
}

val lazyLogger: ReadOnlyProperty<Any, Logger>
    get() = LoggerDelegate()