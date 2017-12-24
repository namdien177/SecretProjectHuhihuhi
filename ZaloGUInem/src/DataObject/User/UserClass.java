package DataObject.User;

public class UserClass {

    private long userId;
    private  String displayName;
    private String avatar;
    private int userGender;
    private String birthDate;
    private String UserPhone;

    public UserClass() {
    }

    public UserClass(long userId, String displayName, String avatar, int userGender, String birthDate, String userPhone) {
        this.userId = userId;
        this.displayName = displayName;
        this.avatar = avatar;
        this.userGender = userGender;
        this.birthDate = birthDate;
        UserPhone = userPhone;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserGender() {
        if (userGender == 1)
            return "Male";
        else
            return "Female";
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
