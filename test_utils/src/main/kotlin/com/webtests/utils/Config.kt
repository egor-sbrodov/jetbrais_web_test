package com.webtests.utils

import java.time.Duration

object Config {

    const val rootUserName = "root"
    const val defaultPassword = "password123"

    val implicitlyWaitDuration: Duration = Duration.ofSeconds(10)
    val pageLoadingWaitDuration: Duration = Duration.ofSeconds(60)
    val elementExplicitlyWaitDuration: Duration = Duration.ofSeconds(10)
    val elementExplicitlyIsAbsentWaitDuration: Duration = Duration.ofSeconds(2)


    private const val appHostVarName = "app.host"
    private val hostName: String = System.getenv(appHostVarName)
        ?: throw IllegalStateException("$appHostVarName not specified.")

    fun getAppUrl(): String {
        return "http://$hostName"
    }
}