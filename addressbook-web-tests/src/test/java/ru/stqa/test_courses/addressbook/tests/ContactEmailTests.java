package ru.stqa.test_courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by i-ru on 09.05.2017.
 */
public class ContactEmailTests extends TestBase {

    @Test
    public void testContactPhone() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
        assertThat(contact.getEmail_2(), equalTo(contactInfoFromEditForm.getEmail_2()));
        assertThat(contact.getEmail_3(), equalTo(contactInfoFromEditForm.getEmail_3()));
    }
}
