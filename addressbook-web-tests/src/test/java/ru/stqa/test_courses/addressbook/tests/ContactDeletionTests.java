package ru.stqa.test_courses.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by i-ru on 20.02.2017.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();
    }

}
