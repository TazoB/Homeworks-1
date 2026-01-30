package ObserverPattern;

public class Subscriber {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String  article) {
        System.out.println(name + ", New Blog is Available: " + article);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
