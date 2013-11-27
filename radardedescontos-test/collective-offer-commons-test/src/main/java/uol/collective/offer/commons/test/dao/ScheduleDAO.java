package uol.collective.offer.commons.test.dao;

import java.util.List;

import uol.collective.offer.commons.test.model.impl.SaleTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;



public interface ScheduleDAO extends GenericDAO<ScheduleTestResponse>
{

    public void deleteAllCascade();

    public List<ScheduleTestResponse> getSchedulesBySale(String status, SaleTestResponse sale);
    
    public List<ScheduleTestResponse> getSchedulesByAlias(String status, String slotAlias);
    
    public List<ScheduleTestResponse> getSchedulesByDateRange(String status, String startDate, String endDate);
}
