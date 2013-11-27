package test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.repository.EventPersistenceRepository;

@Service
public class EventPersistenceService {

    private static final Logger LOG = LoggerFactory.getLogger(EventPersistenceService.class);

    private @Autowired EventPersistenceRepository eventPersistenceRepository;

    private int quantityBeforeRequest = 0;

    public void recordsQuantityBeforeRequest() throws Exception {
        quantityBeforeRequest = eventPersistenceRepository.countInTableAeEventLog();
        LOG.info(String.format("Quantidade de registros antes da requisicao: %s", quantityBeforeRequest));
    }

    public void checkDifferenceOfRecordsAfterRequest() throws Exception {
        int quantityAfterRequest = eventPersistenceRepository.countInTableAeEventLog();
        LOG.info(String.format("Quantidade de registros apos a requisicao: %s", quantityAfterRequest));
        int diff = quantityAfterRequest - quantityBeforeRequest;
        if (diff != 1) {
            throw new Exception(String.format("Diferenca de registros apos requisicao: [%s].", diff));
        }
    }
}
