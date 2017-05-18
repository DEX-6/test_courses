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

        if (app.db().groups().size() == 0) {
            String name =  Integer.toString( 1 + (int) (Math.random() * 1000));
            GroupData group = new GroupData().withName(name).withFooter(name).withHeader(name);
            app.goTo().groupPage();
            app.group().create(group);
        }
    }

    @Test
    public void testContactAddInGroup() {
        GroupData addedGroup;
        Contacts beforeContact;
        Contacts afterContact;
        beforeContact = app.db().contacts();
        Groups addedGroups = app.db().groups();

        ContactData modifiedContact = beforeContact.iterator().next();
        int idModifiedContact = modifiedContact.getId();
        addedGroups.removeAll(modifiedContact.getGroups());
        if (addedGroups.size() == 0) {
            String name =  Integer.toString( 1 + (int) (Math.random() * 1000));
            addedGroup = new GroupData().withName(name).withFooter(name).withHeader(name);
            app.goTo().groupPage();
            app.group().create(addedGroup);
            addedGroups = app.db().groups();
            addedGroups.removeAll(modifiedContact.getGroups());
            addedGroup = addedGroups.iterator().next();

        } else {
            addedGroup = addedGroups.iterator().next();
        }

        app.goTo().homePage();
        app.contact().addInGroup(modifiedContact, addedGroup);

        afterContact = app.db().contacts();
        ContactData afterModifiedContact = afterContact.iterator().next().withId(idModifiedContact);

        Assert.assertTrue(afterModifiedContact.getGroups().contains(addedGroup));

    }
}
