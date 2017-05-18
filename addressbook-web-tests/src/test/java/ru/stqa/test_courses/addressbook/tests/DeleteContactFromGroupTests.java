package ru.stqa.test_courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.ContactData;
import ru.stqa.test_courses.addressbook.model.Contacts;
import ru.stqa.test_courses.addressbook.model.GroupData;
import ru.stqa.test_courses.addressbook.model.Groups;

/**
 * Created by i-ru on 18.05.2017.
 */
public class DeleteContactFromGroupTests extends TestBase {
    GroupData group;
    ContactData contact;
    String groupName = Integer.toString(1 + (int) (Math.random() * 1000));
    String contactName = Integer.toString(1 + (int) (Math.random() * 1000));


    @BeforeMethod
    public void ensurePreconditions() {

        group = new GroupData().withName(groupName).withFooter(groupName).withHeader(groupName);
        app.goTo().groupPage();
        app.group().create(group);
        group = app.db().groups().iterator().next().withName(groupName);

        app.goTo().homePage();
        contact = new ContactData().withName(contactName).withLastName(contactName)
                .withAddress(contactName).withPhone("+79856405255");
        app.contact().create(contact);
        contact = app.db().contacts().iterator().next().withName(contactName);

        app.goTo().homePage();
        app.contact().addInGroup(contact, group);
    }

    @Test
    public void testDeleteContactFromGroup() {
        ContactData afterContact ;
        app.goTo().homePage();
        app.contact().deleteContactFromGroup(contact, group);
        afterContact =  app.db().contacts().iterator().next().withName(groupName);
        Assert.assertTrue(!afterContact.getGroups().contains(group));
    }
}
