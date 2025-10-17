package fop.w9temp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * გამოიყენეთ ეს კლასი და დააიმპლემენტეთ აბსტრაქტული მეთოდები
 */
public class Streams extends Temperatures {

	public Streams() {
		super("src/fop/w9temp/temps.csv");
	}

	/**
	 * გამოიყენეთ ეს მეთოდი დასატესტად
	 */
	public static void main(String[] args) {
		Streams streams = new Streams();
		streams.printSummary();
	}

	/**
	 * გამოიყენეთ stream() მეთოდი და დააბრუნეთ სტრიმის ზომა
	 */
	public long size() {
		return data.stream()
				.count();
	}

	/**
	 * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ტემპერატურის ელემენტების უნიკალური date ატრიბუტების ლისტი
	 */
	public List<LocalDate> dates() {
		return data.stream()
				.map(Temperature::getDate)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ტემპერატურის ელემენტების city ატრიბუტების სეტი
	 */
	public Set<String> cities() {
		return data.stream()
				.map(Temperature::getCity)
				.collect(Collectors.toCollection(HashSet::new));
	}

	/**
	 * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ტემპერატურის ელემენტების city ატრიბუტების სეტი
	 */
	public Set<String> countries() {
		return data.stream()
				.map(Temperature::getCountry)
				.collect(Collectors.toCollection(HashSet::new));
	}

	/**
	 * გამოიყენეთ stream() მეთოდი და დააბრუნეთ მეპი სადაც key იქნება ქვეყნის სახელი
	 * და value იქნება ამ ქვეყნის ტემპერატურების ლისტი
	 */
	public Map<String, List<Double>> temperaturesByCountry() {
		Map<String, List<Double> > temps = new HashMap<>();
		data.stream()
				.map(Temperature::getCountry)
				.collect(Collectors.toCollection(HashSet::new))
				.forEach(country -> {
					temps.put(
							country,
							data.stream()
									.filter(temperature -> temperature.getCountry().equals(country))
									.map(Temperature::getTemperature)
									.collect(Collectors.toCollection(ArrayList::new))
					);
				});
		return temps;
	}

	/**
	 * გამოიყენეთ stream() მეთოდი და დააბრუნეთ მეპი სადაც key იქნება ქვეყნის სახელი
	 * და value იქნება ამ ქვეყნის ტემპერატურების საშუალო არითმეტიკული
	 */
	public Map<String, Double> countriesAvgTemperature() {
		Map<String, Double> temps = new HashMap<>();
		data.stream()
				.map(Temperature::getCountry)
				.collect(Collectors.toCollection(HashSet::new))
				.forEach(country -> {
					temps.put(
							country,
							data.stream()
									.filter(temperature -> temperature.getCountry().equals(country))
									.mapToDouble(Temperature::getTemperature)
									.average().getAsDouble()
					);
				});
		return temps;
	}

	/**
	 * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ქვეყნის სახელი, სადაც დაფიქსირებულია ყველაზე ცივი ტემპერატურა
	 */
	public String coldestCountry() {
		return data.stream()
				.min(Comparator.comparingDouble(Temperature::getTemperature))
				.map(Temperature::getCountry)
				.get();
	}

	/**
	 * გამოიყენეთ stream() მეთოდი და დააბრუნეთ ქვეყნის სახელი, სადაც დაფიქსირებულია ყველაზე ცხელი ტემპერატურა
	 */
	public String hottestCountry() {
		return data.stream()
				.max(Comparator.comparingDouble(Temperature::getTemperature))
				.map(Temperature::getCountry)
				.get();
	}

}
