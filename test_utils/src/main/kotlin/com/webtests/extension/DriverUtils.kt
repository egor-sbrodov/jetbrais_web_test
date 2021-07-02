package com.webtests.extension

import com.webtests.extension.ChromeBrowserExtension.Companion.driverKey
import com.webtests.webdriver.DriverProvider
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL

class DriverUtils {

    companion object {
        fun getDriverFactory(context: ExtensionContext): DriverProvider {
            return context.getStore(GLOBAL).get(driverKey) as DriverProvider
        }
    }
}