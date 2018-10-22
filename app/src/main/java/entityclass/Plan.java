package entityclass;

import java.util.UUID;

public class Plan{
    private UUID PlanID;
    private String PlanContent;
    private int PlanType;
    private int PlanProcess;
    private long Timestamp;          //后期获取时间戳当完成界面逻辑时具体跟进

    public Plan(){
        PlanID = UUID.randomUUID();
    }

    public void setPlanContent(String planContent) {
        PlanContent = planContent;
    }

    public void setPlanType(int planType) {
        PlanType = planType;
    }

    public void setPlanProcess(int planProcess) {
        PlanProcess = planProcess;
    }

    public UUID getPlanID() {

        return PlanID;
    }

    public String getPlanContent() {
        return PlanContent;
    }

    public int getPlanType() {
        return PlanType;
    }

    public int getPlanProcess() {
        return PlanProcess;
    }

    public long getTimestamp() {
        return Timestamp;
    }
}