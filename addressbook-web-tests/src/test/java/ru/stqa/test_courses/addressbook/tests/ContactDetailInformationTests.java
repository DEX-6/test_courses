package ru.stqa.test_courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;
import ru.stqa.test_courses.addressbook.model.Contacts;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by i-ru on 18.05.2017.
 */
public class ContactDetailInformationTests extends TestBase {


    @BeforeMethod()
    public void ensurePreconditions() throws IOException {
        app.goTo().homePage();
        app.contact().selectAllContacts();
        app.contact().deleteSelectedContacts();
        app.contact().acceptAlert();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withName("Денис").withLastName("Воронцов")
                    .withAddress("планета Земля")
                    .withPhone("+79856405255"));
        }
    }

    @Test
    public void testContacrDetailInformation() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        String contactInfoFromDetailForm = app.contact().infoFromDetailForm(contact);
        String contactInfoFromEditForm = app.contact().infoFromEditFormWithAddedPrefixes(contact);

        assertThat(contactInfoFromEditForm, equalTo(cleaned(contactInfoFromDetailForm)));
    }

    public static String cleaned(String contactInfo) {
        return contactInfo.replaceAll("\\n", "");
    }

}
