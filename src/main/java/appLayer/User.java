package appLayer;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private String username;
    private String password;
    private String email;
    private String gender;
    private String age;
    private String role;

    public User(String username, String password, String email, String gender, String age, String role) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public User(String username, String password, String email, String gender, String age) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public boolean verifyPassword(String typedPw, String sqlPW) {
        return (BCrypt.checkpw(typedPw, sqlPW));
    }

    public boolean passwordValidity(String password) {
        if (password.length() < 8 || password.length() > 128) {
            return false;
        } else {
            for (int p = 0; p < password.length(); p++) {
                if (Character.isUpperCase(password.charAt(p))) {
                }
            }
            for (int q = 0; q < password.length(); q++) {
                if (Character.isLowerCase(password.charAt(q))) {
                }
            }
            for (int r = 0; r < password.length(); r++) {
                if (Character.isDigit(password.charAt(r))) {
                }
            }
            /*for (int s = 0; s < password.length(); s++) {
                String input = "";
                if (input.matches("[^a-zA-Z0-9 ]")) {
                //if (Character.isSpecialCharacter(password.charAt(s))) {
                }
            }*/
            return true;
        }
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}