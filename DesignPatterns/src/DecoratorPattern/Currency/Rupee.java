package DecoratorPattern.Currency;

public class Rupee extends Currency {
    private double value;

    public Rupee(double value) {
        this.value = value;
        this.description = "Indian Rupee";
    }

    @Override
    public double cost(double c) {
        return value * c;
    }
}
