package testid;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;
import java.util.regex.*;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Homework2Test  {

    public static final String BASE_URL = "https://ci.itcollege.ee/part2example/";
    private static Pattern SESSION_ID_PATTERN = Pattern.compile("id is (\\w+)");
    private static Pattern SESSION_COUNT_PATTERN = Pattern.compile("count: (\\d+)");
    private static Pattern SESSION_ATTRIBUTE_PATTERN = Pattern.compile("attribute is (\\d+)");

    private static String HOME_PAGE = "HomePage";
    private static String SESSION_COUNT_PAGE = "SessionCount";
    private static String LOG_OUT_PAGE = "LogOut";

    @Test
    public void homePageCreatesSession() {
        String id = getSessionId(getPageSource(HOME_PAGE));

        assertThat(id, is(notNullValue()));
    }

    @Test
    public void sessionIdStaysTheSameOnReturn() {
        WebDriver driver = getDriver();

        String idA = getSessionId(getPageSource(HOME_PAGE, driver));
        String idB = getSessionId(getPageSource(HOME_PAGE, driver));

        assertThat(idA, is(equalTo(idB)));
    }

    @Test
    public void differentUserHasDifferentSession() {
        String idA = getSessionId(getPageSource(HOME_PAGE));
        String idB = getSessionId(getPageSource(HOME_PAGE));

        assertThat(idA, is(not(equalTo(idB))));
    }

    @Test
    public void newSessionIncreasesSessionCount() {

        int sessionCountBefore = getSessionCount(getPageSource(SESSION_COUNT_PAGE));

        getPageSource(HOME_PAGE);

        int sessionCountAfter = getSessionCount(getPageSource(SESSION_COUNT_PAGE));

        assertThat(sessionCountBefore, is(sessionCountAfter - 1));
    }

    @Test
    public void logOutDecreasesSessionCount() {

        int sessionCountBefore = getSessionCount(getPageSource(SESSION_COUNT_PAGE));

        WebDriver driver = getDriver();
        getPageSource(HOME_PAGE, driver);  // start new session
        getPageSource(LOG_OUT_PAGE, driver);  // close session

        int sessionCountAfter = getSessionCount(getPageSource(SESSION_COUNT_PAGE));

        assertThat(sessionCountBefore, is(equalTo(sessionCountAfter)));
    }

    @Test
    public void sessionParametersCanBeSet() {

        String paramValue = String.valueOf(System.currentTimeMillis());

        WebDriver driver = getDriver();
        getPageSource(HOME_PAGE + "?param=" + paramValue, driver);

        String sessionAttribute = getSessionAttribute(getPageSource(HOME_PAGE, driver));

        assertThat(sessionAttribute, is(equalTo(paramValue)));
    }

    @Test
    public void sessionParametersAreGoneAfterLogout() {

        String paramValue = String.valueOf(System.currentTimeMillis());

        WebDriver driver = getDriver();
        getPageSource(HOME_PAGE + "?param=" + paramValue, driver);
        getPageSource(LOG_OUT_PAGE, driver);

        String sessionAttribute = getSessionAttribute(getPageSource(HOME_PAGE, driver));

        assertThat(sessionAttribute, is(nullValue()));
    }

    @After
    public void closeOpenDrivers() {
        for (WebDriver each : openDriversDrivers) {
            each.close();
        }
    }

    private Set<WebDriver> openDriversDrivers = new HashSet<>();

    private String getPageSource(String pageName) {
        return getPageSource(pageName, getDriver());
    }

    private String getPageSource(String pageName, WebDriver driver) {
        driver.get(getUrl(pageName));
        return driver.getPageSource();
    }

    public String getSessionId(String src) {
        return matchCommon(src, SESSION_ID_PATTERN);
    }

    public String getSessionAttribute(String src) {
        return matchCommon(src, SESSION_ATTRIBUTE_PATTERN);
    }

    public int getSessionCount(String src) {
        String countAsString = matchCommon(src, SESSION_COUNT_PATTERN);
        return countAsString != null ? Integer.valueOf(countAsString) : 0;
    }

    private String matchCommon(String src, Pattern pattern) {
        Matcher m = pattern.matcher(src);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }

    private WebDriver getDriver() {
        FirefoxDriver driver = new FirefoxDriver();
        openDriversDrivers.add(driver);
        return driver;
    }

    private String getUrl(String page) {
        return BASE_URL + page;
    }


}