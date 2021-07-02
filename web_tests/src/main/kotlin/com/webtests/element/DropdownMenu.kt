package com.webtests.element

import com.webtests.YouTrackElement
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebElement

class DropdownMenu(self: WebElement) : YouTrackElement<DropdownMenu>(self) {

    fun itemWithText(menuItem: AdminPanelMenuItem): WebElement {
        xpath(".//*[contains(@class, 'ring-dropdown__item') and text()='${menuItem.name}']").let {
            return findElement(it)
        }
    }

    enum class AdminPanelMenuItem() {
        Projects,
        Users,
        Groups,
        More,
    }

}
