package de.conciso.service;

import de.conciso.entity.History;

import java.util.List;


public interface IHistoryService {

    public long createHistory(History history);

    public void deleteHistory(long id);

    public List<History> getAllHistories();

    public History getHistory(long id);

}
