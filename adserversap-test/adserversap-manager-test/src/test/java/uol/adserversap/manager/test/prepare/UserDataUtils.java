package uol.adserversap.manager.test.prepare;

import java.util.List;
import java.util.Random;

import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.entity.User;
import uol.adserversap.manager.test.domain.UserDomain;
import uol.adserversap.utils.RandomString;

/**
 *
 * @author dvrocha
 */
public class UserDataUtils {

    public static String getRandomLogin() {
        String[] specificChars = new String[]{"-", "_"};
        return RandomString.getRandomText(10, RandomString.lower, RandomString.number, specificChars);
    }

    public static int getRandomStatus() {
        int[] array = new int[]{0, 1};
        Random random = new Random();
        return random.nextInt(array.length);
    }
    
    public static char getRandomProfile() {
        char[] array = new char[]{'R', 'A', 'U'};
        Random random = new Random();
        return array[random.nextInt(array.length)];
    }
    
    public static String getRandomProfileDomain() {
        String[] array = new String[]{"root", "admin", "user"};
        Random random = new Random();
        return array[random.nextInt(array.length)];
    }
    
    public static void insertRandomLogins() throws Exception {
        int num = 1 + ((int) Math.random() * 15);
        for (int i = 0; i < num; i++) {
            UserDao.persistUser(new User(getRandomLogin(), getRandomProfile(), getRandomStatus()));
        }
    }

    public static UserDomain[] getActiveUsers() throws Exception {
        List<User> dbUserList = UserDao.selectAllFromUserLoginWhereFlgActiveTrueOrderByNamLogin();
        UserDomain[] userDomainList = new UserDomain[dbUserList.size()];
        for (int i = 0; i < dbUserList.size(); i++) {
            userDomainList[i] = new UserDomain(dbUserList.get(i));
        }
        return userDomainList;
    }
}
