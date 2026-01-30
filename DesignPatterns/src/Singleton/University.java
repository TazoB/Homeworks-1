package Singleton;

import java.util.List;

public class University {
    private static University instance;
    private String name;
    private boolean isPublic;
    private List<String> faculties;

    private University() {}

    public static University getInstance(String name, boolean isPublic, List<String> faculties) {
        if(instance == null) {
            synchronized (University.class) {
                if(instance == null) {
                    instance = new University();
                    instance.name = name;
                    instance.isPublic = isPublic;
                    instance.faculties = faculties;
                }
            }
        } return instance;
    }

    @Override
    public String toString() {
        return "University {" + "\n" +
                "\t\tname - '" + name + '\'' + "\n" +
                "\t\tisPublic - " + isPublic + "\n" +
                "\t\tfaculties - " + faculties + "\n\t" +
                '}';
    }
}
