package com.webtests.steps

import com.google.inject.Inject
import com.webtests.YouTrackElement
import com.webtests.pagefactory.PageFactory
import com.webtests.utils.Config.elementExplicitlyIsAbsentWaitDuration
import com.webtests.utils.Config.elementExplicitlyWaitDuration
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions.*
import org.openqa.selenium.support.ui.WebDriverWait
import javax.swing.text.html.HTML.Attribute.CLASS
import javax.swing.text.html.HTML.Attribute.VALUE

class AwaitilitySteps {

    @Inject private lateinit var pageFactory: PageFactory

    private fun getDriver(): WebDriver {
        return pageFactory.getDriver()
    }

    private fun <T> waitUntil(condition: ExpectedCondition<T>): T {
        return WebDriverWait(getDriver(), elementExplicitlyWaitDuration).until(condition)
    }

    fun waitHasText(element: WebElement, text: String) {
        waitUntil(textToBePresentInElement(element, text))
    }

    fun waitHasClass(element: WebElement, classToCheck: String) {
        waitUntil(attributeContains(element, CLASS.toString(), classToCheck))
    }

    fun waitHasValue(element: WebElement, classToCheck: String) {
        waitUntil(attributeContains(element, VALUE.toString(), classToCheck))
    }

    fun waitUntilDisplayed(webElement: WebElement): WebElement {
        return waitUntil(visibilityOf(webElement))
    }

    fun <T> waitUntilDisplayed(webElement: YouTrackElement<T>): T {
        @Suppress("UNCHECKED_CAST")
        return waitUntil(visibilityOf(webElement)) as T
    }

    fun waitUntilAbsent(by: By) {
        WebDriverWait(getDriver(), elementExplicitlyIsAbsentWaitDuration).until(invisibilityOfElementLocated(by))
    }

}
