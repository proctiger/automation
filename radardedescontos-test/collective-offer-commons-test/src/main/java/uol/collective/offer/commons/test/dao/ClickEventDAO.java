package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.ClickEventTestModel;


public interface ClickEventDAO extends GenericDAO<ClickEventTestModel>
{

    public ClickEventTestModel getLastClick();

    void deleteAllCascade();

    ClickEventTestModel insertDefaultClickEvent();

}
