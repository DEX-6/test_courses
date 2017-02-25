package ru.stqa.test_courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;

/**
 * Created by i-ru on 20.02.2017.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Денис", "Станиславович", "Воронцов", "DEX-6", "Космический Мозгоед", "планета Земля", "+79856405255", "dex-6@mail.ru", "test1"));
        }

        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Денис", "Станиславович", "Воронцов", "DEX-6", "Космический Мозгоед", "планета Земля", "+79856405255", "dex-6@mail.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
