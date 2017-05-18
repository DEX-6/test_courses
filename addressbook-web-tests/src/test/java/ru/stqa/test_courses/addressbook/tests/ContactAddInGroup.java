package ru.stqa.test_courses.addressbook.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;
import ru.stqa.test_courses.addressbook.model.Contacts;
import ru.stqa.test_courses.addressbook.model.GroupData;
import ru.stqa.test_courses.addressbook.model.Groups;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by i-ru on 18.05.2017.
 */
public class ContactAddInGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withName("Денис").withMiddleName("Станиславович").withLastName("Воронцов")
                    .withNickName("DEX-6").withCompany("Космический Мозгоед").withAddress("планета Земля")
                    .withPhone("+79856405255").withEmail("dex-6@mail.ru"));
        }
    }

    @Test
    public void testContactAddInGroup() {
        GroupData addedGroup;
        Contacts beforeContact;
        beforeContact = app.db().contacts();
        Groups addedGroups = app.db().groups();

        app.goTo().homePage();
        ContactData modifiedContact = beforeContact.iterator().next();
        addedGroups.removeAll(modifiedContact.getGroups());
        addedGroup = addedGroups.iterator().next();
        app.contact().addInGroup(modifiedContact, addedGroup);

        for (int i = 0; i < modifiedContact.getGroups().size(); i++){
            if (modifiedContact.getGroups().iterator().hasNext()) {
                System.out.println("группа в контакте " + i + " " + modifiedContact.getGroups().iterator().next());
            }
        }
        System.out.println(addedGroup);

        Assert.assertTrue(modifiedContact.getGroups().contains(addedGroup));

    }
}
