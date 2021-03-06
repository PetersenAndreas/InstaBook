package appLayer;

import encryption.CryptoMngr;
import org.mindrot.jbcrypt.BCrypt;


public class User {

    public CryptoMngr encryptor = new CryptoMngr();

    private int userid;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String age;
    private String role;

    public User(String username, String password, String email, String gender, String age, String role) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.email = encryptor.encrypt(email);
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public User(int userid, String username, String password, String email, String gender, String age, String role) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.email = encryptor.encrypt(email);
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public User(String username, String password, String email, String gender, String age) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.email = encryptor.encrypt(email);
        this.gender = gender;
        this.age = age;
    }

    public boolean verifyPassword(String typedPw, String sqlPW) {
        return (BCrypt.checkpw(typedPw, sqlPW));
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}