package com.webtests.element

import com.webtests.YouTrackElement
import com.webtests.steps.UsersPanelSteps.ColumnHeaderItem.Login
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebElement
import java.lang.String.format

class UsersPanel(self: WebElement) : YouTrackElement<UsersPanel>(self) {

    private val buttonCreateUserXPath = xpath(".//*[text()='Create user']")
    val userCreationDialogXpath = xpath("//*[contains(@class, 'createUserDlg') and .//*[text()='Create User']]")

    fun buttonCreateUser(): WebElement {
        return findElement(buttonCreateUserXPath)
    }

    fun userCreationDialog(): UserCreationDialog {
        return UserCreationDialog(findElement(userCreationDialogXpath))
    }

    fun editUserPanelOf(userLogin: String): EditUserPanel {
        val editUserMenuXPath = xpath(
            """.//*[contains(@id, 'editUserPanel') 
            and .//*[contains(@class,'breadcrumb-item') and contains(text(), '$userLogin')]]"""
        )
        return EditUserPanel(findElement(editUserMenuXPath))
    }

    fun userTableRow(userLogin: String): UserTableRow {
        val columnIndexXPathString = format(userDataXPathFormat, Login)
        val userTableRowXPath = xpath(".//*[contains(@class, 'users-table')]//tr[$columnIndexXPathString and .//a[text()='$userLogin']]")
        return UserTableRow(findElement(userTableRowXPath))
    }

    companion object {
        const val userDataXPathFormat = ".//td[count(//tr/th[contains(text(),'%s')]/preceding-sibling::th) + 1]"
    }

}
