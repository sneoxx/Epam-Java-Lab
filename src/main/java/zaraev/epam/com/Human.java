package zaraev.epam.com;

import java.util.Objects;

/**
 * Класс Human (человек), с полями ФИО, возраст, адрес
 */
public class Human {
    private String FullName;
    private int age;
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(FullName, human.FullName) && Objects.equals(address, human.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FullName, age, address);
    }

    @Override
    public String toString() {
        return "Human{" +
                "FullName='" + FullName + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address.toString();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Human(String FullName, int age, Address address) {
        this.FullName = FullName;
        this.age = age;
        this.address = address;
    }
}