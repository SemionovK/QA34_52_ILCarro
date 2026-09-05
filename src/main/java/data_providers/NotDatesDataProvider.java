package data_providers;

import org.testng.annotations.DataProvider;

public class NotDatesDataProvider {
    @DataProvider
    public Object[][] noDatesDataProvider(){
        return new Object[][]{
                {"englishLetters", "abcdef"},
                {"hebrewLetters", "אבגדה"},
                {"specialCharacters", "!@#$%^&*"},
                {"spaces", "     "},
                {"digitsWithSpaces", "12 34 56"},
                {"digitsWithLetters", "123abc"},
                {"digitsWithSpecialCharacters", "123!@#"},
                {"emptyString", ""}
        };
    }
}
