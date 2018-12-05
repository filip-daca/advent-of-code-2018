package day04b;

import java.time.LocalDateTime;

public class LogEntry implements Comparable<LogEntry> {

    private LocalDateTime dateTime;

    private Integer guardId;

    private LogKind logKind;

    public LogEntry(LocalDateTime dateTime, LogKind logKind, Integer guardId) {
        this.dateTime = dateTime;
        this.logKind = logKind;
        this.guardId = guardId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Integer getGuardId() {
        return guardId;
    }

    public LogKind getLogKind() {
        return logKind;
    }

    @Override
    public int compareTo(LogEntry o) {
        return this.dateTime.compareTo(o.dateTime);
    }
}
