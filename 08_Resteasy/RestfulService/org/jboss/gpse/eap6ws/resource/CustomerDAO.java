package org.jboss.gpse.eap6ws.resource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Singleton;

import org.jboss.gpse.eap6ws.domain.Customer;

@Singleton
public class CustomerDAO {
	private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
	private AtomicInteger idCounter = new AtomicInteger();
	
	public Map<Integer, Customer> getCustomerDB() {
		return customerDB;
	}
	public void setCustomerDB(Map<Integer, Customer> customerDB) {
		this.customerDB = customerDB;
	}
	public AtomicInteger getIdCounter() {
		return idCounter;
	}
	public void setIdCounter(AtomicInteger idCounter) {
		this.idCounter = idCounter;
	}
}
