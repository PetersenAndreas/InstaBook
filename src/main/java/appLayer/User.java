package appLayer;

public class User {

    public boolean isValidUser(String sUser, String sUserPassword) {
        if(sUser.equals("user@user.com") && sUserPassword.equals("Password123")) {
            return true;
        } else {
            return false;
        }
    }

}
