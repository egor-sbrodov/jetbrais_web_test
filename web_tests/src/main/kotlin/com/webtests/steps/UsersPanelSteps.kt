package com.webtests.steps

import com.google.inject.Inject
import com.webtests.element.UsersPanel
import com.webtests.page.MainPage
import com.webtests.pagefactory.PageFactory
import io.qameta.allure.Step
import kotlin.text.Typography.nbsp

class UsersPanelSteps {

    @Inject lateinit var pageFactory: PageFactory
    @Inject lateinit var awaitilitySteps: AwaitilitySteps

    private fun onUserPanel(): UsersPanel {
        return pageFactory.on(MainPage::class).usersPanel()
    }

    @Step("Open user creation dialog via clicking `Create user`")
    fun clickCreateUser() {
        awaitilitySteps.waitUntilDisplayed(onUserPanel().buttonCreateUser()).click()
        awaitilitySteps.waitUntilDisplayed(onUserPanel().userCreationDialog())
    }

    @Step("Check edit user for `{userLogin}` is opened")
    fun checkEditUserPanelOfUserOpened(userLogin: String): UsersPanelSteps {
        awaitilitySteps.waitUntilDisplayed(onUserPanel().editUserPanelOf(userLogin))
        return this
    }

    @Step("Check user `{userLogin}` is in table ")
    fun checkUserIsInUsersTable(userLogin: String): UsersPanelSteps {
        awaitilitySteps.waitUntilDisplayed(onUserPanel().userTableRow(userLogin))
        return this
    }

    @Step("Check user `{userLogin}` has a full name `{expectedFullName}`")
    fun checkUserFullNameIs(userLogin: String, expectedFullName: String): UsersPanelSteps {
        val fullNameInputElement = awaitilitySteps.waitUntilDisplayed(onUserPanel().userTableRow(userLogin).fullName())
        awaitilitySteps.waitHasText(fullNameInputElement, expectedFullName)
        return this
    }

    @Step("Check user `{userLogin}` has an email `{expectedEmail}`")
    fun checkUserEmailIs(userLogin: String, expectedEmail: String): UsersPanelSteps {
        val emailInputElement = awaitilitySteps.waitUntilDisplayed(onUserPanel().userTableRow(userLogin).email())
        awaitilitySteps.waitHasText(emailInputElement, expectedEmail)
        return this
    }

    @Step("Check user `{userLogin}` has a jabber `{expectedJabber}`")
    fun checkUserActualJabberIs(userLogin: String, expectedJabber: String): UsersPanelSteps {
        awaitilitySteps.waitUntilDisplayed(onUserPanel().wrappedElement)
        awaitilitySteps.waitHasText(onUserPanel().userTableRow(userLogin).jabber(), expectedJabber)
        return this
    }

    @Step("Wait user creation dialog is absent")
    fun waitCreationDialogIsAbsent(): UsersPanelSteps {
        awaitilitySteps.waitUntilAbsent(onUserPanel().userCreationDialogXpath)
        return this
    }

    enum class ColumnHeaderItem(val title: Any) {
        Login("Login"),
        FullName("Full name"),
        EmailAndJabber("Email/Jabber"),
        Groups("Groups"),
        LastAccess("Last access"),
        Actions(nbsp),
    }

}
