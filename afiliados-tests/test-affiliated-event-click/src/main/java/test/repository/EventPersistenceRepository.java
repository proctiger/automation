package test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventPersistenceRepository {

    private @Autowired JdbcTemplate jdbcTemplate;

    private final String sql = "select count(*) from AE_EVENT_LOG where DES_NAME IN ('Clique','Indicacao')";

    public Integer countInTableAeEventLog() throws Exception {
        try {
            return jdbcTemplate.queryForInt(sql);
        } catch (DataAccessException ex) {
            throw new Exception("Falha ao buscar dados no banco de dados", ex);
        }
    }
}
