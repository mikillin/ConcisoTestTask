package de.conciso.dao.impl;

import de.conciso.dao.IHistoryDAO;
import de.conciso.entity.History;
import de.conciso.util.HibernateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Repository
public class HistoryDAOImpl implements IHistoryDAO {

    //delete
    public HistoryDAOImpl() {

    }

    @Autowired
    private HibernateUtil hibernateUtil;


    public long createHistory(History history) {
        return (Long) hibernateUtil.create(history);
    }

    public void deleteHistory(long id) {
        History history = new History();
        history.setId(id);
        hibernateUtil.delete(history);
    }

    public History getHistory(long id) {
        return hibernateUtil.fetchById(id, History.class);
    }

    @SuppressWarnings("unchecked")
    public List<History> getAllHistories() {
        return hibernateUtil.fetchAll(History.class);
    }
}