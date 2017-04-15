package ru.stqa.test_courses.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.test_courses.addressbook.appmeneger.ApplicationManager;

/**
 * Created by i-ru on 09.02.2017.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
// TODO: 15.04.2017 Добит урок 4.10 (для групп)
}
