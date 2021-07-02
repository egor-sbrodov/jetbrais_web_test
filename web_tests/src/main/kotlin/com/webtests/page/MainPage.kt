package com.webtests.page

import com.webtests.element.ChangePasswordDialog
import com.webtests.element.HeaderPanel
import com.webtests.element.TopMessage
import com.webtests.element.UsersPanel
import com.webtests.pagefactory.Page
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebDriver

class MainPage(driver: WebDriver) : Page(driver) {

    private val headerPanelXPath = xpath("//*[@class='header']")
    private val usersTableXPath = xpath("//*[contains(@class, 'admin-center') and .//*[text()='Users']]")
    private val topMessageXPath = xpath("//*[contains(@class, 'message') and not(contains(@class, 'head-message')) and contains(@id, 'popup')]")
    private val changePasswordDialogXPath = xpath("//*[contains(@class, 'dialog') and .//*[text()='Change password']]")

    fun headerPanel(): HeaderPanel {
        return HeaderPanel(waitDisplayedAndGetElement(headerPanelXPath))
    }

    fun usersPanel(): UsersPanel {
        return UsersPanel(waitDisplayedAndGetElement(usersTableXPath))
    }

    fun systemMessage(): TopMessage {
        return TopMessage(waitDisplayedAndGetElement(topMessageXPath))
    }

    fun passwordChangeDialog(): ChangePasswordDialog {
        return ChangePasswordDialog(waitDisplayedAndGetElement(changePasswordDialogXPath))
    }

}