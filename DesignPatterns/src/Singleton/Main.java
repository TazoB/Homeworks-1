package Singleton;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        University university = University.getInstance(
                "Massachusetts Institute of Technology",
                false,
                List.of(
                        "Software Engineering",
                        "Economics",
                        "Physics",
                        "Medicine",
                        "Chemistry"
                )
        );

        Student student = new Student("Tazo", "Bendianishvili", "01955003768", 16, university);
        System.out.println(student);
    }
}