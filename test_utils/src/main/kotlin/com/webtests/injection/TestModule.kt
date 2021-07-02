package com.webtests.injection

import com.google.inject.AbstractModule
import com.webtests.webdriver.DriverManager
import com.webtests.webdriver.DriverProvider

class TestModule : AbstractModule() {
    override fun configure() {
        bind(DriverProvider::class.java).toInstance(DriverManager())
    }

}