package ru.stqa.test_courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;

/**
 * Created by i-ru on 20.02.2017.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Денис", "Станиславович", "Воронцов", "DEX-6", "Космический Мозгоед", "планета Земля", "+79856405255", "dex-6@mail.ru", "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

}
