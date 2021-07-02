package com.webtests

import org.openqa.selenium.*

open class ExtendedWebElement(private val element: WebElement) : WebElement, WrapsElement {

    override fun findElements(by: By?): MutableList<WebElement> {
        return element.findElements(by)
    }

    override fun findElement(by: By?): WebElement {
        return element.findElement(by)
    }

    override fun <T : Any?> getScreenshotAs(target: OutputType<T>?): T {
        return element.getScreenshotAs(target)
    }

    override fun click() {
        return element.click()
    }

    override fun submit() {
        return element.submit()
    }

    override fun sendKeys(vararg keysToSend: CharSequence?) {
        return element.sendKeys()
    }

    override fun clear() {
        return element.clear()
    }

    override fun getTagName(): String {
        return element.tagName
    }

    override fun getAttribute(name: String?): String {
        return element.getAttribute(name)
    }

    override fun isSelected(): Boolean {
        return element.isSelected
    }

    override fun isEnabled(): Boolean {
        return element.isEnabled
    }

    override fun getText(): String {
        return element.text
    }

    override fun isDisplayed(): Boolean {
        return element.isDisplayed
    }

    override fun getLocation(): Point {
        return element.location
    }

    override fun getSize(): Dimension {
        return element.size
    }

    override fun getRect(): Rectangle {
        return element.rect
    }

    override fun getCssValue(propertyName: String?): String {
        return element.getCssValue(propertyName)
    }

    override fun getWrappedElement(): WebElement {
        return element
    }
}