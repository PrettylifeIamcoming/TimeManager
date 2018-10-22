package entityclass;

import java.util.UUID;

public class Introspection{
    private UUID IntrospectionID;
    private String IntrospectionMyself;
    private int IntrospectionType;
    private long Timestamp;             //后期获取时间戳当完成界面逻辑时具体跟进

    public Introspection(){
        IntrospectionID = UUID.randomUUID();
    }

    public void setIntrospectionMyself(String introspectionMyself) {
        IntrospectionMyself = introspectionMyself;
    }

    public UUID getIntrospectionID() {
        return IntrospectionID;
    }

    public String getIntrospectionMyself() {
        return IntrospectionMyself;
    }

    public int getIntrospectionType() {
        return IntrospectionType;
    }

    public long getTimestamp() {
        return Timestamp;
    }
}