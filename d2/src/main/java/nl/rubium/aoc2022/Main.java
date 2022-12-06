package nl.rubium.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("input");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        int score = 0;
        int otherScore = 0;

        for (String line; (line = reader.readLine()) != null;) {
            var opponents = optionToEnum(line.substring(0,1));
            var yours = optionToEnum(line.substring(2,3));

            score += getScore(yours, opponents);
            otherScore += getSecondScore(line.substring(2,3), opponents);
        }

        System.out.println("Score: " + score);
        System.out.println("Other score: " + otherScore);
    }

    private static RPSOption optionToEnum(String option){
        return switch (option) {
            case "A", "X" -> RPSOption.ROCK;
            case "B", "Y" -> RPSOption.PAPER;
            case "C", "Z" -> RPSOption.SCISSORS;
            default -> RPSOption.PAPER;
        };
    }

    private static RPSOption whichOptionWins(RPSOption option){
        if (option.equals(RPSOption.ROCK)){
            return RPSOption.PAPER;
        } else if (option.equals(RPSOption.PAPER)){
            return RPSOption.SCISSORS;
        }
        return RPSOption.ROCK;
    }

    private static RPSOption whichOptionLoses(RPSOption option){
        if (option.equals(RPSOption.ROCK)) {
            return RPSOption.SCISSORS;
        } else if (option.equals(RPSOption.PAPER)) {
            return RPSOption.ROCK;
        }
        return RPSOption.PAPER;
    }

    private static int getScore(RPSOption yours, RPSOption opponents){
        if(yours.equals(opponents)){
            return 3 + shapePoints(yours);
        }else if (hasWon(yours, opponents)){
            return 6 + shapePoints(yours);
        }
        return shapePoints(yours);
    }

    private static int getSecondScore(String whatYouShouldDo, RPSOption opponents){
        if (whatYouShouldDo.equals("X")){
            return shapePoints(whichOptionLoses(opponents));
        }else if (whatYouShouldDo.equals("Y")){
            return 3 + shapePoints(opponents);
        }
        return 6 + shapePoints(whichOptionWins(opponents));
    }

    private static int shapePoints(RPSOption yours){
        if(yours.equals(RPSOption.ROCK)){
            return 1;
        }else if (yours.equals(RPSOption.PAPER)){
            return 2;
        }
        return 3;
    }

    private static boolean hasWon(RPSOption yours, RPSOption opponents){
        return (yours.equals(RPSOption.ROCK) && opponents.equals(RPSOption.SCISSORS))
            || (yours.equals(RPSOption.SCISSORS) && opponents.equals(RPSOption.PAPER))
            || (yours.equals(RPSOption.PAPER) && opponents.equals(RPSOption.ROCK));
    }

    public enum RPSOption {
        ROCK, PAPER, SCISSORS
    }

}