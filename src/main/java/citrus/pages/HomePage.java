package citrus.pages;

import com.codeborne.selenide.SelenideElement;
import fragments.SearchFragment;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class HomePage extends BasePage {

    SearchFragment searchFragment = new SearchFragment();
    SelenideElement langIcon = $("a[href='/']");

    public HomePage changeLang() {
        langIcon.hover().click();
        return this;
    }

    public HomePage hoverMenuLine(String menuText) {
        $$x("//span[@class='title'][contains(text(),'" + menuText + "')]").get(1).hover();
        return this;
    }

    public HomePage clickLinkInMenu(String linkText) {
//        $x("//a[@href='/smartfony/brand-apple/']/span[contains(text(),'" + linkText + "')]").click();
        $("a[title=" + linkText + "]").click();
        return this;
    }

    public HomePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public HomePage closePopUp() {
        super.closePopUp();
        return this;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

}
