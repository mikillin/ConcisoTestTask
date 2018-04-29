package de.conciso.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class HibernateUtil {

    @Autowired
    private SessionFactory sessionFactory;

    public <T> Serializable create(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    public <T> void delete(Serializable id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM " + entityClass.getName()).list();
    }


    public <T> void deleteAll(Class<T> entityClass) {
        sessionFactory.getCurrentSession().createQuery(" DELETE FROM " + entityClass.getName())
                .executeUpdate();
    }

    public <T> void deleteEarliest(Class<T> entityClass) {
        DetachedCriteria minCreationTime = DetachedCriteria.forClass(entityClass)
                .setProjection(Projections.min("creationTime"));
        List<T> list = sessionFactory.getCurrentSession().createCriteria(entityClass)
                .add(Property.forName("creationTime").eq(minCreationTime)).list();

        list.forEach(item -> {
                    delete(item);
                }
        );
        System.out.println("");

    }

    public <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }


}
