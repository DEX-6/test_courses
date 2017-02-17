package ru.stqa.test_courses.addressbook.appmeneger;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by i-ru on 09.02.2017.
 */
public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }
}
