import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Calibration {
    public static int part1(String input) {
        List<String> parsedInput = parseInput(input);
        List<Integer> integers = new ArrayList<>();

        for (String line : parsedInput) {
            integers.add(findIntegers(line));
        }

        return integers.stream().mapToInt(Integer::intValue).sum();
    }

    public static int part2(String input) {
        List<String> parsedInput = parseInput(input);
        List<Integer> integers = new ArrayList<>();

        for (String line : parsedInput) {
            integers.add(findRealIntegers(line));
        }

        return integers.stream().mapToInt(Integer::intValue).sum();
    }

    public static int findIntegers(String line) {
        char[] chars = line.toCharArray();
        List<Character> digits = new ArrayList<>();

        for (char c : chars) {
            if (Character.isDigit(c)) {
                digits.add(c);
            }
        }

        if (digits.isEmpty()) {
            // Handle the case where no digits are found in the line
            return 0;
        }

        char first = digits.get(0);
        char last = digits.get(digits.size() - 1);

        return Integer.parseInt(String.valueOf(first) + String.valueOf(last));
    }
    public static int findRealIntegers(String line) {
        char[] chars = line.toCharArray();
        StringBuilder currentNumber = new StringBuilder();
        int firstNumericValue = 0;
        int lastNumericValue = 0;

        for (char c : chars) {
            if (Character.isDigit(c)) {
                if (firstNumericValue == 0) {
                    firstNumericValue = Character.getNumericValue(c);
                }
                lastNumericValue = Character.getNumericValue(c);
            } else if (Character.isAlphabetic(c)) {
                currentNumber.append(c);
                // Check if the current spelled-out representation is a valid number
                int numericValue = getNumericValueFromSpelledOut(currentNumber.toString());
                if (numericValue > 0) {
                    if (firstNumericValue == 0) {
                        firstNumericValue = numericValue;
                    } else if (lastNumericValue == 0) {
                        lastNumericValue = numericValue;
                    }
                    currentNumber = new StringBuilder();
                }
            }
        }

        // If only one number is found, repeat it
        if (lastNumericValue == 0) {
            lastNumericValue = firstNumericValue;
        }

        return firstNumericValue * 10 + lastNumericValue;
    }

/*
* Takes in a string that checks if a digit has been spelled out, and converts it if yes
* */
    private static int getNumericValueFromSpelledOut(String word) {
        switch (word.toLowerCase()) {
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            default:
                return 0;
        }
    }

    public static List<String> parseInput(String input) {
        String[] lines = input.split("\n");
        List<String> result = new ArrayList<>();

        for (String line : lines) {
            result.add(line);
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            String input = readFile("input.txt");
            int result1 = part1(input);
            int result2 = part2(input);

            System.out.println("Part 1 Result: " + result1);
            System.out.println("Part 2 Result: " + result2);
        } catch (IOException e) {
            System.out.println("Error reading the input file: " + e.getMessage());
        }
    }

    private static String readFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
