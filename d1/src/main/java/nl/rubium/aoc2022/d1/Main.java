package nl.rubium.aoc2022.d1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // Part one
        var resource = Thread.currentThread().getContextClassLoader().getResource("input");
        var content = Files.readString(Paths.get(resource.toURI()));

        var aggregatedCalories = Arrays.stream(content.split("\n\n"))
            .map(listOfCaloriesAsString ->
                Arrays.stream(listOfCaloriesAsString.split("\n"))
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum)
            ).toList();

        var maxCalories = aggregatedCalories.stream()
            .max(Comparator.comparing(Integer::valueOf)).orElseThrow(NoSuchElementException::new);

        System.out.printf("Maximum calories carried is %s", maxCalories);
        System.out.println();

        // Part two
        var sortedCalories = aggregatedCalories.stream()
            .sorted((f1, f2) -> Integer.compare(f2, f1)).toList();

        var topThreeCalories = sortedCalories.get(0) + sortedCalories.get(1) +sortedCalories.get(2);

            System.out.printf("Top three calories carried is %s", topThreeCalories);
    }
}