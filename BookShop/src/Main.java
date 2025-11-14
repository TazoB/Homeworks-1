public class Main {

    public static void main(String[] args) {
        BookShop shop = new BookShop();
        shop.addBooksInStore(7500);

        Customer peter = new Customer("Peter", shop);
        Customer paul = new Customer("Pauls", shop);


        Thread thread1 = new Thread(peter);
        Thread thread2 = new Thread(paul);
        thread1.start();
        thread2.start();

        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        shop.printSummary();
        peter.printSummary();
        paul.printSummary();
    }

}