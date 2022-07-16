import page.objects.HomePageScooter;
import page.objects.OrderPageScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static page.objects.HomePageScooter.URL_YANDEX;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestCasesOrderPageBigButton {
    private WebDriver driver;

    private final String FNAME;
    private final String SNAME;
    private final String ADDR;
    private final String METRO;
    private final String NUMBER;
    private final String DATE;
    private final String TIME;
    private final String COMM;

    public TestCasesOrderPageBigButton(String fname, String sname, String addr, String metro, String number, String date, String time, String comm) {
        this.FNAME = fname;
        SNAME = sname;
        ADDR = addr;
        METRO = metro;
        NUMBER = number;
        DATE = date;
        TIME = time;
        COMM = comm;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2} {3} {4} {5} {6} {7}")
    public static Object[][] getNumber() {
        return new Object[][]{
                {"Вероника", "Курохтина", "ул. Академическая д.36 кв.8", "Академическая", "89161234567", "11.01.2018", "сутки", "Приветы проверяющим =)"}
        };
    }

    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL_YANDEX);
    }

    @Test
    public void testOrderWithBigButton() {

        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.scrollToBigButton();
        objHomePage.clickToOrderButtonBig();

        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        objOrderPage.closeCookieButton();
        objOrderPage.orderPageFirstInput(FNAME, SNAME, ADDR, METRO, NUMBER);
        objOrderPage.tapOnBlackCheckBox();
        objOrderPage.orderPageSecondInput(DATE, TIME, COMM);
        objOrderPage.tapToButtonYes();

        assertTrue("Window Order Completed - not found!", objOrderPage.waitForWindowOrderCompleted());
    }

    @Test
    public void testOrderWitSmallButton() {

        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.clickToOrderButtonSmall();

        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        objOrderPage.closeCookieButton();
        objOrderPage.orderPageFirstInput(FNAME, SNAME, ADDR, METRO, NUMBER);
        objOrderPage.tapOnGreyCheckBox();
        objOrderPage.orderPageSecondInput(DATE, TIME, COMM);
        objOrderPage.tapToButtonYes();

        assertTrue("Window Order Completed - not found!", objOrderPage.waitForWindowOrderCompleted());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}