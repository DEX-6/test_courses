package ru.stqa.test_courses.addressbook.appmeneger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.test_courses.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by i-ru on 18.02.2017.
 */
public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

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
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id +"']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id +"']")).click();
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
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        acceptAlert();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr[td]"));
        for (WebElement row : rows) {
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            String name = row.findElement(By.xpath(".//td[3]")).getText();
            String lastName = row.findElement(By.xpath(".//td[2]")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withLastName(lastName));
        }
        return contacts;
    }


}
