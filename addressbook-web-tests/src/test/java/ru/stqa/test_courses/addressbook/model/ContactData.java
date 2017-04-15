package ru.stqa.test_courses.addressbook.model;

public class ContactData {
    private int id;
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String address;
    private final String phone;
    private final String email;
    private String group;

    public ContactData(String name, String middleName, String lastName, String nickName, String company, String address, String phone, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public ContactData(int id, String name, String middleName, String lastName, String nickName, String company, String address, String phone, String email, String group) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
}
