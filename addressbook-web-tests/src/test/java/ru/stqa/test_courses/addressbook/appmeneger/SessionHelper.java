package ru.stqa.test_courses.addressbook.appmeneger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by i-ru on 09.02.2017.
 */
public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {

        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
