package Singleton;

public class Student {
    private String firstName;
    private String lastName;
    private String personalId;
    private int age;
    private University university;

    public Student(String firstName, String lastName, String personalId, int age, University university) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.age = age;
        this.university = university;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student {" + "\n" +
                "\tfirstName - '" + firstName + '\'' + "\n" +
                "\tlastName - '" + lastName + '\'' + "\n" +
                "\tpersonalId - '" + personalId + '\'' + "\n" +
                "\tage - " + age + "\n" +
                "\tuniversity:  " + university + "\n" +
                '}';
    }
}