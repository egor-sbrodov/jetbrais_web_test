package com.webtests.steps

import com.google.inject.Inject
import com.webtests.element.DropdownMenu.AdminPanelMenuItem
import com.webtests.element.HeaderPanel
import com.webtests.page.MainPage
import com.webtests.pagefactory.PageFactory
import io.qameta.allure.Step

class MainPageSteps {

    @Inject lateinit var pageFactory: PageFactory
    @Inject lateinit var awaitilitySteps: AwaitilitySteps

    private fun onMainPage(): MainPage {
        return pageFactory.on(MainPage::class)
    }

    private fun onHeaderPanel(): HeaderPanel {
        return onMainPage().headerPanel()
    }

    @Step("Wait page opening")
    fun waitPageOpenedForUser(expectedUser: String) {
        awaitilitySteps.waitHasText(onHeaderPanel().userDropDown(), expectedUser)
    }

    @Step("Open menu `{item}`")
    fun openAdminPanel(item: AdminPanelMenuItem): MainPageSteps {
        awaitilitySteps.waitUntilDisplayed(onHeaderPanel().cogWheel()).click()
        awaitilitySteps.waitUntilDisplayed(onHeaderPanel().dropdownMenu().itemWithText(item)).click()
        return this
    }

    @Step("Check service message has text: `messageToCheck`")
    fun checkSystemMessage(messageToCheck: String, isError: Boolean = false): MainPageSteps {
        awaitilitySteps.waitHasText(onMainPage().systemMessage().message(isError), messageToCheck)
        if (isError) {
            awaitilitySteps.waitHasClass(onMainPage().systemMessage(), "error")
        }
        return this
    }

    @Step("Check password change dialog is displayed")
    fun checkUserHasBeenAskedToChangePassword(): MainPageSteps {
        checkSystemMessage("Please change your password!")
        awaitilitySteps.waitUntilDisplayed(onMainPage().passwordChangeDialog())
        return this
    }

}
