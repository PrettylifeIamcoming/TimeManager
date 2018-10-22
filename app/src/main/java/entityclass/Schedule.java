package entityclass;

import java.util.UUID;

public class Schedule{
    private UUID ScheduleID;
    private String ScheduleName;
    private long BeginTimestamp;
    private long TerminalTimestamp;
    private int ScheduleType;

    public Schedule(){
        ScheduleID = UUID.randomUUID();
    }

    public void setScheduleName(String scheduleName) {
        ScheduleName = scheduleName;
    }

    public void setBeginTimestamp(long beginTimestamp) {
        BeginTimestamp = beginTimestamp;
    }

    public void setTerminalTimestamp(long terminalTimestamp) {
        TerminalTimestamp = terminalTimestamp;
    }

    public void setScheduleType(int scheduleType) {
        ScheduleType = scheduleType;
    }

    public UUID getScheduleID() {
        return ScheduleID;
    }

    public String getScheduleName() {
        return ScheduleName;
    }

    public long getBeginTimestamp() {
        return BeginTimestamp;
    }

    public long getTerminalTimestamp() {
        return TerminalTimestamp;
    }

    public int getScheduleType() {
        return ScheduleType;
    }
}