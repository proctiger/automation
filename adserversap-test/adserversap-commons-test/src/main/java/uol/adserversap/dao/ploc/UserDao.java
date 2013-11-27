package uol.adserversap.dao.ploc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;
import uol.adserversap.entity.User;

/**
 *
 * @author dvrocha
 */
public class UserDao {

    public static int selectCountFromUserLoginWhereNamLogin(String namLogin) throws Exception {
        int result = 0;
        String query = String.format(""
                + " SELECT  count(nam_login)"
                + " FROM    ADSERVER_SAP_ADM.user_login"
                + " WHERE   nam_login = '%s'",
                namLogin.toLowerCase());
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getEtlInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (!rs.next() || rs == null) {
                throw new Exception(String.format("Query nao retornou nenhum registro [%s]", query));
            }
            result = rs.getInt(1);
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static User selectAllFromUserLoginWhereNamLogin(String namLogin) throws Exception {
        User result = new User();
        String query = String.format(""
                + " SELECT  *"
                + " FROM    ADSERVER_SAP_ADM.user_login"
                + " WHERE   nam_login = '%s'",
                namLogin.toLowerCase());
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getEtlInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (!rs.next() || rs == null) {
                throw new Exception(String.format("Query nao retornou nenhum registro [%s]", query));
            }
            result.setLogin(rs.getString("nam_login"));
            result.setStatus(rs.getInt("flg_active"));
            result.setProfile(rs.getString("ind_permission").charAt(0));
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static void insertUserLogin(User user) throws Exception {
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getEtlInstance().
                    updateStatement(String.format(""
                    + " INSERT INTO ADSERVER_SAP_ADM.user_login"
                    + " (nam_login, ind_permission, flg_active)"
                    + " VALUES"
                    + " ('%s', '%s', %d)",
                    user.getLogin().toLowerCase(), user.getProfile(), user.getStatus()));
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
    }

    public static void updateUserLogin(User user) throws Exception {
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getEtlInstance().
                    updateStatement(String.format(""
                    + " UPDATE  ADSERVER_SAP_ADM.user_login"
                    + " SET     ind_permission = '%s', flg_active = %d"
                    + " WHERE   nam_login = '%s'",
                    user.getProfile(), user.getStatus(), user.getLogin().toLowerCase()));
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
    }

    public static void deleteUserLogin(String userLogin) throws Exception {
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getEtlInstance().
                    updateStatement(String.format(""
                    + " DELETE FROM ADSERVER_SAP_ADM.user_login"
                    + " WHERE       nam_login = '%s'",
                    userLogin.toLowerCase()));
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
    }

    public static User persistUser(User user) throws Exception {
        if (selectCountFromUserLoginWhereNamLogin(user.getLogin()) < 1) {
            insertUserLogin(user);
        } else {
            updateUserLogin(user);
        }
        return user;
    }

    public static List<User> selectAllFromUserLoginWhereFlgActiveTrueOrderByNamLogin() throws Exception {
        List<User> result = new ArrayList<>();
        String query = ""
                + " SELECT      *"
                + " FROM        ADSERVER_SAP_ADM.user_login"
                + " WHERE       flg_active = 1"
                + " ORDER BY    nam_login";
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getEtlInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query nao retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result.add(new User(rs.getString("nam_login"), rs.getString("ind_permission").charAt(0), rs.getInt("flg_active")));
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static void deleteAllFromUserLogin() throws Exception {
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getEtlInstance().updateStatement("DELETE FROM ADSERVER_SAP_ADM.user_login");
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
    }
}
