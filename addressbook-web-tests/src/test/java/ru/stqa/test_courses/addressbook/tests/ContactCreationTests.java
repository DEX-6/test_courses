package ru.stqa.test_courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("Денис", "Станиславович", "Воронцов", "DEX-6", "Космический Мозгоед", "планета Земля", "+79856405255", "dex-6@mail.ru", "test1"));
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }
}
