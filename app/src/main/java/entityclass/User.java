package entityclass;

import android.widget.ImageView;

import java.util.UUID;

public class User{
    private UUID UserID;
    private String UserTelephone;
    private String Password;
    private String UserName;
    private String UserSignature;
    private ImageView UserHeadPortrait;

    public User(){
        UserID = UUID.randomUUID();
    }

    public void setUserTelephone(String userTelephone) {
        UserTelephone = userTelephone;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setUserSignature(String userSignature) {
        UserSignature = userSignature;
    }

    public void setUserHeadPortrait(ImageView userHeadPortrait) {
        UserHeadPortrait = userHeadPortrait;
    }

    public UUID getUserID() {
        return UserID;
    }

    public String getUserTelephone() {
        return UserTelephone;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserSignature() {
        return UserSignature;
    }

    public ImageView getUserHeadPortrait() {
        return UserHeadPortrait;
    }
}