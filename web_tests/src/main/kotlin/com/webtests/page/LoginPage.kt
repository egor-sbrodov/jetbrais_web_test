package com.webtests.page

import com.webtests.pagefactory.Page
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class LoginPage(driver: WebDriver) : Page(driver) {

    fun loginOrEmailInput(): WebElement {
        return waitDisplayedAndGetElement(xpath("//*[./*[./*[contains(text(), 'Login or Email')]]]//input"))
    }

    fun passwordInput(): WebElement {
        return waitDisplayedAndGetElement(xpath("//*[./*[./*[contains(text(), 'Password')]]]//input"))
    }

    fun buttonLogin(): WebElement {
        return waitDisplayedAndGetElement(xpath(".//input[@type='submit']"))
    }
}