package uol.test.step.persistence;

import uol.affiliated.commons.test.DBUtil;
import uol.test.step.persistence.model.PersistenceResponse;
import uol.test.step.persistence.util.URLBuilder;


public abstract class AbstractPersistenceSteps {
    
    protected static DBUtil dbUtil = new DBUtil();
    protected static URLBuilder builder = new URLBuilder();
    protected static Integer httpStatus ;
    protected static PersistenceResponse response;
}