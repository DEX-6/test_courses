package ru.stqa.test_courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by i-ru on 20.02.2017.
 */
public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create( new ContactData().withName("Денис").withMiddleName("Станиславович").withLastName("Воронцов")
                    .withNickName("DEX-6").withCompany("Космический Мозгоед").withAddress("планета Земля")
                    .withPhone("+79856405255").withEmail("dex-6@mail.ru").withGroup("test1"));
        }
    }

    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }


}
