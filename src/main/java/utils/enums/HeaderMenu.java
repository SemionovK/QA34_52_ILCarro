package utils.enums;

public enum HeaderMenu {
    LOGO("//img[@alt='logo']"),
    SEARCH("//a[@href='/search']"),
    LET_THE_CAR_WORK("//div[@class='header']/a[3]"),
    TERMS_OF_USE("//a[@href='/terms-of-use']"),
    SIGN_UP("//a[text()=' Sign up ']"),
    LOG_IN("//a[text()=' Log in ']"),
    LOGOUT("//a[@href='/logout?url=%2Fsearch']"),
    DELETE_ACCOUNT("//a[text()='Delete account']");

    private final String locator;

    HeaderMenu(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
