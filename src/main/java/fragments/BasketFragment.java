package fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BasketFragment {

    ElementsCollection basketPrice = $$("span.ctrs-main-price");
    SelenideElement basketVidget = $x("//div[@class='el-dialog el-dialog--medium']");
    ElementsCollection prodNamesFromBasket = $$("a[class$='ctrs-basket-product__name']");
    SelenideElement basketButton = $("icon-new-citrus-cart");
    ElementsCollection prodToBasketByOrder = $$("i[class='icon-new-citrus-cart el-tooltip item']");
    SelenideElement basketIcon = $x("//div[2]/div/div[1]/button[2]");
    SelenideElement basketTotalPrice = $x("//span[@class='ctrs-main-price ctrs-basket-footer__new-price']");
    ElementsCollection deleteIcon = $$("div.ctrs-basket-item__delete");
    SelenideElement deleteApprove = $x("//button[@class='ctrs-basket-item__delete-button'][contains(text(),'Да')]");

    public SelenideElement getBasket() {
        return basketVidget;
    }

    public ElementsCollection getProductNamesFromBasket() {
        return prodNamesFromBasket;
    }

    public SelenideElement getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public BasketFragment clickBasketButton() {
        basketButton.click();
        return this;
    }

    public BasketFragment addProdToBasketByOrder(int productOrder) {
        prodToBasketByOrder.get(productOrder).click();
        return this;
    }

    public BasketFragment closeBasket() {
        basketIcon.click();
        return this;
    }

    public BasketFragment deleteProdFromBasket() {

        for (int counter = 0; counter < deleteIcon.size(); counter++) {
            deleteIcon.get(0).shouldBe(Condition.visible).hover().click();
            deleteApprove.shouldBe(Condition.visible).click();
        }
        return this;
    }

    public ElementsCollection getBasketPrice() {
        return basketPrice;
    }

    public String getTotalFromPricesInBasket(String firstPrice, String secondPrice) {
        int intTotal = Integer.parseInt(firstPrice.replaceAll("[^\\d.]", "")) + Integer.parseInt(secondPrice.replaceAll("[^\\d.]", ""));
        StringBuffer sb = new StringBuffer(String.valueOf(intTotal));
        String total = String.valueOf(sb.insert(sb.length() - 3, " "));
        return total;
    }

}
