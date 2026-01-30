package FactoryMethod;

public class FullTime extends Employee {
    private double fixedSalary;

    public FullTime(String firstName, String lastName, String personalId, int age, double fixedSalary) {
        super(firstName, lastName, personalId, age);
        this.fixedSalary = fixedSalary;
    }

    @Override
    public double getSalary() {
        return fixedSalary;
    }

    public double getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "\tfixedSalary - " + fixedSalary;
    }
}
