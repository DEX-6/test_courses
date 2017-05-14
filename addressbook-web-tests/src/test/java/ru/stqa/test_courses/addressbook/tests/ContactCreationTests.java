package ru.stqa.test_courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;
import ru.stqa.test_courses.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/pic.jpg");

        ContactData contact = new ContactData().withName("Денис").withMiddleName("Станиславович").withLastName("Воронцов")
                .withNickName("DEX-6").withCompany("Космический Мозгоед").withAddress("планета Земля")
                .withPhone("+79856405255").withEmail("dex-6@mail.ru").withGroup("test1").withPhoto(photo);
//        ContactData contact = new ContactData().withName("Денис").withLastName("Воронцов").withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println("Абсолютный путь: " + currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/pic.jpg");
        System.out.println("Абсолютный путь: " + photo.getAbsolutePath());
        System.out.println(photo.exists());

    }
}
