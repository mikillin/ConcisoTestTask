package de.conciso.service.impl;

import de.conciso.dao.IHistoryDAO;
import de.conciso.entity.History;

import de.conciso.service.IHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private IHistoryDAO historyDAO;


    public long createHistory(History history) {
        return historyDAO.createHistory(history);
    }


    public void deleteHistory(long id) {
        historyDAO.deleteHistory(id);
    }

    public void deleteAllHistories() {
        historyDAO.deleteAllHistories();
    }

    public void deleteEarliestHistory() {
        historyDAO.deleteEarliestHistory();
    }

    public List<History> getAllHistories() {
        return historyDAO.getAllHistories();
    }


    public History getHistory(long id) {
        return historyDAO.getHistory(id);
    }


}
