package de.conciso.dao;

import java.util.List;

import de.conciso.entity.History;

public interface IHistoryDAO {
    public long createHistory(History history);

    public void deleteHistory(long id);

    public List<History> getAllHistories();

    public History getHistory(long id);

}
