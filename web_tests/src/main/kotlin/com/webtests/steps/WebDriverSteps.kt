package com.webtests.steps

import com.google.inject.Inject
import com.webtests.pagefactory.PageFactory
import io.qameta.allure.Step
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

class WebDriverSteps {

    @Inject private lateinit var pageFactory: PageFactory

    private fun getDriver(): WebDriver {
        return pageFactory.getDriver()
    }

    @Step("Open url: {url}")
    fun openUrl(url: String) {
        getDriver().get(url)
    }

    fun hoverOn(element: WebElement) {
        Actions(pageFactory.getDriver()).moveToElement(element).build().perform()
    }

}
