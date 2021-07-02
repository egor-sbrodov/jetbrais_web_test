package com.webtests.element

import com.webtests.YouTrackElement
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebElement

class HeaderPanel(self: WebElement) : YouTrackElement<HeaderPanel>(self) {

    private val cogWheelXPath = ".//a[.//*[contains(@class, 'ring-font-icon_cog')]]"
    private val dropdownMenuXPath = "//*[@class='ring-dropdown']"
    private val userDropdownXPath = ".//*[contains(@class, 'user')]"

    fun cogWheel(): WebElement {
        return findElement(xpath(cogWheelXPath))
    }

    fun dropdownMenu(): DropdownMenu {
        return DropdownMenu(findElement(xpath(dropdownMenuXPath)))
    }

    fun userDropDown(): WebElement {
        return findElement(xpath(userDropdownXPath))
    }


}
