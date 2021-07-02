package com.webtests

import com.google.inject.Inject
import com.webtests.element.DropdownMenu
import com.webtests.extension.WebTestsExtension
import com.webtests.steps.LoginPageSteps
import com.webtests.steps.MainPageSteps
import com.webtests.steps.UserCreationDialogSteps
import com.webtests.steps.UsersPanelSteps
import com.webtests.utils.*
import com.webtests.utils.Config.defaultPassword
import com.webtests.utils.Config.rootUserName
import io.qameta.allure.Epic
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@WebTestsExtension
@Epic("user_creation_test")
class UserCreationTest {

    @Inject private lateinit var loginPageSteps: LoginPageSteps
    @Inject private lateinit var mainPageSteps: MainPageSteps
    @Inject private lateinit var usersPanelSteps: UsersPanelSteps
    @Inject private lateinit var userCreationDialogSteps: UserCreationDialogSteps

    @Test
    fun minimumUserCreationTest() {
        val userLogin = generateUserLogin()

        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(userLogin)
        usersPanelSteps
            .waitCreationDialogIsAbsent()
            .checkEditUserPanelOfUserOpened(userLogin)
        loginPageSteps
            .openLoginPage()
            .enterCredentialAndSubmit(userLogin, defaultPassword)
        mainPageSteps
            .waitPageOpenedForUser(userLogin)
    }

    @Test
    fun createUserWithoutLogin() {
        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser("")
        userCreationDialogSteps
            .checkLoginInputHasError()
            .checkErrorIconTooltipMessage("Login is required!")
    }

    @Test
    fun createUserWithoutPassword() {
        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(generateUserLogin(), password = "")
        userCreationDialogSteps
            .checkPasswordInputHasError()
            .checkErrorIconTooltipMessage("Password is required!")
    }

    @ParameterizedTest
    @MethodSource("getFullName")
    fun createUserWithFullName() {
        val userLogin = generateUserLogin()
        val fullName = generateUserFullName()
        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(userLogin, fullName = fullName)
        usersPanelSteps
            .waitCreationDialogIsAbsent()
        mainPageSteps
            .openAdminPanel(DropdownMenu.AdminPanelMenuItem.Users)
        usersPanelSteps
            .checkUserFullNameIs(userLogin, fullName)
    }

    @Test
    fun notMatchPasswords() {
        loginAndClickCreateUser()
        userCreationDialogSteps
            .fillUserLogin(generateUserLogin())
            .fillPassword(defaultPassword)
            .fillPasswordConfirmation("password_")
            .clickButtonOk()
            .checkPasswordConformationInputHasError()
            .checkErrorIconTooltipMessage("Password doesn't match!")
    }

    @Test
    fun createUserWithRootLogin() {
        loginAndClickCreateUser()
        userCreationDialogSteps
            .fillUserLogin(rootUserName)
            .fillUserPasswordAndConfirmOne(defaultPassword)
            .clickButtonOk()
        mainPageSteps
            .checkSystemMessage("Removing null is prohibited", true)
    }

    @Test
    fun createUserWithExistingLogin() {
        val userLogin = generateUserLogin()

        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(userLogin)
        usersPanelSteps
            .waitCreationDialogIsAbsent()
        mainPageSteps
            .openAdminPanel(DropdownMenu.AdminPanelMenuItem.Users)
        usersPanelSteps
            .clickCreateUser()
        userCreationDialogSteps
            .createUser(userLogin)
        mainPageSteps
            .checkSystemMessage("Value should be unique: login", true)
    }

    @Test
    fun createUserWithSpaceInLogin() {
        loginAndClickCreateUser()

        userCreationDialogSteps
            .createUser("incorrect ${generateName()}")
            .clickButtonOk()
        mainPageSteps
            .checkSystemMessage("Restricted character ' ' in the name", true)
    }


    @ParameterizedTest
    @MethodSource("getIncorrectLoginCharacters")
    fun createUserWithIncorrectLoginSymbols(incorrectSymbol: String) {
        val incorrectLogin = generateUserLogin().replaceFirst("_", incorrectSymbol)

        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(incorrectLogin, defaultPassword)
        mainPageSteps
            .checkSystemMessage("""login shouldn't contain characters "<", "/", ">": login""", true)
    }

