package citrus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import fragments.BasketFragment;
import fragments.FilterFragment;

import static com.codeborne.selenide.Selenide.*;

public class ProductListPage extends BasePage {

    BasketFragment basketFragment = new BasketFragment();

    FilterFragment filterFragment = new FilterFragment();

    ElementsCollection prodNameFromFilterPage = $$(".product-card__name>a>span");
    ElementsCollection prodPricesFilterPage = $$x("//div[@class='prices__price']/span[@class='price']");
    ElementsCollection prodMaterials = $$("ul[class=properties__items]");
    ElementsCollection prodToCompareByOrderOnFilter = $$("button.product-card__to-compare");
    ElementsCollection productPricesFromProdPage = $$("span.price-number");
    ElementsCollection productNamesFromProdPage = $$x("//h5[contains(text(),'Apple')]");
    ElementsCollection prodToCompareByOrder = $$("i[class='icon-comparison2 el-tooltip item']");

    public ProductListPage clickOnProductByName(String productName) {
        $x("//span[contains(text(),'" + productName + "')]").click();
        return this;
    }

    public ProductListPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public ProductListPage closePopUp() {
        super.closePopUp();
        return this;
    }

    public String getProductPriceByName(String productName) {
        return $x("//h5[contains(text(),'" + productName + "')]/../../..//div[@class='base-price']/span").getText();
    }

    public ProductListPage addProductToBasket(String productName) {
        $x("//h5[contains(text(),'" + productName + "')]/../../..//i[@class='icon-new-citrus-cart el-tooltip item']").click();
        return this;
    }

    public ElementsCollection getProductPricesFromProdPage() {
        return productPricesFromProdPage;
    }

    public ElementsCollection getProductNamesFromProdPage() {
        return productNamesFromProdPage;
    }

    public ElementsCollection getProdNamesFromFiltersPage() {
        return prodNameFromFilterPage;
    }

    public ProductListPage addProdToCompareByOrder(int productOrder) {
        prodToCompareByOrder.get(productOrder).click();
        return this;
    }

    public ProductListPage clickCompareButton() {
        super.clickCompareButton();
        return this;
    }

    public ProductListPage waitForPopUpAndCloseIt() {
        super.waitForPopUpAndCloseIt();
        return this;
    }

    public ElementsCollection getProdPricesFilterPage() {
        return prodPricesFilterPage;
    }

    public ProductListPage hoverProdView(int materialListSize) {
        prodNameFromFilterPage.get(materialListSize).hover();
        return this;
    }

    public ElementsCollection getProdMaterials() {
        return prodMaterials;
    }

    public ProductListPage addProdToCompareByOrderOnFilter(int productOrder) {
        prodToCompareByOrderOnFilter.get(productOrder).hover().click();
        return this;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public FilterFragment getFilterFragment() {
        return filterFragment;
    }

}
