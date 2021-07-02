package com.webtests.injection

import com.google.inject.AbstractModule

class GuiceTestModule : AbstractModule() {
    override fun configure() {
        install(TestModule())
    }
}
