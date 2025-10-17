package fop.w9temp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Temperatures {

    protected List<Temperature> data;

    public Temperatures(String filepath) {
        data = parseCsvFile(new File(filepath));
    }
    
    public Stream<Temperature> stream() {
        return data.stream();
    }

    public void printSummary() {
        System.out.println("Number of temperature data points:" + size());
        System.out.println("Cities:" + cities());
        System.out.println("Countries:" + countries());
        System.out.println("Coldest Country (absolute Temperature):" + coldestCountry());
        System.out.println("Hottest Country (absolute Temperature):" + hottestCountry());
        System.out.println("Countries' average temperature:" + countriesAvgTemperature());
    }

    static private List<Temperature> parseCsvFile(final File csvFile) {
        try {
            return Files.lines(csvFile.toPath()).skip(1) // skip header of csv file
                    .map(lineToTemperature) // convert line to DateTemperature
                    .filter(Objects::nonNull) // filter null values due to parsing errors
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("File " + csvFile + " not found.");
        }
    }

    static private Function<String, Temperature> lineToTemperature = (line) -> {
        try {
            final String[] fields = line.split(","); // a CSV with comma-separated lines
            final LocalDate date = LocalDate.parse(fields[0]);
            final double avgTemperature = Double.parseDouble(fields[1]);
            // Skipping uncertainty
            final String city = fields[3];
            final String country = fields[4];
            final double latitude = Double.parseDouble(fields[5].substring(0, fields[5].length() - 1));
            final double longitude = Double.parseDouble(fields[6].substring(0, fields[6].length() - 1));
            return new Temperature(date, avgTemperature, city, country, latitude, longitude);
        } catch (Exception e) {
            return null;
        }
    };


    /**
     * გამოიყენეთ stream() მეთოდი და დააბრუნეთ სტრიმის ზომა
     */
    public abstract long size();

    /**
     * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ტემპერატურის ელემენტების უნიკალური date ატრიბუტების ლისტი
     */
    public abstract List<LocalDate> dates();

    /**
     * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ტემპერატურის ელემენტების city ატრიბუტების სეტი
     */
    public abstract Set<String> cities();

    /**
     * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ტემპერატურის ელემენტების city ატრიბუტების სეტი
     */
    public abstract Set<String> countries();

    /**
     * გამოიყენეთ stream() მეთოდი და დააბრუნეთ მეპი სადაც key იქნება ქვეყნის სახელი
     * და value იქნება ამ ქვეყნის ტემპერატურების ლისტი
     */
    public abstract Map<String, List<Double>> temperaturesByCountry();

    /**
     * გამოიყენეთ stream() მეთოდი და დააბრუნეთ მეპი სადაც key იქნება ქვეყნის სახელი
     * და value იქნება ამ ქვეყნის ტემპერატურების საშუალო არითმეტიკული
     */
    public abstract Map<String, Double> countriesAvgTemperature();

    /**
     * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ქვეყნის სახელი, სადაც დაფიქსირებულია ყველაზე ცივი ტემპერატურა
     */
    public abstract String coldestCountry();

    /**
     * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ქვეყნის სახელი, სადაც დაფიქსირებულია ყველაზე ცხელი ტემპერატურა
     */
    public abstract String hottestCountry();
}
