package data_providers;

import org.testng.annotations.DataProvider;

public class CarDataProvider {
    @DataProvider
    public Object[][] requiredFieldsDataProvider(){
        return new Object[][]{
                {"location"},
                {"manufacture"},
                {"model"},
                {"year"},
                {"fuel"},
                {"seats"},
                {"carClass"},
                {"carRegistrationNumber"},
        };
    }

    @DataProvider
    public Object[][] wrongYearDataProvider(){
        return new Object[][]{
                {"belowMinYear"},
                {"aboveMaxYear"},
        };
    }
}
