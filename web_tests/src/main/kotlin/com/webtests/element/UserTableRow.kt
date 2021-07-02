package com.webtests.element

import com.webtests.YouTrackElement
import com.webtests.element.UsersPanel.Companion.userDataXPathFormat
import com.webtests.steps.UsersPanelSteps.ColumnHeaderItem.*
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebElement
import java.lang.String.format

class UserTableRow(self: WebElement) : YouTrackElement<UserTableRow>(self) {

    fun login(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, Login.title))
        return findElement(xpath)
    }

    fun fullName(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, FullName.title))
        return findElement(xpath)
    }

    fun email(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, EmailAndJabber.title) + "/*[1]")
        return findElement(xpath)
    }

    fun jabber(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, EmailAndJabber.title) + "/*[2]")
        return findElement(xpath)
    }

    fun groups(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, Groups.title))
        return findElement(xpath)
    }

    fun lastAccess(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, LastAccess.title))
        return findElement(xpath)
    }

    fun actionDelete(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, Actions.title) + "//*[contains(text(), 'Delete')]")
        return findElement(xpath)
    }

    fun actionMerge(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, Actions.title) + "//*[contains(text(), 'Merge')]")
        return findElement(xpath)
    }

    fun actionBan(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, Actions) + "//*[contains(text(), 'Ban')]")
        return findElement(xpath)
    }

    fun actionUnban(): WebElement {
        val xpath = xpath(format(userDataXPathFormat, Actions) + "//*[contains(text(), 'Unban')]")
        return findElement(xpath)
    }

}
