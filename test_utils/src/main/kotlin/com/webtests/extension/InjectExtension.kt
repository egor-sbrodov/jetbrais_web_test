package com.webtests.extension

import com.google.inject.Injector
import com.webtests.injection.GuiceTestModule
import com.webtests.injection.InjectionUtils.createInjectorFor
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class InjectExtension : BeforeEachCallback {

    override fun beforeEach(extensionContext: ExtensionContext) {
        val injector: Injector = createInjectorFor(GuiceTestModule::class)
        injector.injectMembers(extensionContext.testInstance.orElseThrow { IllegalStateException("There is no test class") })
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put(INJECTOR_KEY, injector)
    }

    companion object {
        const val INJECTOR_KEY = "InjectorGlobalContextKey"
    }

}
