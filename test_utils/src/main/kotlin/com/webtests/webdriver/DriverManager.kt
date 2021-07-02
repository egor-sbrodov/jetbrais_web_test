package com.webtests.webdriver

import com.webtests.utils.Config.implicitlyWaitDuration
import com.webtests.utils.Config.pageLoadingWaitDuration
import com.webtests.utils.lazyLogger
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

open class DriverManager : DriverProvider {
    private val logger by lazyLogger
    private var webDriverInstance: WebDriver? = null

    init {
        System.setProperty("webdriver.chrome.driver", "${System.getProperty("user.dir")}/../test_utils/src/main/resources/chromedriver")
    }

    override fun createDriverAndGet(): WebDriver {
        logger.info("Creating web driver.")
        webDriverInstance = ChromeDriver(ChromeOptions())
        if (webDriverInstance == null) {
            throw IllegalStateException("Browser wasn't created")
        } else {
            webDriverInstance!!.manage().timeouts().pageLoadTimeout(pageLoadingWaitDuration)
            webDriverInstance!!.manage().timeouts().implicitlyWait(implicitlyWaitDuration)
        }
        return webDriverInstance!!
    }

    override fun closeDriver() {
        logger.info("Closing web driver.")
        webDriverInstance?.close()
    }

    override fun get(): WebDriver {
        return webDriverInstance ?: createDriverAndGet()
    }
}