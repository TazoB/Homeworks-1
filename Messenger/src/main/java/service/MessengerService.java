package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Persistence;
import model.Message;
import model.User;

import java.util.List;

public class MessengerService {
    private static MessengerService INSTANCE;
    private final EntityManager entityManager;

    private MessengerService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mziuri");
        entityManager = emf.createEntityManager();
        entityManager.setFlushMode(FlushModeType.AUTO);
    }

    public static MessengerService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MessengerService();
        }
        return INSTANCE;
    }

    public void addUser(User user) {
        runInTransaction(() -> entityManager.persist(user));
    }

    public boolean userExists(String username) {
        try {
            System.out.println(
                    entityManager.createQuery("SELECT u FROM User u", User.class)
                            .getResultList()
            );
            Long count = entityManager.createQuery(
                    "SELECT COUNT(u) FROM User u WHERE u.username = :username",
                     Long.class
                    )
                    .setParameter("username", username)
                    .getSingleResult();

            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> getMessages(String username) {
        try {
            return entityManager.createQuery(
                            "SELECT m FROM Message m WHERE m.receiver.username = :username",
                            Message.class)
                    .setParameter("username", username)
                    .getResultList();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean passwordIsCorrect(String username, String password) {
        try {
            Long count = entityManager.createQuery(
                    "SELECT COUNT(u) FROM User u WHERE u.username = :username and u.password = :password",
                    Long.class
            )
                    .setParameter("username", username)
                    .getSingleResult();

            return count > 0;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User findByUsername(String username) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username",
                            User.class)
                    .setParameter("username", username)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMessage(Message message) {
        runInTransaction(() -> entityManager.persist(message));
    }

    private void runInTransaction(Runnable runnable){
        try {
            entityManager.getTransaction().begin();
            runnable.run();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            entityManager.close();
            throw e;
        }
    }
}
