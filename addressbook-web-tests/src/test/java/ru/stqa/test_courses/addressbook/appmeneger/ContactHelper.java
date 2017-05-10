package ru.stqa.test_courses.addressbook.appmeneger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.test_courses.addressbook.model.ContactData;
import ru.stqa.test_courses.addressbook.model.Contacts;

import java.util.List;

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


        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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
            String[] phones = cells.get(5).getText().split("\n");
            String[] emails = cells.get(4).getText().split("\n");
            String address = cells.get(3).getText();
            contactCashe.add(new ContactData().withId(id).withName(name).withLastName(lastName)
                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]).withEmail(emails[0])
                    .withEmail_2(emails[1]).withEmail_3(emails[2]).withAddress(address));
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
}
