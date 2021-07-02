package com.webtests.webdriver

import com.google.inject.Provider
import org.openqa.selenium.WebDriver

interface DriverProvider : Provider<WebDriver> {

    fun createDriverAndGet(): WebDriver

    fun closeDriver()

    override fun get(): WebDriver

}