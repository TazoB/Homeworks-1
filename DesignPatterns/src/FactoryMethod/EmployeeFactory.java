package FactoryMethod;

public class EmployeeFactory {
    public Employee getEmployee(boolean isFullTime) {
        if(isFullTime) {
            return new FullTime("Tazo", "Bendianishvili", "12343456987", 16, 20000);
        } else {
            return new PartTime("Lasha", "Tskhelishvili", "13897660932", 16, 17, 70);
        }
    }
}
