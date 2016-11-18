package eapcourse.producer;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericProducer {
    @Produces
    @PersistenceContext
    private EntityManager em;
}
