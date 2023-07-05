package model;

public class Account {
    private int type;
    private int userName;
    private int userPassword;


//    public enum  ACCOUNT_TYPE{
//        MANAGER, STAFF
//    }

//    use ACCOUNT_TYPE FOR DELCARING STAFF TYPE


    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }

    public int getUserName() {
        return userName;
    }

    public void setUserPassword(int userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserPassword() {
        return userPassword;
    }
}
