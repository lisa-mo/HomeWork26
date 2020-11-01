package citrus.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import fragments.BasketFragment;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ComparePage extends BasePage {
    BasketFragment basketFragment = new BasketFragment();

    ElementsCollection productNamesFromComparePage = $$("div[class=title-itm]>h5");
    ElementsCollection prodAmountOnComparePage = $$("div.relative");
    ElementsCollection prodPricesFromComparePage = $$("div.base-price>span");
    SelenideElement addMoreToCompare = $(".add-comparison");
    SelenideElement moreProdVidget = $("div[class='el-dialog el-dialog--tiny']");
    ElementsCollection toAddAnotherProdToCompare = $$(".product-name");
    ElementsCollection nameOfAnotherProdToCompare = $$(".product-name");
    ElementsCollection anotherProdPricesToCompare = $$("div > span.price-new > span.price-number");
    SelenideElement addButton = $("button[class='el-button el-button--primary']");

    public ElementsCollection getProdNamesFromComparePage() {
        return productNamesFromComparePage;
    }

    public ElementsCollection checkProdAmountOnComparePage() {
        return prodAmountOnComparePage;
    }

    public ElementsCollection getProdPricesFromComparePage() {
        return prodPricesFromComparePage;
    }

    public ComparePage ckickAddMoreToCompare() {
        addMoreToCompare.click();
        return this;
    }

    public SelenideElement getMoreProd() {
        return moreProdVidget;
    }

    public ComparePage clickToAddAnotherProdToCompare(int prodOrder) {
        toAddAnotherProdToCompare.get(prodOrder).click();
        return this;
    }

    public ElementsCollection getNamesOfAnotherProdToCompare() {
        return nameOfAnotherProdToCompare;
    }

    public ElementsCollection getAnotherProdPricesToCompare() {
        return anotherProdPricesToCompare;
    }

    public ComparePage clickAddButton() {
        addButton.click();
        return this;
    }

    public ComparePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

}
