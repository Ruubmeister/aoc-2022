package nl.rubium.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("input");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        //Puzzle 1
        var scoreOne = new AtomicInteger();

        for (String line; (line = reader.readLine()) != null;) {
            int middleOfString = line.length() / 2;
            String firstPart = line.substring(0, middleOfString);
            String secondPart = line.substring(middleOfString);

            var characterNumber = firstPart.chars()
                .filter(character -> secondPart.contains(String.valueOf((char) character)))
                    .findFirst();

            characterNumber.ifPresent(character -> scoreOne.addAndGet(character > 96 ? character - 96 : character - 38));
        }

        System.out.println("Score one is " + scoreOne);

        //Puzzle 2

        // Go over three lines
        // Find corresponding characters
        // Do same as puzzle 1

        InputStream isTwo = classloader.getResourceAsStream("input");
        InputStreamReader streamReaderTwo = new InputStreamReader(isTwo, StandardCharsets.UTF_8);
        BufferedReader readerTwo = new BufferedReader(streamReaderTwo);

        var rucksackContent = new ArrayList<List<String>>();
        var rucksackGroup = new ArrayList<String>();
        var scoreTwo = new AtomicInteger();

        for (String line; (line = readerTwo.readLine()) != null;) {
            rucksackGroup.add(line);

            if (rucksackGroup.size() == 3 ){
                rucksackContent.add(rucksackGroup);
                rucksackGroup = new ArrayList<>();
            }
        }

        rucksackContent.forEach(group -> {
            var characterNumber = group.get(0).chars()
                .filter(character -> group.get(1).contains(String.valueOf((char) character)) && group.get(2).contains(String.valueOf((char) character)))
                .findFirst();

            characterNumber.ifPresent(character -> scoreTwo.addAndGet(character > 96 ? character - 96 : character - 38));
        });

        System.out.println("Score two is " + scoreTwo);
    }
}