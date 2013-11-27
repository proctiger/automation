package uol.collective.offer.service.test.optOutHistory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.NewErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.NewErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.OptOutHistoryTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;


public class OptOutHistorySelectTest extends OptOutHistoryBase {

	@Before
	public void initialize() {
		super.initialize();
		insertNextOptOutEntryToday();
		insertNextOptOutEntryToday();
		insertNextOptOutEntryToday();
	}

	@Test
	public void successfullySelect() {
		OptOutHistoryTestResponse optOut = getAnyOptOutEntry();

		if (optOut == null) {
			Assert.fail("Erro ao testar um select com sucesso, nao foi possivel retornar um exemplo.");
		} else {
			Assert.assertTrue(executeSelectAndValidate(
					new OptOutHistoryTestResponse(optOut.getId()), optOut));
		}
	}

	@Test
	public void selectInformingNonexistingId() {
		NewErrorsTestResponse expectedErrors = new NewErrorsTestResponse();
		expectedErrors.addError((new NewErrorTestResponse(
				"Campanha com identificador 999999 não encontrado(a)")));

		Assert.assertTrue(executeSelectAndValidate(
				new OptOutHistoryTestResponse("999999"), expectedErrors));
	}

	@Test
	public void selectInformingInvalidId() {
		ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
		expectedErrors.addError(new ErrorTestResponse("1100",
				"Uma requisição de cliente inválida foi efetuada",
				defineFields("/optOutHistory/teste")));

		Assert.assertTrue(executeSelectAndValidate(
				new OptOutHistoryTestResponse("teste"), expectedErrors));
	}

	@Test
	public void selectAll() {
		ListTestResponse<OptOutHistoryTestResponse> listFromDataBase = new ListTestResponseImpl<OptOutHistoryTestResponse>(
				optOutHistoryDAO.findAll(), OptOutHistoryTestResponse.class);

		Assert.assertTrue(executeListAllAndValidate(listFromDataBase));
	}

	@Test
	public void selectWithoutDateStart() {
		ErrorsTestResponse expectedErrors = new ErrorsTestResponse();

		expectedErrors.addError(new ErrorTestResponse("666",
				"Período de data não é válido",
				defineFields("startDate/endDate")));

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("dateEnd", DateTimeUtil.formatDateToStringXML(DateUtils
				.truncate(new Date(), Calendar.DAY_OF_MONTH)));

		Assert.assertTrue(executeListAllAndValidate(expectedErrors,
				new OptOutHistoryTestResponse(), null, parameters));
	}

	@Test
	public void selectSchedulesStartDateInvalid() {
		ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
		expectedErrors.addError(new ErrorTestResponse("0004",
				"Formato de data inválido", defineFields("startDate")));

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("dateStart", "X");
		parameters.put("dateEnd", DateTimeUtil.formatDateToStringXML(DateUtils
				.truncate(DateUtils.addDays(new Date(), 4),
						Calendar.DAY_OF_MONTH)));

		Assert.assertTrue(executeListAllAndValidate(expectedErrors,
				new OptOutHistoryTestResponse(), null, parameters));
	}

	@Test
	public void selectWithoutDateEnd() {
		ErrorsTestResponse expectedErrors = new ErrorsTestResponse();

		expectedErrors.addError(new ErrorTestResponse("666",
				"Período de data não é válido",
				defineFields("startDate/endDate")));

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("dateStart", DateTimeUtil
				.formatDateToStringXML(DateUtils.truncate(new Date(),
						Calendar.DAY_OF_MONTH)));

		Assert.assertTrue(executeListAllAndValidate(expectedErrors,
				new OptOutHistoryTestResponse(), null, parameters));
	}

	@Test
	public void selectSchedulesEndDateInvalid() {
		ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
		expectedErrors.addError(new ErrorTestResponse("0004",
				"Formato de data inválido", defineFields("endDate")));

		Map<String, String> parameters = new HashMap<String, String>();
		parameters
				.put("dateStart", DateTimeUtil.formatDateToStringXML(DateUtils
						.truncate(DateUtils.addDays(new Date(), 4),
								Calendar.DAY_OF_MONTH)));
		parameters.put("dateEnd", "X");

		Assert.assertTrue(executeListAllAndValidate(expectedErrors,
				new OptOutHistoryTestResponse(), null, parameters));
	}

	@Test
	public void selectSchedulesStartDateAfterEndDate() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters
				.put("dateStart", DateTimeUtil.formatDateToStringXML(DateUtils
						.truncate(DateUtils.addDays(new Date(), 7),
								Calendar.DAY_OF_MONTH)));
		parameters.put("dateEnd", DateTimeUtil.formatDateToStringXML(DateUtils
				.truncate(DateUtils.addDays(new Date(), 4),
						Calendar.DAY_OF_MONTH)));

		Assert.assertTrue(executeListAllAndValidate(
				new ListTestResponseImpl<OptOutHistoryTestResponse>(
						OptOutHistoryTestResponse.class), parameters));
	}

	@Test
	public void selectOptOutEntriesFromOneDay() {
		List<OptOutHistoryTestResponse> optOuts = listOptOutEntries(
				DateTimeUtil.formatDateToStringHibernate(DateUtils.truncate(
						new Date(), Calendar.DAY_OF_MONTH)),
				DateTimeUtil.formatDateToStringHibernate(DateUtils.truncate(
						new Date(), Calendar.DAY_OF_MONTH)));
		ListTestResponse<OptOutHistoryTestResponse> listFromDataBase = new ListTestResponseImpl<OptOutHistoryTestResponse>(
				optOuts, OptOutHistoryTestResponse.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("dateStart", DateTimeUtil.formatDateToStringXML(DateUtils
				.truncate(new Date(), Calendar.DAY_OF_MONTH)));
		map.put("dateEnd", DateTimeUtil.formatDateToStringXML(DateUtils
				.truncate(new Date(), Calendar.DAY_OF_MONTH)));
		Assert.assertTrue(executeListAllAndValidate(listFromDataBase, map));
	}

	@Test
	public void selectOptOutEntriesDateRange() {
		insertNextOptOutEntry(DateTimeUtil
				.formatDateToStringHibernate(DateUtils.addDays(
						DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH),
						3)));

		List<OptOutHistoryTestResponse> optOuts = listOptOutEntries(
				DateTimeUtil.formatDateToStringHibernate(DateUtils.truncate(
						new Date(), Calendar.DAY_OF_MONTH)),
				DateTimeUtil.formatDateToStringHibernate(DateUtils.truncate(
						new Date(), Calendar.DAY_OF_MONTH)));
		ListTestResponse<OptOutHistoryTestResponse> listFromDataBase = new ListTestResponseImpl<OptOutHistoryTestResponse>(
				optOuts, OptOutHistoryTestResponse.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("dateStart", DateTimeUtil.formatDateToStringXML(DateUtils
				.truncate(new Date(), Calendar.DAY_OF_MONTH)));
		map.put("dateEnd", DateTimeUtil.formatDateToStringXML(DateUtils
				.truncate(new Date(), Calendar.DAY_OF_MONTH)));
		Assert.assertTrue(executeListAllAndValidate(listFromDataBase, map));
	}
}