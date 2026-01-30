package DecoratorPattern.Currency;

public class Dollar extends Currency {
    double value;

    public Dollar(double value) {
        this.value = value;
        this.description = "US Dollar";
    }

    @Override
    public double cost(double c) {
        return value * c;
    }
}
