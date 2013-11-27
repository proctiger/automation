package uol.adserversap.entity;

/**
 *
 * @author dvrocha
 */
public class User {

    private String login;
    private int status;
    private char profile;

    public User() {
    }

    public User(String login, char profile, int status) {
        this.login = login;
        this.profile = profile;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char getProfile() {
        return profile;
    }

    public void setProfile(char profile) {
        this.profile = profile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
