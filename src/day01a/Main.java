package day01a;

import commons.InputReader;

public class Main {

    private static int sum;

    private static int decode(String line) {
        int result = Integer.parseInt(line.substring(1));
        if (line.charAt(0) == '-') {
            return -result;
        } else {
            return result;
        }
    }

    public static void main(String[] args) {
        InputReader.init();
        while (InputReader.hasNext()) {
            sum += decode(InputReader.nextLine());
        }
        InputReader.close();

        System.out.println(sum);
    }
}
