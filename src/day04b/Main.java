package day04b;

import commons.InputReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    private static Set<LogEntry> log = new TreeSet<>();
    private static Map<Integer, Integer[]> guardsSleepMinutes = new HashMap<>();

    private static void readInput(String line) {
        String dateTimePart = line.substring(1, 17);  // [1518-08-29 00:24] ...
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimePart, formatter);

        LogKind logKind = null;
        if (line.contains("falls asleep")) {
            logKind = LogKind.FALL_ASLEEP;
        } else if (line.contains("wakes up")) {
            logKind = LogKind.WAKE_UP;
        } else if (line.contains("begins shift")) {
            logKind = LogKind.GUARD_CHANGE;
        }

        Integer guardId = null;
        if (logKind.equals(LogKind.GUARD_CHANGE)) {
            String idPart = line.substring(line.indexOf("#") + 1);
            idPart = idPart.substring(0, idPart.indexOf(' '));
            guardId = Integer.parseInt(idPart);
        }

        log.add(new LogEntry(dateTime, logKind, guardId));
    }

    public static void main(String[] args) {
        InputReader.init();
        while (InputReader.hasNext()) {
            readInput(InputReader.nextLine());
        }
        InputReader.close();

        countGuardSleepMinutes();

        findLaziestGuard();
    }

    private static void findLaziestGuard() {
        int laziestGuard = 0;
        int laziestMinute = 0;
        int laziestMinuteDays = 0;

        for (int i = 0; i < 60; i++) {
            for (Integer guardId : guardsSleepMinutes.keySet()) {
                int minuteVal = guardsSleepMinutes.get(guardId)[i];
                if (minuteVal > laziestMinuteDays) {
                    laziestMinute = i;
                    laziestGuard = guardId;
                    laziestMinuteDays = minuteVal;
                }
            }
        }

        System.out.println("Guard: " + laziestGuard + " minute: " + laziestMinute + " multiply: " + laziestGuard * laziestMinute);
    }

    private static void countGuardSleepMinutes() {
        Integer guardId = null;
        boolean sleeping = false;
        int lastMinute = 0;

        for (LogEntry logEntry : log) {

            // Assuming sleep only after FALL_ASLEEP
            if (sleeping) {
                while (lastMinute < logEntry.getDateTime().getMinute()) {
                    guardsSleepMinutes.get(guardId)[lastMinute]++;
                    lastMinute = lastMinute + 1;
                }
            }

            lastMinute = logEntry.getDateTime().getMinute();

            if (logEntry.getLogKind().equals(LogKind.GUARD_CHANGE)) {
                guardId = logEntry.getGuardId();
                if (!guardsSleepMinutes.containsKey(guardId)) {
                    guardsSleepMinutes.put(guardId, new Integer[60]);
                    for (int i = 0; i < 60; i++) {
                        guardsSleepMinutes.get(guardId)[i] = 0;
                    }
                }
            } else if (logEntry.getLogKind().equals(LogKind.WAKE_UP)) {
                sleeping = false;
            } else if (logEntry.getLogKind().equals(LogKind.FALL_ASLEEP)) {
                sleeping = true;
            }
        }
    }
}
