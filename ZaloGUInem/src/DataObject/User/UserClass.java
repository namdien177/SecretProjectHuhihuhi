package DataObject.User;

public class UserClass {
    private long userId;
    private  String displayName;
    private String UserPhone;
    private int userGender;

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public UserClass() {
    }

    public UserClass(long userID, String userName, String userPhone) {
        userId = userID;
        displayName = userName;
        UserPhone = userPhone;
    }

    public long getUserID() {
        return userId;
    }

    public void setUserID(long userID) {
        userId = userID;
    }

    public String getUserName() {
        return displayName;
    }

    public void setUserName(String userName) {
        displayName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }
}
