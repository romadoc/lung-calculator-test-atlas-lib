package pages;

import elements.CovidCalcElements;
import io.qameta.atlas.webdriver.WebPage;

public interface CalculatorTomographyOff extends WebPage, CovidCalcElements {
    default void insertDaysHighTemperature(String i) {
        inputDaysNoTomHighFever().sendKeys(i);
    }

    default void insertDaysSuperHighTemperature(String i) {
        inputDaysNoTomSuperHighFever().sendKeys(i);
    }

    default String getCalculatedSquareNoTomography() {
        return labelSquareNoTom().getText();
    }

    @Override
    default void calculateSquareNoTomography() {
        CovidCalcElements.super.calculateSquareNoTomography();
    }
}
