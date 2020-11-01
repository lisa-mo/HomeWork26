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

public class FilterTest extends BaseTest {

    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparePage comparePage;

    String menuText = "Смартфоны";
    String linkTextSamsung = "Samsung";
    String linkTextXiaomi = "Xiaomi";
    String linkTextZTE = "ZTE";
    String minPriceFilter = "5000";
    String maxPriceFilter = "9000";
    String firstRAM = "32";
    String secondRAM = "64";
    String material = "Пластик";

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

//        1.Use price filter
    @Test
    public void filterProdByPrice() throws Exception {
//        homePage.changeLang();

        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuText)
                .clickLinkInMenu(linkTextSamsung);

        productListPage.getFilterFragment().fillInPriceFilters(0, minPriceFilter);
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.getFilterFragment().fillInPriceFilters(1, maxPriceFilter);
        productListPage.getFilterFragment().clickAsideFilter();
        productListPage.waitForPageToLoad()
                .closePopUp();
        productListPage.getFilterFragment().getPriceTo().shouldBe(Condition.visible);

        int prodListSize = productListPage.waitForPageToLoad()
                .getProdNamesFromFiltersPage().size();
        checkForOneStrContains(prodListSize, productListPage.getProdNamesFromFiltersPage(), linkTextSamsung);

        for (int priceCounter = 0; priceCounter < prodListSize; priceCounter++) {
            String strPrice = productListPage.getProdPricesFilterPage().get(priceCounter).getText().replaceAll("[^\\d.]", "");
            int intPrice = Integer.parseInt(strPrice);
            System.out.println(intPrice);
            if (intPrice < Integer.parseInt(minPriceFilter) || intPrice > Integer.parseInt(maxPriceFilter)) {
                throw new Exception("Wrong price value.");
            }
        }
    }

//        2.Use memory size filter
    @Test
    public void filterProdByRAM() throws Exception {
//        homePage.changeLang();

        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuText)
                .clickLinkInMenu(linkTextXiaomi);

        productListPage.waitForPageToLoad()
                .closePopUp()
                .getFilterFragment()
                .clickRamCheckbox(firstRAM);
        productListPage.waitForPageToLoad()
                .closePopUp()
                .getFilterFragment()
                .clickRamCheckbox(secondRAM);
        productListPage.waitForPageToLoad()
                .closePopUp();

//        productListPage.waitForPopUpAndCloseIt();
        int prodListSize = productListPage.waitForPageToLoad()
                .getProdNamesFromFiltersPage().size();
        productListPage.getProdNamesFromFiltersPage().get(prodListSize - 1).shouldBe(Condition.visible);
//Here will be a bug because of: Element: '<span>Poco X3 6/64Gb (Shadow Gray)</span>'
//        checkForOneStrContains(prodListSize, productListPage.getProdNamesFromFiltersPage(), linkTextXiaomi);

        checkForTwoStrContains(prodListSize, productListPage.getProdNamesFromFiltersPage(), firstRAM, secondRAM);
    }

    //        3.Use body material filter
    @Test
    public void filterProdByMaterial() {
//        homePage.changeLang();

        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuText)
                .clickLinkInMenu(linkTextZTE);

        productListPage.waitForPageToLoad()
                .closePopUp()
                .getFilterFragment()
                .clickMaterialCheckbox();
        productListPage.waitForPageToLoad()
                .closePopUp();

        productListPage.waitForPopUpAndCloseIt();
        int prodListSize = productListPage.waitForPageToLoad()
                .getProdNamesFromFiltersPage().size();
        productListPage.getProdNamesFromFiltersPage().get(prodListSize - 1).shouldBe(Condition.visible);

        checkForOneStrContains(prodListSize, productListPage.getProdNamesFromFiltersPage(), linkTextZTE);

        for (int materialCounter = 0; materialCounter < prodListSize; materialCounter++) {
            productListPage.hoverProdView(materialCounter);
            productListPage.getProdMaterials().get(materialCounter).shouldHave(Condition.text(material));
            System.out.println(productListPage.getProdMaterials().get(materialCounter));
        }
    }
}
