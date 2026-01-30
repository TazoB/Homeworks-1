package FactoryMethod;

public class PartTime extends Employee {
    private int workedDays;
    private int dailyWage;

    public PartTime(String firstName, String lastName, String personalId, int age, int workedDays, int dailyWage) {
        super(firstName, lastName, personalId, age);
        this.workedDays = workedDays;
        this.dailyWage = dailyWage;
    }

    @Override
    public double getSalary() {
        return workedDays * dailyWage;
    }

    public int getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(int workedDays) {
        this.workedDays = workedDays;
    }

    public int getDailyWage() {
        return dailyWage;
    }

    public void setDailyWage(int dailyWage) {
        this.dailyWage = dailyWage;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "\tworkedDays - " + workedDays + "\n" +
                "\tdailyWage - " + dailyWage + "\n";
    }
}
