package DecoratorPattern;

import DecoratorPattern.Currency.Currency;
import DecoratorPattern.Currency.Dollar;
import DecoratorPattern.Currency.Rupee;
import DecoratorPattern.Decorator.USDDecorator;

public class Main {
    public static void main(String[] args) {
        Currency rupee = new Rupee(75);
        Currency dollar = new Dollar(1);

        USDDecorator usdDollar = new USDDecorator(dollar);

        System.out.println(rupee.getCurrencyDescription());
        System.out.println("Cost: " + rupee.cost(2));

        System.out.println(dollar.getCurrencyDescription());
        System.out.println("Cost: " + dollar.cost(2));

        System.out.println(usdDollar.getDescription());
        System.out.println("Cost: " + usdDollar.cost(2));
    }
}
