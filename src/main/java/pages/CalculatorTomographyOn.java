package pages;

import elements.CovidCalcElements;
import io.qameta.atlas.webdriver.WebPage;

public interface CalculatorTomographyOn extends WebPage, CovidCalcElements {

    default void insertInjuredSquareOnTomography(String i) {
        inputPercentsOnTomography().sendKeys(i);
    }

    default void insertDaysHighTemperature(String i) {
        inputDaysTomOnHighFever().sendKeys(i);
    }

    default void insertDaysSuperHighTemperature(String i) {
        inputDaysTomOnSuperHighFever().sendKeys(i);
    }

    default String getCalculatedSquareWithTomography() {
        return labelSquareTomOn().getText();
    }

    default void calculateSquareWithTomography() {
        CovidCalcElements.super.calculateSquareTomographyOn();
    }

}
