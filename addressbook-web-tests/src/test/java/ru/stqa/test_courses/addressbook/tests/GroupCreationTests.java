package ru.stqa.test_courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test_courses.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
}
