package DecoratorPattern.Decorator;

import DecoratorPattern.Currency.Currency;

public class USDDecorator extends Decorator {
    public USDDecorator(Currency currency) {
        super(currency);
    }

    @Override
    public String getDescription() {
        return currency.getCurrencyDescription() + " (decorated as USD)";
    }

    @Override
    public double cost(double c) {
        return c;
    }
}
