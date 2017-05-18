package ru.stqa.test_courses.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;
import ru.stqa.test_courses.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testContactCreation(ContactData contact) {

//        Contacts before = app.contact().all();
//        File photo = new File("src/test/resources/pic.jpg");
//
//        ContactData contact = new ContactData().withName("Денис").withMiddleName("Станиславович").withLastName("Воронцов")
//                .withNickName("DEX-6").withCompany("Космический Мозгоед").withAddress("планета Земля")
//                .withPhone("+79856405255").withEmail("dex-6@mail.ru").withGroup("test1").withPhoto(photo);
//        ContactData contact = new ContactData().withName("Денис").withLastName("Воронцов").withPhoto(photo);
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));

//        Contacts after = app.contact().all();
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactsListInUI();
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
