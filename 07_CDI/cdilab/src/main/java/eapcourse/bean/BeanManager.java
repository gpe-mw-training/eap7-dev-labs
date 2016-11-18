package eapcourse.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import eapcourse.domain.SimpleProperty;
import eapcourse.ejb.ServiceBean;
import eapcourse.producer.Producer;

@RequestScoped
@Named("manager")
public class BeanManager {

    @Inject
    ServiceBean ejb;

    @Produces 
    @Named    
    SimpleProperty property;

    @Inject
    Producer producer;

    @PostConstruct   
    public void initNewProperty() {
        property = new SimpleProperty();
    }

    public void save() {
        System.out.println("Calling ServiceBean.put operation.  Property = " + property);
        ejb.put(property);
        initNewProperty();
    }

    public void clear(SimpleProperty property) {
        System.out.println("Calling ServiceBean.clear operation.  Property = " + property);
        ejb.delete(property);
    }

}
