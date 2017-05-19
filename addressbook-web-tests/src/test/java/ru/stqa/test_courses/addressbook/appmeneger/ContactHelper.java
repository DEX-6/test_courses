package ru.stqa.test_courses.addressbook.appmeneger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.test_courses.addressbook.model.ContactData;
import ru.stqa.test_courses.addressbook.model.Contacts;
import ru.stqa.test_courses.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by i-ru on 18.02.2017.
 */
public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    private Contacts contactCashe = null;

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("work"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());
//        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    /**
     * Первая поправка +1 на выбор строки (начинается со второй), вторая поправка +1 на отсчет в массиве с нуля
     */
    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void initViewContact(int id) {
        wd.findElement(By.xpath("//a[@href='view.php?id=" + id + "']")).click();

    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    /*
    * метод returnToHomePage() заменен для сохранения единообразия,
    * т. к. после модификации контакта не появляется ссылка возвращения на дом. страницу
    * */
    public void returnToHomePage() {
//        click(By.linkText("home page"));
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void initCreateContact() {
        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        initCreateContact();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCashe = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCashe = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        acceptAlert();
        contactCashe = null;
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr[td]"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String name = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contactCashe.add(new ContactData().withId(id).withName(name).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactCashe);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());

        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");

        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");

        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email_2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email_3 = wd.findElement(By.name("email3")).getAttribute("value");

        String address = wd.findElement(By.name("address")).getAttribute("value");


        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname).withLastName(lastname).withHomePhone(home)
                .withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail_2(email_2).withEmail_3(email_3)
                .withAddress(address);
    }

    public String infoFromEditFormWithAddedPrefixes(ContactData contact) {
        String infoWithPrefixes;
        initContactModificationById(contact.getId());

        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");

        String FLName = firstname + " " + lastname;

        String home = wd.findElement(By.name("home")).getAttribute("value");
        if (!home.equals("")) {
            home = "H: " + home;
        }
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        if (!mobile.equals("")) {
            mobile = "M: " + mobile;
        }
        String work = wd.findElement(By.name("work")).getAttribute("value");
        if (!work.equals("")) {
            work = "W: " + work;
        }

        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email_2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email_3 = wd.findElement(By.name("email3")).getAttribute("value");

        String address = wd.findElement(By.name("address")).getAttribute("value");

        infoWithPrefixes = Arrays.asList(FLName, address, home, mobile, work, email, email_2, email_3)
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining(""));

        wd.navigate().back();
        return infoWithPrefixes;
    }

    public String infoFromDetailForm(ContactData contact) {
        initViewContact(contact.getId());
        String contactInfo = wd.findElement(By.id("content")).getText();
        wd.navigate().back();
        return contactInfo;
    }

    public void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public void addInGroup(ContactData contact, GroupData group) {
        String id = Integer.toString(group.getId());
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(id);
        click(By.name("add"));
    }

    public void deleteContactFromGroup(ContactData contact, GroupData group) {
        String id = Integer.toString(group.getId());
        String removeContactName = "remove";
        new Select(wd.findElement(By.name("group"))).selectByValue(id);
        (new WebDriverWait(wd, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name(removeContactName)));
        selectContactById(contact.getId());
        click(By.name(removeContactName));
    }
}
