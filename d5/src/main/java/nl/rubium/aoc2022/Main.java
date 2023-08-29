package nl.rubium.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("stack-input");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        var stack = new HashMap<Integer, ArrayList<String>>();

        for(String line; (line = reader.readLine()) != null; ){

            for(int i = 1; i < line.length(); i += 4){
                if (line.charAt(i) != ' '){
                    stack.computeIfAbsent((i - 1) / 4, k -> new ArrayList<>()).add(String.valueOf(line.charAt(i)));
                }
            }
        }

        InputStream movementInputStream = classloader.getResourceAsStream("movement-input");
        InputStreamReader movementStreamReader = new InputStreamReader(movementInputStream, StandardCharsets.UTF_8);
        BufferedReader movementReader = new BufferedReader(movementStreamReader);

        var movements = new ArrayList<Movement>();

        Pattern p = Pattern.compile("\\d+");

        for(String line; (line = movementReader.readLine()) != null; ){

            Matcher m = p.matcher(line);
            var foundMatches = new ArrayList<Integer>();

            while(m.find()){
                foundMatches.add(Integer.parseInt(m.group()));
            }

            movements.add(new Movement(foundMatches.get(0), foundMatches.get(1), foundMatches.get(2)));
        }

//        for(Movement movement: movements){
//            var from = stack.get(movement.getFrom() - 1);
//            var to = stack.get(movement.getTo() - 1);
//
//            for(int i = 0; i < movement.getCount(); i++){
//                to.add(0, from.get(0));
//                from.remove(0);
//            }
//        }
//
//        var resultOne = stack.values().stream().map(list -> list.get(0)).collect(Collectors.joining(""));
//        System.out.println("Result one is " + resultOne);
        for(Movement movement: movements){
            var from = stack.get(movement.getFrom() - 1);
            var to = stack.get(movement.getTo() - 1);

            for(int i = 0; i < movement.getCount(); i++){
                to.add(i, from.get(0));
                from.remove(0);
            }
        }

        var resultOne = stack.values().stream().map(list -> list.get(0)).collect(Collectors.joining(""));
        System.out.println("Result two is " + resultOne);
    }

    private static class Movement{
        private final int count;
        private final int from;
        private final int to;

        public Movement(
            int count, int from, int to
        ){
            this.count = count;
            this.from = from;
            this.to = to;
        }

        public int getCount() {
            return count;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }
    }
}