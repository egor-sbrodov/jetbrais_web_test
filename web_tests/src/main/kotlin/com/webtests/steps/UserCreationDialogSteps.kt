package com.webtests.steps

import com.google.inject.Inject
import com.webtests.element.UserCreationDialog
import com.webtests.page.MainPage
import com.webtests.pagefactory.PageFactory
import com.webtests.utils.Config.defaultPassword
import io.qameta.allure.Step
import org.openqa.selenium.Keys

class UserCreationDialogSteps {

    @Inject private lateinit var pageFactory: PageFactory
    @Inject private lateinit var webDriverSteps: WebDriverSteps
    @Inject private lateinit var awaitilitySteps: AwaitilitySteps

    private fun onUserCreationDialog(): UserCreationDialog {
        return pageFactory.on(MainPage::class).usersPanel().userCreationDialog()
    }

    @Step("Fill login: `{userLogin}`")
    fun fillUserLogin(userLogin: String): UserCreationDialogSteps {
        onUserCreationDialog().loginInput().sendKeys(userLogin)
        return this
    }

    fun fillUserPasswordAndConfirmOne(password: String): UserCreationDialogSteps {
        fillPassword(password)
        return fillPasswordConfirmation(password)
    }

    @Step("Fill password: `{password}`")
    fun fillPassword(password: String): UserCreationDialogSteps {
        onUserCreationDialog().passwordInput().sendKeys(password)
        return this
    }

    @Step("Fill password conformation: `{password}`")
    fun fillPasswordConfirmation(password: String): UserCreationDialogSteps {
        onUserCreationDialog().confirmPasswordInput().sendKeys(password)
        return this
    }

    @Step("Switch force password change to `{to}`")
    fun switchForcePasswordChangeCheckbox(to: Boolean): UserCreationDialogSteps {
        val isCheckboxSelected = onUserCreationDialog().forcePasswordChangeCheckbox().isSelected
        if (to != isCheckboxSelected) {
            onUserCreationDialog().forcePasswordChangeCheckbox().click()
        }
        return this
    }

    @Step("Fill full name: `{fullName}`")
    fun fillFullName(fullName: String): UserCreationDialogSteps {
        onUserCreationDialog().fullNameInput().sendKeys(fullName)
        return this
    }

    @Step("Fill email: `{email}`")
    fun fillEmail(email: String): UserCreationDialogSteps {
        onUserCreationDialog().emailInput().sendKeys(email)
        return this
    }

    @Step("Fill jabber: `{jabber}`")
    fun fillJabber(jabber: String): UserCreationDialogSteps {
        onUserCreationDialog().jabberInput().sendKeys(jabber)
        return this
    }


    @Step("Click button `OK`")
    fun clickButtonOk(): UserCreationDialogSteps {
        onUserCreationDialog().buttonOk().click()
        return this
    }

    fun clickCancel(): UserCreationDialogSteps {
        onUserCreationDialog().buttonCancel().click()
        return this
    }

    fun clickOnCross(): UserCreationDialogSteps {
        awaitilitySteps.waitUntilDisplayed(onUserCreationDialog().crossSigh()).click()
        return this
    }

    fun submitFormViaEnterKey(): UserCreationDialogSteps {
        onUserCreationDialog().loginInput().sendKeys(Keys.ENTER)
        return this
    }

    fun checkErrorIconTooltipMessage(errorMessage: String): UserCreationDialogSteps {
        webDriverSteps.hoverOn(onUserCreationDialog().errorBubble())
        val errorTooltip = awaitilitySteps.waitUntilDisplayed(onUserCreationDialog().errorTooltip())
        awaitilitySteps.waitHasText(errorTooltip, errorMessage)
        return this
    }

    fun checkLoginInputHasError(): UserCreationDialogSteps {
        awaitilitySteps.waitHasClass(onUserCreationDialog().loginInput(), formHasErrorClass)
        return this
    }

    fun checkPasswordInputHasError(): UserCreationDialogSteps {
        awaitilitySteps.waitHasClass(onUserCreationDialog().passwordInput(), formHasErrorClass)
        return this
    }

    fun checkPasswordConformationInputHasError(): UserCreationDialogSteps {
        awaitilitySteps.waitHasClass(onUserCreationDialog().confirmPasswordInput(), formHasErrorClass)
        return this
    }

    fun checkLoginInputIs(expectedLogin: String): UserCreationDialogSteps {
        awaitilitySteps.waitHasValue(onUserCreationDialog().loginInput(), expectedLogin)
        return this
    }

    fun checkFullNameInputIs(expectedFullName: String): UserCreationDialogSteps {
        awaitilitySteps.waitHasValue(onUserCreationDialog().fullNameInput(), expectedFullName)
        return this
    }

    fun createUser(
        login: String,
        password: String = defaultPassword,
        isForceChangePassword: Boolean = false,
        fullName: String? = null,
        email: String? = null,
        jabber: String? = null
    ): UserCreationDialogSteps {
        fillUserLogin(login)
        fillUserPasswordAndConfirmOne(password)
        switchForcePasswordChangeCheckbox(isForceChangePassword)
        fullName?.let { fillFullName(it) }
        email?.let { fillEmail(it) }
        jabber?.let { fillJabber(it) }
        clickButtonOk()
        return this
    }

    companion object {
        private const val formHasErrorClass = "form-has-error"
    }

}
