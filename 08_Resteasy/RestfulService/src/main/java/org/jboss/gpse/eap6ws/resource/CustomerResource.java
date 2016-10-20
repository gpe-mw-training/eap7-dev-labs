package org.jboss.gpse.eap6ws.resource;



import java.net.URI;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.jboss.gpse.eap6ws.domain.Customer;

/**
 * JAX-RS Resource service for Customer objects
 * @author GPSE
 */
@Singleton
@Path("/customers")
public class CustomerResource {
	
	@Inject
	private CustomerDAO custDAO;
	
	@Resource(name="max-set-size")
	private int setSize;
	
	public CustomerResource() {
	}
	
	@PostConstruct
	public void init() {
		Customer c = new Customer();
		c.setFirstName("first");
		c.setLastName("last");
		c.setCity("city");
		c.setCountry("country");
		c.setEmail("email");
		c.setMobileNumber("mobile");
		c.setState("ss");
		c.setStreet("street");
		c.setZip("00000");
		c.setId(100);
		custDAO.getCustomerDB().put(100, c);
		System.out.println(" ------ Initialized customer resource  ------ ");
		System.out.println(" setSize =  " + setSize);
	}
	
	/*
	 * Receives customer objects in XML format.  RESTEasy uses JAXB to convert from XML
	 * back to a customer for the parameter to this method.
	 */
	@POST
	@Consumes("application/xml")
	public Response createCustomer(Customer customer) {
		customer.setId(custDAO.getIdCounter().incrementAndGet());
		custDAO.getCustomerDB().put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("/customers/" + customer.getId())).build();
	}
	
	/*
	 * retrieves the customer object associated with the id parameter.
	 * JAXB is used by RESTEasy to convert the object to XML prior to 
	 * sending back to the client.
	 */
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Customer getCustomer(@PathParam("id") int id) {
		Customer customer = custDAO.getCustomerDB().get(id);
		if(customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return customer;
	}
	
	/* The client must know the id of the resource to use the PUT operation.
	 * The operation is idempotent since sending the message more than once has no affect on the service.
	 * The XML customer object in the body of the message received is used to update the database.
	 * JAXB is used by RESTEasy to convert the XML to a Customer Object as input to this method.
	 */

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public Response updateCustomer(@PathParam("id") int id, Customer customer) {
		Customer current = custDAO.getCustomerDB().get(id);
		if (current == null){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		current.copyValues(customer);
		return Response.noContent().build();
	}
}
