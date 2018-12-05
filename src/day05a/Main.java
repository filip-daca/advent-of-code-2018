package day05a;

import commons.InputReader;

public class Main {

    private static String compact(String line) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < line.length() - 1) {
            String a = String.valueOf(line.charAt(i));
            String b = String.valueOf(line.charAt(i + 1));

            if (!a.equals(b) && (a.toUpperCase().equals(b) || a.equals(b.toUpperCase()))) {
                i++;
            } else {
                sb.append(a);
            }
            i++;
        }
        if (i < line.length()) {
            sb.append(line.charAt(i));
        }
        return sb.toString();
    }

    private static String tryCompressing(String line) {
        String result = compact(line);
        if (result.length() < line.length()) {
            return tryCompressing(result);
        } else {
            return line;
        }
    }

    public static void main(String[] args) {
        InputReader.init();
        while (InputReader.hasNext()) {
            String compactionResult = tryCompressing(InputReader.nextLine());
            System.out.println(compactionResult);
            System.out.println(compactionResult.length());
        }
        InputReader.close();
    }
}
