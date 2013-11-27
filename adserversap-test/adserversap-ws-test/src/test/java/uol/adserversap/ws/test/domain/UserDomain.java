package uol.adserversap.ws.test.domain;

import uol.adserversap.entity.User;

/**
 *
 * @author dvrocha
 */
public class UserDomain {

    private String login;
    private String status;
    private String profile;
    public static final char DB_PROFILE_ROOT = 'R';
    public static final char DB_PROFILE_ADMIN = 'A';
    public static final char DB_PROFILE_USER = 'U';
    public static final String DOMAIN_PROFILE_ROOT = "root";
    public static final String DOMAIN_PROFILE_ADMIN = "admin";
    public static final String DOMAIN_PROFILE_USER = "user";

    public UserDomain() {
    }

    public UserDomain(User user) throws Exception {
        this.login = user.getLogin();
        this.status = String.valueOf(user.getStatus());
        this.profile = convertProfile(user.getProfile());
    }

    public UserDomain(String login, String profile) {
        this.login = login;
        this.profile = profile;
    }

    public UserDomain(String login, String status, String profile) {
        this.login = login;
        this.status = status;
        this.profile = profile;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public char getDbProfile() throws Exception {
        return convertProfile(this.profile);
    }

    public char convertProfile(String profile) throws Exception {
        if (profile.equals(DOMAIN_PROFILE_ROOT)) {
            return DB_PROFILE_ROOT;
        }
        if (profile.equals(DOMAIN_PROFILE_ADMIN)) {
            return DB_PROFILE_ADMIN;
        }
        if (profile.equals(DOMAIN_PROFILE_USER)) {
            return DB_PROFILE_USER;
        }
        throw new Exception("Perfil inválido: " + profile);
    }

    public String convertProfile(char profile) throws Exception {
        if (profile == DB_PROFILE_ROOT) {
            return DOMAIN_PROFILE_ROOT;
        }
        if (profile == DB_PROFILE_ADMIN) {
            return DOMAIN_PROFILE_ADMIN;
        }
        if (profile == DB_PROFILE_USER) {
            return DOMAIN_PROFILE_USER;
        }
        throw new Exception("Perfil inválido: " + profile);
    }
}
