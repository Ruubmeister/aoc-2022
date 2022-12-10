package nl.rubium.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("input");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        var sectionCleanings = new ArrayList<SectionCleaning>();

        var overlappingSections = 0;

        for (String line; (line = reader.readLine()) != null; ) {
            var inputSections = line.split(",");

            var sectionOne = inputSections[0].split("-");
            var sectionTwo = inputSections[1].split("-");

            var sectionCleaning = new SectionCleaning(sectionOne[0], sectionOne[1], sectionTwo[0], sectionTwo[1]);

            sectionCleanings.add(sectionCleaning);
            if (sectionCleaning.hasFullSectionOverlap()){
                overlappingSections += 1;
            }
        }

        System.out.println("Overlapping sections: " + overlappingSections);

        AtomicInteger puzzleTwoResult = new AtomicInteger();

        sectionCleanings.forEach(section -> {
            if (section.hasPartialOrFullSectionOverlap()){
                puzzleTwoResult.addAndGet(1);
            }
        });

        System.out.println("Overlapping sections for puzzle 2: " + puzzleTwoResult);
    }

    private static class SectionCleaning {

        private final int sectionOneLowerRange;
        private final int sectionOneUpperRange;
        private final int sectionTwoLowerRange;
        private final int sectionTwoUpperRange;

        public SectionCleaning(
            String sectionOneLowerRange,
            String sectionOneUpperRange,
            String sectionTwoLowerRange,
            String sectionTwoUpperRange
        ) {
            this.sectionOneLowerRange = Integer.parseInt(sectionOneLowerRange);
            this.sectionTwoLowerRange = Integer.parseInt(sectionTwoLowerRange);
            this.sectionOneUpperRange = Integer.parseInt(sectionOneUpperRange);
            this.sectionTwoUpperRange = Integer.parseInt(sectionTwoUpperRange);
        }

        public boolean hasFullSectionOverlap() {
            return
                (this.sectionOneLowerRange <= this.sectionTwoLowerRange
                    && this.sectionOneUpperRange >= this.sectionTwoUpperRange)
                    ||
                    (this.sectionOneLowerRange >= this.sectionTwoLowerRange
                        && this.sectionOneUpperRange <= this.sectionTwoUpperRange);
        }

        public boolean hasPartialOrFullSectionOverlap() {
            return this.sectionOneUpperRange >= this.sectionTwoLowerRange
                && this.sectionOneLowerRange <= this.sectionTwoUpperRange;
        }
    }
}