    @Test
    fun createUserWithForceChangePassword() {
        val userLogin = generateUserLogin()

        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(userLogin, isForceChangePassword = true)
        usersPanelSteps
            .waitCreationDialogIsAbsent()
        loginPageSteps
            .openLoginPage()
            .enterCredentialAndSubmit(userLogin, defaultPassword)
        mainPageSteps
            .checkUserHasBeenAskedToChangePassword()
    }

    @Test
    fun submitUserCreationViaEnter() {
        loginAndClickCreateUser()

        userCreationDialogSteps
            .fillUserLogin(generateUserLogin())
            .fillUserPasswordAndConfirmOne("password")
            .submitFormViaEnterKey()
    }

    @Test
    fun closeViaCancel() {
        loginAndClickCreateUser()
        userCreationDialogSteps
            .clickCancel()
        usersPanelSteps
            .waitCreationDialogIsAbsent()
    }

    @Test
    fun closeViaCross() {
        loginAndClickCreateUser()
        userCreationDialogSteps
            .clickOnCross()
        usersPanelSteps
            .waitCreationDialogIsAbsent()
    }

    @Test
    fun createUserWithEmailAndLogin() {
        val userLogin = generateUserLogin()
        val userEmail = generateEmail()

        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(userLogin, email = userEmail)
        usersPanelSteps
            .waitCreationDialogIsAbsent()
        mainPageSteps
            .openAdminPanel(DropdownMenu.AdminPanelMenuItem.Users)
        usersPanelSteps
            .checkUserIsInUsersTable(userLogin)
            .checkUserEmailIs(userLogin, userEmail)
        loginPageSteps
            .openLoginPage()
            .enterCredentialAndSubmit(userEmail, defaultPassword)
        mainPageSteps
            .waitPageOpenedForUser(userLogin)
    }

    @Test
    fun createUserWithJabber() {
        val userLogin = generateUserLogin()
        val jabber = generateJabber()

        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(userLogin, jabber = jabber)
        usersPanelSteps
            .waitCreationDialogIsAbsent()
        mainPageSteps
            .openAdminPanel(DropdownMenu.AdminPanelMenuItem.Users)
        usersPanelSteps
            .checkUserActualJabberIs(userLogin, jabber)
    }

    @Test
    fun userWithLongLogin() {
        val maxLength = 50
        val extraLongLogin = generateLongUserName(51)

        loginAndClickCreateUser()
        userCreationDialogSteps
            .fillUserLogin(extraLongLogin)
            .checkLoginInputIs(extraLongLogin.substring(0, maxLength))
    }

    @Test
    fun userWithLongFullName() {
        val extraLongFullName = generateLongUserName(51)

        loginAndClickCreateUser()
        userCreationDialogSteps
            .fillFullName(extraLongFullName)
            .checkFullNameInputIs(extraLongFullName.substring(0, maxLength))
    }

    @Test
    @Disabled("Bug. Email could be incorrect")
    fun createUserWithIncorrectEmail() {
        val userLogin = generateUserLogin()
        val incorrectUserEmail = generateName()

        loginAndClickCreateUser()
        userCreationDialogSteps
            .createUser(userLogin, email = incorrectUserEmail)
        usersPanelSteps
            .checkEditUserPanelOfUserOpened(userLogin)
        mainPageSteps
            .checkSystemMessage("Value is incorrect: email", true)
    }

    @Test
    @Disabled("Bug. User could be created with non-breaking space")
    fun userWithNonBreakingSpaceInLogin() {
        loginAndClickCreateUser()

        userCreationDialogSteps
            .createUser(generateUserLogin().replace("_", ' '.toString()))
            .checkLoginInputHasError()
            .checkErrorIconTooltipMessage("Login is required!")
    }

    private fun loginAndClickCreateUser() {
        loginPageSteps
            .openLoginPage()
            .enterRootCredentialAndSubmit()
        mainPageSteps
            .openAdminPanel(DropdownMenu.AdminPanelMenuItem.Users)
        usersPanelSteps
            .clickCreateUser()
    }

    @Suppress("UNUSED")
    companion object {
        const val maxLength = 50

        @JvmStatic
        fun getIncorrectLoginCharacters(): List<String> {
            return listOf("<", ">", "/")
        }

        @JvmStatic
        fun getFullName(): List<String> {
            return listOf(
                generateUserFullName(),
                getCyrillicString() + " " + getCyrillicString(),
                getHieroglyphString() + " " + getHieroglyphString()
            )
        }
    }

}
