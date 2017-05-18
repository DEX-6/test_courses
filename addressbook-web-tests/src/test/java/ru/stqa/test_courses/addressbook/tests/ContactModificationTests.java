package ru.stqa.test_courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;
import ru.stqa.test_courses.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by i-ru on 20.02.2017.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withName("Денис").withMiddleName("Станиславович").withLastName("Воронцов")
                    .withNickName("DEX-6").withCompany("Космический Мозгоед").withAddress("планета Земля")
                    .withPhone("+79856405255").withEmail("dex-6@mail.ru").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Денис").withMiddleName("Станиславович").withLastName("Воронцов")
                .withNickName("DEX-6").withCompany("Космический Мозгоед").withAddress("планета Земля")
                .withPhone("+79856405255").withEmail("dex-6@mail.ru");
        app.goTo().homePage();
        app.contact().modify(contact);
        Assert.assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactsListInUI();
    }
}
