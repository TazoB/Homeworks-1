package ObserverPattern;

public class Main {
    public static void main(String[] args) {
        Subscriber s1 = new Subscriber("Tazo");
        Subscriber s2 = new Subscriber("Lasha");
        Subscriber s3 = new Subscriber("Gio");

        Blog techBlog = new Blog();
        Blog sportBlog = new Blog();

        techBlog.addSubscriber(s1);
        techBlog.addSubscriber(s2);

        sportBlog.addSubscriber(s2);
        sportBlog.addSubscriber(s3);

        techBlog.setArticle("Java Observer Pattern");
        sportBlog.setArticle("Champions League Results");

        techBlog.notifySubscribers();
        sportBlog.notifySubscribers();
    }
}
