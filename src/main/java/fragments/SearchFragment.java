package fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SearchFragment {

    SelenideElement searchInput = $("#search-input");
//    SelenideElement scrollToFooter = $("img[alt=foot-master]");

    public SearchFragment searchProduct(String productName) {
        searchInput.val(productName).pressEnter();
        return this;
    }

    public SearchFragment clearSearch() {
        searchInput.hover().pressEscape();
//        scrollToFooter.scrollTo();
//        searchInput.scrollTo();
        return this;
    }
}
