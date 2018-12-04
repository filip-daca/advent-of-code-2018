package day01b;

import commons.InputReader;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {

    private static int sum;
    private static Set<Integer> sums = new HashSet<>();
    private static List<String> lines = new LinkedList<>();

    private static int decode(String line) {
        int result = Integer.parseInt(line.substring(1));
        if (line.charAt(0) == '-') {
            return -result;
        } else {
            return result;
        }
    }

    private static Integer findFirstDuplicateSum() {
        while (true) {
            for (String l : lines) {
                if (sums.contains(sum)) {
                    return sum;
                }
                sums.add(sum);
                sum += decode(l);
            }
        }
    }

    private static void readInput() {
        InputReader.init();
        while (InputReader.hasNext()) {
            lines.add(InputReader.nextLine());
        }
        InputReader.close();
    }

    public static void main(String[] args) {
        readInput();
        System.out.println(findFirstDuplicateSum());
    }
}
