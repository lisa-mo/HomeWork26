package citrus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    SelenideElement popUpCloseButton = $("i[class='el-dialog__close el-icon el-icon-close']");
    SelenideElement compareButton = $("i[class='icon-comparison2']");

    public BasePage waitForPageToLoad() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public BasePage closePopUp() {
        if (popUpCloseButton.isDisplayed()) {
            popUpCloseButton.click();
        }
        return this;
    }

    public BasePage waitForPopUpAndCloseIt() {
        popUpCloseButton.waitUntil(Condition.visible, 60000).click();
        return this;
    }

    public BasePage clickCompareButton() {
        compareButton.click();
        return this;
    }

}
