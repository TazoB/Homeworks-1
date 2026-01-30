package FactoryMethod;

public class Main {
    public static void main(String[] args) {
        EmployeeFactory factory = new EmployeeFactory();
        Employee employee1 = factory.getEmployee(true);
        Employee employee2 = factory.getEmployee(false);

        System.out.println(employee1);
        System.out.println(employee2);
    }
}
