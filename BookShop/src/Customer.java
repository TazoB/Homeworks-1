public class Customer implements Runnable {

    private String name;
    private int booksBought;
    private BookShop shop;

    public Customer(String name, BookShop shop) {
        this.name =  name;
        this.shop = shop;
    }

    public void printSummary() {
        System.out.println(name + " bought " + booksBought + " books.");
    }

    @Override
    public synchronized void run() {
        for(int i = 1; i <= 5000; i++)
            if(shop.buyBook())
                booksBought++;
    }
}