package com.webtests.extension

import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(InjectExtension::class, ChromeBrowserExtension::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class WebTestsExtension {
}
