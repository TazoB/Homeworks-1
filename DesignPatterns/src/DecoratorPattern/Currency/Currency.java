package DecoratorPattern.Currency;

public abstract class Currency {
    String description;

    public String getCurrencyDescription() {
        return description;
    }

    public abstract double cost(double c);
}
