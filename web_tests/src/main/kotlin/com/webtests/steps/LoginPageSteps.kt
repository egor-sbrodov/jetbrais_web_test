package com.webtests.steps

import com.google.inject.Inject
import com.webtests.page.LoginPage
import com.webtests.pagefactory.PageFactory
import com.webtests.utils.Config
import com.webtests.utils.Config.getAppUrl
import io.qameta.allure.Step

class LoginPageSteps {

    @Inject private lateinit var pageFactory: PageFactory
    @Inject private lateinit var webDriverSteps: WebDriverSteps
    @Inject private lateinit var awaitilitySteps: AwaitilitySteps

    private val loginPostfix = "/login"

    private fun onLoginPage(): LoginPage {
        return pageFactory.on(LoginPage::class)
    }

    @Step("Open login page")
    fun openLoginPage(): LoginPageSteps {
        val loginUrl = "${getAppUrl()}$loginPostfix"
        webDriverSteps.openUrl(loginUrl)
        return this
    }

    @Step("Login via `root` user")
    fun enterRootCredentialAndSubmit() {
        enterCredentialAndSubmit(Config.rootUserName, Config.defaultPassword)
    }

    fun enterCredentialAndSubmit(login: String, password: String) {
        enterLogin(login)
        enterPassword(password)
        submit()
    }

    @Step("Enter user login `{login}`")
    private fun enterLogin(login: String): LoginPageSteps {
        awaitilitySteps.waitUntilDisplayed(onLoginPage().loginOrEmailInput()).sendKeys(login)
        return this
    }

    @Step("Enter user password `{password}`")
    private fun enterPassword(password: String): LoginPageSteps {
        awaitilitySteps.waitUntilDisplayed(onLoginPage().passwordInput()).sendKeys(password)
        return this
    }

    @Step("Submit login form via button login")
    private fun submit() {
        awaitilitySteps.waitUntilDisplayed(onLoginPage().buttonLogin()).click()
    }

}
