package commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class InputReader {

    private enum ReadMode {LINE, CHAR}

    ;

    private static ReadMode mode;
    private static Scanner scanner;
    private static Reader reader;
    private static Character character;

    public static void init() {
        initLineByLine();
    }

    public static void initLineByLine() {
        scanner = new Scanner(System.in);
        mode = ReadMode.LINE;
    }

    public static void initCharByChar() {
        reader = new InputStreamReader(System.in);
        reader = new BufferedReader(reader);
        mode = ReadMode.CHAR;
        readChar();
    }

    public static void close() {
        switch (mode) {
            case LINE:
                closeLineByLine();
                break;
            case CHAR:
                closeCharByChar();
                break;
        }
    }

    public static void closeLineByLine() {
        scanner.close();
    }

    public static void closeCharByChar() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasNext() {
        switch (mode) {
            case LINE:
                return hasNextLine();
            case CHAR:
                return hasNextChar();
            default:
                return hasNextLine();
        }
    }

    public static boolean hasNextLine() {
        return scanner.hasNext();
    }

    public static boolean hasNextChar() {
        return character != null;
    }

    public static String nextLine() {
        return scanner.nextLine();
    }

    public static Character nextChar() {
        Character result = character;
        readChar();
        return result;
    }

    private static void readChar() {
        try {
            int inputInteger = reader.read();
            if (inputInteger != -1) {
                character = Character.valueOf((char) inputInteger);
            } else {
                character = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }
}
