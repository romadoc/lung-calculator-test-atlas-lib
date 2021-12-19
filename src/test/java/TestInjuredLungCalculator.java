import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.internal.LogManagerStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import unit.BaseTest;
import java.util.Arrays;
import java.util.List;


public class TestInjuredLungCalculator extends BaseTest {
    private final Logger logger = LogManager.getRootLogger();
    @DataProvider(name = "integers")
    public static Object[][] integerDatas() {
        return new Object[][]{
                {"1", "0"},
                {"0", "2"},
                {"5", "2"},
                {"0", "0"},
                {"10", "3"},
                {"3", "10"},
                {"1", "5"},
                {"10", "2"},
                {"-10", "2"}
        };
    }

    @DataProvider(name = "negatives")
    public static Object[][] withNegativeDatas() {
        return new Object[][]{
                {"-1", "-10"},
                {"-4", "-1"},
                {"-1000", "-5000"},

        };
    }

    @DataProvider(name = "incorrectFormat")
    public static Object[][] withMistakenDatas() {
        return new Object[][]{
                {"-1.1", "-0"},
                {"2.4", "-1.6"},
                {"-1000.8", "-5000.2"},
                {"555555", "33333333333333333333333333333333333333"}

        };
    }

    @DataProvider(name = "integersOn")
    public static Object[][] intDatas() {
        return new Object[][]{
                {"1", "0", "0"},
                {"0", "2", "3"},
                {"5", "2", "3"},
                {"0", "0", "0"},
                {"10", "3", "10"},
                {"3", "10", "3"},
                {"1", "5", "5"},
                {"10", "2", "2"},
                {"-10", "2", "2"}
        };
    }

    @DataProvider(name = "negativesOn")
    public static Object[][] negativeDatas() {
        return new Object[][]{
                {"-1", "-10", "-77"},
                {"-4", "-1", "-1"},
                {"-1000", "-5000", "-14"},
        };
    }

    @DataProvider(name = "incorrectFormatOn")
    public static Object[][] mistakenDatas() {
        return new Object[][]{
                {"-1.1", "-0", "-ff"},
                {"2.4", "-1.6", " "},
                {"-1000.8", "-5000.2", "4.9"},
                {"5.77777777777777777", "33333333333333333333333333333333333333", "022222222222222222"},
                {"0.77777777777777777", "33333333333333.333333333333333333333333", "0.22222222222222222"}


        };
    }

    @Test(dataProvider = "integers")
    public void couldBeInjuringSquareNegative(String first, String sec) {
        onCalculatorTomographyOff().insertDaysHighTemperature(first);
        onCalculatorTomographyOff().insertDaysSuperHighTemperature(sec);
        onCalculatorTomographyOff().calculateSquareNoTomography();
        logger.info("part no tomography");
        logger.info("try input days with temperature up to 38,9: " + first + " ; days with temperature 39,0 and higher: " + sec );
        int square = getInjuredSquareNoCt();
        logger.info("method get square of injured lungs: " + square + "%" );
        Assert.assertTrue(square >= 0, "lung injuring square can't be negative!");

    }

    @Test(dataProvider = "negatives")
    public void isNegativeNumbersAllowedToEnter(String first, String sec) {
        onCalculatorTomographyOff().insertDaysHighTemperature(first);
        onCalculatorTomographyOff().insertDaysSuperHighTemperature(sec);
        onCalculatorTomographyOff().calculateSquareNoTomography();
        logger.info("part no tomography try enter negatives");
        logger.fatal("try input days with temperature up to 38,9: " + first + " ; days with temperature 39,0 and higher: " + sec );
        int square = getInjuredSquareNoCt();
        logger.fatal("method get square of injured lungs: " + square + "%" );

        Assert.assertTrue(square >= 0, "negative numbers should be not allowed!");
    }

    private int getInjuredSquareNoCt() {
        String injuredSquare = onCalculatorTomographyOff().getCalculatedSquareNoTomography();
        List<String> stringList = Arrays.asList(injuredSquare.split(" "));
        int square = Integer.parseInt(stringList.get(0));
        if(square < 0 || square > 100) {
            logger.fatal("incorrect calculated data:");
        }
        return square;
    }

