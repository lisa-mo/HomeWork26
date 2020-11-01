package citrus;

import citrus.pages.ComparePage;
import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CitrusBasketTest {
    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparePage comparePage;
    String productName = "Apple iPhone 11 128Gb Black";
    String menuText = "Смартфоны";
    String linkText = "Apple";
    String brandForSearch = "Apple iPhone";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.timeout = 20000;
        Configuration.startMaximized = true;
        open("");
        homePage = new HomePage();
        productListPage = new ProductListPage();
        productPage = new ProductPage();
        comparePage = new ComparePage();
    }

    @Test
    public void addProdViaMenuToBasket() {
//        homePage.changeLang();

        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuText)
                .clickLinkInMenu(linkText);
        productListPage.waitForPageToLoad()
                .closePopUp()
                .clickOnProductByName(productName);
        String productPrice = productPage.getProductPrice();
        productPage.clickBuyButton();

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketPrice().get(1).shouldHave(Condition.text(productPrice));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));

        productPage.getBasketFragment().deleteProdFromBasket()
                .closeBasket();
    }

    @Test
    public void addProdViaSearchToBasket() {
//        homePage.changeLang();

        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .searchProduct(productName);
        String productPrice = productListPage.waitForPageToLoad()
                .closePopUp()
                .getProductPriceByName(productName);
        productListPage
                .addProductToBasket(productName);

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketPrice().get(1).shouldHave(Condition.text(productPrice));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));

        productPage.getBasketFragment().deleteProdFromBasket()
                .closeBasket();
    }

    @Test
    public void addTwoProdToBasketViaSearch() {
//        homePage.changeLang();

        homePage.waitForPageToLoad()
                .closePopUp();
        homePage.getSearchFragment()
                .clearSearch()
                .searchProduct(brandForSearch);
        String strFirstProductPrice = productListPage.waitForPageToLoad()
                .closePopUp()
                .getProductPricesFromProdPage().get(0).getText();
        String strSecondProductPrice = productListPage.getProductPricesFromProdPage().get(1).getText();
        String firstProductName = productListPage.getProductNamesFromProdPage().get(0).getText();
        String secondProductName = productListPage.getProductNamesFromProdPage().get(1).getText();

        productListPage.getBasketFragment().addProdToBasketByOrder(0);
        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().closeBasket();
        productListPage.getBasketFragment().addProdToBasketByOrder(1);

        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(2);

        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(secondProductName));
        productListPage.getBasketFragment().getBasketPrice().get(1).shouldHave(Condition.text(strFirstProductPrice));
        productListPage.getBasketFragment().getBasketPrice().get(2).shouldHave(Condition.text(strSecondProductPrice));

        productListPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productListPage
                .getBasketFragment()
                .getTotalFromPricesInBasket(strFirstProductPrice, strSecondProductPrice)));

        productListPage.getBasketFragment().deleteProdFromBasket()
                .closeBasket();
    }

    @Test
    public void addTwoProdToBasketViaCompare() {
        //        homePage.changeLang();

        homePage.waitForPageToLoad()
                .closePopUp()
                .getSearchFragment()
                .clearSearch()
                .searchProduct(brandForSearch);
        String strFirstProductPrice = productListPage.waitForPageToLoad()
                .closePopUp()
                .getProductPricesFromProdPage().get(0).getText();
        String strSecondProductPrice = productListPage.getProductPricesFromProdPage().get(1).getText();
        String firstProductName = productListPage.getProductNamesFromProdPage().get(0).getText();
        String secondProductName = productListPage.getProductNamesFromProdPage().get(1).getText();

        productListPage.addProdToCompareByOrder(0);
        productListPage.addProdToCompareByOrder(1);
        productListPage.clickCompareButton();
        comparePage.getBasketFragment().addProdToBasketByOrder(0);
        comparePage.getBasketFragment().closeBasket();
        comparePage.getBasketFragment().addProdToBasketByOrder(2);

        productListPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productListPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(2);

        productListPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getBasketFragment().getProductNamesFromBasket().get(1).shouldHave(Condition.text(secondProductName));
        productListPage.getBasketFragment().getBasketPrice().get(1).shouldHave(Condition.text(strFirstProductPrice));
        productListPage.getBasketFragment().getBasketPrice().get(2).shouldHave(Condition.text(strSecondProductPrice));

        productListPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productListPage
                .getBasketFragment()
                .getTotalFromPricesInBasket(strFirstProductPrice, strSecondProductPrice)));

        productListPage.getBasketFragment().deleteProdFromBasket()
                .closeBasket();
    }
}
