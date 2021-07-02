package com.webtests.extension

import com.google.inject.Injector
import com.webtests.extension.DriverUtils.Companion.getDriverFactory
import com.webtests.extension.InjectExtension.Companion.INJECTOR_KEY
import com.webtests.webdriver.DriverProvider
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL

class ChromeBrowserExtension : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext) {
        val globalStore = context.getStore(GLOBAL)
        val injector = globalStore.get(INJECTOR_KEY) as Injector
        val driverFactory = injector.getInstance(DriverProvider::class.java)
        globalStore.put(driverKey, driverFactory)

        driverFactory.createDriverAndGet()
    }

    override fun afterEach(context: ExtensionContext) {
        getDriverFactory(context).closeDriver()
    }

    companion object {
        const val driverKey = "DriverGlobalContextKey"
    }
}
