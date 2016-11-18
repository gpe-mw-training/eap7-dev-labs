package eapcourse.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import eapcourse.domain.SimpleProperty;

import java.util.List;

public class RepositoryManager {

    @Inject private EntityManager em;

    public List<SimpleProperty> queryCache() {
        Query query = em.createQuery("FROM eapcourse.domain.SimpleProperty");

        @SuppressWarnings("unchecked")
        List <SimpleProperty> list = query.getResultList();
        return list;
    }
}
