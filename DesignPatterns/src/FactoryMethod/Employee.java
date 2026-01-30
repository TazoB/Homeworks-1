package FactoryMethod;

public abstract class Employee {
    private String firstName;
    private String lastName;
    private String personalId;
    private int age;
    public abstract double getSalary();

    public Employee(String firstName, String lastName, String personalId, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee:" + "\n" +
                "\tfirstName - '" + firstName + '\'' + "\n" +
                "\tlastName - '" + lastName + '\'' + "\n" +
                "\tpersonalId - '" + personalId + '\'' + "\n" +
                "\tage - " + age;
    }
}
