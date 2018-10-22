package entityclass;

import java.util.UUID;

public class Task{
    private UUID TaskID;
    private String TaskName;
    private long BeginTimestamp;
    private long TerminalTimestamp;
    private int TaskType;
    private int TaskLevel;
    private int TaskProcess;
    private int TaskCustomLevel;

    public Task(){
        TaskID = UUID.randomUUID();
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public void setBeginTimestamp(long beginTimestamp) {
        BeginTimestamp = beginTimestamp;
    }

    public void setTerminalTimestamp(long terminalTimestamp) {
        TerminalTimestamp = terminalTimestamp;
    }

    public void setTaskType(int taskType) {
        TaskType = taskType;
    }

    public void setTaskLevel(int taskLevel) {
        TaskLevel = taskLevel;
    }

    public void setTaskProcess(int taskProcess) {
        TaskProcess = taskProcess;
    }

    public void setTaskCustomLevel(int taskCustomLevel) {
        TaskCustomLevel = taskCustomLevel;
    }

    public UUID getTaskID() {
        return TaskID;
    }

    public String getTaskName() {
        return TaskName;
    }

    public long getBeginTimestamp() {
        return BeginTimestamp;
    }

    public long getTerminalTimestamp() {
        return TerminalTimestamp;
    }

    public int getTaskType() {
        return TaskType;
    }

    public int getTaskLevel() {
        return TaskLevel;
    }

    public int getTaskProcess() {
        return TaskProcess;
    }

    public int getTaskCustomLevel() {
        return TaskCustomLevel;
    }
}