package fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FilterFragment {
    ElementsCollection priceFilters = $$(".el-input__inner");
    SelenideElement priceTo = $("div[title='Цена до: 9000']");
    SelenideElement materialCheckbox = $("a[href$='materialy-korpusa_plastik/']");
    SelenideElement asideFilter = $(".icon-profil");


    public FilterFragment fillInPriceFilters(int inputOrder, String price) {
        priceFilters.get(inputOrder).clear();
        priceFilters.get(inputOrder).val(price).pressEnter();
        return this;
    }
    public SelenideElement getPriceTo() {
        return priceTo;
    }

    public FilterFragment clickRamCheckbox(String ramSize) {
        $("a[href$='" + ramSize + "-gb/']").click();
        return this;
    }

    public FilterFragment clickMaterialCheckbox() {
        materialCheckbox.click();
        return this;
    }

    public FilterFragment clickAsideFilter() {
        asideFilter.contextClick();
        return this;
    }

}
