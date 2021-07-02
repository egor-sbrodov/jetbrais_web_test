package com.webtests.pagefactory

import com.webtests.utils.Config.elementExplicitlyWaitDuration
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

open class Page(private val driver: WebDriver) {

    fun waitDisplayedAndGetElement(by: By): WebElement {
        return WebDriverWait(driver, elementExplicitlyWaitDuration).until(ExpectedConditions.visibilityOfElementLocated(by))
    }
}