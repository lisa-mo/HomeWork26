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

//Comparison
//        1.Compare 2+1 products
//        1) Click Notebooks/Acer in menu
//        2) Add first and second laptop to comparison (no navigation to product page). Remember names, prices
//        3) Click on comparison icon in header
//        4) Verify
//        - only 2 products in comparison
//        - prices and names of products are correct
//        5) Click 'add new product to comparison'
//        6) Choose first (remember name, price)
//        7) Verify
//        - only 3 products in comparison
//        - prices and names of products are correct

public class CompareTest extends BaseTest{
    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparePage comparePage;

    String menuText = "Ноутбуки, планшеты, МФУ";
    String linkTextAcer = "Acer";

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
    public void compareProducts() throws Exception {
//        homePage.changeLang();

        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuText)
                .clickLinkInMenu(linkTextAcer);

        productListPage.waitForPageToLoad()
                .closePopUp();

        String strFirstProductPrice = productListPage.waitForPageToLoad()
                .closePopUp()
                .getProdPricesFilterPage().get(0).getText();
        String strSecondProductPrice = productListPage.getProdPricesFilterPage().get(1).getText();
        String firstProductName = productListPage.getProdNamesFromFiltersPage().get(0).getText().replace(" ...", "");
        String secondProductName = productListPage.getProdNamesFromFiltersPage().get(1).getText();

        productListPage.addProdToCompareByOrderOnFilter(0);
        productListPage.addProdToCompareByOrderOnFilter(1);
        productListPage.clickCompareButton()
                .waitForPageToLoad();

        comparePage.waitForPopUpAndCloseIt();
        comparePage.checkProdAmountOnComparePage().shouldHaveSize(2);
        int prodListSizeOne = comparePage.waitForPageToLoad()
                .getProdNamesFromComparePage().size();
        checkForTwoStrContains(prodListSizeOne, comparePage.getProdNamesFromComparePage(), firstProductName, secondProductName);
        checkForTwoStrContains(prodListSizeOne, comparePage.getProdPricesFromComparePage(), strFirstProductPrice, strSecondProductPrice);

        comparePage.ckickAddMoreToCompare();
        comparePage.getMoreProd().shouldBe(Condition.visible);
        comparePage.clickToAddAnotherProdToCompare(0);
        String newNameToCompare = comparePage.getNamesOfAnotherProdToCompare().get(0).getText();
        String newPriceToCompare = comparePage.getAnotherProdPricesToCompare().get(0).getText();
        comparePage.clickAddButton()
                .waitForPageToLoad()
                .closePopUp();

        comparePage.checkProdAmountOnComparePage().shouldHaveSize(3);
        int prodListSizeTwo = comparePage.waitForPageToLoad()
                .getProdNamesFromComparePage().size();
        checkForThreeStrContains(prodListSizeTwo, comparePage.getProdNamesFromComparePage(), firstProductName, secondProductName, newNameToCompare);
        checkForThreeStrContains(prodListSizeTwo, comparePage.getProdPricesFromComparePage(), strFirstProductPrice, strSecondProductPrice, newPriceToCompare);

    }
}
