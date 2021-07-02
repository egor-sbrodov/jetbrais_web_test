package com.webtests.element

import com.webtests.YouTrackElement
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebElement

class TopMessage(self: WebElement) : YouTrackElement<TopMessage>(self) {

    fun message(isError: Boolean): WebElement {
        return findElement(xpath(".//li${if (isError) "[contains(@class, 'errorSeverity')]" else ""}"))
    }

}
