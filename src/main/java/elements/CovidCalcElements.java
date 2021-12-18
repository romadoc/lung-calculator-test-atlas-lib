package elements;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Selector;

public interface CovidCalcElements extends AtlasWebElement {
    // Tomography is off section
    @FindBy(value = "input1", selector = Selector.ID)
    AtlasWebElement inputDaysNoTomHighFever();

    @FindBy(value = "input2", selector = Selector.ID)
    AtlasWebElement inputDaysNoTomSuperHighFever();

    @FindBy(value = "result", selector = Selector.ID)
    AtlasWebElement labelSquareNoTom();

    @FindBy(value = "//form[@name='form1']//input[@name='bt']", selector = Selector.XPATH)
    AtlasWebElement buttonCalculateNoTom();

    // Tomography is on section
    @FindBy(value = "input3", selector = Selector.ID)
    AtlasWebElement inputPercentsOnTomography();

    @FindBy(value = "input4", selector = Selector.ID)
    AtlasWebElement inputDaysTomOnHighFever();

    @FindBy(value = "input5", selector = Selector.ID)
    AtlasWebElement inputDaysTomOnSuperHighFever();

    @FindBy(value = "result1", selector = Selector.ID)
    AtlasWebElement labelSquareTomOn();

    @FindBy(value = "//form[@name='form2']//input[@name='bt']", selector = Selector.XPATH)
    AtlasWebElement buttonCalculateTomOn();

    default void calculateSquareNoTomography() {
        buttonCalculateNoTom().click();
    }

    default void calculateSquareTomographyOn() {
        buttonCalculateTomOn().click();
    }

}
