package uol.collective.offer.commons.test.dao;

import java.util.List;

import uol.collective.offer.commons.test.model.impl.OptOutHistoryTestResponse;


public interface OptOutHistoryDAO extends GenericDAO<OptOutHistoryTestResponse>
{

    List<OptOutHistoryTestResponse> getOptOutEntriesByDateRange(String startDate, String endDate);

} 
