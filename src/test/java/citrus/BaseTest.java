package citrus;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class BaseTest {

    public void checkForOneStrContains(int listSize, ElementsCollection getList, String sampleText) {
        for (int counter = 0; counter < listSize; counter++) {
            getList.get(counter).shouldHave(Condition.text(sampleText));
            System.out.println(getList.get(counter));
        }
    }

    public void checkForTwoStrContains(int listSize, ElementsCollection getList, String sampleTextOne, String sampleTextTwo) throws Exception {
        for (int counter = 0; counter < listSize; counter++) {
            System.out.println(getList.get(counter));
            if (!getList.get(counter).getText().contains(sampleTextOne)) {
                if (!getList.get(counter).getText().contains(sampleTextTwo)) {
                    throw new Exception("There are no products with " + sampleTextOne + " or " + sampleTextTwo + " on the page.");
                }
            }
        }
    }

    public void checkForThreeStrContains(int listSize, ElementsCollection getList, String sampleTextOne, String sampleTextTwo, String sampleTextTree) throws Exception {
        for (int counter = 0; counter < listSize; counter++) {
            System.out.println(getList.get(counter));
            if (!getList.get(counter).getText().contains(sampleTextOne)) {
                if (!getList.get(counter).getText().contains(sampleTextTwo)) {
                    if (!getList.get(counter).getText().contains(sampleTextTree)) {
                        throw new Exception("There are no products with " + sampleTextOne + ", " + sampleTextTwo + " or " + sampleTextTree + " on the page.");
                    }
                }
            }
        }
    }
}
