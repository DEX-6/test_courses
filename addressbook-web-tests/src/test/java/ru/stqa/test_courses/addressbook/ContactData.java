package ru.stqa.test_courses.addressbook;

public class ContactData {
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String address;
    private final String phone;
    private final String email;

    public ContactData(String name, String middleName, String lastName, String nickName, String company, String address, String phone, String email) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
