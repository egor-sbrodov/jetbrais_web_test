package com.webtests.pagefactory

import com.google.inject.Inject
import com.webtests.webdriver.DriverProvider
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import kotlin.reflect.KClass

open class PageFactory {

    @Inject lateinit var driverProvider: DriverProvider

    open fun <T : Page> on(pageClass: KClass<T>): T {
        return PageFactory.initElements(getDriver(), pageClass.java)
    }

    fun getDriver(): WebDriver {
        return driverProvider.get()
    }
}