    @Test(dataProvider = "incorrectFormat")
    public void isIncorrectFormatsAllowedToEnter(String first, String sec) {
        onCalculatorTomographyOff().insertDaysHighTemperature(first);
        onCalculatorTomographyOff().insertDaysSuperHighTemperature(sec);
        onCalculatorTomographyOff().calculateSquareNoTomography();
        logger.info("part no tomography, try to enter incorrect format");
        logger.fatal("try input days with temperature up to 38,9: " + first + " ; days with temperature 39,0 and higher: " + sec );
        String injuredSquare = onCalculatorTomographyOff().getCalculatedSquareNoTomography();

        Assert.assertTrue(injuredSquare.contains("illegal format"), "illegal parameters were found");
        Assert.assertTrue(injuredSquare.contains("неверный формат данных"), "illegal parameters were found");
    }

    @Test(dataProvider = "integersOn")

    public void couldBeDataNegativeTmOn(String first, String sec, String third) {
        onCalculatorTomographyOn().insertInjuredSquareOnTomography(first);
        onCalculatorTomographyOn().insertDaysHighTemperature(sec);
        onCalculatorTomographyOn().insertDaysSuperHighTemperature(third);
        onCalculatorTomographyOn().calculateSquareWithTomography();
        logger.info("part tomography is on, integer format");
        logger.info("injured square on tomography: "+ first + "% ; try to input days with temperature up to 38,9: " + sec + " ; days with temperature 39,0 and higher: " + third );
        if (Integer.parseInt(first) < 0 || Integer.parseInt(first) > 100) {
            logger.fatal("incorrect input data (The lung lesion volume defined by CT scanning) is allowed in tomographyOn  section!");
        }
        int square = getInjuredSquareCtOn();

        Assert.assertTrue(square >= 0 && Integer.parseInt(first) >= 0 && Integer.parseInt(sec) >= 0 && Integer.parseInt(third) >= 0, "lung injuring square can't be negative!");
    }

    @Test(dataProvider = "negativesOn")
    public void isNegativeNumbersAllowedToEnterTmOn(String first, String sec, String third) {
        onCalculatorTomographyOn().insertInjuredSquareOnTomography(first);
        onCalculatorTomographyOn().insertDaysHighTemperature(sec);
        onCalculatorTomographyOn().insertDaysSuperHighTemperature(third);
        onCalculatorTomographyOn().calculateSquareWithTomography();
        logger.info("part tomography is on, try to use negative format");
        logger.info("injured square on tomography: "+ first + "% ; try to input days with temperature up to 38,9: " + sec + " ; days with temperature 39,0 and higher: " + third );
        int square = getInjuredSquareCtOn();

        Assert.assertTrue(square >= 0, "negative numbers should be not allowed!");
    }

    private int getInjuredSquareCtOn() {
        String injuredSquare = onCalculatorTomographyOn().getCalculatedSquareWithTomography();
        List<String> stringList = Arrays.asList(injuredSquare.split(" "));
        int square = Integer.parseInt(stringList.get(0));
        if(square < 0 || square > 100) {
            System.out.println(square);
            logger.fatal("incorrect calculated data:");
        }
        return square;
    }

    @Test(dataProvider = "incorrectFormatOn")
    public void isIncorrectFormatsAllowedToEnterTmOn(String first, String sec, String third) {
        onCalculatorTomographyOn().insertInjuredSquareOnTomography(first);
        onCalculatorTomographyOn().insertDaysHighTemperature(sec);
        onCalculatorTomographyOn().insertDaysSuperHighTemperature(third);
        onCalculatorTomographyOn().calculateSquareWithTomography();
        logger.info("part tomography is on, try to use incorrect input data format");
        logger.info("injured square on tomography: "+ first + "% ; try to input days with temperature up to 38,9: " + sec + " ; days with temperature 39,0 and higher: " + third );
        String injuredSquare = onCalculatorTomographyOn().getCalculatedSquareWithTomography();

        Assert.assertTrue(injuredSquare.contains("illegal format"), "illegal parameters were found");
        Assert.assertTrue(injuredSquare.contains("неверный формат данных"), "illegal parameters were found");
    }


}
