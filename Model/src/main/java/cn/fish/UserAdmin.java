package cn.fish;

public class UserAdmin {
    private String userName;
    private String userPWD;
    private String userORG;
    private boolean isAdmin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPWD() {
        return userPWD;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public String getUserORG() {
        return userORG;
    }

    public void setUserORG(String userORG) {
        this.userORG = userORG;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }
}
