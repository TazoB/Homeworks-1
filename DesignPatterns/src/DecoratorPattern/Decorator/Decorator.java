package DecoratorPattern.Decorator;

import DecoratorPattern.Currency.Currency;

public abstract class Decorator extends Currency {
    public Currency currency;

    public Decorator(Currency currency) {
        this.currency = currency;
    }

    public abstract String getDescription();
}
