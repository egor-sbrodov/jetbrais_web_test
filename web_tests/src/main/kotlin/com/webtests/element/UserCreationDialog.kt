package com.webtests.element

import com.webtests.YouTrackElement
import org.openqa.selenium.By
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebElement

class UserCreationDialog(self: WebElement) : YouTrackElement<UserCreationDialog>(self) {

    fun loginInput(): WebElement {
        return findElement(loginInputXPath)
    }

    fun passwordInput(): WebElement {
        return findElement(passwordInputXPath)
    }

    fun confirmPasswordInput(): WebElement {
        return findElement(confirmPasswordXPath)
    }

    fun fullNameInput(): WebElement {
        return findElement(fullNameXPath)
    }

    fun emailInput(): WebElement {
        return findElement(emailXPath)
    }

    fun jabberInput(): WebElement {
        return findElement(jabberXPath)
    }

    fun buttonOk(): WebElement {
        return findElement(xpath(".//button[text()='OK']"))
    }

    fun buttonCancel(): WebElement {
        return findElement(xpath(".//button[text()='Cancel']"))
    }

    fun forcePasswordChangeCheckbox(): WebElement {
        return findElement(forcePasswordChangeCheckbox)
    }

    fun crossSigh(): WebElement {
        return findElement(xpath(".//*[contains(@class, 'dialog-close')]"))
    }

    fun errorBubble(): WebElement {
        return findElement(xpath(".//*[contains(@class, 'error-bulb')]"))
    }

    fun errorTooltip(): WebElement {
        return findElement(xpath("//*[contains(@class, 'error-tooltip')]"))
    }

    private val loginInputXPath = getInputXPath("Login:")
    private val passwordInputXPath = getInputXPath("Password")
    private val confirmPasswordXPath = getInputXPath("Confirm password")
    private val fullNameXPath = getInputXPath("Full name")
    private val emailXPath = getInputXPath("Email")
    private val jabberXPath = getInputXPath("Jabber")
    private val forcePasswordChangeCheckbox = getInputXPath("Force password change on first login")

    private fun getInputXPath(inputTitle: String): By {
        return xpath(".//*[./*[text()='$inputTitle']]//input")
    }

}
