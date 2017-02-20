package ru.stqa.test_courses.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.test_courses.addressbook.appmeneger.ApplicationManager;

/**
 * Created by i-ru on 09.02.2017.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    // TODO: 18.02.2017 Добит урок 3.4
}
